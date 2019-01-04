package org.monolith.web.tools.mail;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName HtmlMail
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @date 2016年11月6日
 * @version 1.0
 * @description html格式邮件
 */
public class HtmlMail {
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

	/**
	 * @Fields <font color="blue">attachments</font>
	 * @description 文件集合
	 */
	private Set<File> files;

	public HtmlMail() {
	}

	public HtmlMail(String[] to, String subject, String text) {
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public HtmlMail(String[] to, String subject, String text, File file) {
		this.to = to;
		this.subject = subject;
		this.text = text;
		Set<File> files = new HashSet<>();
		files.add(file);
		this.files = files;
	}

	public HtmlMail(String to, String subject, String text) {
		this.to = new String[] { to };
		this.subject = subject;
		this.text = text;
	}

	public HtmlMail(String to, String subject, String text, File file) {
		this.to = new String[] { to };
		this.subject = subject;
		this.text = text;
		Set<File> files = new HashSet<>();
		files.add(file);
		this.files = files;
	}

	public HtmlMail(String[] to, String subject, String text, Set<File> files) {
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.files = files;
	}

	public HtmlMail(String to, String subject, String text, Set<File> files) {
		this.to = new String[] { to };
		this.subject = subject;
		this.text = text;
		this.files = files;
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

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}
}
