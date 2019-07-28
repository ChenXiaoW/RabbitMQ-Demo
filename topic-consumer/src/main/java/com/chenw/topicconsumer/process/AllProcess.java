package com.chenw.topicconsumer.process;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: ChenWei
 * @description: 所有 - 消息消费者
 * @create: 2019/7/23 - 23:33
 **/
@Component
@RabbitListener(
		bindings = @QueueBinding(
		value = @Queue(value = "${mq.config.queue.logs.name}",autoDelete = "true"),
		exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.TOPIC),
		key = "${mq.config.queue.logs.key}"
	)
)
public class AllProcess {
	@RabbitHandler
	public void process(String msg){
		System.out.println("[all消费者]>>>"+msg);
	}
}
