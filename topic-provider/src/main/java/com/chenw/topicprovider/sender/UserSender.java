package com.chenw.topicprovider.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: ChenWei
 * @description: 用户 - 消息生产者
 * @create: 2019/7/23 - 22:51
 **/
@Component
public class UserSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${mq.config.exchange}")
	private String exchange;

	/** 路由名称 */
	@Value("${routing.user.info}")
	private String userInfo;
	@Value("${routing.user.warn}")
	private String userWarn;
	@Value("${routing.user.debug}")
	private String userDebug;
	@Value("${routing.user.error}")
	private String userError;

	/**
	 * 消息发送者
	 * @param msg
	 */
	public void userSender(String msg){
		this.amqpTemplate.convertAndSend(this.exchange,userInfo,"[user.info]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,userError,"[user.error]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,userDebug,"[user.debug]>>>"+msg);
		this.amqpTemplate.convertAndSend(this.exchange,userWarn,"[user.warn]>>>"+msg);
	}
}
