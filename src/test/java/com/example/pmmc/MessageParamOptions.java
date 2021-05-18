package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName MessageParamOptions.java
 * @Description TODO
 * @createTime 2020/09/16 10:56:16
 */
@Data
public class MessageParamOptions {
	//接收者的设备类型(逗号分隔)，即只发送给哪些设备。01:WEB 02:手机 03:PC
	private String deviceType;
	//收到消息时，手机通知栏消息分组信息
	private GroupBy groupBy;
	//[ 0 1 2 ] 在会话列表里点击该消息对应的聊天对象时，正常是进入到聊天会话界面，但也可以进入到指定的定制界面。 1 表示进入到Action中设定的指令界面 2 表示进入到正常的聊天会话界面
	//TODO 0是什么界面
	private String  invokeType;
	//收到消息时，手机提醒铃声的等级，值越高，铃声越紧急
	private Integer ringLevel;
}
