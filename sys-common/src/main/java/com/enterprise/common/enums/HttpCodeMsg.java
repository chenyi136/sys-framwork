package com.enterprise.common.enums;

import com.enterprise.common.constant.HttpCode;
import com.enterprise.common.model.ICodeMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务代码枚举
 */
@Getter
@AllArgsConstructor
public enum HttpCodeMsg implements ICodeMessage {
	/**
	 * 操作成功
	 */
	SUCCESS(HttpCode.SC_OK, "操作成功"),

	/**
	 * 业务异常
	 */
	FAILURE(HttpCode.SC_BAD_REQUEST, "业务异常"),

	/**
	 * 请求未授权
	 */
	UN_AUTHORIZED(HttpCode.SC_UNAUTHORIZED, "认证失败"),

    /**
     * 非法令牌
     */
     ILLEGAL_TOKEN(HttpCode.SC_ILLEGAL_TOKEN, "非法令牌"),

	/**
	 * 404 没找到请求
	 */
	NOT_FOUND(HttpCode.SC_NOT_FOUND, "404 没找到请求"),

	/**
	 * 消息不能读取
	 */
	MSG_NOT_READABLE(HttpCode.SC_BAD_REQUEST, "消息不能读取"),

	/**
	 * 不支持当前请求方法
	 */
	METHOD_NOT_SUPPORTED(HttpCode.SC_METHOD_NOT_ALLOWED, "不支持当前请求方法"),

	/**
	 * 请求被拒绝
	 */
	REQ_REJECT(HttpCode.SC_FORBIDDEN, "请求被拒绝"),

	/**
	 * 服务器异常
	 */
	INTERNAL_SERVER_ERROR(HttpCode.SC_INTERNAL_SERVER_ERROR, "服务器异常"),

	/**
	 * 缺少必要的请求参数
	 */
	PARAM_MISS(HttpCode.SC_BAD_REQUEST, "缺少必要的请求参数"),

	/**
	 * 请求参数类型错误
	 */
	PARAM_TYPE_ERROR(HttpCode.SC_BAD_REQUEST, "请求参数类型错误"),

	/**
	 * 请求参数绑定错误
	 */
	PARAM_BIND_ERROR(HttpCode.SC_BAD_REQUEST, "请求参数绑定错误"),

	/**
	 * 参数校验失败
	 */
	PARAM_VALID_ERROR(HttpCode.SC_BAD_REQUEST, "参数校验失败"),

	/**
	 * 请求超出限制
	 */
	SURPASS_LIMIT(HttpCode.SC_SURPASS_LIMIT, "请求超出限制"),
	;

	/**
	 * code编码
	 */
	final int code;
	/**
	 * 中文信息描述
	 */
	final String message;
}
