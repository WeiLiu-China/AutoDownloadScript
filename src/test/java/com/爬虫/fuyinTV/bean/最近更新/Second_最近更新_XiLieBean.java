package com.爬虫.fuyinTV.bean.最近更新;

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
public class Second_最近更新_XiLieBean {
	private List<XiLie> new_list;
	private List<XiLie> update_list;

	@Data
	public static class XiLie {
		private int movid;
		private String title;
		private String subtitle;
		private String actor;
		private String area;
		private String director;
		private String category;
		private String pic;
		private int addtime;
		private int modifytime;
		private int urlcount_1;
		private int urlcount_5;
		private int smil;
		private int desc;
	}

}
