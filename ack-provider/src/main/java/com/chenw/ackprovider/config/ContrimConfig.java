package com.chenw.ackprovider.config;

import com.rabbitmq.client.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author chenw
 * @title: ContrimConfig
 * @description: TODO
 * @date 2019/7/25 16:49
 */
@Component
public class ContrimConfig implements RabbitTemplate.ConfirmCallback {

	/**
	 *
	 * @param correlationData - 消息唯一标识
	 * @param ack - 确认结果
	 * @param cause - 失败原因
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("-------------消息发送至Exchange后的回调---------------");
		System.out.println("消息唯一标识："+correlationData);
		System.out.println("确认结果："+ack);
		System.out.println("失败原因："+cause);
	}
}
