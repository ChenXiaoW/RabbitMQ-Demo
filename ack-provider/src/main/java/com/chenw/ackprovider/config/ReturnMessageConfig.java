package com.chenw.ackprovider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: ReturnMessageConfig
 * @description: TODO
 * @date 2019/7/25 17:37
 */
@Component
public class ReturnMessageConfig implements RabbitTemplate.ReturnCallback {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
	 * @param message - 消息主体
	 * @param replyCode - 回复代码
	 * @param replyText - 描述
	 * @param exchange - 交换器
	 * @param routingKey - 路由键
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("------------Exchange路由到Queue失败----------------");
		System.out.println("消息主体 message : "+message);
		System.out.println("回复代码 message : "+replyCode);
		System.out.println("描述："+replyText);
		System.out.println("消息使用的交换器 exchange : "+exchange);
		System.out.println("消息使用的路由键 routing : "+routingKey);
	}
}
