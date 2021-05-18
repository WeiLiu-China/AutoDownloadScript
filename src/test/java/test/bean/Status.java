package test.bean;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @ClassName Status.java
 * @Description 状态枚举
 *
 * @author lw
 * @version 1.0.0
 * @createTime 2020/11/6 14:49
 */
public enum Status {
	/**
	 * 启用
	 */
	ENABLE(1, "启用"),
	/**
	 * 停用
	 */
	DISABLE(0, "停用"),
	/**
	 * 删除
	 */
	DELETE(-1, "删除");

	private Integer code;
	private String desc;

	Status(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}
}
