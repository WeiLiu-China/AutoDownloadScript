package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName GridColumn.java
 * @Description TODO
 * @createTime 2020/09/16 10:50:15
 */
@Data
public class GridColumn {
	//[ left center right ]
	private  String align;
	private  String title;
	private  String width;
}
