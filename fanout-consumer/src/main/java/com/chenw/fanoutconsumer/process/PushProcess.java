package com.chenw.fanoutconsumer.process;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: PushProcess
 * @description: push 服务消费者
 * @date 2019/7/24 9:57
 */
@Component
@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(value = "${mq.config.queue.push.name}",autoDelete = "false"),
				exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.FANOUT)
		)
)
public class PushProcess {

	@RabbitHandler
	public void process(String msg){
		System.out.println("[push 服务]>>>"+msg);
	}
}
