package com.chenw.fanoutconsumer.process;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chenw
 * @title: SmsProcess
 * @description: TODO
 * @date 2019/7/24 9:57
 */
@Component
@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(value = "${mq.config.queue.sms.name}",autoDelete = "true"),
				exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.FANOUT)
		)
)
public class SmsProcess {

	@RabbitHandler
	public void process(String msg){
		System.out.println("[sms 服务]>>>"+msg);
	}
}
