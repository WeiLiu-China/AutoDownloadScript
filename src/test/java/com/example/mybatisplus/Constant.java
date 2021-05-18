package com.example.mybatisplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName Constant.java
 * @Description TODO
 * @createTime 2021/05/18 09:40:31
 */
public class Constant {
	public static int threadNum = 30;
	/**
	 * 仍在内存中的线程，可用于判断下载失败的
	 */
	public static List<String> threadNameList = new ArrayList<>();
}
