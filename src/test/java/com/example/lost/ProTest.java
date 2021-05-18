package com.example.lost;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by administrator on 2017/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ProTest {

	@Test
	public void read() throws Exception {

		StringBuffer buffer = new StringBuffer();
		BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\dept(1)\\dept.txt"));
		String s = null;
		while ((s = bf.readLine()) != null) {//使用readLine方法，一次读一行
			buffer.append(s.trim());
		}
		String str = buffer.toString();
		JSONArray jsonArray = JSON.parseArray(str);
		List<Dep> list = JSONObject.parseArray(jsonArray.toJSONString(), Dep.class);

		List cfList = new ArrayList();


		Set<String> ids = new HashSet<>();
		Set<String> idAndNameList = new HashSet<>();

		list.forEach(dep -> {
			if (ids.contains(dep.getDeptId())) {
				cfList.add(dep);
			}
			ids.add(dep.getDeptId());
			idAndNameList.add(dep.getDeptId() + "br/>" + dep.getDepartmentName());

		});

		System.out.println("depId数量:  " + list.size());
		System.out.println("去重数量:  " + idAndNameList.size());
		System.out.println("入库数量:  18230");

		System.out.println();
	}
}
