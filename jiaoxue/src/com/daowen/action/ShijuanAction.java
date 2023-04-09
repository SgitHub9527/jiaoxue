package com.daowen.action;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.daowen.dal.DALBase;
import com.daowen.entity.Ceshiti;
import com.daowen.entity.Dajuan;
import com.daowen.entity.Dajuanitem;
import com.daowen.entity.Shijuan;
import com.daowen.entity.Shijuanitem;
import com.daowen.util.PagerMetal;

public class ShijuanAction extends PageActionBase {

	private void exam() {

		String sjid = request.getParameter("sjid");
		String kemu = request.getParameter("kemu");
		String title = request.getParameter("title");
		String dajuanren = request.getParameter("dajuanren");
		String stname = request.getParameter("stname");
		String forwardurl = request.getParameter("forwardurl");

		String[] shitiids = request.getParameterValues("tihaolist");

		Dajuan dajuan = new Dajuan();
		dajuan.setKemu(kemu);
		dajuan.setShijuanid(sjid);
		dajuan.setDajuanren(dajuanren);
		dajuan.setTitle(title);
		dajuan.setStatus("待阅卷");
		dajuan.setStname(stname);
		dajuan.setDefen(0);
		DALBase.save(dajuan);

		dajuan.setZujuanren(dajuanren);
		int djtataldefen = 0;
		if (shitiids != null) {
			for (String shitiid : shitiids) {

				Dajuanitem dajuanitem = new Dajuanitem();
				// 获取试题分数
				String fenshu = request.getParameter("fenshu" + shitiid);
				// 答案
				String daan = request.getParameter("daan" + shitiid);

				// 题号
				String tihao = request.getParameter("tihao" + shitiid);

				// 参考答案
				String stdaan = request.getParameter("stdaan" + shitiid);
				// 参考答案
				String tixing = request.getParameter("tixing" + shitiid);

				dajuanitem.setDajuanid(dajuan.getId());
				dajuanitem.setShijuanid(Integer.parseInt(sjid));
				dajuanitem.setDajuanren(dajuanren);
				dajuanitem.setDaan(daan);
				dajuanitem.setTixing(tixing);
				System.out.println();
				if (tihao != null)
					dajuanitem.setTihao(Integer.parseInt(tihao));
				dajuanitem.setShijuantitle(title);
				if (stdaan.toUpperCase().equals(daan)) {
					dajuanitem.setDefen(Integer.parseInt(fenshu));
					djtataldefen += Integer.parseInt(fenshu);
				} else {
					dajuanitem.setDefen(0);
				}
				DALBase.save(dajuanitem);

			}
		}// end if
		dajuan.setDefen(djtataldefen);
		DALBase.update(dajuan);
		redirect("/e/huiyuan/dajuaninfo.jsp?djid=" + dajuan.getId());

	}

	private void yuejuan() {

		String djid = request.getParameter("id");

		String[] djids = request.getParameterValues("djids");

		Dajuan dajuan = (Dajuan) DALBase.load("dajuan", "where id=" + djid);

		int djtataldefen = 0;
		if (djids != null) {
			for (String dji : djids) {

				Dajuanitem dajuanitem = (Dajuanitem) DALBase.load("dajuanitem",
						"where id=" + dji);
				// 获取试题分数
				String defen = request.getParameter("defen" + dji);

				dajuanitem.setDefen(Integer.parseInt(defen));
				// 答卷总分
				djtataldefen += dajuanitem.getDefen();
				// 答案
				DALBase.update(dajuanitem);

			}
		}// end if

		dajuan.setDefen(djtataldefen);
		dajuan.setStatus("阅卷完成");
		DALBase.update(dajuan);

		String forwardurl = request.getParameter("forwardurl");
		redirect(forwardurl);

	}

	/*
	 * 移除试卷上面试题
	 */
	private void removeShiti() {

		String shijuanid = request.getParameter("sjid");
		String tihao = request.getParameter("tihao");
		String forwardurl = request.getParameter("forwardurl");
		Shijuanitem sji = new Shijuanitem();

		String tixing = request.getParameter("tixing");

		if (tixing != null && tixing.equals("1")) {

			tixing = "单选题";
		}

		if (shijuanid != null && tihao != null) {

			DALBase.delete("shijuanitem", MessageFormat.format(
					"where tihao={0} and shijuanid={1} and tixing=''{2}'' ",
					tihao, shijuanid, tixing));

		}

		redirect(forwardurl);

	}

	/**
	 * 组卷 完成
	 * 
	 */
	private void zujuanOver() {

		String sjid = request.getParameter("sjid");
		String forwardurl = request.getParameter("forwardurl");
		Shijuan shijuan = (Shijuan) DALBase.load("shijuan", "where id=" + sjid);
		String[] shitiids = request.getParameterValues("tihaolist");

		if (shijuan != null) {

			if (shitiids != null) {
				for (String shitiid : shitiids) {

					Shijuanitem shijuanitem = (Shijuanitem) DALBase.load(
							"shijuanitem",
							MessageFormat.format("where id={0} ", shitiid));
					// 获取试题分数
					String fenshu = request.getParameter(shitiid);
					if (shijuanitem != null) {
						shijuanitem.setFenshu(Integer.parseInt(fenshu));
						DALBase.update(shijuanitem);

					}

				}
			}
			shijuan.setStatus("组卷完成");
			DALBase.update(shijuan);

		}

		redirect(forwardurl);

	}

	private void zujuanAuto() {

		String sjid = request.getParameter("sjid");
		String forwardurl = request.getParameter("forwardurl");
		String errorurl = request.getParameter("errorurl");
		Shijuan shijuan = (Shijuan) DALBase.load("shijuan", "where id=" + sjid);
		String danxuancount = request.getParameter("danxuancount");

		String danxuanfen = request.getParameter("danxuanfen");

		String kemu = request.getParameter("kemu");

		int totaldanxuancount = DALBase.getRecordCount("ceshiti",
				"where kemu='" + kemu + "'");
		if (totaldanxuancount < Integer.parseInt(danxuancount)) {
			request.setAttribute("errormsg", MessageFormat.format(
					"<label class='error'>题库单选题只有{0}道,请加入试题</label>",
					totaldanxuancount));
			try {
				request.getRequestDispatcher(errorurl).forward(request,
						response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		// 加入测试题
		randomCeshitiToPaper(danxuancount, danxuanfen, sjid);

		shijuan.setStatus("组卷完成");
		DALBase.update(shijuan);

		redirect(forwardurl);

	}

	/**
	 * 把单选题加入试卷
	 * 
	 * @param danxuancount
	 *            单选题数目
	 * @param fenzhi
	 *            分值
	 * @param sjid
	 *            试卷编号
	 */
	private void randomCeshitiToPaper(String danxuancount, String fenzhi,
			String sjid) {

		System.out.println("danxuancount=" + danxuancount);
		System.out.println("fenzhi=" + fenzhi);
		List<Ceshiti> danxuanlist = getRandomCeshiti(Integer
				.parseInt(danxuancount));
		System.out.println("danxuanlit.size=" + danxuanlist.size());
		for (Ceshiti ceshiti : danxuanlist) {

			Shijuanitem sji = new Shijuanitem();
			sji.setFenshu(Integer.parseInt(fenzhi));
			sji.setTihao(ceshiti.getId());
			sji.setShijuanid(Integer.parseInt(sjid));
			sji.setTixing("单选题");
			DALBase.save(sji);
		}

	}

	/**
	 * 随机选择单选题
	 * 
	 * @param count
	 * @return
	 */
	private List<Ceshiti> getRandomCeshiti(int count) {

		List<Ceshiti> chooselist = new ArrayList<Ceshiti>();
		List<Ceshiti> danxuanlist = DALBase.getEntity("ceshiti", "");
		if (danxuanlist.size() <= count)
			return danxuanlist;

		int[] seqarr = randomCommon(1, danxuanlist.size(), count);

		int j = 1;
		for (Ceshiti ceshiti : danxuanlist) {

			if (hasInAarray(j, seqarr))
				chooselist.add(ceshiti);
			j++;

		}
		System.out.println("抽取试题总数:" + chooselist.size());
		return chooselist;

	}

	private boolean hasInAarray(int ele, int[] arr) {

		for (int x : arr) {
			if (x == ele)
				return true;
		}
		return false;

	}

	public static void main(String[] args) {

		ShijuanAction shaction = new ShijuanAction();
		shaction.getRandomCeshiti(15);

	}

	public int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

	/**
	 * 添加试题
	 */
	private void addShiti() {

		String shijuanid = request.getParameter("shijuanid");
		String tihao = request.getParameter("tihao");
		String tixing = request.getParameter("tixing");
		String forwardurl = request.getParameter("forwardurl");
		Shijuanitem sji = new Shijuanitem();

		if (shijuanid != null && tihao != null) {

			sji.setShijuanid(Integer.parseInt(shijuanid));
			sji.setTihao(Integer.parseInt(tihao));

			sji.setFenshu(2);
			sji.setTixing(tixing);
			DALBase.save(sji);

		}

		redirect(forwardurl);

	}

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		// 删除试卷
		DALBase.delete("shijuan", " where id=" + id);
		// 删除试卷内容
		DALBase.delete("shijuanitem", "where shijuanid=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String title = request.getParameter("title");
		String zujuanren = request.getParameter("zujuanren");
		String zongfen = request.getParameter("zongfen");
		String kemu = request.getParameter("kemu");
		String tupian = request.getParameter("tupian");
		String kmid = request.getParameter("kmid");
		String des = request.getParameter("des");
		SimpleDateFormat sdfshijuan = new SimpleDateFormat("yyyy-MM-dd");
		Shijuan shijuan = new Shijuan();
		shijuan.setTitle(title == null ? "" : title);
		shijuan.setZujuanren(zujuanren == null ? "" : zujuanren);
		shijuan.setZongfen(zongfen == null ? 0 : new Integer(zongfen));
		shijuan.setKemu(kemu == null ? "" : kemu);
		shijuan.setKemu(kemu);
		shijuan.setKmid(kmid == null ? 0 : Integer.parseInt(kmid));
		// 试卷入库后进入待组卷状态
		shijuan.setStatus("待组卷");
		shijuan.setTupian(tupian == null ? "" : tupian);
		shijuan.setDes(des == null ? "" : des);
		DALBase.save(shijuan);

		if (forwardurl == null) {
			forwardurl = "/admin/shijuanmanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");

		if (id == null)
			return;
		Shijuan shijuan = (Shijuan) DALBase
				.load(Shijuan.class, new Integer(id));
		if (shijuan == null)
			return;
		String title = request.getParameter("title");
		String zongfen = request.getParameter("zongfen");
		String kmid = request.getParameter("kmid");
		String kemu = request.getParameter("kemu");
		String des = request.getParameter("des");
		String tupian = request.getParameter("tupian");
		SimpleDateFormat sdfshijuan = new SimpleDateFormat("yyyy-MM-dd");
		shijuan.setTitle(title);
		shijuan.setKmid(kmid == null ? 0 : Integer.parseInt(kmid));
		shijuan.setZongfen(zongfen == null ? 0 : new Integer(zongfen));
		shijuan.setKemu(kemu);
		shijuan.setTupian(tupian == null ? "" : tupian);
		shijuan.setDes(des);
		DALBase.update(shijuan);

		if (forwardurl == null) {
			forwardurl = "/admin/shijuanmanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 加载内部支持*********************
	 *******************************************************/
	public void load() {
		//
		String id = request.getParameter("id");
		String actiontype = "save";
		dispatchParams(request, response);
		if (id != null) {
			Shijuan shijuan = (Shijuan) DALBase.load("shijuan", "where id="
					+ id);
			if (shijuan != null) {
				request.setAttribute("shijuan", shijuan);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		List<Object> kemu_datasource = DALBase.getEntity("kecheng", "");
		request.setAttribute("kmid_datasource", kemu_datasource);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/shijuanadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		String title = request.getParameter("title");
		String status = request.getParameter("status");
		String zujuanren = request.getParameter("zujuanren");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
		// 查找组卷完成试卷
		if (status != null && status.equals("2"))
			filter += "  and status='组卷完成'  ";
		if (zujuanren != null)
			filter += "  and zujuanren='" + zujuanren + "'";

		int pageindex = 1;
		int pagesize = 10;
		// 获取当前分页
		String currentpageindex = request.getParameter("currentpageindex");
		// 当前页面尺寸
		String currentpagesize = request.getParameter("pagesize");
		// 设置当前页
		if (currentpageindex != null)
			pageindex = new Integer(currentpageindex);
		// 设置当前页尺寸
		if (currentpagesize != null)
			pagesize = new Integer(currentpagesize);
		List<Shijuan> listshijuan = DALBase.getPageEnity("shijuan", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("shijuan",
				filter == null ? "" : filter);
		request.setAttribute("listshijuan", listshijuan);
		PagerMetal pm = new PagerMetal(recordscount);
		// 设置尺寸
		pm.setPagesize(pagesize);
		// 设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/shijuanmanager.jsp";
		}
		forward(forwardurl);
	}

	public void waitExamPaper() {

		String filter = "";

		String stno = request.getParameter("stno");

		String title = request.getParameter("title");

		if (stno != null)
			filter += " and stno='" + stno + "'";
		if (title != null)
			filter += " and title='" + title + "'";

		String SQL = " select sj.* from shijuan sj  where  sj.status='组卷完成'  ";

		List<Shijuan> listshijuan = DALBase.runNativeSQL(SQL, Shijuan.class);

		request.setAttribute("listshijuan", listshijuan);

		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/shijuanmanager.jsp";
		}
		forward(forwardurl);
	}

}
