package com.example.mybatisplus.gongzhonghao;


import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test1 {
	public static void main(String[] args) {

		//MzIxOTc0MzQzMg==
		//MzA3NjkxNDIyOQ%3D%3D
		String biz = "MzA3NjkxNDIyOQ==";
		//MjYyMjc0NzA0Mw%3D%3D
		//777
		String uin = "777";
		// 这个是用的的我的微信号 动态改变的只有key
		String key = "af4c4474d22b7e510ffb9a836217934142013a224ea9f909f2762f52f45f5062868454601519f489fc119061433823dc131c7ab44c519fd0bba40b284935ff53a721808cb1eb9e04027e28eb2ce97e327ca2dac3a97cbb9313f032850d66335fc3b0223c26ebb15a26f99879aa9643745069960c3bb252c253419feefe9e86e8";
		int offset = 0;
		int count = 10;

		int countFlag = 1;

		while (true) {
//            HttpRequest httpRequest = HttpUtil.createGet("").header("", "");
//            httpRequest.execute().body();
			String response = HttpUtil.get("https://mp.weixin.qq.com/mp/profile_ext?" +
					"action=getmsg&" +
					"__biz=" + biz +
					"&f=json&offset=" + offset +
					"&count=10" +
					"&uin=" + uin +
					"&key=" + key);

			offset = offset + count;
			// 解析数据返回内容
			JSONObject jsonObject = JSONObject.parseObject(response);
			String general_msg_list = jsonObject.getString("general_msg_list");
			if (StringUtils.isEmpty(general_msg_list)) {
				Console.log("====访问秘钥已过期，停止采集====");
				break;
			}
			JSONObject jsonObject1 = JSONObject.parseObject(general_msg_list);
			JSONArray list = jsonObject1.getJSONArray("list");
			if (list.isEmpty()) {
				Console.log("=======该公众号没有更多文章了，停止采集====");
				break;
			}
			for (Object o : list) {
				String toJSONString = JSONObject.toJSONString(o);
				JSONObject jsonObject2 = JSONObject.parseObject(toJSONString);
				JSONObject app_msg_ext_info = jsonObject2.getJSONObject("app_msg_ext_info");
				String title = app_msg_ext_info.getString("title");
				String content_url = app_msg_ext_info.getString("content_url");
				Console.log("获取到第：" + countFlag++ + " 条数据");
				Console.log("获取到文章标题：" + title);
				Console.log("获取到文章url：" + content_url);
				System.out.println("");
				/*MyUtil.saveAsFileWriter("获取到文章标题：" + title + "\n" + "获取到文章url：" + content_url + "\n");*/
			}
		}

	}
}
