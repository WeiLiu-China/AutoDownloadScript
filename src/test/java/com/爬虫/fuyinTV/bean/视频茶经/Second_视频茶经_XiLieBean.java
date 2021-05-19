package com.爬虫.fuyinTV.bean.视频茶经;

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
public class Second_视频茶经_XiLieBean {

	private List<Second_视频茶经_XiLieBean> child;

	private String name;
	private int bible_id;
	private int catid;
	private String letter;
}
