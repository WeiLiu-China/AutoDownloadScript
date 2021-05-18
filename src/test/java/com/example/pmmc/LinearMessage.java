package com.example.pmmc;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName LinearMessage.java
 * @Description TODO
 * @createTime 2020/09/16 10:32:04
 */
@Data
public class LinearMessage {
	/*private String serviceNoId;*/
	//返回结果是什么样子的
	private ServiceNoMessageParam linearMessage;
}

