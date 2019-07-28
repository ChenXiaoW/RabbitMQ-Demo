package com.chenw.directprovider.SenderTest;

import com.chenw.directprovider.Sender.ErrorSender;
import com.chenw.directprovider.Sender.InfoMsgSender;
import com.chenw.directprovider.Sender.InfoSender;
import com.chenw.directprovider.model.LogMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author chenw
 * @title: SenderTest
 * @description: 消息发送测试
 * @date 2019/7/23 15:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

	@Autowired
	public InfoSender infoSender;
	@Autowired
	public ErrorSender errorSender;
	@Autowired
	public InfoMsgSender infoMsgSender;

	@Test
	public void sender_info()throws Exception{
		//int falg = 0;
		//while (true){
			//falg++;
			Thread.sleep(3000);
			this.infoSender.senderInfoMsg("Hello , info");
	//	}
	}

	@Test
	public void sender_error()throws Exception{

		while (true){

			Thread.sleep(1000);
			this.errorSender.senderErrorMsg("Hello , error");
		}
	}

	@Test
	public void sender_msg()throws Exception{
		int i = 0;
		while (true){
			i++;
			LogMsg logMsg = new LogMsg(i,"CONTENT", UUID.randomUUID().toString());
			this.infoMsgSender.sender(logMsg);
			if(i==50){
				return;
			}
		}
	}
}
