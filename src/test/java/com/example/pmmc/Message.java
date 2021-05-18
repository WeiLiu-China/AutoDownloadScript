package com.example.pmmc;

import link.message.client.MessageClient;
import link.message.client.SendMessageResult;
import link.message.client.content.complex.ComplexMessageContent;
import link.message.client.content.complex.ComplexMessageContentItem;
import link.message.client.content.complex.ComplexMessageType;
import link.message.client.messager.PersonMessageReceiver;
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
public class Message {

	protected MessageClient messageClient;

	@Before
	public void setUp() throws Exception {
		messageClient = new MessageClient(
				"http://linktest.bingocc.cc:10082/svrnum/",
				"3ecc8782-d1bd-45dc-88a5-b65d83dc5c30",
				"1e5bc77ad12f496b8f9d038291de3680");
	}

	@Test
	public void testSendTextMessage() {
		ComplexMessageContent complexTextMessage = new ComplexMessageContent("复杂文本消息概要", ComplexMessageType.TEXT);
		ComplexMessageContentItem contentItem = new ComplexMessageContentItem("这里是复杂文本消息的内容");
		complexTextMessage.addMessageContentItem(contentItem);

		SendMessageResult result = messageClient.sendSingleMessage(complexTextMessage, new PersonMessageReceiver("zhongt", "钟涛"));
	}

}

