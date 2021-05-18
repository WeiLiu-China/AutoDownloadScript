package com.example.mybatisplus;

import lombok.SneakyThrows;

import static com.example.mybatisplus.Constant.threadNameList;
import static com.example.util.FileUtil.download;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName DownloadMyThread.java
 * @Description TODO
 * @createTime 2021/05/18 08:41:23
 */
public class DownloadThread extends Thread {

	/**
	 * D:\爬虫\天上灵粮\初信造就\陈希曾\2002年12月06-18日 也谈雅比斯的祷告 中英双语版（陈希曾）
	 */
	private String path;

	/**
	 * name  :  2002年12月06-18日 也谈雅比斯的祷告 中英双语版（陈希曾）
	 */
	private String name;

	/**
	 * type: .mp3  /.mp4
	 */
	private String type;

	/**
	 * www.***.mp3
	 */
	private String url;

	/**
	 * app:天上灵粮
	 */
	private String app;

	public DownloadThread(String app, String path, int i, String name, String type, String url) {
		this.app = app;
		this.path = path;
		this.name = "/" + i + "_" + name + type;
		this.url = url;
	}


	@SneakyThrows
	@Override
	public void run() {
		String inputName = "Thread--" + app + "-" + path + name +
				"\n-------------------------------------\n  url" + url;
		threadNameList.add(inputName);
		Thread.currentThread().setName(inputName);

		download(path, name, url);
		threadNameList.remove(inputName);
	}

}
