package com.yootk.dubbo.test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yootk.dubbo.service.IMessageService;
import com.yootk.dubbo.vo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = {"classpath:spring/spring-base.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageService {
    @Reference(mock = "return null",check = false)
    private IMessageService messageInstance ;
    @Test
    public void testEcho() throws Exception {
        for (int x = 0 ; x < 10000 ; x ++ ) {
            TimeUnit.MILLISECONDS.sleep(10);
            new Thread(()->{
                Message message = new Message() ;
                message.setTitle("Hello同学们");
                message.setContent("即将迎来伟大的假期！");
                message.setSender("超级可爱的小李");
                System.out.println(this.messageInstance.echo(message));
            }).start();
        }
        TimeUnit.DAYS.sleep(Long.MAX_VALUE);
    }
}
