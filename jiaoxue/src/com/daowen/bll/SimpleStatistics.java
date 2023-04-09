package com.daowen.bll;

import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.daowen.dal.DALBase;

public class SimpleStatistics {

	
	public  JFreeChart  buildPieChart(String SQL,String chartdes){
   	 
   	     DefaultPieDataset dpd = new DefaultPieDataset();
         List list=DALBase.runNativeSQL(SQL);
		 for(Object object : list)
		 {
			 Object[]item=(Object[])object;
		     int number=new Integer(item[1].toString());
		     
			 dpd.setValue(item[0].toString(),number);
//	        
		 }
      
        JFreeChart chart
      
            = ChartFactory.createPieChart3D(chartdes, dpd, true, true,true);
        
        
        Font font = new Font("宋体" , Font.PLAIN , 20);
        chart.getTitle().setFont(font);
        chart.getLegend().setItemFont(font);
        PiePlot piePlot = (PiePlot)chart.getPlot();
        piePlot.setLabelFont(font);
        
        return chart;
   	 
   	 
    }
	
	//构建柱状图 
	public  JFreeChart buildColumnChart(String xaxis ,String yaxis,String chartdes,String dsSQL) 
	{
	    	
	    	CategoryDataset dataset=getCloumnDatasource(dsSQL);
	        JFreeChart chart=ChartFactory.createBarChart("hi", xaxis, 
	                yaxis, dataset, PlotOrientation.VERTICAL, true, true, false); 
	        chart.setTitle(new TextTitle(chartdes,new Font("宋体",Font.BOLD+Font.ITALIC,20)));
	        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
	        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
	        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
	        return chart;
	 }
	 
	 private  CategoryDataset getCloumnDatasource(String SQL) //创建柱状图数据集
	    {
	        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	        
	        List list=DALBase.runNativeSQL(SQL);
	        
			 for(Object object : list)
			 {
				 Object[]item=(Object[])object;
				 //第一个 为x轴   第二个位y轴
				 double jine=new Double(item[1].toString());
			    
			     dataset.setValue(jine,item[0].toString(),item[0].toString());
    
			 }

	        return dataset;
	    }
 
	
	
	
}
