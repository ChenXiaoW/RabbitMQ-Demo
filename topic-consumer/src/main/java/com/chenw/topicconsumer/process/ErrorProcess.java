package com.chenw.topicconsumer.process;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: ChenWei
 * @description: error - 消息消费
 * @create: 2019/7/23 - 23:31
 **/
@Component
@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "${mq.config.queue.error.name}",autoDelete = "true"),
		exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.TOPIC),
		key = "${mq.config.queue.error.key}"
	)
)
public class ErrorProcess {


	@RabbitHandler
	public void process(String msg){
		System.out.println("[error消费者]>>>"+msg);
	}
}
