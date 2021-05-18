/*
package com.example.lost;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.Api.JsonResponse;
import com.example.Api.JsonUtil;
import com.example.Api.ServiceResponseCodeEnum;
import com.example.util.HttpUtil;
import com.example.util.UUIDUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class TestManager {

	public static final String UAS_USER_CREDENTIAL = "/uas/v1/identifyController/indentifyUserCredential.do";

	private static final Logger logger = LoggerFactory.getLogger(TestManager.class);

	*/
/**
	 * long to date
	 **//*

	@org.junit.Test
	public void test22() throws Exception {

		String userCredential = "{\\\"credential\\\":{\\\"head\\\":{\\\"credType\\\":\\\"1\\\",\\\"duration\\\":{\\\"endTime\\\":\\\"1600312221601\\\",\\\"startTime\\\":\\\"1600305021601\\\"},\\\"token\\\":{\\\"orgId\\\":\\\"120000000000\\\",\\\"tokenId\\\":\\\"2c918083749084ce0174999cc2a30170\\\"},\\\"version\\\":\\\"1.0\\\"},\\\"load\\\":{\\\"userInfo\\\":{\\\"jh\\\":\\\"000301\\\",\\\"orgId\\\":\\\"410000000000\\\",\\\"sfzh\\\":\\\"410105198403010095\\\",\\\"userId\\\":\\\"2c91808470cd9c120170e1384f0c0072\\\",\\\"xm\\\":\\\"张斌\\\"}},\\\"serverSign\\\":{\\\"alg\\\":\\\"SM3+SM2\\\",\\\"signature\\\":\\\"2WIjCJ/+94XmtIei2+/8eV40dxfvQGqV4ws7WDA5iNPc//zeZTFNewibqa8OSKprWq2P6mnWHMkVPYP+Qdl7Huc0qHJMVFUovF+mqtO5pSxrngem5kXmVK9v1NV7OMurPpa1FiQc1uU0A4Zae4ZrpynM2+qEck/D8KSvLvsKkCQHYPERLINK \"\\\\u003d\\\"\\\\u003d\\\",\\\"sn\\\":\\\"b11000000000bdc\\\",\\\"url\\\":\\\"http://192.168.24.108:8080\\\"}}}";

		String appCredential = "{\\\"credential\\\":{\\\"head\\\":{\\\"credType\\\":\\\"2\\\",\\\"duration\\\":{\\\"endTime\\\":\\\"1600312221764\\\",\\\"startTime\\\":\\\"1600305021764\\\"},\\\"token\\\":{\\\"orgId\\\":\\\"120000000000\\\",\\\"tokenId\\\":\\\"2c918083749084ce0174999cc3520171\\\"},\\\"version\\\":\\\"1.0\\\"},\\\"load\\\":{\\\"appInfo\\\":{\\\"appId\\\":\\\"120000000000-3-1-a939f44b7b9c4a0e8d418585149c8fb7\\\",\\\"csType\\\":\\\"1\\\",\\\"name\\\":\\\"动态数据分析\\\",\\\"networkAreaCode\\\":\\\"3\\\",\\\"orgId\\\":\\\"120000000000\\\",\\\"packageName\\\":\\\"com.xdja.jzgkk\\\"}},\\\"serverSign\\\":{\\\"alg\\\":\\\"SM3+SM2\\\",\\\"signature\\\":\\\"2WIjCJ/+94XmtIei2+/8eV40dxfvQGqV4ws7WDA5iNPc//zeZTFNewibqa8OSKprWq2P6mnWHMkVPYP+Qdl7Huc0qHJMVFUovF+mqtO5pSxrngem5kXmVK9v1NV7OMurPpa1FiQc1uU0A4Zae4ZrpynM2+qEck/D8KSvLvsKkCQ\\\\u003d\\\",\\\"sn\\\":\\\"b11000000000bdc\\\",\\\"url\\\":\\\"http://192.168.24.108:8080\\\"}}}";



		String userCredential1 = "{\\\"credential\\\":{\\\"head\\\":{\\\"credType\\\":\\\"1\\\"," +
				"\\\"duration\\\":{\\\"endTime\\\":\\\"1600312221601\\\",\\\"startTime\\\":\\\"1600305021601\\\"},\\\"token\\\":{\\\"orgId\\\":\\\"120000000000\\\",\\\"tokenId\\\":\\\"2c918083749084ce0174999cc2a30170\\\"},\\\"version\\\":\\\"1.0\\\"},\\\"load\\\":{\\\"userInfo\\\":{\\\"jh\\\":\\\"000301\\\",\\\"orgId\\\":\\\"410000000000\\\",\\\"sfzh\\\":\\\"410105198403010095\\\",\\\"userId\\\":\\\"2c91808470cd9c120170e1384f0c0072\\\",\\\"xm\\\":\\\"张斌\\\"}},\\\"serverSign\\\":{\\\"alg\\\":\\\"SM3+SM2\\\",\\\"signature\\\":\\\"2WIjCJ/+94XmtIei2+/8eV40dxfvQGqV4ws7WDA5iNPc//zeZTFNewibqa8OSKprWq2P6mnWHMkVPYP+Qdl7Huc0qHJMVFUovF+mqtO5pSxrngem5kXmVK9v1NV7OMurPpa1FiQc1uU0A4Zae4ZrpynM2+qEck/D8KSvLvsKkCQHYPERLINK \"\\\\u003d\\\"\\\\u003d\\\",\\\"sn\\\":\\\"b11000000000bdc\\\",\\\"url\\\":\\\"http://192.168.24.108:8080\\\"}}}";

		String appCredential1 = "{\\\"credential\\\":{\\\"head\\\":{\\\"credType\\\":\\\"2\\\"," +
				"\\\"duration\\\":{\\\"endTime\\\":\\\"1600312221764\\\",\\\"startTime\\\":\\\"1600305021764\\\"},\\\"token\\\":{\\\"orgId\\\":\\\"120000000000\\\",\\\"tokenId\\\":\\\"2c918083749084ce0174999cc3520171\\\"},\\\"version\\\":\\\"1.0\\\"},\\\"load\\\":{\\\"appInfo\\\":{\\\"appId\\\":\\\"120000000000-3-1-a939f44b7b9c4a0e8d418585149c8fb7\\\",\\\"csType\\\":\\\"1\\\",\\\"name\\\":\\\"动态数据分析\\\",\\\"networkAreaCode\\\":\\\"3\\\",\\\"orgId\\\":\\\"120000000000\\\",\\\"packageName\\\":\\\"com.xdja.jzgkk\\\"}},\\\"serverSign\\\":{\\\"alg\\\":\\\"SM3+SM2\\\",\\\"signature\\\":\\\"2WIjCJ/+94XmtIei2+/8eV40dxfvQGqV4ws7WDA5iNPc//zeZTFNewibqa8OSKprWq2P6mnWHMkVPYP+Qdl7Huc0qHJMVFUovF+mqtO5pSxrngem5kXmVK9v1NV7OMurPpa1FiQc1uU0A4Zae4ZrpynM2+qEck/D8KSvLvsKkCQ\\\\u003d\\\",\\\"sn\\\":\\\"b11000000000bdc\\\",\\\"url\\\":\\\"http://192.168.24.108:8080\\\"}}}";


		JsonResponse mobile = loginToUas(userCredential1, appCredential1, "Android");
		System.out.println(mobile);
		System.out.println();

	}

	public JsonResponse loginToUas(String userCredential, String appCredential, String platform) throws Exception {
		logger.info("----------->>>loginToUas传入的userCredential 是--->>{}", JSON.toJSONString(userCredential));
		logger.info("----------->>>loginToUas传入的 appCredential是--->>{}", JSON.toJSONString(appCredential));

		String host = "http://11.12.95.204:9011";
		if (ObjectUtils.isEmpty(host)) {
			logger.error("userCredentialHost not config");
			return JsonResponse.createMessage(ServiceResponseCodeEnum.FAILURE, " 票据异常");
		}
		String url = UAS_USER_CREDENTIAL;
		String token = UUIDUtil.getUUID();
		Boolean saveToken = true;
		if (!saveToken) {
			return JsonResponse.createMessage(ServiceResponseCodeEnum.FAILURE, "token 生成异常");
		}
		logger.info(" loginToUas验证用户凭证票据开始url={} 开始时间{}", host + url, System.currentTimeMillis());
		Map<String, String> header = new HashMap<>();
		header.put("userCredential", userCredential);
		header.put("appCredential", appCredential);
		header.put("messageId", token);
		Map<String, String> param = new HashMap<>();
		param.put("backInfo", "0");
		HttpPost post = new HttpPost(host + url);
		if (CollectionUtils.isNotEmpty(param)) {
			post.addHeader("Content-type", "application/json;charset=UTF-8 ");
			post.setEntity(new StringEntity(JSON.toJSONString(param), "utf-8"));
		}
		HttpResponse response = HttpUtil.sendPOSTHttpResponse(host + url, header, JSON.toJSONString(param));
		logger.info("loginToUas验证用户凭证票据调用接口结束时间{}返回结果{}", System.currentTimeMillis(), JsonUtil.marshal(response));
		if (ObjectUtils.isEmpty(response)) {
			throw new RuntimeException("验证失败");
		}
		Header[] codes = response.getHeaders("code");
		if(ObjectUtils.isEmpty(codes)){
			throw new RuntimeException("验证失败");
		}
		String code = codes[0].getValue();
		logger.info("loginToUas验证用户凭证票据调用接口code{}", code);
		if (!ObjectUtils.isEmpty(code)) {
			if ("0".equals(code)) {
				return JsonResponse.createMessage(ServiceResponseCodeEnum.SUCCESS, token);
			} else {
				switch (code) {
					case "10001":
						logger.info(" loginToUas验证用户凭证票据 异常{}", "凭证信息过期");
						break;
					case "10002":
						logger.info(" loginToUas验证用户凭证票据 异常{}", "凭证信息验证未通过");
						break;
					case "10003":
						logger.info(" loginToUas验证用户凭证票据 异常{}", "该凭证信息不属于移动警务平台发放凭证");
						break;
					case "10004":
						logger.info(" loginToUas验证用户凭证票据 异常{}", "其他错误");
						break;
					case "10005":
						logger.info(" loginToUas验证用户凭证票据 异常{}", "系统异常");
						break;
					case "10006":
						logger.info(" loginToUas验证用户凭证票据 异常{}", "生成异常");
						break;
					default:
						logger.info(" loginToUas验证用户凭证票据 异常{}", "参数异常");
				}
				return JsonResponse.createMessage(ServiceResponseCodeEnum.FAILURE, "凭证验证失败");
			}
		}
		return JsonResponse.createMessage(ServiceResponseCodeEnum.SUCCESS, token);
	}

	@org.junit.Test
	public void test223() throws Exception {

		Dep d1 = new Dep();
		d1.setDepartmentName("a");
		d1.setDeptId("1");

		Dep d2 = new Dep();
		d1.setDepartmentName("b");
		d1.setDeptId("2");

		List<Dep> deps = new ArrayList<>();
		deps.add(d1);
		deps.add(d2);
		JSONArray jsonArray2 = JSON.parseArray(deps.toString());
		System.out.println(jsonArray2.toJSONString());
		System.out.println();

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
		List<String> comIds = new ArrayList<>();


		Set<String> ids = new HashSet<>();
		Set<String> idAndNameList = new HashSet<>();

		list.forEach(dep -> {
			if (ids.contains(dep.getDeptId())) {
				cfList.add(dep);
			}
			ids.add(dep.getDeptId());
			idAndNameList.add(dep.getDeptId() + "br/>" + dep.getDepartmentName());

		});


		System.out.println("depId去重数量:  " + list.size());
		System.out.println("去重数量:  " + idAndNameList.size());
		System.out.println();

	}

	public static boolean checkDate(String str) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");//括号内为日期格式，y代表年份，M代表年份中的月份（为避免与小时中的分钟数m冲突，此处用M），d代表月份中的天数
		try {
			sd.setLenient(false);//此处指定日期/时间解析是否不严格，在true是不严格，false时为严格
			sd.parse(str);//从给定字符串的开始解析文本，以生成一个日期
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@org.junit.Test
	public void test2223() {
		add();
	}

	@org.junit.Test
	public void add() {
		Long aMin = 1000L * 60;
		Long aSec = 1000L;
		long pre = System.currentTimeMillis();
		*/
/**
		 * 10亿
		 *//*

		Integer size = 1000000000;
		Integer a3 = 0;
		for (int i = 0; i < size; i++) {
			Integer a1 = 3;
			Integer a2 = 2;
			a3 = a1 / a2;
		}
		long aft = System.currentTimeMillis();
		long sec = (aft - pre) / aSec;
		System.out.println(a3);


		Long b3 = 0L;
		System.out.println("Integer用时：" + sec + "秒");
		for (int i = 0; i < size; i++) {
			Long b1 = 3L;
			Long b2 = 2L;
			b3 = b1 / b2;
		}
		System.out.println(b3);
		long end = System.currentTimeMillis();
		long sec2 = (end - aft) / aSec;

		System.out.println("Long用时：" + sec2 + "秒");

	}

	private static long getWeekByDate(Long time, int number) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		if (time != null) {
			cal.setTimeInMillis(time);
		}
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.add(Calendar.WEEK_OF_YEAR, number);
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// 分
		cal.set(Calendar.MINUTE, 0);
		// 秒
		cal.set(Calendar.SECOND, 0);
		// 毫秒
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}

	public static long getWeekStart(Long time, int number) {
		Calendar cal = Calendar.getInstance();
		if (time != null) {
			cal.setTimeInMillis(time);
		}
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.add(Calendar.WEEK_OF_YEAR, number);
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// 分
		cal.set(Calendar.MINUTE, 0);
		// 秒
		cal.set(Calendar.SECOND, 0);
		// 毫秒
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}


	@org.junit.Test
	public void test4() throws Exception {
	}

	@org.junit.Test
	public void download() throws ParseException {
		String convert = convert("20200607", 1);
		System.out.println(convert);
	}

	private static DateFormat[] dateFormats = {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy-MM-dd HH"),
			new SimpleDateFormat("yyyy-MM-ddHH"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy-MM"),
			new SimpleDateFormat("yyyyMMdd"),
			new SimpleDateFormat("yyyyMMddHH")
	};

	public static String convert(String source, Integer type) {
		DateFormat f = null;
		if (type == 1) {
			f = new SimpleDateFormat("yyyy-MM-dd");
		}
		if (type == 2) {
			f = new SimpleDateFormat("yyyy-MM-dd HH");
		}
		if (type == 3) {
			f = new SimpleDateFormat("yyyy-MM-dd");
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(source)) {
			return null;
		}
		String parse = null;
		Date date = null;
		boolean flag = false;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(source);
			flag = true;
		} catch (ParseException e) {
		}
		try {
			parse = f.format(date);
		} catch (Exception e) {
		}
		if (flag) {
			return parse;
		} else {
			throw new IllegalArgumentException("不能解析日期:" + source);
		}
	}

	@org.junit.Test
	public void getDayRange2() {
		Date dayYouLike = getDayYouLike(new Date(System.currentTimeMillis()), 0);
		//上个七天
		Date sevenStart = getDayYouLike(new Date(System.currentTimeMillis()), -7);
		Date sevenEnd = getDayYouLike(new Date(System.currentTimeMillis()), 0);
		//上上个七天
		Date lastTwoSevenStart = getDayYouLike(new Date(System.currentTimeMillis()), -7);
		Date lastTwoSevenEnd = getDayYouLike(new Date(System.currentTimeMillis()), -14);
		System.out.println(dayYouLike);
	}


	@org.junit.Test
	public void getDayRang3() {
		int a = 1;
		int b = 2;
		double c = (double) (a / b);
		System.out.println(c);
	}


	public static Date getDayYouLike(Date date, Integer num) {
		if (date == null) {
			System.out.println("入参为空");
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//calendar.add(Calendar.DAY_OF_MONTH, +1);//明天
		//calendar.add(Calendar.DAY_OF_MONTH, 0);//今天
		calendar.add(Calendar.DAY_OF_MONTH, num);//昨天:-1
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	public static String getDay(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr_yyyyMMdd);
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}

	public static String getDay(Long timestamp) {
		if (timestamp == null) {
			return null;
		}
		Date date = new Date();
		date.setTime(timestamp);
		return getDay(date);
	}

	public static final String formatStr_yyyyMMdd = "yyyy-MM-dd";

}
*/
