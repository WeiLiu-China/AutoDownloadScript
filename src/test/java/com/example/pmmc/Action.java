package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName Action.java
 * @Description TODO
 * @createTime 2020/09/16 10:47:39
 */
@Data
public class Action {
	//参数, 类型为1时，必须传入名字为url的参数；类型为3时，必须传入的参数有：appCode(上架到应用市场的应用编码)、appUrl(启动页，相对路径，可以不传入)、pcHomeUrl(pc端收到这条消息时，打开的URL，可以不传入)
	private ActionParam[] action_param;
	//string [ 0 1 2 3 ] 	指令的类型
	private String action_type;
}
