package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName LinearMessageTextBlockParam.java
 * @Description TODO
 * @createTime 2020/09/16 10:53:34
 */
@Data
public class LinearMessageTextBlockParam {
	//可选项
	private LinearMessageBlockOptions options;
	//文本内容
	private String text;
}
