package org.monolith.web.tools.mail;

/**
 * @ClassName TextMail
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @date 2016年11月6日
 * @version 1.0
 * @description 文本格式邮件
 */
public class TextMail {
	/**
	 * @Fields <font color="blue">to</font>
	 * @description 发送对象 多个以数组形势
	 */
	private String[] to;
	/**
	 * @Fields <font color="blue">subject</font>
	 * @description 邮件主题
	 */
	private String subject;
	/**
	 * @Fields <font color="blue">text</font>
	 * @description 文本内容
	 */
	private String text;

	public TextMail() {
	}

	public TextMail(String[] to, String subject, String text) {
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public TextMail(String to, String subject, String text) {
		this.to = new String[] { to };
		this.subject = subject;
		this.text = text;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
