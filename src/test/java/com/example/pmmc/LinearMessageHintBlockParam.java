package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName LinearMessageHintBlockParam.java
 * @Description TODO
 * @createTime 2020/09/16 10:51:18
 */
@Data
public class LinearMessageHintBlockParam {
	//	可选项
	private LinearMessageBlockOptions options;
	//文本内容
	private  String text;
}
