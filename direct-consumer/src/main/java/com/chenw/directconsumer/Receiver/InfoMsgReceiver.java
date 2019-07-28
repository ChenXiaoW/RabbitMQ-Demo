package com.chenw.directconsumer.Receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenw.directconsumer.model.LogMsg;
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
 * 有ACK机制，手动签收模式
 */
@Component
@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(value = "${mq.config.queue.msg.name}",autoDelete = "false"),
				exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.DIRECT),
				key = "${mq.config.queue.msg.routingkey}"
		)
)
public class InfoMsgReceiver {

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
		LogMsg logMsg = JSON.parseObject(json, LogMsg.class);
		//消费者操作
		System.out.println("[消费消息ID]>>>"+logMsg.getId());
		Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
		//模拟消费者异常
		/*if(logMsg.getId() == 5){
			log.error("消费者异常");
			throw new RuntimeException();
		}else {*/
		//手工签收，告诉MQ该消息处理完毕
		channel.basicAck(deliveryTag,false);
		//}
	}
}