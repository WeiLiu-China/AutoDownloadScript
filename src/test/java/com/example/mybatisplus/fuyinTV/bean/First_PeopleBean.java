package com.example.mybatisplus.fuyinTV.bean;

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
public class First_PeopleBean {
	private String id;
	private String category;
	private int parentid;
	private int sort;
	private int sort2;
	private int moviecount;
	private int nodes;
	private String thumb;
}
