package com.chenw.ackconsumer.process;

import com.alibaba.fastjson.JSON;
import com.chenw.ackconsumer.model.ShopMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chenw
 * @title: ShopProcess
 * @description: TODO
 * @date 2019/7/25 16:19
 */
@Component
@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(value = "${mq.config.queue.shop.name}",autoDelete = "false"),
				exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.DIRECT),
				key = "${mq.config.queue.shop.routingkey}"
		)
)
public class ShopProcess {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 接收消息的方法
	 * @Payload
	 * @Headers - 注解参数为了访问所有头信息，必须能指定为java.util.Map
	 * @Header-注解方法参数可 提取一个特定头部值，包括标准的AMQP头.
	 * Channel - 手工签收模式必须有信道
	 */
	@RabbitHandler
	public void process(@Payload String json, @Headers Map<String,Object> headers, Channel channel)throws Exception{
		ShopMessage msg = JSON.parseObject(json, ShopMessage.class);
		//消费者操作
		System.out.println("[消费消息ID]>>>"+msg.getId());
		Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
		//手工签收，告诉MQ该消息处理完毕
		channel.basicAck(deliveryTag,false);
	}
}