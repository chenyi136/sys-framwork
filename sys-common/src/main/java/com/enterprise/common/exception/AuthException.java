package com.enterprise.common.exception;

import com.enterprise.common.enums.HttpCodeMsg;
import com.enterprise.common.model.ICodeMessage;
import lombok.Getter;

/**
 * Secure异常
 *
 */
public class AuthException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Getter
	private final ICodeMessage codeMessage;

	public AuthException(String message) {
		super(message);
		this.codeMessage = HttpCodeMsg.UN_AUTHORIZED;
	}

	public AuthException(ICodeMessage codeMessage) {
		super(codeMessage.getMessage());
		this.codeMessage = codeMessage;
	}

	public AuthException(ICodeMessage resultCode, Throwable cause) {
		super(cause);
		this.codeMessage = resultCode;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
