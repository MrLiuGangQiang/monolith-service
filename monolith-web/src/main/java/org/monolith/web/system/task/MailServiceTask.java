package org.monolith.web.system.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2018年1月10日
 * @version 1.0
 * @description 定时任务
 */
@Component
public class MailServiceTask {
	@Scheduled(cron = "0/10 * * ? * MON-FRI")
	public void testTask() {
		System.out.println("定时任务>>当前时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}
