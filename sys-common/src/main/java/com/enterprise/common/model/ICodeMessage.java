package com.enterprise.common.model;

import java.io.Serializable;

/**
 * 业务代码接口
 */

public interface ICodeMessage extends Serializable {

	String getMessage();

	int getCode();

}
