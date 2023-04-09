package com.daowen.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Yingpian {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String ypcatename;

	public String getYpcatename() {
		return ypcatename;
	}

	public void setYpcatename(String ypcatename) {
		this.ypcatename = ypcatename;
	}

	private String pubren;

	public String getPubren() {
		return pubren;
	}

	public void setPubren(String pubren) {
		this.pubren = pubren;
	}

	private Date pubtime;

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	private int clickcount;

	public int getClickcount() {
		return clickcount;
	}

	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}

	private String des;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	private String tupian;

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public int getXshowtype() {
		return xshowtype;
	}

	public void setXshowtype(int xshowtype) {
		this.xshowtype = xshowtype;
	}

	public String getRemoteurl() {
		return remoteurl;
	}

	public void setRemoteurl(String remoteurl) {
		this.remoteurl = remoteurl;
	}

	private int xshowtype;

	private String remoteurl;
	private  int ypcateid;

	public int getYpcateid() {
		return ypcateid;
	}

	public void setYpcateid(int ypcateid) {
		this.ypcateid = ypcateid;
	}
	/**
	 * 字母索引
	 */
	private String alphabetindex;

	public String getAlphabetindex() {
		return alphabetindex;
	}

	public void setAlphabetindex(String alphabetindex) {
		this.alphabetindex = alphabetindex;
	}

}
