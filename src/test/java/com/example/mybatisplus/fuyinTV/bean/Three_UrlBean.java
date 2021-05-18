package com.example.mybatisplus.fuyinTV.bean;

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
public class Three_UrlBean {
	private String actor;
	private int addtime;

	private String area;
	private int category;
	private String content;
	private int desc;
	private String director;
	private int modifytime;
	private int movid;
	private String pic;
	private int smil;
	private String subtitle;
	private String title;
	private int urlcount_1;
	private int urlcount_5;

	private List<Url> urls;
	private List zs;

	@Data
	public static class Url {
		private Boolean doc;
		private Boolean mp3;
		private Boolean mp4;
		private String img_url;
		private String title;
		private Boolean txt;
		private int urlid;
	}
}
