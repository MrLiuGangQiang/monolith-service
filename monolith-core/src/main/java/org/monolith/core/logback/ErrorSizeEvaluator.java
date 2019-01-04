package org.monolith.core.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

public class ErrorSizeEvaluator extends EventEvaluatorBase<ILoggingEvent> {
	private Integer bufferErrorSize;
	private static Integer total = 0;

	@Override
	public boolean evaluate(ILoggingEvent event) throws NullPointerException, EvaluationException {
		if (event.getLevel().toInt() >= Level.ERROR.toInt()) {
			total++;
		}
		if (total >= getBufferErrorSize()) {
			total = 0;
			return true;
		}
		return false;
	}

	public Integer getBufferErrorSize() {
		return bufferErrorSize;
	}

	public void setBufferErrorSize(Integer bufferErrorSize) {
		this.bufferErrorSize = bufferErrorSize;
	}

}