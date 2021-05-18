package com.example.lost;

/**
 * @Author yinweiyang
 * @Date 2020/7/10 11:20
 * @Version 1.0
 **/
public enum DateEnum {
	/**
	 * 小时
	 */
	HOUR,
	/**
	 * 日
	 */
	DAY,
	/**
	 * 周
	 */
	WEEK,
	/**
	 * 月
	 */
	MONTH;

	public Boolean judgeName(String name) {
		Boolean flag = false;
		if (this.toString().equals(name) || this.toString().equals(name.toLowerCase())) {
			flag = true;
		}
		return flag;
	}

	public String getName() {
		return this.name();
	}
}
