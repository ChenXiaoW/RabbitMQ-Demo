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
public class OtherSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${mq.config.exchange}")
	private String exchange;

	@Value("${routing.other.info}")
	private String otherInfo;
	@Value("${routing.other.warn}")
	private String otherWarn;
	@Value("${routing.other.error}")
	private String otherError;
	@Value("${routing.other.debug}")
	private String otherDebug;
	/**
	 * 消息发送者
	 * @param msg
	 */
	public void otherSender(String msg){
		this.amqpTemplate.convertAndSend(this.exchange,otherInfo,"[other.info]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,otherError,"[other.error]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,otherDebug,"[other.debug]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,otherWarn,"[other.warn]>>ShopSender>"+msg);
	}
}
