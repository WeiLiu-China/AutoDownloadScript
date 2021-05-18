package com.example.pmmc;

import lombok.Data;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName LinearMessageGridBlockParam.java
 * @Description TODO
 * @createTime 2020/09/16 10:49:22
 */
@Data
public class LinearMessageGridBlockParam {
	//表格块的表头字段信息
	private GridColumn[] columns ;
	//表格块的内容数据信息，二维数组
	private  String[][] datas;
}
