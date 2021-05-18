package context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author lw
 * @version 1.0.0
 * @ClassName DemoSon.java
 * @Description TODO
 * @createTime 2020/11/10 09:52:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DemoSon extends Demo {
	private String DemoSon;

	public DemoSon(String demoSon, Integer id, String name) {
		super(id, name);
		this.DemoSon = demoSon;
	}
}
