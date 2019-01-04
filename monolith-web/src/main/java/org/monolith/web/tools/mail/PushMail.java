package org.monolith.web.tools.mail;

import java.io.File;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.monolith.web.tools.spring.SpringContextUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @ClassName PushMail
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @date 2016年11月6日
 * @version 1.0
 * @description 邮件推送工具类 多线程执行不关心结果
 */
public class PushMail {
	private static JavaMailSenderImpl mailSender;
	static ExecutorService pool = Executors.newCachedThreadPool();

	static {
		mailSender = SpringContextUtils.getBean(JavaMailSenderImpl.class);
	}

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param mail
	 * @date 2016年11月6日
	 * @version 1.0
	 * @description 推送文本格式邮件
	 */
	public static void pushText(final TextMail mail) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(mail.getTo());
				message.setFrom(mailSender.getUsername());
				message.setSubject(mail.getSubject());
				message.setText(mail.getText());
				message.setSentDate(new Date());
				mailSender.send(message);
			}
		});
	}

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param mail
	 * @date 2016年11月6日
	 * @version 1.0
	 * @description 推送富文本格式邮件包括附件
	 */
	public static void pushHtml(final HtmlMail mail) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				try {
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
					helper.setTo(mail.getTo());
					helper.setFrom(mailSender.getUsername());
					helper.setSubject(mail.getSubject());
					helper.setText(mail.getText(), true);
					helper.setSentDate(new Date());
					Set<File> files = mail.getFiles();
					if (files != null) {
						for (File file : files) {
							helper.addAttachment(file.getName(), file);
						}
					}
					mailSender.send(mimeMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
