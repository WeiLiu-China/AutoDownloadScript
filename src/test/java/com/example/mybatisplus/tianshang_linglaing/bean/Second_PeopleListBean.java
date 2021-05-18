package com.example.mybatisplus.tianshang_linglaing.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName user.java
 * @Description TODO
 * @createTime 2021/05/15 15:18:42
 */
@Data
public class Second_PeopleListBean {
	private String status;
	private List<User> data = new ArrayList();

	@Data
	public static class User {
		private String author;
		private String user_img;
		private String number;
		private String lxid;
	}
}
