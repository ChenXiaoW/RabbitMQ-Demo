package com.chenw.ackprovider.sender;

import com.chenw.ackprovider.model.ShopMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author chenw
 * @title: ShopSenderTest
 * @description: TODO
 * @date 2019/7/25 17:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSenderTest {

	@Autowired
	ShopSender shopSender;

	@Test
	public void sender_ack()throws Exception{
		int flag = 0;
		while (true){
			flag++;
			Thread.sleep(1000);
			ShopMessage shopMessage = new ShopMessage();
			shopMessage.setId(flag);
			shopMessage.setMessageId(UUID.randomUUID().toString());
			shopMessage.setName("SHOP");
			this.shopSender.sender(shopMessage);
			if(flag == 10){
				return;
			}
		}
	}

}