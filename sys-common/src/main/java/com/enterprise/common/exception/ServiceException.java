package com.enterprise.common.exception;

import com.enterprise.common.enums.HttpCodeMsg;
import com.enterprise.common.model.ICodeMessage;
import lombok.Getter;


/**
 * 业务异常
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 2359767895161832954L;

	@Getter
	private final ICodeMessage codeMessage;

	public ServiceException(String message) {
		super(message);
		this.codeMessage = HttpCodeMsg.FAILURE;
	}

	public ServiceException(ICodeMessage codeMessage) {
		super(codeMessage.getMessage());
		this.codeMessage = codeMessage;
	}

	public ServiceException(ICodeMessage resultCode, Throwable cause) {
		super(cause);
		this.codeMessage = resultCode;
	}

	/**
	 * 提高性能
	 *
	 * @return Throwable
	 */
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public Throwable doFillInStackTrace() {
		return super.fillInStackTrace();
	}

}
