package com.爬虫.tianshang_linglaing;


import com.alibaba.fastjson.JSONObject;
import com.爬虫.CommonThread;
import com.爬虫.tianshang_linglaing.bean.First_AllListBean;
import com.爬虫.tianshang_linglaing.bean.Four_DetailBean;
import com.爬虫.tianshang_linglaing.bean.Second_PeopleListBean;
import com.爬虫.tianshang_linglaing.bean.Three_XiLieBean;
import com.爬虫.util.FileUtil;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.*;

import static com.爬虫.util.FileUtil.download;
import static com.爬虫.util.HttpUtil.doPostXWwwFormUrlencoded;

public class tianshang_LingLiang {

	@Test
	public void test4() {
		String s = "html";
		int index = s.indexOf(".mp3");
		int end = s.indexOf("</a>", index);
		s = s.substring(index, end);
		s = s.substring(s.indexOf(">") + 1);
		System.out.println("");
	}

	String host = "https://www.tingdao.org";

	@Test
	public void download() throws Exception {
		CommonThread commonThread = new CommonThread();
		commonThread.start();

		String path = "E:\\爬虫\\天上灵粮";      //保存目录
		getAllList(path);
	}

	//主页获取所有类型
	void getAllList(String path) throws Exception {
		Map<String, String> param = new HashMap<>();
		String url = "/index/Recommend/aggregate";
		param.put("id", "1");
		param.put("page", "1");
		String allList = sendPost(host, url, param, "全部系列");
		First_AllListBean firstAllListBean = JSONObject.parseObject(allList, First_AllListBean.class);
		List<First_AllListBean.Ico> ico = firstAllListBean.getIco();
		if (firstAllListBean.getStatus().equals("1") && !ObjectUtils.isEmpty(ico)) {
			for (int i = 0; i < ico.size(); i++) {
				First_AllListBean.Ico ic = ico.get(i);
				getPeopleList(path + "/" + ic.getLmc(), ic.getId(), "1");
			}
		} else {
			System.out.println("------------error------------" + ico.toString());
		}

	}

	void getPeopleList(String path, String id, String page) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("page", page);
		String url = "/index/Sermon/Sermon";
		String retrunString = sendPost(host, url, param, "牧人列表");
		Second_PeopleListBean secondPeopleListBean = JSONObject.parseObject(retrunString, Second_PeopleListBean.class);
		if (!ObjectUtils.isEmpty(secondPeopleListBean) && secondPeopleListBean.getStatus().equals("1")
				&&
				!ObjectUtils.isEmpty(secondPeopleListBean.getData())) {
			for (int i = 0; i < secondPeopleListBean.getData().size(); i++) {
				Second_PeopleListBean.User user = secondPeopleListBean.getData().get(i);
				getPeopleXilie(path + "/" + user.getAuthor(), user.getAuthor(), user.getLxid(), "1");
			}
		} else {
			System.out.println("------------error------------" + secondPeopleListBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(secondPeopleListBean.getData()) && secondPeopleListBean.getData().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getPeopleList(path, id, "" + nextPage);
		}

	}

	void getPeopleXilie(String path, String author, String id, String page) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("classid", id);
		param.put("page", page);
		param.put("author", author);
		param.put("order", "倒序");
		param.put("tag_id", "");
		param.put("time", "");
		param.put("type", "");

		String url = "/index/Sermon/Authoralbum";
		String retrunString = sendPost(host, url, param, "牧人系列");
		Three_XiLieBean threeXiLieBean = JSONObject.parseObject(retrunString, Three_XiLieBean.class);
		if (!ObjectUtils.isEmpty(threeXiLieBean) && threeXiLieBean.getStatus().equals("1") &&
				!ObjectUtils.isEmpty(threeXiLieBean.getList())) {
			for (int i = 0; i < threeXiLieBean.getList().size(); i++) {
				Three_XiLieBean.XiLie xiLie = threeXiLieBean.getList().get(i);
				getDetail(path + "/" + xiLie.getTitle(), xiLie.getZjid(), "1", 0);
			}
		} else {
			System.out.println("------------error------------" + threeXiLieBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(threeXiLieBean.getList()) && threeXiLieBean.getList().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getPeopleXilie(path, author, id, "" + nextPage);
		}

	}

	void getDetail(String path, String id, String page, Integer j) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("page", page);


		String url = "/index/Sermon/details";
		String retrunString = sendPost(host, url, param, "详情");
		Four_DetailBean four_detailBean = JSONObject.parseObject(retrunString, Four_DetailBean.class);
		if (!ObjectUtils.isEmpty(four_detailBean) && four_detailBean.getStatus().equals("1") &&
				!ObjectUtils.isEmpty(four_detailBean.getList())) {
			for (int i = 0; i < four_detailBean.getList().size(); i++) {
				Four_DetailBean.Detail detail = four_detailBean.getList().get(i);
				try {
					//downDetail(i + 1, path, detail.getTitle(), detail.getVideo_url());
					int name_num = i + 1 + j;
					FileUtil.download(path, "/" + name_num + "_" + detail.getTitle() + ".mp3", detail.getVideo_url());

				} catch (Exception e) {
					System.out.println("------------失败------------" + detail.getVideo_url());
					System.out.println("------------失败原因------------" + e.getMessage());

				}
			}
		} else {
			System.out.println("------------失败------------" + four_detailBean.toString());
		}
		//继续往下
		if (!ObjectUtils.isEmpty(four_detailBean.getList()) && four_detailBean.getList().size() >= 10) {
			int nextPage = Integer.parseInt(page) + 1;
			getDetail(path, id, "" + nextPage, four_detailBean.getList().size() + j);
		}

	}


	String sendPost(String host, String url, Map<String, String> param, String type) throws Exception {
		String rt = null;
		try {
			rt = doPostXWwwFormUrlencoded(host + url, param, type);

		} catch (Exception e) {
			System.out.println("-------------------------\ndoPostXWwwFormUrlencoded rt为null:\t" + host + url + type + "\n");
			System.out.println("-----------错误信息：" + e.getMessage());
		}
		return rt;
	}

	/**
	 * 截取字符串str中指定字符 strStart、strEnd之间的字符串
	 *
	 * @return
	 */
	public static String subString(String str, String strStart, String strEnd) {

		/* 找出指定的2个字符在 该字符串里面的 位置 */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index 为负数 即表示该字符串中 没有该字符 */
		if (strStartIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
		}
		if (strEndIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
		}
		/* 开始截取 */
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
	}
}
