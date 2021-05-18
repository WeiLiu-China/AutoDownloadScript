package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName LinearMessageBlockOptions.java
 * @Description TODO
 * @createTime 2020/09/16 10:52:02
 */
@Data
public class LinearMessageBlockOptions {
	//构成块底色，用十六进制格式
	private String bg_color;
	//构成块边框颜色，用十六进制格式
	private String border_color;
	//构成块字体是否加粗
	private Boolean font_bold;
	//构成块字体颜色，用十六进制格式
	private String font_color;
}
