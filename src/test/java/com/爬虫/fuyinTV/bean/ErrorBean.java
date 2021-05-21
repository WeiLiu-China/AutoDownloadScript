package com.爬虫.fuyinTV.bean;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author lw
 * @version 1.0.0
 * @ClassName ErrorBean.java
 * @Description TODO
 * @createTime 2021/05/21 20:50:38
 */
@Data
public class ErrorBean {
	private String app;
	private String path;
	private String name;
	private String url;
	private String errorMessage;

	public ErrorBean(String app, String path, String name, String url, String errorMessage) {
		this.path = path;
		this.name = name;
		this.url = url;
		this.app = app;
		this.errorMessage = errorMessage;
	}
}

