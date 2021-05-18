package com.example.pmmc;

import link.message.client.IMConfig;
import link.message.client.MessageClientV3;
import link.message.client.SendMessageResult;
import link.message.client.auth.AccessTokenProvider;
import link.message.client.auth.AccessTokenProviderImpl;
import link.message.client.content.MessageContent;
import link.message.client.content.TextMessageContent;
import link.message.client.content.complex.ComplexMessageContent;
import link.message.client.content.complex.ComplexMessageContentItem;
import link.message.client.content.complex.ComplexMessageType;
import link.message.client.content.complex.linear.*;
import link.message.client.messager.PersonMessageReceiver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName MessageClientV3Test.java
 * @Description TODO
 * @createTime 2020/09/17 14:32:56
 */
public class MessageClientV3Test {

	protected MessageClientV3 messageClient;

	@Before
	public void setUp() throws Exception {
		String imUrl = "http://10.201.76.141:10082";
		IMConfig config = new IMConfig(imUrl);

		String tokenUrl = "http://10.201.76.141:8088/sso/oauth2/token";
		String clientId = "clientId";
		String clientSecret = "clientSecret";
		AccessTokenProvider tp = new AccessTokenProviderImpl(tokenUrl, clientId, clientSecret);

		messageClient = new MessageClientV3(config, tp);
	}

	@Test
	public void testSendTextMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("复杂文本消息概要", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这里是复杂文本消息的内容");
		complexTextMessage.addMessageContentItem(contentItem);

		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
	}

	@Test
	public void test() {
		// 构造简单文本消息
		ComplexMessageContent complexMessageContent = new ComplexMessageContent("Grid线性消息概要", ComplexMessageType.LINEAR);

		TitleLinearComplexMessageContentItem title = new TitleLinearComplexMessageContentItem("Grid标题");
		TextLinearComplexMessageContentItem brief  = new TextLinearComplexMessageContentItem("前言提示");
		TextLinearComplexMessageContentItem  hint  = new TextLinearComplexMessageContentItem("Grid提示");

		MessageContent msg = new TextMessageContent("");

		GridContent gridContent = new GridContent(2);
		gridContent.addColumn(new Column("姓名", Alignment.center, 40));
		gridContent.addColumn(new Column("部门", Alignment.left, 60));
		gridContent.addData(new String[] {"张三","云应用平台部"});
		gridContent.addData(new String[] {"李四","云应用体验部"});
		gridContent.addData(new String[] {"王五","云应用平台部"});
		gridContent.addData(new String[] {"孙六","云应用体验部"});
		GridLinearComplexMessageContentItem grid = new GridLinearComplexMessageContentItem(gridContent);

		//显示顺序是添加到容器的顺序
		complexMessageContent.addMessageContentItem(title)
				.addMessageContentItem(brief)
				.addMessageContentItem(grid)
				.addMessageContentItem(hint);

		SendMessageResult result = messageClient.sendSingleMessage(complexMessageContent, new PersonMessageReceiver("zhongt", "钟涛"));
		Assert.assertEquals(true, result.isSuccess());
	}

}

