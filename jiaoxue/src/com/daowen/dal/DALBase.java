package com.daowen.dal;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daowen.entity.Xinxi;
import com.daowen.util.NameUtil;

public class DALBase {

	private static Session session = null;

	public static Session getSession() {

		if (session == null || !session.isOpen()) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
			session = sf.openSession();
		}
		return session;

	}



	public static List getEntity(String tablename, String filter) {

		Session s = getSession();
		Transaction t = s.beginTransaction();
		String classname = NameUtil.toFirstUpper(tablename);
		String HQL = " from " + classname + " "
				+ (filter == null ? "" : filter);
		System.out.println("HQL=" + HQL);
		List list = null;
		try {

			Query q = s.createQuery(HQL);
			list = q.list();
			t.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return list;

	}

	public static Boolean save(Object o) {

		Session s = getSession();
		Transaction t = s.beginTransaction();
		boolean status = true;
		try {
			s.save(o);

			t.commit();

		} catch (HibernateException e) {

			e.printStackTrace();

			status = false;
		}
		return status;

	}

	public static Boolean update(Object o) {

		Session s = getSession();
		Transaction t = s.beginTransaction();
		boolean status = true;
		try {
			s.update(o);
			t.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			status = false;
		}
		return status;

	}

	/****/
	public static Boolean delete(Object o) {

		Session s = getSession();
		Transaction t = s.beginTransaction();

		boolean status = true;
		try {
			s.delete(o);
			t.commit();

		} catch (HibernateException e) {
			status = false;
		}
		return status;

	}

	public static int delete(String tablename, String filter) {

		Session s = getSession();
		Transaction t = s.beginTransaction();
		String HQL = "delete from " + NameUtil.toFirstUpper(tablename)
				+ (filter == null ? "" : "  " + filter);
		System.out.println("HQL =" + HQL);
		SQLQuery q = s.createSQLQuery(HQL);

		int i = 0;

		try {
			i = q.executeUpdate();
			t.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(tablename + e.getMessage());
			i = -1;
		}
		return i;

	}

	public static int executeUpdate(String SQL) {

		Session s = getSession();
		Transaction t = s.beginTransaction();
		System.out.println("SQL =" + SQL);
		SQLQuery q = s.createSQLQuery(SQL);
		int i = 0;

		try {
			i = q.executeUpdate();
			t.commit();
			s.clear();

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			i = -1;
		}
		return i;

	}

	public static Object load(Class type, Object key) {

		Session s = getSession();
		Transaction t = s.beginTransaction();
		Object o = null;
		try {
			o = s.load(type, (Serializable) key);
		} catch (HibernateException e) {
			System.out.println("" + e.getMessage());
		}
		return o;

	}

	public static Object load(String tablename, String filter) {
		List list = getEntity(tablename, filter);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public static Boolean isExist(String tablename, String filter) {

		List list = getEntity(tablename, filter);
		if (list.isEmpty())
			return false;
		else
			return true;

	}

	public static List getRecords(String HQL, int pageindex, int pagesize) {

		Session session = getSession();
		Transaction t = session.beginTransaction();
		List list = null;
		System.out.print("SQL=" + HQL);
		try {
			Query query = session.createQuery(HQL);
			System.out.println("first=" + ((pageindex - 1) * pagesize));
			System.out.println("max=" + (pageindex * pagesize - 1));
			list = query.setFirstResult((pageindex - 1) * pagesize).setMaxResults(pagesize).list();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.print("系统出现异常" + e.getMessage());
		}

		return list;

	}

	/***
	 * 获取表分页实体
	 * 
	 * @param tablename
	 *            表名
	 * @param filter
	 *            过滤器
	 * @param pageindex
	 *            当前页
	 * @param pagesize
	 *            分页尺寸
	 * @return 分页记录
	 */
	public static List getPageEnity(String tablename, String filter,
			int pageindex, int pagesize) {

		String SQL = MessageFormat.format(" from {0} {1} ",
				NameUtil.toFirstUpper(tablename), filter);
		return getRecords(SQL, pageindex, pagesize);

	}

	/*****
	
	 * @param tablename
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	public static List getPageEnity(String tablename, int pageindex,
			int pagesize) {

		String SQL = MessageFormat.format(" form {0}  ",NameUtil.toFirstUpper(tablename));
		return getRecords(SQL, pageindex, pagesize);

	}

	/**
	 * 获取记录总数
	 * 
	 * @param tablename
	 * @param filter
	 * @return
	 */
	public static int getRecordCount(String tablename, String filter) {

		Session session = getSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(MessageFormat.format(
				"select count(*) total from {0} {1}",
				NameUtil.toFirstUpper(tablename), filter));
		Number count = (Number) query.uniqueResult();
		t.commit();
		return count.intValue();
	}

	public static List runNativeSQL(String SQL) {

		Session session = getSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(SQL);
		List list = query.list();
		t.commit();
		return list;
	}

	public static int getUniqueResult(String nativesql) {

		Session session = getSession();
		if (session == null)
			return 0;
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(nativesql);
		int count = ((BigInteger) query.uniqueResult()).intValue();
		t.commit();
		return count;
	}

	public static List runNativeSQL(String SQL, Class c) {

		Session session = getSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(SQL);
		List list = query.addEntity(c).list();
		t.commit();
		return list;
	}
	public static List runNativeSQL(String SQL, Class c,int pageIndex,int pageSize) {

		Session session = getSession();
		
		SQLQuery query = session.createSQLQuery(SQL);
		List list = query.addEntity(c).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
		session.flush();
		return list;
	}
	

	public static List getTopList(String tablename, String filter,
			int recordcount) {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String classname = NameUtil.toFirstUpper(tablename);
		String HQL = " from " + classname + " "
				+ (filter == null ? "" : filter);
		System.out.println("顶端HQL=" + HQL);
		List list = null;
		try {
			Query q = s.createQuery(HQL);
			q.setFirstResult(0);
			q.setMaxResults(recordcount);
			list = q.list();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.print("系统出现异常" + e.getMessage());
		}
		return list;

	}

	public static List executeNativeQuery(String sql) {
		ResultSet rs = null;
		Connection conn = null;
		List list=null;
		try {
		    conn = getSession().connection();
			Statement ps = conn.createStatement();
		    rs = ps.executeQuery(sql);
			list=convertList(rs);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
            if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
	
	
	public static DataTable executeQuery(String sql) {
		ResultSet rs = null;
		Connection conn = null;
		DataTable table=null;
		try {
		    conn = getSession().connection();
			Statement ps = conn.createStatement();
		    rs = ps.executeQuery(sql);
			table=convertDataTable(rs);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
            if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return table;
	}

	private static List convertList(ResultSet rs) throws SQLException {
		List list = new ArrayList();
		ResultSetMetaData md = rs.getMetaData();// 获取键名
		int columnCount = md.getColumnCount();// 获取行的数量
		while (rs.next()) {
			Map rowData = new HashMap();// 声明Map
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));// 获取键名及值
			}
			list.add(rowData);
		}
		return list;
	}
	
	private static DataTable convertDataTable(ResultSet rs) throws SQLException {
		DataTable table = new DataTable();
		ResultSetMetaData md = rs.getMetaData();// 获取键名
		int columnCount = md.getColumnCount();// 获取行的数量
		while (rs.next()) {
			DataRow rowData = new DataRow();// 声明Map
			for (int i = 1; i <= columnCount; i++) {
				DataColumn  dataColumn=new DataColumn();
				dataColumn.setName(md.getColumnName(i));
				dataColumn.setColumnVal(rs.getObject(i));
				rowData.addColumn(dataColumn);
			}
			table.addRow(rowData);
		}
		return table;
	}




}
