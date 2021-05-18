package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName LinearMessageContentParam.java
 * @Description TODO
 * @createTime 2020/09/16 10:45:08
 */
@Data
public class LinearMessageContentParam {
	//点击消息块后触发的动作
	//TODO 没看懂Action是什么意思
	private Action action ;
	//显示在消息会话列表里的消息摘要
	private String brief;
	//表格块，在中部
	private LinearMessageGridBlockParam grid;
	//提示块，在底部，一般用于提醒用户触摸可以看详情
	private LinearMessageHintBlockParam hint;
	//文本块，在中部
	private LinearMessageTextBlockParam text;
	//标题块，在顶部
	private  LinearMessageTitleBlockParam title;
}
