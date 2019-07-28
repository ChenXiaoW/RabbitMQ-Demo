package com.chenw.directprovider.Sender;

import com.alibaba.fastjson.JSON;
import com.chenw.directprovider.model.LogMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class InfoMsgSender {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RabbitTemplate rabbitTemplate;


	/** 交换机名称 */
	@Value("${mq.config.exchange}")
	private String exchange;
	/** 路由键 */
	@Value("${mq.config.queue.msg.routingkey}")
	private String routingKey;

	/**
	 * 发送消息
	 * @param msg
	 */
	public void sender(LogMsg msg){
		CorrelationData correlationData = new CorrelationData(msg.getMessage());

		String  json = JSON.toJSONString(msg);
		System.out.println("[消息发送]>>>"+json);
		this.rabbitTemplate.convertAndSend(
				exchange, //交换器
				routingKey, //路由key
				json,    //消息
				correlationData //消息唯一id
		);
	}
}
