package com.grhtest.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author grh
 */
// 使用component注解，让程序可以自动扫描到此类
@Component
public class TestTask {

    private static final SimpleDateFormat formate = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 3000)
    //@Scheduled(cron = "50-55 * * * * ?")
    public void reportCurrentTime() {
        System.out.println("现在的时间：" + formate.format(new Date()));
    }
}
