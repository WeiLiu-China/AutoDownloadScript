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
public class CommonThread extends Thread {

	public CommonThread() {
	}


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("进行中的线程 :-----------------\n");

			String[] split = threadNameList.toString().split(",");
			for (String name : split) {
				System.out.println(name + "\n");
			}
			System.out.println("\n");

		}
	}

}
