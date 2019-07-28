package com.chenw.directconsumer.Receiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: InfoReceiver
 * @description: info队列消息消费者
 * @date 2019/7/23 10:05
 */

/**
 * @RabbitListener - 消息监听器
 *      bindings - 绑定队列
 * @QueueBinding
 *      value - 绑定队列的名称
 * @Queue - 队列信息
 * value：配置队列名称 ;autoDelete：是否是一个可删除的临时队列
 *      exchange - 配置交换器
 * @Exchange - 交换器信息
 * value ：配置交换器 ；type ：交换器类型
 *      key - 指定路由键
 *
 */
@Component
@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(value = "${mq.config.queue.info.name}",autoDelete = "true"),
				exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.DIRECT),
				key = "${mq.config.queue.info.routingkey}"
		)
)
public class InfoReceiver {
	/**
	 * 接收消息的方法
	 *
	 * 当 @RabbitListener 注解贴在类上面时，需要通过 @RabbitHandler 注解指定消费消息的方法
	 */
	@RabbitHandler
	public void process(String msg){
		System.out.println("[Info消息 - 消费者]>>>"+msg);
	}
}
