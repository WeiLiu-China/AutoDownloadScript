package com.爬虫.fuyinTV.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName Four_DetailBean.java
 * @Description TODO
 * @createTime 2021/05/16 17:44:25
 */
@Data
public class Four_DetailBean {
	private Boolean doc;
	private int movid;
	private Boolean mp3;
	private Boolean mp4;
	private String referer;
	private String share_url;
	private String sort_title;
	private String title;
	private String url_1;
	private String url_2;
	private String url_5;
	private String url_6;
	private int urlid;

	private List<Server> server;

	@Data
	public static class Server {
		private String cdn_mp4db_url;
		private String ticdn_referertle;
		private String channel_id;
		private String channelid_prefix;
		private String db_cdn;
		private String db_cdn_id;
		private String m3u8;
		private String mp3db_func;
		private String mp3db_url;
		private String mp3xz_func;
		private String mp3xz_url;
		private String mp4db_func;
		private String mp4db_url;
		private String mp4xz_func;
		private String mp4xz_url;
		private int rewrite_segment_id;
		private String ws_signaler;
		private String xz_cdn;
		private String xz_cdn_id;
	}
}
