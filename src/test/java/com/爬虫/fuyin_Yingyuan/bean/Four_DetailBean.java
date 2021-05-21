package com.爬虫.fuyin_Yingyuan.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName Four_DetailBean.java
 * @Description TODO
 * @createTime 2021/05/16 17:44:25
 */
@Data
public class Four_DetailBean {
	private String dyzt;
	private String status;
	private String is_down;
	private List details;
	private List detailstime;
	private List number;
	private List<Detail> list;

	@Data
	public static class Detail {
		private String add_time;
		private String id;
		private String img_url;
		private String is_down;
		private String title;
		private String video_url;
	}
}
