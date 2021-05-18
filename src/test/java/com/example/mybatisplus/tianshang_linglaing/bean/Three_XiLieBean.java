package com.example.mybatisplus.tianshang_linglaing.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName Three_XiLieBean.java
 * @Description TODO
 * @createTime 2021/05/16 17:34:56
 */
@Data
public class Three_XiLieBean {
	private String dyzt;
	private String status;
	private List authortime;
	private List data;
	private List<XiLie> list;

	@Data
	public static class XiLie {
		/*add_time: "2005-07-06 00:00:00"
		img_url: "https://www.tingdao.org/Public/Images/Admin/Upload/15842795955e6e302b5f245.jpg"
		is_down: "1"
		number: "11"
		title: "2005年07月06-10日 新加坡特会：持定生命之道（陈希曾，孙国铎，柏盛享）"
		zjid: "1173"
		zjl: "1322"*/
		private String add_time;
		private String img_url;
		private String is_down;
		private String number;
		private String title;
		private String zjid;
		private String zjl;
	}
}
