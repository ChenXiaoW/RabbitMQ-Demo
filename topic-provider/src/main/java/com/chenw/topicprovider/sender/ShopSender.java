package com.chenw.topicprovider.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: ChenWei
 * @description: 商品 - 消息生产者
 * @create: 2019/7/23 - 22:51
 **/
@Component
public class ShopSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${mq.config.exchange}")
	private String exchange;

	@Value("${routing.shop.info}")
	private String shopInfo;
	@Value("${routing.shop.warn}")
	private String shopWarn;
	@Value("${routing.shop.error}")
	private String shopError;
	@Value("${routing.shop.debug}")
	private String shopDebug;

	/**
	 * 发送消息到指定交换机
	 * @param msg
	 */
	public void shopSender(String msg){
		this.amqpTemplate.convertAndSend(this.exchange,shopInfo,"[shop.info]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,shopError,"[shop.error]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,shopDebug,"[shop.debug]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,shopWarn,"[shop.warn]>>ShopSender>"+msg);
	}
}
