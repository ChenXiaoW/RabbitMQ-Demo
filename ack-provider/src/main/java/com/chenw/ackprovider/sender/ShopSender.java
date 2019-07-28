package com.chenw.ackprovider.sender;

import com.alibaba.fastjson.JSON;
import com.chenw.ackprovider.config.ContrimConfig;
import com.chenw.ackprovider.config.ReturnMessageConfig;
import com.chenw.ackprovider.model.ShopMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: ShopSender
 * @description: TODO
 * @date 2019/7/25 16:59
 */
@Component
public class ShopSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	/** 交换机名称 */
	@Value("${mq.config.exchange}")
	private String exchange;
	/** 路由键 */
	@Value("${mq.config.queue.shop.routingkey}")
	private String routingKey;

	@Autowired
	ContrimConfig contrimConfig;

	@Autowired
	ReturnMessageConfig returnMessageConfig;

	public void sender(ShopMessage shopMessage){
		//设置回调方法
		this.rabbitTemplate.setConfirmCallback(contrimConfig);
		//设置Exchange路由到Queue失败的回调方法
		this.rabbitTemplate.setReturnCallback(returnMessageConfig);
		CorrelationData correlationData = new CorrelationData(shopMessage.getMessageId());
		String msg = JSON.toJSONString(shopMessage);
		this.rabbitTemplate.convertAndSend(exchange,routingKey,msg,correlationData);
	}
}
