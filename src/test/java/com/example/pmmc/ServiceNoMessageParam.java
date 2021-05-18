package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName ServiceNoMessageParam.java
 * @Description TODO
 * @createTime 2020/09/16 10:43:25
 */
@Data
public class ServiceNoMessageParam {
	//消息内容
	private LinearMessageContentParam content ;
	//消息参数的可选参数
	private MessageParamOptions options;
	//	服务号消息接收者————用户登录账号数组，
	private String[] receiver ;
}
