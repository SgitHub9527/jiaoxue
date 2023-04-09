package com.daowen.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daowen.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//****控制器基类

public abstract class PageActionBase extends ActionSupport{
	
	private Map<String,String> routeMethods=new HashMap<String,String>(){{
		put("search","binding");
		put("get","binding");
	
	}};
	
    protected HttpServletResponse response;
    
    protected HttpServletRequest  request;
    
	
	@Override
	public String execute(){
		response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		
		request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		
		mappingMethod(request,response);
		return null;
	}
	
	/**
	 * 路由方法
	 * @param request
	 * @param response
	 */
	public void mappingMethod(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
		String actionType = request.getParameter("actiontype");
		String doMethod=request.getParameter("domethod");
		//不存在
		if (actionType == null&&doMethod==null)
			return ;
		String methodName=!StringUtil.isEmpty(doMethod)?doMethod:actionType ;
		if(StringUtil.isEmpty(methodName)){
			System.out.println("调用"+this.getClass().getName()+"方法:"+methodName+"失败");
			System.out.println("方法名="+methodName);
			return ;
		}
		Method invokeMethod=null;
		if(routeMethods.containsKey(methodName)){
			invokeMethod=getMethod(this,routeMethods.get(methodName));
		}else{
			invokeMethod=getMethod(this,methodName);
		}
		if(invokeMethod!=null)
			try {
			    Boolean flag=invokeMethod.isAccessible();
			    invokeMethod.setAccessible(true);
				invokeMethod.invoke(this);
				invokeMethod.setAccessible(flag);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				
				e.printStackTrace();
			}
		
	}
	
	private Method getMethod(Object target,String methodName){
		Method invokeMethod=null;
		try {
			invokeMethod=this.getClass().getDeclaredMethod(methodName);
			
		} catch (Exception e) {
			System.out.print("获取"+this.getClass().getName()+"."+methodName+"失败");
			e.printStackTrace();
		} 
		return invokeMethod;
	}

	
	public  void  dispatchParams(HttpServletRequest request,HttpServletResponse response){
		Enumeration  params=request.getParameterNames();
		while (params.hasMoreElements())
		{
		   String paramname=params.nextElement().toString();
		   String value=request.getParameter(paramname);
		   request.setAttribute(paramname, value);
		}
	}
	
	public  String join(String join,String[] strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.length;i++){
             if(i==(strAry.length-1)){
                 sb.append(strAry[i]);
             }else{
                 sb.append(strAry[i]).append(join);
             }
        }
        
        return new String(sb);
    }
	/**
	 * 也没跳转
	 * @param url
	 */
	public void redirect(String url){
		if (url == null) {
			return;
		}
		try {
			response.sendRedirect(request.getContextPath() + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重定向
	 * @param url
	 */
	public void forward(String url){
		if (url == null) {
			return;
		}
		try {
			request.getRequestDispatcher(url).forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
