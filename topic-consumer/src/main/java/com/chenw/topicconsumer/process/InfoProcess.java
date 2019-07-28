package com.chenw.topicconsumer.process;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: ChenWei
 * @description: info - 消息消费
 * @create: 2019/7/23 - 23:18
 **/
@Component
@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "${mq.config.queue.info.name}",autoDelete = "true"),
		exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.TOPIC),
		key = "${mq.config.queue.info.key}"
	)
)
public class InfoProcess {

	@RabbitHandler
	public void process(String msg){
		System.out.println("[info消费者]>>>"+msg);
	}
}
