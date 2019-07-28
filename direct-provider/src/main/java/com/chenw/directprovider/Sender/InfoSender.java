package com.chenw.directprovider.Sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: InfoSender
 * @description: Info消息发送者
 * @date 2019/7/23 14:53
 */
@Component
public class InfoSender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	/** 交换机名称 */
	@Value("${mq.config.exchange}")
	private String exchange;
	/** 路由键 */
	@Value("${mq.config.queue.info.routingkey}")
	private String routingKey;

	public void senderInfoMsg(String msg){
		/**
		 * 向消息队列发送消息
		 * 参数1：交换机名称
		 * 参数2：路由键
		 * 参数3：消息
		 */
		this.amqpTemplate.convertAndSend(this.exchange,this.routingKey,msg);
	}
}
