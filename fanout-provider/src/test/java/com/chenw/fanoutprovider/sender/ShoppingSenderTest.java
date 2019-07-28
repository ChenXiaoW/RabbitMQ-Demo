package com.chenw.fanoutprovider.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author chenw
 * @title: ShoppingSenderTest
 * @description: TODO
 * @date 2019/7/24 10:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingSenderTest {

	@Autowired
	private ShoppingSender shoppingSender;

	@Test
	public void sender() throws Exception {
		int flag = 0;
		while (true){
			flag++;
			Thread.sleep(5000);
			this.shoppingSender.sender("消息>>>"+flag);
		}
	}
}