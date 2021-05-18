package com.example.mybatisplus.Zan;


import com.example.Bean.Bean;
import com.example.pmmc.GroupBy;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class downloadZanMusic {
	private static String nb = "1";

	@Test
	public void test0() {
		String a = "1,2";
		List<String> cityIds = Arrays.stream(a.split(",")).collect(Collectors.toList());
		System.out.println(cityIds);
	}


	@Test
	public void test2() {
		// 定义人名数组
		final String[] names = {"Zebe", "Hebe", "Mary", "July", "David"};
		Stream<String> stream1 = Stream.of(names);
		Stream<String> stream2 = Stream.of(names);
		Stream<String> stream3 = Stream.of(names);
		// 拼接成 [x, y, z] 形式
		String result1 = stream1.collect(Collectors.joining(", ", "[", "]"));
		// 拼接成 x | y | z 形式
		String result2 = stream2.collect(Collectors.joining(" | ", "", ""));
		// 拼接成 x -> y -> z] 形式
		String result3 = stream3.collect(Collectors.joining());
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
	}

	/**
	 * date -> long
	 **/
	@Test
	public void test3() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = "20181112";
		Date date = sdf.parse(str);
		System.out.println(date);
		System.out.println(date.getTime());
        /*String dateFormate = sdf.format(str);
        System.out.println(dateFormate);*/
	}

	/**
	 * long to date
	 **/
	@Test
	public void test22() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Long l = 2016516516L;
		//把long转换成Date
		String format = sdf.format(new Date(l));
		sdf.format(System.currentTimeMillis());
		System.out.println(format);
	}

	@Test
	public void test223() {
		String startTree = "-123-456-";
		String endTree = "-123-456-789-";
		List<String> startList = new ArrayList<String>(Arrays.asList(startTree.substring(1).split("-")));
		List<String> endList = new ArrayList<String>(Arrays.asList(endTree.substring(1).split("-")));
		System.out.println(startList);
		System.out.println(endList);
		endList.removeAll(startList);
		//String s = startList.get(startList.size() - 1);
		System.out.println("1");
		endList.add(0, startList.get(startList.size() - 1));
		System.out.println(endList);
	}

	@Test
	public void test4() {
		String s = "<!doctype html>\n" +
				"<html>\n" +
				"         <!-- playlist content is mirrored here --> \n" +
				"         <div class=\"sm2-playlist-wrapper\"> \n" +
				"          <ul class=\"sm2-playlist-bd\"> \n" +
				"           <li> <a href=\"//play.zanmeishi.com/song/p/1.mp3\">真爱在呼唤</a> </li> \n" +
				"          </ul> \n" +
				"         </div> \n" +
				"        </div> \n" +
				"       </div> \n" +
				"       <!--单曲播放器END--> \n" +
				"       <div style=\"height: 15px;\"></div>\n" +
				"       <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top: 0px;\"> \n" +
				"        <tbody>\n" +
				"         <tr> \n" +
				"          <td class=\"t\">所属歌手：</td> \n" +
				"          <td class=\"c\"><a href=\"/artist/the-lily-of-wild.html\" title=\"查看歌手主页\">野地百合</a></td> \n" +
				"          <td class=\"t\">所属专辑：</td> \n" +
				"          <td class=\"c\"><a href=\"/album/the-true-love-is-calling.html\" title=\"查看专辑主页\">心的归回</a> <a href=\"javascript:;\" rel=\"5162d15c7d4c500788487d84\" title=\"播放整张专辑\" class=\"fa-playall\"><i class=\"fas fa-play-circle fa-lg\"></i></a></td> \n" +
				"         </tr> \n" +
				"         <tr> \n" +
				"          <td class=\"t\">总 人 气：</td> \n" +
				"          <td class=\"c\" title=\"404791\">40.5 万</td> \n" +
				"          <td class=\"t\"></td> \n" +
				"          <td class=\"c\"></td> \n" +
				"         </tr> \n" +
				"         <tr> \n" +
				"          <td class=\"t\">分享歌曲：</td> \n" +
				"          <td class=\"c\" colspan=\"3\">\n" +
				"           <div class=\"bdsharebuttonbox\"> \n" +
				"            <a href=\"#\" class=\"bds_qzone\" data-cmd=\"qzone\" title=\"分享到QQ空间\"></a> \n" +
				"            <a href=\"#\" class=\"bds_tsina\" data-cmd=\"tsina\" title=\"分享到新浪微博\"></a> \n" +
				"            <a href=\"#\" class=\"bds_sqq\" data-cmd=\"sqq\" title=\"分享给QQ好友\"></a> \n" +
				"            <a href=\"#\" class=\"bds_bdhome\" data-cmd=\"bdhome\" title=\"分享给百度新首页\"></a> \n" +
				"            <a href=\"#\" class=\"bds_weixin\" data-cmd=\"weixin\" title=\"分享到微信\"></a> \n" +
				"            <a href=\"#\" class=\"bds_print\" data-cmd=\"print\" title=\"打印当前页面\"></a> \n" +
				"            <a href=\"#\" class=\"bds_copy\" data-cmd=\"copy\" title=\"复制网址\"></a> \n" +
				"            <a href=\"#\" class=\"bds_more\" data-cmd=\"more\"></a> &nbsp;&nbsp;\n" +
				"            <span class=\"gray\">@微博好友，送歌给TA +_+</span> \n" +
				"           </div> <script>\n" +
				"window._bd_share_config={\"common\":{\"bdSnsKey\":{\"tsina\":\"443534961\"},\"bdText\":\"点击在线收听赞美诗真爱在呼唤\",\"bdMini\":\"2\",\"bdMiniList\":false,\"bdPic\":\"https://file.zanmeishi.com/album/2008/02/22/5162d15c7d4c500788487d84.jpg\",\"bdStyle\":\"0\",\"bdSize\":\"16\"},\"share\":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='https://static.zanmeishi.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];\n" +
				"</script> \n" +
				"           <!-- OLD Baidu Button BEGIN --> \n" +
				"           <!--\n" +
				"    <div id=\"bdshare\" class=\"bdshare_t bds_tools get-codes-bdshare\">\n" +
				"        <a class=\"bds_qzone\"></a>\n" +
				"        <a class=\"bds_tsina\"></a>\n" +
				"        <a class=\"bds_tqq\"></a>\n" +
				"        <a class=\"bds_renren\"></a>\n" +
				"\t\t<a class=\"bds_copy kaobei\"></a>\n" +
				"        <span class=\"bds_more\"></span>\n" +
				"        <a class=\"shareCount\"></a> &nbsp;&nbsp;<span class=\"gray\">@微博好友，送歌给TA +_+</span>\n" +
				"    </div>\n" +
				"<script type=\"text/javascript\" id=\"bdshare_js\" data=\"type=tools&amp;uid=180503\" ></script>\n" +
				"<script type=\"text/javascript\" id=\"bdshell_js\"></script>\n" +
				"<script type=\"text/javascript\">\n" +
				"\tdocument.getElementById(\"bdshell_js\").src = \"http://share.baidu.com/static/js/shell_v2.js?t=\" + new Date().getHours();\n" +
				"</script>\n" +
				"--> \n" +
				"           <!-- Baidu Button END --></td> \n" +
				"         </tr> \n" +
				"         <tr> \n" +
				"          <td class=\"t\">歌曲操作：</td> \n" +
				"          <td colspan=\"3\" class=\"c\"> <a id=\"btn_play\" href=\"javascript:;\" rel=\"5162d1be7d4c500788488123\"><i class=\"fas fa-play font_org\"></i> 弹出播放</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id=\"btn_list\" href=\"javascript:;\" rel=\"5162d1be7d4c500788488123\"><i class=\"fas fa-list\"></i> 加入列表</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:zms.send('真爱在呼唤', '5162d1be7d4c500788488123');\"><i class=\"fas fa-at\"></i> 送给朋友</a> \n" +
				"           <!--                            &nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:zms.singleplayer('--> \n" +
				"           <!--',  '--> \n" +
				"           <!--');\" ><i class=\"fas fa-quote-left\"></i> 转贴</a>--> &nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:zms.box('真爱在呼唤', '5162d1be7d4c500788488123');\"><i class=\"fas fa-plus\"></i> 加入歌单</a> </td> \n" +
				"         </tr> \n" +
				"         <tr> \n" +
				"          <td colspan=\"4\"> \n" +
				"           <div> \n" +
				"            <input type=\"text\" id=\"input_text\" class=\"input1\" value=\"http://zms.so/s1\" style=\"min-width:130px; color:#999;padding-left: 5px;\"> \n" +
				"            <button class=\"copylink btn_txt\" data-clipboard-target=\"#input_text\">复制网址 &nbsp;&nbsp;<a href=\"javascript:zms.mobile('试听歌曲 真爱在呼唤');\"><i class=\"fas fa-mobile-alt\"></i> 手机听歌</a> <script>\n" +
				"                                    var clipboard = new ClipboardJS('.copylink');\n" +
				"                                    clipboard.on('success', function () {\n" +
				"                                        art.dialog({title: '消息', icon: 'succeed', content: '恭喜您，网址复制成功！', time: 1500});\n" +
				"                                    });\n" +
				"\n" +
				"                                    clipboard.on('error', function () {\n" +
				"                                        art.dialog({\n" +
				"                                            title: '消息',\n" +
				"                                            icon: 'warning',\n" +
				"                                            content: '请使用Ctrl+C手动复制',\n" +
				"                                            time: 1500\n" +
				"                                        });\n" +
				"                                    });\n" +
				"                                </script> </button>\n" +
				"           </div> </td> \n" +
				"         </tr> \n" +
				"         <tr> \n" +
				"          <td colspan=\"4\">请注意：本歌曲仅提供低音质试听，不提供下载。未经作者许可，不得转载至其他平台。<a href=\"http://www.zanmeishi.com/help/3.html\"><i class=\"fas fa-question-circle\"></i></a></td> \n" +
				"         </tr>  \n" +
				"        </tbody>\n" +
				"       </table> \n" +
				"      </div> \n" +
				"      <!--歌词--> \n" +
				"      <div id=\"lyrics\"> \n" +
				"       <h5>歌曲\"真爱在呼唤\"的歌词：</h5> \n" +
				"       <div class=\"line\"></div> \n" +
				"       <div class=\"ml20 t_r\"> \n" +
				"        <button class=\"copytxt btn_txt\" data-clipboard-text=\"歌名：真爱在呼唤\n" +
				"专辑：心的归回\n" +
				"歌手：野地百合\n" +
				"\n" +
				"词曲：凡口草\n" +
				"\n" +
				"世界哪里有真爱\n" +
				"或许早被人们忘怀\n" +
				"明天桃花依旧开放\n" +
				"你的笑容是否依旧在脸上\n" +
				"\n" +
				"耶稣十字架的爱\n" +
				"自古从末被人忘怀\n" +
				"世界所有爱都是假像\n" +
				"耶稣给你真爱永远\n" +
				"\n" +
				"真爱是耶稣 真爱在呼唤\n" +
				"呼唤你的回航\n" +
				"亲爱的朋友 让祂为你扬帆\n" +
				"回转吧 浪迹的游子\n" +
				"\n" +
				"可听见主的呼唤\n" +
				"拥有耶稣就怀抱希望\n" +
				"祂是你生命中的唯一曙光\n" +
				"\n" +
				"点击听歌☞ zms.so/s1\" style=\"color: #0187C5;\">复制歌词 </button> \n" +
				"        <span class=\"font_gray\">|</span> \n" +
				"        <script>\n" +
				"                            var clipboard = new ClipboardJS('.copytxt');\n" +
				"                            clipboard.on('success', function () {\n" +
				"                                art.dialog({title: '消息', icon: 'succeed', content: '恭喜您，复制歌词成功', time: 1500});\n" +
				"                            });\n" +
				"\n" +
				"                            clipboard.on('error', function () {\n" +
				"                                art.dialog({title: '消息', icon: 'warning', content: '请使用Ctrl+C手动复制', time: 1500});\n" +
				"                            });\n" +
				"                        </script> \n" +
				"        <a href=\"javascript:zms.ppt('5162d1be7d4c500788488123');\">上传PPT</a> | \n" +
				"        <a target=\"_blank\" href=\"/down/lyric/5162d1be7d4c500788488123.lrc\">下载LRC动态歌词</a> &nbsp;&nbsp;\n" +
				"        <span class=\"font_gray\">|</span>&nbsp;&nbsp;\n" +
				"        <a href=\"javascript:zms.lyric('5162d1be7d4c500788488123');\">LRC歌词有误？点此纠错</a> \n" +
				"        <br> \n" +
				"        <div class=\"tips t_l\">\n" +
				"         TIPS: 本歌曲含LRC歌词，PC浏览器按F11键可进入/退出全屏投影模式播放\n" +
				"        </div> \n" +
				"       </div> \n" +
				"       <div class=\"mt20 ml20 gray\"> \n" +
				"        <a href=\"javascript:zms.ctext('5162d1be7d4c500788488123');\">以下文本歌词有误？点此纠错</a> \n" +
				"        <pre>词曲：凡口草\n" +
				"\n" +
				"世界哪里有真爱\n" +
				"或许早被人们忘怀\n" +
				"明天桃花依旧开放\n" +
				"你的笑容是否依旧在脸上\n" +
				"\n" +
				"耶稣十字架的爱\n" +
				"自古从末被人忘怀\n" +
				"世界所有爱都是假像\n" +
				"耶稣给你真爱永远\n" +
				"\n" +
				"真爱是耶稣 真爱在呼唤\n" +
				"呼唤你的回航\n" +
				"亲爱的朋友 让祂为你扬帆\n" +
				"回转吧 浪迹的游子\n" +
				"\n" +
				"可听见主的呼唤\n" +
				"拥有耶稣就怀抱希望\n" +
				"祂是你生命中的唯一曙光                </pre> \n" +
				"       </div> \n" +
				"       <!--<a href=\"\" id=\"lyricSwitch\" class=\"lyric-switch\">\n" +
				"                <span class=\"text\">展开</span><span class=\"icon\"></span>\n" +
				"                </a>--> \n" +
				"       <div id=\"lyricshow\" class=\"display_none\"> \n" +
				"        <pre>[00:01.13]真爱在呼唤\n" +
				"[00:07.13]词曲：凡口草\n" +
				"[00:15.16]演唱：野地百合\n" +
				"[00:18.56]专辑：心的归回\n" +
				"[00:20.55]\n" +
				"[00:25.64]世界哪里有真爱\n" +
				"[00:31.20]或许早被人们忘怀\n" +
				"[00:36.42]明天桃花依旧开放\n" +
				"[00:42.24]你的笑容是否依旧在脸上\n" +
				"[00:48.96]耶稣十字架的爱\n" +
				"[00:54.52]自古从末被人忘怀\n" +
				"[01:00.88]世界所有爱都是假像\n" +
				"[01:06.66]耶稣给你真爱永远\n" +
				"[01:12.65]真爱是耶稣 真爱在呼唤\n" +
				"[01:24.97]呼唤你的回航\n" +
				"[01:31.20]亲爱的朋友 让祂为你扬帆\n" +
				"[01:36.92]回转吧 浪迹的游子\n" +
				"[01:42.98]可听见主的呼唤\n" +
				"[01:48.53]拥有耶稣就怀抱希望\n" +
				"[01:55.09]祂是你生命中的唯一曙光\n" +
				"[02:01.82]\n" +
				"[02:10.99]\n" +
				"[02:24.95]世界哪里有真爱\n" +
				"[02:30.63]或许早被人们忘怀\n" +
				"[02:37.57]明天桃花依旧开放\n" +
				"[02:42.61]你的笑容是否依旧在脸上\n" +
				"[02:49.30]耶稣十字架的爱\n" +
				"[02:55.40]自古从末被人忘怀\n" +
				"[03:00.85]世界所有爱都是假像\n" +
				"[03:06.86]耶稣给你真爱永远\n" +
				"[03:13.00]真爱是耶稣 真爱在呼唤\n" +
				"[03:24.93]呼唤你的回航\n" +
				"[03:31.16]亲爱的朋友 让祂为你扬帆\n" +
				"[03:36.97]回转吧 浪迹的游子\n" +
				"[03:42.91]可听见主的呼唤\n" +
				"[03:48.84]拥有耶稣就怀抱希望\n" +
				"[03:54.83]祂是你生命中的唯一曙光\n" +
				"[04:01.01]真爱是耶稣 真爱在呼唤\n" +
				"[04:12.89]呼唤你的回航\n" +
				"[04:19.21]亲爱的朋友 让祂为你扬帆\n" +
				"[04:24.96]回转吧 浪迹的游子\n" +
				"[04:30.87]可听见主的呼唤\n" +
				"[04:37.00]拥有耶稣就怀抱希望\n" +
				"[04:43.08]祂是你生命中的唯一曙光\n" +
				"[04:49.27]祂是你生命中的唯一曙光\n" +
				"[04:59.46]\n" +
				"</pre> \n" +
				"       </div> \n" +
				"      </div> \n" +
				"      <!--收藏的用户--> \n" +
				"      <h5 class=\"mt10\"><a href=\"/song/1/box.html\" class=\"r\"><span>更多...</span></a>歌曲\"真爱在呼唤\"关联的歌单：</h5> \n" +
				"      <div class=\"line\"></div> \n" +
				"      <div class=\"boxes\"> \n" +
				"       <ul> \n" +
				"        <li> <span class=\"hits\"><i class=\"fas fa-headphones-alt\"></i> 77.1 万</span> <a class=\"play-icon\" title=\"播放整个歌单\" href=\"javascript:;\" rel=\"5162d8ba7d4c501dcc92063d\"><i class=\"far fa-play-circle\"></i></a><a href=\"/box/22701.html\" title=\"天韵之声\"><img alt=\"box\" src=\"https://file.zanmeishi.com/album/2008/02/22/5162d15e7d4c500788487dcf.jpg\"></a> \n" +
				"         <div class=\"hidden\"> \n" +
				"          <a href=\"/box/22701.html\" title=\"天韵之声\">天韵之声</a>\n" +
				"         </div> <p class=\"gray hidden\"><a href=\"/my/56218\" class=\"gray_link\" title=\"查看lujun1990的主页\">lujun1990</a></p> </li> \n" +
				"        <li> <span class=\"hits\"><i class=\"fas fa-headphones-alt\"></i> 4.6 万</span> <a class=\"play-icon\" title=\"播放整个歌单\" href=\"javascript:;\" rel=\"5162d8997d4c501dcc91c5f9\"><i class=\"far fa-play-circle\"></i></a><a href=\"/box/3746.html\" title=\"一生歌颂赞美\"><img alt=\"box\" src=\"https://file.zanmeishi.com/album/2008/02/22/5162d15c7d4c500788487d84.jpg\"></a> \n" +
				"         <div class=\"hidden\"> \n" +
				"          <a href=\"/box/3746.html\" title=\"一生歌颂赞美\">一生歌颂赞美</a>\n" +
				"         </div> <p class=\"gray hidden\"><a href=\"/my/31779\" class=\"gray_link\" title=\"查看sugarcc的主页\">sugarcc</a></p> </li> \n" +
				"        <li> <span class=\"hits\"><i class=\"fas fa-headphones-alt\"></i> 3.6 万</span> <a class=\"play-icon\" title=\"播放整个歌单\" href=\"javascript:;\" rel=\"5162d8987d4c501dcc91c26c\"><i class=\"far fa-play-circle\"></i></a><a href=\"/box/2792.html\" title=\"十字架的歌\"><img alt=\"box\" src=\"https://file.zanmeishi.com/box/2009/06/18/5162d8987d4c501dcc91c26c.jpg\"></a> \n" +
				"         <div class=\"hidden\"> \n" +
				"          <a href=\"/box/2792.html\" title=\"十字架的歌\">十字架的歌</a>\n" +
				"         </div> <p class=\"gray hidden\"><a href=\"/my/41317\" class=\"gray_link\" title=\"查看2800449110的主页\">2800449110</a></p> </li> \n" +
				"        <li> <span class=\"hits\"><i class=\"fas fa-headphones-alt\"></i> 2 万</span> <a class=\"play-icon\" title=\"播放整个歌单\" href=\"javascript:;\" rel=\"57653ff5c162dce0658b478d\"><i class=\"far fa-play-circle\"></i></a><a href=\"/box/408208.html\" title=\"赞美主\"><img alt=\"box\" src=\"https://file.zanmeishi.com/album/2014/09/16/5417d22800769ecc28000001.jpg\"></a> \n" +
				"         <div class=\"hidden\"> \n" +
				"          <a href=\"/box/408208.html\" title=\"赞美主\">赞美主</a>\n" +
				"         </div> <p class=\"gray hidden\"><a href=\"/my/358913\" class=\"gray_link\" title=\"查看卧室2的主页\">卧室2</a></p> </li> \n" +
				"        <li> <span class=\"hits\"><i class=\"fas fa-headphones-alt\"></i> 1.8 万</span> <a class=\"play-icon\" title=\"播放整个歌单\" href=\"javascript:;\" rel=\"5162d8937d4c501dcc91b984\"><i class=\"far fa-play-circle\"></i></a><a href=\"/box/371.html\" title=\"神人\"><img alt=\"box\" src=\"https://file.zanmeishi.com/album/2008/02/22/5162d1637d4c500788487ed3.jpg\"></a> \n" +
				"         <div class=\"hidden\"> \n" +
				"          <a href=\"/box/371.html\" title=\"神人\">神人</a>\n" +
				"         </div> <p class=\"gray hidden\"><a href=\"/my/5372\" class=\"gray_link\" title=\"查看神人的主页\">神人</a></p> </li> \n" +
				"       </ul> \n" +
				"       <div class=\"clear\"></div> \n" +
				"      </div> \n" +
				"      <script type=\"text/javascript\">\n" +
				"$(function(){\n" +
				"    zmsp.bindCollection('a.play-icon', 'box');\n" +
				"});\n" +
				"</script> \n" +
				"      <!--收藏的用户--> \n" +
				"      <h5 class=\"mt10\">最近收藏了\"真爱在呼唤\"的弟兄姊妹：</h5> \n" +
				"      <div class=\"line\"></div> \n" +
				"      <div id=\"favusers\"> \n" +
				"       <div class=\"users\"> \n" +
				"        <ul> \n" +
				"         <li> <a href=\"/my/1378991\" title=\"访问火流星主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"火流星\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1378991\" title=\"访问火流星主页\">火流星</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1355657\" title=\"访问小瑄瑄主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"小瑄瑄\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1355657\" title=\"访问小瑄瑄主页\">小瑄瑄</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1319037\" title=\"访问日月星宿主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2020/09/12/5f5c96e9fecf2668eb218058.jpg\" title=\"日月星宿\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1319037\" title=\"访问日月星宿主页\">日月星宿</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1378521\" title=\"访问甜甜921主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"甜甜921\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1378521\" title=\"访问甜甜921主页\">甜甜921</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1376412\" title=\"访问zhangaixue主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"zhangaixue\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1376412\" title=\"访问zhangaixue主页\">zhangaixue</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1355703\" title=\"访问基甸的遇见主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"基甸的遇见\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1355703\" title=\"访问基甸的遇见主页\">基甸的遇见</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1360977\" title=\"访问听！天上的声音主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"听！天上的声音\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1360977\" title=\"访问听！天上的声音主页\">听！天上的声音</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1262669\" title=\"访问花夜落主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2019/08/16/5d5613b667a7ce7ede1d9842.jpg\" title=\"花夜落\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1262669\" title=\"访问花夜落主页\">花夜落</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1243359\" title=\"访问aiyan-a主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2019/04/24/5cbfb1f033a2ce5654708f9c.jpg\" title=\"aiyan-a\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1243359\" title=\"访问aiyan-a主页\">aiyan-a</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1118177\" title=\"访问生命中有你耶稣主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"生命中有你耶稣\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1118177\" title=\"访问生命中有你耶稣主页\">生命中有你耶稣</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1352863\" title=\"访问怀保主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"怀保\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1352863\" title=\"访问怀保主页\">怀保</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1375600\" title=\"访问永久甜蜜主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"永久甜蜜\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1375600\" title=\"访问永久甜蜜主页\">永久甜蜜</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/317990\" title=\"访问anjingdege主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2013/03/13/5162d3f77d4c501e90a14708.jpg\" title=\"anjingdege\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/317990\" title=\"访问anjingdege主页\">anjingdege</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1358455\" title=\"访问liu188主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"liu188\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1358455\" title=\"访问liu188主页\">liu188</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1375316\" title=\"访问爱充满主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2021/05/06/6093596b3cdf6e186006b1ed.jpg\" title=\"爱充满\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1375316\" title=\"访问爱充满主页\">爱充满</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1374323\" title=\"访问王乾乾雅各主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2021/05/04/6090852c9b7da6508c69ab85.jpg\" title=\"王乾乾雅各\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1374323\" title=\"访问王乾乾雅各主页\">王乾乾雅各</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1374263\" title=\"访问66270主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"66270\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1374263\" title=\"访问66270主页\">66270</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1374486\" title=\"访问陈lq主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"陈lq\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1374486\" title=\"访问陈lq主页\">陈lq</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1325145\" title=\"访问以勒人生阿芝主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"以勒人生阿芝\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1325145\" title=\"访问以勒人生阿芝主页\">以勒人生阿芝</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1369979\" title=\"访问耶稣与我同在！主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"耶稣与我同在！\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1369979\" title=\"访问耶稣与我同在！主页\">耶稣与我同在！</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1338578\" title=\"访问sf123654789主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"sf123654789\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1338578\" title=\"访问sf123654789主页\">sf123654789</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1322065\" title=\"访问牧政主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2020/10/10/5f81068b004ae40e204ea56a.jpg\" title=\"牧政\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1322065\" title=\"访问牧政主页\">牧政</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1371433\" title=\"访问上帝视角主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"上帝视角\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1371433\" title=\"访问上帝视角主页\">上帝视角</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1358487\" title=\"访问鹿溪小喵主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2021/03/20/6055a19435502f0fa731167c.jpg\" title=\"鹿溪小喵\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1358487\" title=\"访问鹿溪小喵主页\">鹿溪小喵</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/1066225\" title=\"访问粉红小猪妹。主页\"> <img alt=\"avatar\" src=\"https://file.zanmeishi.com/avatar/2017/08/17/59953c2e9dfc044c706f8402.jpg\" title=\"粉红小猪妹。\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/1066225\" title=\"访问粉红小猪妹。主页\">粉红小猪妹。</a> \n" +
				"          </div> </li> \n" +
				"         <li> <a href=\"/my/857258\" title=\"访问15953107770主页\"> <img alt=\"avatar\" src=\"https://static.zanmeishi.com/images/noavatar.jpg\" title=\"15953107770\"></a> <br> \n" +
				"          <div class=\"hidden\">\n" +
				"           <a href=\"/my/857258\" title=\"访问15953107770主页\">15953107770</a> \n" +
				"          </div> </li> \n" +
				"        </ul> \n" +
				"        <div class=\"clear\"></div> \n" +
				"       </div> \n" +
				"      </div> \n" +
				"      <script language=\"javascript\">\n" +
				"                window.onload = function () {\n" +
				"                    var dl = document.getElementsByTagName(\"dl\");\n" +
				"                    if (dl.length < 1) return false;\n" +
				"                    for (var i = 0; i < dl.length; i++) {\n" +
				"                        if (dl[i].className.indexOf(\"over\") == -1) {\n" +
				"                            dl[0].className = \"over\";\n" +
				"                        }\n" +
				"                        dl[i].onmouseover = function () {\n" +
				"                            for (var j = 0; j < dl.length; j++) {\n" +
				"                                dl[j].className = \"\";\n" +
				"                            }\n" +
				"                            this.className = \"over\";\n" +
				"                        }\n" +
				"                    }\n" +
				"                }\n" +
				"            </script> \n" +
				"      <h5>收藏了《<em>真爱在呼唤</em>》的弟兄姊妹还收藏了：</h5> \n" +
				"      <div class=\"line\" style=\"padding-bottom:1px;\"></div> \n" +
				"      <div class=\"faf\"> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/14720.html\">你的爱不离不弃</a> <span>(35.45%) <a href=\"/artist/when-the-wind-of-the-lord-is-rising.html\" class=\"gray_link\">神机会的风</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"14720\"></a> <img alt=\"你的爱不离不弃\" src=\"//file.zanmeishi.com/album/2011/11/28/5162d16a7d4c5007884880ac.jpg\" width=\"40\"> </span> <span><a href=\"/song/14720.html\">你的爱不离不弃</a><br><a href=\"/album/when-the-wind-of-the-lord-is-rising.html\" class=\"gray_link\">神机会的风</a> By <a href=\"/artist/rolcc.html\" class=\"gray_link\">生命河灵粮堂</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"14720\" title=\"播放你的爱不离不弃\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"14720\" title=\"将你的爱不离不弃添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d2017d4c50078848b220');\" title=\"下载 你的爱不离不弃\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('你的爱不离不弃', '5162d2017d4c50078848b220');\" title=\"将你的爱不离不弃送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '你的爱不离不弃', '5162d2017d4c50078848b220');\" title=\"收藏你的爱不离不弃\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '你的爱不离不弃', '5162d2017d4c50078848b220');\" title=\"推荐你的爱不离不弃\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('你的爱不离不弃', '5162d2017d4c50078848b220');\" title=\"将你的爱不离不弃添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/13696.html\">如鹰展翅上腾</a> <span>(33.72%) <a href=\"/artist/soar-on-wings-like-an-eagle.html\" class=\"gray_link\">如鹰展翅上腾</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"13696\"></a> <img alt=\"如鹰展翅上腾\" src=\"//file.zanmeishi.com/album/2011/04/12/5162d1697d4c500788488066.jpg\" width=\"40\"> </span> <span><a href=\"/song/13696.html\">如鹰展翅上腾</a><br><a href=\"/album/soar-on-wings-like-an-eagle.html\" class=\"gray_link\">如鹰展翅上腾</a> By <a href=\"/artist/rolcc.html\" class=\"gray_link\">生命河灵粮堂</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"13696\" title=\"播放如鹰展翅上腾\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"13696\" title=\"将如鹰展翅上腾添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d1fc7d4c50078848ae34');\" title=\"下载 如鹰展翅上腾\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('如鹰展翅上腾', '5162d1fc7d4c50078848ae34');\" title=\"将如鹰展翅上腾送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '如鹰展翅上腾', '5162d1fc7d4c50078848ae34');\" title=\"收藏如鹰展翅上腾\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '如鹰展翅上腾', '5162d1fc7d4c50078848ae34');\" title=\"推荐如鹰展翅上腾\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('如鹰展翅上腾', '5162d1fc7d4c50078848ae34');\" title=\"将如鹰展翅上腾添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/15813.html\">恩典的记号</a> <span>(31.18%) <a href=\"/artist/blessed.html\" class=\"gray_link\">幸福</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"15813\"></a> <img alt=\"恩典的记号\" src=\"//file.zanmeishi.com/album/2012/10/17/5162d16c7d4c500788488105.jpg\" width=\"40\"> </span> <span><a href=\"/song/15813.html\">恩典的记号</a><br><a href=\"/album/blessed.html\" class=\"gray_link\">幸福</a> By <a href=\"/artist/amy-sand.html\" class=\"gray_link\">盛晓玫</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"15813\" title=\"播放恩典的记号\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"15813\" title=\"将恩典的记号添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d2077d4c50078848b664');\" title=\"下载 恩典的记号\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('恩典的记号', '5162d2077d4c50078848b664');\" title=\"将恩典的记号送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '恩典的记号', '5162d2077d4c50078848b664');\" title=\"收藏恩典的记号\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '恩典的记号', '5162d2077d4c50078848b664');\" title=\"推荐恩典的记号\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('恩典的记号', '5162d2077d4c50078848b664');\" title=\"将恩典的记号添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/10568.html\">心的归回</a> <span>(29.91%) <a href=\"/artist/the-true-love-is-calling.html\" class=\"gray_link\">心的归回</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"10568\"></a> <img alt=\"心的归回\" src=\"//file.zanmeishi.com/album/2008/02/22/5162d15c7d4c500788487d84.jpg\" width=\"40\"> </span> <span><a href=\"/song/10568.html\">心的归回</a><br><a href=\"/album/the-true-love-is-calling.html\" class=\"gray_link\">心的归回</a> By <a href=\"/artist/the-lily-of-wild.html\" class=\"gray_link\">野地百合</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"10568\" title=\"播放心的归回\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"10568\" title=\"将心的归回添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d1ec7d4c50078848a242');\" title=\"下载 心的归回\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('心的归回', '5162d1ec7d4c50078848a242');\" title=\"将心的归回送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '心的归回', '5162d1ec7d4c50078848a242');\" title=\"收藏心的归回\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '心的归回', '5162d1ec7d4c50078848a242');\" title=\"推荐心的归回\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('心的归回', '5162d1ec7d4c50078848a242');\" title=\"将心的归回添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/10284.html\">使命</a> <span>(28.52%) <a href=\"/artist/until-the-lord-jesus-comes.html\" class=\"gray_link\">直到主耶稣再来时候</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"10284\"></a> <img alt=\"使命\" src=\"//file.zanmeishi.com/album/2008/09/17/5162d1667d4c500788487f7e.jpg\" width=\"40\"> </span> <span><a href=\"/song/10284.html\">使命</a><br><a href=\"/album/until-the-lord-jesus-comes.html\" class=\"gray_link\">直到主耶稣再来时候</a> By <a href=\"/artist/ezra.html\" class=\"gray_link\">以斯拉</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"10284\" title=\"播放使命\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"10284\" title=\"将使命添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d1eb7d4c50078848a12a');\" title=\"下载 使命\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('使命', '5162d1eb7d4c50078848a12a');\" title=\"将使命送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '使命', '5162d1eb7d4c50078848a12a');\" title=\"收藏使命\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '使命', '5162d1eb7d4c50078848a12a');\" title=\"推荐使命\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('使命', '5162d1eb7d4c50078848a12a');\" title=\"将使命添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/28231.html\">一粒麦子</a> <span>(28.52%) <a href=\"/artist/unless-a-seed.html\" class=\"gray_link\">一粒麦子</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"28231\"></a> <img alt=\"一粒麦子\" src=\"//file.zanmeishi.com/album/2015/07/27/55b5d45a8062dc885b8b6282.jpg\" width=\"40\"> </span> <span><a href=\"/song/28231.html\">一粒麦子</a><br><a href=\"/album/unless-a-seed.html\" class=\"gray_link\">一粒麦子</a> By <a href=\"/artist/lamb-music.html\" class=\"gray_link\">小羊诗歌</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"28231\" title=\"播放一粒麦子\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"28231\" title=\"将一粒麦子添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('55b5d4f58062dc8a5b8b6342');\" title=\"下载 一粒麦子\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('一粒麦子', '55b5d4f58062dc8a5b8b6342');\" title=\"将一粒麦子送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '一粒麦子', '55b5d4f58062dc8a5b8b6342');\" title=\"收藏一粒麦子\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '一粒麦子', '55b5d4f58062dc8a5b8b6342');\" title=\"推荐一粒麦子\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('一粒麦子', '55b5d4f58062dc8a5b8b6342');\" title=\"将一粒麦子添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/15820.html\">活出爱</a> <span>(27.37%) <a href=\"/artist/blessed.html\" class=\"gray_link\">幸福</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"15820\"></a> <img alt=\"活出爱\" src=\"//file.zanmeishi.com/album/2012/10/17/5162d16c7d4c500788488105.jpg\" width=\"40\"> </span> <span><a href=\"/song/15820.html\">活出爱</a><br><a href=\"/album/blessed.html\" class=\"gray_link\">幸福</a> By <a href=\"/artist/amy-sand.html\" class=\"gray_link\">盛晓玫</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"15820\" title=\"播放活出爱\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"15820\" title=\"将活出爱添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d2077d4c50078848b66b');\" title=\"下载 活出爱\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('活出爱', '5162d2077d4c50078848b66b');\" title=\"将活出爱送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '活出爱', '5162d2077d4c50078848b66b');\" title=\"收藏活出爱\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '活出爱', '5162d2077d4c50078848b66b');\" title=\"推荐活出爱\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('活出爱', '5162d2077d4c50078848b66b');\" title=\"将活出爱添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/12.html\">恩曲不休</a> <span>(26.33%) <a href=\"/artist/the-true-love-is-calling.html\" class=\"gray_link\">心的归回</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"12\"></a> <img alt=\"恩曲不休\" src=\"//file.zanmeishi.com/album/2008/02/22/5162d15c7d4c500788487d84.jpg\" width=\"40\"> </span> <span><a href=\"/song/12.html\">恩曲不休</a><br><a href=\"/album/the-true-love-is-calling.html\" class=\"gray_link\">心的归回</a> By <a href=\"/artist/the-lily-of-wild.html\" class=\"gray_link\">野地百合</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"12\" title=\"播放恩曲不休\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"12\" title=\"将恩曲不休添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d1be7d4c50078848812d');\" title=\"下载 恩曲不休\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('恩曲不休', '5162d1be7d4c50078848812d');\" title=\"将恩曲不休送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '恩曲不休', '5162d1be7d4c50078848812d');\" title=\"收藏恩曲不休\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '恩曲不休', '5162d1be7d4c50078848812d');\" title=\"推荐恩曲不休\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('恩曲不休', '5162d1be7d4c50078848812d');\" title=\"将恩曲不休添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/6637.html\">有一天</a> <span>(25.64%) <a href=\"/artist/one-day.html\" class=\"gray_link\">有一天</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"6637\"></a> <img alt=\"有一天\" src=\"//file.zanmeishi.com/album/2008/02/22/5162d1637d4c500788487ed2.jpg\" width=\"40\"> </span> <span><a href=\"/song/6637.html\">有一天</a><br><a href=\"/album/one-day.html\" class=\"gray_link\">有一天</a> By <a href=\"/artist/amy-sand.html\" class=\"gray_link\">盛晓玫</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"6637\" title=\"播放有一天\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"6637\" title=\"将有一天添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('5162d1da7d4c5007884894b4');\" title=\"下载 有一天\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('有一天', '5162d1da7d4c5007884894b4');\" title=\"将有一天送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '有一天', '5162d1da7d4c5007884894b4');\" title=\"收藏有一天\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '有一天', '5162d1da7d4c5007884894b4');\" title=\"推荐有一天\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('有一天', '5162d1da7d4c5007884894b4');\" title=\"将有一天添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"       <dl> \n" +
				"        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
				"         <tbody>\n" +
				"          <tr> \n" +
				"           <td style=\"width:80%;\"> <h4><a href=\"/song/18358.html\">只愿得着你</a> <span>(25.40%) <a href=\"/artist/heaven-opened.html\" class=\"gray_link\">天开了</a></span></h4> <p> <span> <a class=\"play-icon\" title=\"播放这首歌曲\" href=\"javascript:;\" rel=\"18358\"></a> <img alt=\"只愿得着你\" src=\"//file.zanmeishi.com/album/2013/06/26/51ca8e2400769e1425000001.jpg\" width=\"40\"> </span> <span><a href=\"/song/18358.html\">只愿得着你</a><br><a href=\"/album/heaven-opened.html\" class=\"gray_link\">天开了</a> By <a href=\"/artist/rolcc.html\" class=\"gray_link\">生命河灵粮堂</a></span> </p> </td> \n" +
				"           <td class=\"btn\" style=\"padding-top:3px;\"> <a class=\"btn_play\" href=\"javascript:;\" rel=\"18358\" title=\"播放只愿得着你\"></a> <a class=\"btn_list\" href=\"javascript:;\" rel=\"18358\" title=\"将只愿得着你添加至当前播放列表\"></a> <a class=\"btn_down\" href=\"javascript:zms.down('51ca8e3800769e1425000010');\" title=\"下载 只愿得着你\"></a> <a class=\"btn_send\" href=\"javascript:zms.send('只愿得着你', '51ca8e3800769e1425000010');\" title=\"将只愿得着你送给朋友\"></a> <a class=\"btn_fav\" href=\"javascript:zms.fav(13, '只愿得着你', '51ca8e3800769e1425000010');\" title=\"收藏只愿得着你\"></a> <a class=\"btn_show\" href=\"javascript:zms.show(13, '只愿得着你', '51ca8e3800769e1425000010');\" title=\"推荐只愿得着你\"></a> <a class=\"btn_box\" href=\"javascript:zms.box('只愿得着你', '51ca8e3800769e1425000010');\" title=\"将只愿得着你添加至歌单\"></a> </td> \n" +
				"          </tr> \n" +
				"         </tbody>\n" +
				"        </table> \n" +
				"       </dl> \n" +
				"      </div> \n" +
				"      <h5 class=\"mt20\">歌曲\"真爱在呼唤\"的相关评论</h5> \n" +
				"      <div class=\"line\"></div> \n" +
				"      <!--评论--> \n" +
				"      <div id=\"conmment\"> \n" +
				"       <a name=\"comment\" id=\"comment\"></a> \n" +
				"       <script language=\"javascript\">\n" +
				"    function keyPress()\n" +
				"    {\n" +
				"        var textLength = document.getElementById(\"message\").value;\n" +
				"        var len; //记录剩余字符串的长度\n" +
				"        if (textLength.length >= 300) \n" +
				"        {\n" +
				"            document.getElementById(\"message\").value = textLength.substr(0, 300);\n" +
				"            len = 0;\n" +
				"        } else {\n" +
				"            len = 300 - textLength.length;\n" +
				"        }\n" +
				"        var show = \"你还可以输入\" + len + \"个字\";\n" +
				"        document.getElementById(\"textLeft\").innerText = show;\n" +
				"    }\n" +
				"\n" +
				"    function postComment(){\n" +
				"        $.ajax({\n" +
				"            url:'/ajax/comment',\n" +
				"            type:\"POST\",\n" +
				"            data:$('#form1').serialize(),\n" +
				"            success: function(data) {\n" +
				"                $(\"#result\").html(data);\n" +
				"                if(data == \"<span class='font_green'>恭喜您,评论发表成功</span>\"){\n" +
				"                $('#form1')[0].reset();\n" +
				"                setTimeout(function(){parent.location.reload();},2000);\n" +
				"                }\n" +
				"            }\n" +
				"        });\n" +
				"    };\n" +
				"\n" +
				"    $(document).ready(function() {\n" +
				"\n" +
				"        var track_click = 1;\n" +
				"\n" +
				"        var total_pages = 0;\n" +
				"        $('#comment-list').load(\"/ajax/comments?id=5162d1be7d4c500788488123&typeid=13\", {'page':track_click}, function() {track_click++;});\n" +
				"\n" +
				"        $(\".load_more\").click(function (e) {\n" +
				"\n" +
				"            $(this).hide();\n" +
				"            $('.animation_image').show();\n" +
				"\n" +
				"            if(track_click <= total_pages)\n" +
				"            {\n" +
				"                $.post('/ajax/comments?id=5162d1be7d4c500788488123&typeid=13',{'page': track_click}, function(data) {\n" +
				"\n" +
				"                    $(\".load_more\").show();\n" +
				"\n" +
				"                    $(\"#comment-list\").append(data);\n" +
				"                    $(\"html, body\").animate({scrollTop: $(\"#load_more_button\").offset().top}, 500);\n" +
				"                    $('.animation_image').hide();\n" +
				"                    track_click++;\n" +
				"\n" +
				"                }).fail(function(xhr, ajaxOptions, thrownError) {\n" +
				"                    alert(thrownError);\n" +
				"                    $(\".load_more\").show();\n" +
				"                    $('.animation_image').hide();\n" +
				"                });\n" +
				"\n" +
				"\n" +
				"                if(track_click >= total_pages)\n" +
				"                {\n" +
				"                    //$(\".load_more\").text(\"评论到此为止啦\");\n" +
				"                    $(\".load_more\").attr(\"class\", \"hide\");\n" +
				"                }\n" +
				"            }\n" +
				"\n" +
				"        });\n" +
				"    });\n" +
				"\n" +
				"</script> \n" +
				"       <div class=\"comment-login font_14 mt10 mb20\">\n" +
				"         您需要登录后才可以发表评论，请用赞美诗网帐号 \n" +
				"        <a href=\"https://www.zanmeishi.com/user/login\">登录</a> 或 \n" +
				"        <a href=\"https://www.zanmeishi.com/user/register\">免费注册</a> \n" +
				"       </div> \n" +
				"       <div class=\"c\"></div> \n" +
				"       <!-- Comment list BEGIN --> \n" +
				"       <div class=\"t_c mb10 mt10 font_14 font_gray\">\n" +
				"        您将会是第一位在这里发表评论的人哦:)\n" +
				"       </div> \n" +
				"       <div class=\"comment-list\" id=\"comment-list\"> \n" +
				"       </div> \n" +
				"       <div class=\"btn_more\" style=\"display:none;\"> \n" +
				"        <button class=\"load_more\" id=\"load_more_button\">加载更多评论</button> \n" +
				"        <div class=\"animation_image\" style=\"display:none;\">\n" +
				"         <img src=\"https://static.zanmeishi.com/images/ajax-loading.gif\"> 正在加载...\n" +
				"        </div> \n" +
				"       </div> \n" +
				"       <!-- Comment list END --> \n" +
				"      </div> \n" +
				"     </div> \n" +
				"     <div class=\"clear\"></div> \n" +
				"    </div> \n" +
				"    <script type=\"text/javascript\" src=\"https://static.zanmeishi.com/z-mod/??zanlrc/0.0.7/core.js,zanlrc/0.0.7/player.js,zanlrc/0.0.7/f11.js\"></script> \n" +
				"    <div id=\"fullscreen\"> \n" +
				"     <!--[if IE 6]>\n" +
				"    <iframe style=\"position:absolute;top:0;left:0;width:100%;height:100%;filter:alpha(opacity=0);\"></iframe>\n" +
				"    <![endif]--> \n" +
				"     <div id=\"box\"> \n" +
				"      <div id=\"current_time\"></div> \n" +
				"      <div id=\"lyric_editor\"></div> \n" +
				"     </div> \n" +
				"    </div> \n" +
				"    <script type=\"text/javascript\">\n" +
				"    //<![CDATA[\n" +
				"    $(document).ready(function () {\n" +
				"        zmsp.bind('#btn_play', 'play');\n" +
				"        zmsp.bind('#btn_list', 'list');\n" +
				"        zmsp.bindCollection('.fa-playall', 'album');\n" +
				"        zmsp.bind('.play-icon', 'play');\n" +
				"        zmsp.bind('a.btn_play', 'play');\n" +
				"        zmsp.bind('a.btn_list', 'list');\n" +
				"    });\n" +
				"    //]]>\n" +
				"</script> \n" +
				"    <div id=\"qrcode\" style=\"display: none;\"></div> \n" +
				"    <div id=\"sustain_qrcode\" style=\"display: none;\"></div> \n" +
				"    <script type=\"text/javascript\">\n" +
				"    new QRCode(document.getElementById(\"qrcode\"), {\n" +
				"        text: \"https://www.zanmeishi.com/song/1.html\",\n" +
				"        width: 128,\n" +
				"        height: 128,\n" +
				"        colorDark: \"#000000\",\n" +
				"        colorLight: \"#ffffff\",\n" +
				"        correctLevel: QRCode.CorrectLevel.H\n" +
				"    });\n" +
				"\n" +
				"    new QRCode(document.getElementById(\"sustain_qrcode\"), {\n" +
				"        text: \"https://www.zanmeishi.com/song/1.html?sustain\",\n" +
				"        width: 128,\n" +
				"        height: 128,\n" +
				"        colorDark: \"#000000\",\n" +
				"        colorLight: \"#ffffff\",\n" +
				"        correctLevel: QRCode.CorrectLevel.H\n" +
				"    });\n" +
				"</script> \n" +
				"   </div> \n" +
				"   <div class=\"footer\"> \n" +
				"    <div class=\"bom\"> \n" +
				"     <dl> \n" +
				"      <dt>\n" +
				"       赞美诗网\n" +
				"      </dt> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/site/about.html\">关于我们</a> \n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/site/contact.html\">联系我们</a> \n" +
				"      </dd> \n" +
				"      <!--                    <dd><a href=\"--> \n" +
				"      <!--\" style=\"color:#ff0000;\">代祷与支持</a></dd>--> \n" +
				"      <!--dd><a href=\"http://team.zanmeishi.com/recruiting/\" style=\"color:#ff0000;\">同工招聘</a></dd--> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/site/service.html\">服务协议</a> \n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/site/privacy.html\">隐私政策</a> \n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/site/copyright.html\">权利声明</a> \n" +
				"      </dd> \n" +
				"      <dd style=\"display:none\">\n" +
				"       <a href=\"http://team.zanmeishi.com/join-us/\">加入我们</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/site/release.html\" style=\"color: #ee834e;\">诗歌上架</a>\n" +
				"      </dd> \n" +
				"      <!--                    <dd><a target=\"blank\" href=\"http://team.zanmeishi.com\">ZMS Team</a></dd>--> \n" +
				"      <!--<dd><a href=\"https://www.zanmeishi.com/site/partner.html\">合作伙伴</a></dd>--> \n" +
				"     </dl> \n" +
				"     <dl> \n" +
				"      <dt>\n" +
				"       赞美诗歌\n" +
				"      </dt> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/artist/\">歌手库</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/album/\">专辑库</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/song/\">歌曲库</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/video/\">视频&amp;MV</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/radio/\">电台</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/tab/\">歌谱</a>\n" +
				"      </dd> \n" +
				"     </dl> \n" +
				"     <dl> \n" +
				"      <dt>\n" +
				"       排行榜\n" +
				"      </dt> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/top/artist\">歌手排行</a> \n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/top/album\">专辑排行</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/top/song\">歌曲排行</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/top/tab\">歌谱排行</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/top/\">更多...</a>\n" +
				"      </dd> \n" +
				"     </dl> \n" +
				"     <dl> \n" +
				"      <dt>\n" +
				"       其它...\n" +
				"      </dt> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/worshipschool/\">敬拜学校</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"http://www.xueshengshi.com\" target=\"_blank\">学圣诗</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/holybible/\">听圣经</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/client/\">客户端</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"https://www.zanmeishi.com/help/\">帮助中心</a>\n" +
				"      </dd> \n" +
				"      <dd>\n" +
				"       <a href=\"javascript:zms.feedback();\">意见或建议</a>\n" +
				"      </dd> \n" +
				"     </dl> \n" +
				"     <dl> \n" +
				"      <dt>\n" +
				"       赞美诗网客户端\n" +
				"      </dt> \n" +
				"      <dd style=\"height:68px;\"> \n" +
				"       <div class=\"l mr5\">\n" +
				"        <a href=\"https://www.zanmeishi.com/client/\"><img alt=\"apps\" height=\"60\" src=\"https://static.zanmeishi.com/images/app-qrcode.png\"></a> \n" +
				"       </div> \n" +
				"       <strong>苹果或安卓</strong>\n" +
				"       <br>手机扫码安装或\n" +
				"       <br>点击下载安装 \n" +
				"      </dd> \n" +
				"      <dd> \n" +
				"       <span class=\"fa-stack fa-md\"> <i class=\"fas fa-circle fa-stack-2x\" style=\"color:#a3c444\"></i> <i class=\"fab fa-android fa-stack-1x fa-inverse\"></i> </span> \n" +
				"       <a href=\"https://static.zanmeishi.com/apps/zan5.0.5.apk\">赞！For Android</a> \n" +
				"      </dd> \n" +
				"      <dd class=\"pt5\"> \n" +
				"       <span class=\"fa-stack fa-md\"> <i class=\"fas fa-circle fa-stack-2x\" style=\"color:#3f474d\"></i> <i class=\"fab fa-apple fa-stack-1x fa-inverse\"></i> </span> \n" +
				"       <a href=\"https://itunes.apple.com/cn/app/zan!/id597860018?mt=8\">赞！For iOS</a>\n" +
				"      </dd> \n" +
				"      <dd class=\"pt5\"> \n" +
				"       <span class=\"fa-stack fa-md\"> <i class=\"fas fa-circle fa-stack-2x\" style=\"color:#00b0ec\"></i> <i class=\"fab fa-windows fa-stack-1x fa-inverse\"></i> </span> \n" +
				"       <a href=\"http://www.zanplayer.com/dl.php\">赞！For Windows</a>\n" +
				"      </dd> \n" +
				"     </dl> \n" +
				"     <div class=\"clear\"></div> \n" +
				"     <div class=\"copyright\">\n" +
				"       Copyright © 2008 - 2021 Zanmeishi.Com | \n" +
				"      <a href=\"http://beian.miit.gov.cn/\" target=\"_blank\">沪ICP备12022286号</a> |&nbsp;&nbsp;赞美诗网QQ交流群：207108618(请注明赞美诗网)&nbsp;&nbsp;\n" +
				"      <div class=\"r\">\n" +
				"       关注我们：\n" +
				"       <a class=\"thumbnail\" href=\"#\"><img alt=\"wechat\" src=\"https://static.zanmeishi.com/images/icons/wechat-48px.png\" width=\"24\" height=\"24\"><span><img alt=\"wechat\" src=\"https://static.zanmeishi.com/images/wechat-zms.jpg\" height=\"80\"></span></a> \n" +
				"      </div> \n" +
				"      <div>\n" +
				"       赞美诗网播放器使用最新HTML5技术开发，不支持8.0以及8.0以下的Internet Explorer浏览器。\n" +
				"      </div> \n" +
				"      <div>\n" +
				"       为获得最佳体验效果，我们推荐您使用最新版本 \n" +
				"       <a href=\"https://www.google.cn/chrome/\" target=\"_blank\">Google Chrome（谷歌浏览器）</a> 或 \n" +
				"       <a href=\"http://www.firefox.com.cn/\" target=\"_blank\">Firefox（火狐浏览器）</a> 访问本站。 \n" +
				"      </div> \n" +
				"     </div> \n" +
				"     <a name=\"gobottom\">&nbsp;</a> \n" +
				"    </div> \n" +
				"   </div> \n" +
				"  </div> \n" +
				"  <div id=\"gototop\"> \n" +
				"   <div id=\"zms_wechat\" class=\"zms_wechat\">\n" +
				"    <a href=\"javascript:void(0)\">微信公众号 \n" +
				"     <div class=\"zms_wechath\"></div> </a>\n" +
				"   </div> \n" +
				"   <div id=\"zms_client\" class=\"zms_client\">\n" +
				"    <a href=\"javascript:void(0)\">客户端 \n" +
				"     <div class=\"zms_clienth\"></div> </a>\n" +
				"   </div> \n" +
				"   <div id=\"zms_feedback\">\n" +
				"    <a href=\"javascript:zms.feedback();\" class=\"zms_feedback\">反馈</a>\n" +
				"   </div> \n" +
				"   <a id=\"zms_top\" href=\"javascript:void(0)\"></a> \n" +
				"  </div> \n" +
				"  <script type=\"text/javascript\">\n" +
				"    var _bdhmProtocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");\n" +
				"    document.write(unescape(\"%3Cscript src='\" + _bdhmProtocol + \"hm.baidu.com/h.js%3F230a799cf38f1165f8b3c42637fcb547' type='text/javascript'%3E%3C/script%3E\"));\n" +
				"</script> \n" +
				"  <script type=\"text/javascript\">\n" +
				"    function b() {\n" +
				"        h = $(window).height(),\n" +
				"            t = $(document).scrollTop(),\n" +
				"            t > h ? $(\"#zms_top\").show() : $(\"#zms_top\").hide()\n" +
				"    }\n" +
				"\n" +
				"    $(document).ready(function () {\n" +
				"        b(),\n" +
				"            $(\"#zms_top\").click(function () {\n" +
				"                $(document).scrollTop(0)\n" +
				"            })\n" +
				"    }),\n" +
				"        $(window).scroll(function () {\n" +
				"            b()\n" +
				"        });\n" +
				"</script>   \n" +
				" </body>\n" +
				"</html>";

		int index = s.indexOf(".mp3");
		int end = s.indexOf("</a>", index);
		s = s.substring(index, end);
		s = s.substring(s.indexOf(">") + 1);
		System.out.println("");

	}

	@Test
	public void download() throws IOException {
		//https://play.zanmeishi.com/song/p/
		//https://www.zanmeishi.com/song/20123.html
		String photoRealUrl = "https://play.zanmeishi.com/song/p/";//   219318/1.jpg";   //文件URL地址
		String htmlUrl = "https://www.zanmeishi.com/song/";

		String filemusicpath = "D:\\赞-音频\\music";      //保存目录
		String fileHtmlPath = "D:\\赞-音频\\html";      //保存目录
		int number = 28000;
		for (int j = 281; j < 20100; j++) {
			for (int i = 1; i < 101; i++) {
				try {
					number++;
					try {
						down(j, i, photoRealUrl, htmlUrl, filemusicpath, fileHtmlPath, number);
					} catch (Exception e) {
						System.out.println("\n第" + number + "首第一次下载失败");
						down(j, i, photoRealUrl, htmlUrl, filemusicpath, fileHtmlPath, number);
					}
				} catch (Exception e) {
					System.out.println("\n第" + number + "首第二次下载失败");
				}
			}

		}
	}

	void down(int j, int i, String photoRealUrl, String htmlUrl, String filemusicpath, String fileHtmlPath
			, int number) throws Exception {
		int numMusi = j * 100 - 100 + i;
		String musicUrl = photoRealUrl + numMusi + ".mp3";
		String htmlDocumentUrl = htmlUrl + numMusi + ".html";
		Document doc = Jsoup.parse(new URL(htmlDocumentUrl), (4000));
		String html = doc.toString();
		String name = "";
		String s = html;
		int index = s.indexOf(".mp3");
		int end = s.indexOf("</a>", index);
		s = s.substring(index, end);
		name = s.substring(s.indexOf(">") + 1);
		String url = musicUrl;
		//创建不同的文件夹目录
		File file = new File(filemusicpath);
		//判断文件夹是否存在
		if (!file.exists()) {
			//如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}

		File file2 = new File(fileHtmlPath);
		//判断文件夹是否存在
		if (!file2.exists()) {
			//如果文件夹不存在，则创建新的的文件夹
			file2.mkdirs();
		}

		if (!fileHtmlPath.endsWith("/")) {
			fileHtmlPath += "/";
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		// 建立链接
		URL httpUrl = new URL(url);
		conn = (HttpURLConnection) httpUrl.openConnection();
		//以Post方式提交表单，默认get方式
		conn.setDoInput(true);
		conn.setDoOutput(true);
		// post方式不能使用缓存
		conn.setUseCaches(false);
		//连接指定的资源
		conn.connect();
		conn.setConnectTimeout(5000);
		//获取网络输入流
		inputStream = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		//判断文件的保存路径后面是否以/结尾
		if (!filemusicpath.endsWith("/")) {
			filemusicpath += "/";
		}
		//写入到文件（注意文件保存路径的后面一定要加上文件的名称）
		int i_s = j * 100 - 99;
		int i_e = j * 100;
		String targetMusicDir = filemusicpath + i_s + "-" + i_e;
		makeDir(targetMusicDir);
		String htmlPre = fileHtmlPath + i_s + "-" + i_e;
		String musicPre = filemusicpath + i_s + "-" + i_e + "/" + name + "_" + number;
		fileOut = new FileOutputStream(musicPre + ".mp3");
		BufferedOutputStream bos = new BufferedOutputStream(fileOut);

		byte[] buf = new byte[4096];
		int length = bis.read(buf);
		//保存文件
		while (length != -1) {
			bos.write(buf, 0, length);
			length = bis.read(buf);
		}
		bos.close();
		bis.close();
		conn.disconnect();

		makeDir(htmlPre);
		WriteStringToFile(htmlPre + "/" + name + "_" + number + ".html", html);
		System.out.println("第" + number + "首" + name + "音频下载成功");
	}

	public void WriteStringToFile(String filePath, String s) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(s.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void makeDir(String path) {
		File file = new File(path);
		//如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在 创建！");
			file.mkdir();
		} else {
		}
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
