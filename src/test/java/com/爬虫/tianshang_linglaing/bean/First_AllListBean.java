package com.爬虫.tianshang_linglaing.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName AllList.java
 * @Description TODO
 * @createTime 2021/05/16 17:04:52
 */
@Data
public class First_AllListBean {
	private String status;
	private List img;
	private List<Ico> ico;
	private List album;
	private List data;

	@Data
	public static class Ico {
		private String id;
		private String img_url;
		private String lmc;
		private String sort;
	}
}
