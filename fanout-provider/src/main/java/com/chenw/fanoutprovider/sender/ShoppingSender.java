package com.chenw.fanoutprovider.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: ShoppingSender
 * @description: 推送消息
 * @date 2019/7/24 10:03
 */
@Component
public class ShoppingSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${mq.config.exchange}")
	private String exchange;

	/**
	 * 推送消息
	 * @param msg
	 */
	public void sender(String msg){
		this.amqpTemplate.convertAndSend(this.exchange,null,msg);
	}
}
