package com.chenw.topicprovider.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenw
 * @title: SenderTest
 * @description: 消息发送测试
 * @date 2019/7/24 8:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

	@Autowired
	OtherSender otherSender;
	@Autowired
	ShopSender shopSender;
	@Autowired
	UserSender userSender;
	@Test
	public void sender_test() throws Exception{
		while (true){
			Thread.sleep(1000);
			this.userSender.userSender("usermsg");
			this.shopSender.shopSender("shopmsg");
		}
	}
}
