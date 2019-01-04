package org.monolith.core.logback.html;

import static ch.qos.logback.core.CoreConstants.LINE_SEPARATOR;

import java.util.Map;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.html.DefaultCssBuilder;
import ch.qos.logback.classic.html.DefaultThrowableRenderer;
import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.helpers.Transform;
import ch.qos.logback.core.html.HTMLLayoutBase;
import ch.qos.logback.core.html.IThrowableRenderer;
import ch.qos.logback.core.pattern.Converter;

public class HTMLLayout extends HTMLLayoutBase<ILoggingEvent> {

	static final String DEFAULT_CONVERSION_PATTERN = "%date%thread%level%logger%mdc%msg";
	private String title;

	IThrowableRenderer<ILoggingEvent> throwableRenderer;

	public HTMLLayout() {
		pattern = DEFAULT_CONVERSION_PATTERN;
		throwableRenderer = new DefaultThrowableRenderer();
		cssBuilder = new DefaultCssBuilder();
	}

	@Override
	public void start() {
		int errorCount = 0;
		if (throwableRenderer == null) {
			addError("ThrowableRender cannot be null.");
			errorCount++;
		}
		if (errorCount == 0) {
			super.start();
		}
	}

	@Override
	public String getPresentationHeader() {
		StringBuilder sbuf = new StringBuilder();
		if (getTitle() != null && !"".equals(getTitle())) {
			sbuf.append("<h2 align=\"center\">");
			sbuf.append(getTitle());
			sbuf.append("</h2>");
		}
		sbuf.append("<table cellspacing=\"0\">");
		buildHeaderRowForTable(sbuf);
		return sbuf.toString();
	}

	private void buildHeaderRowForTable(StringBuilder sbuf) {
		@SuppressWarnings("rawtypes")
		Converter c = head;
		String name;
		sbuf.append("<tr class=\"header\">");
		sbuf.append(LINE_SEPARATOR);
		while (c != null) {
			name = computeConverterName(c);
			if (name == null) {
				c = c.getNext();
				continue;
			}
			sbuf.append("<td class=\"");
			sbuf.append(computeConverterName(c));
			sbuf.append("\">");
			sbuf.append(computeConverterName(c));
			sbuf.append("</td>");
			sbuf.append(LINE_SEPARATOR);
			c = c.getNext();
		}
		sbuf.append("</tr>");
		sbuf.append(LINE_SEPARATOR);
	}

	protected Map<String, String> getDefaultConverterMap() {
		return PatternLayout.defaultConverterMap;
	}

	public String doLayout(ILoggingEvent event) {
		StringBuilder buf = new StringBuilder();
		startNewTableIfLimitReached(buf);

		boolean odd = true;
		if (((counter++) & 1) == 0) {
			odd = false;
		}

		String level = event.getLevel().toString().toLowerCase();

		buf.append(LINE_SEPARATOR);
		buf.append("<tr class=\"");
		buf.append(level);
		if (odd) {
			buf.append(" odd\">");
		} else {
			buf.append(" even\">");
		}
		buf.append(LINE_SEPARATOR);

		Converter<ILoggingEvent> c = head;
		while (c != null) {
			appendEventToBuffer(buf, c, event);
			c = c.getNext();
		}
		buf.append("</tr>");
		buf.append(LINE_SEPARATOR);

		if (event.getThrowableProxy() != null) {
			throwableRenderer.render(buf, event);
		}
		return buf.toString();
	}

	private void appendEventToBuffer(StringBuilder buf, Converter<ILoggingEvent> c, ILoggingEvent event) {
		buf.append("<td style=\"white-space:pre\" class=\"");
		buf.append(computeConverterName(c));
		buf.append("\">");
		buf.append(Transform.escapeTags(c.convert(event)));
		buf.append("</td>");
		buf.append(LINE_SEPARATOR);
	}

	@SuppressWarnings("rawtypes")
	public IThrowableRenderer getThrowableRenderer() {
		return throwableRenderer;
	}

	public void setThrowableRenderer(IThrowableRenderer<ILoggingEvent> throwableRenderer) {
		this.throwableRenderer = throwableRenderer;
	}

	@Override
	protected String computeConverterName(@SuppressWarnings("rawtypes") Converter c) {
		if (c instanceof MDCConverter) {
			MDCConverter mc = (MDCConverter) c;
			String key = mc.getFirstOption();
			if (key != null) {
				return key;
			} else {
				return "MDC";
			}
		} else {
			return super.computeConverterName(c);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}