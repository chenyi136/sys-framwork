package com.enterprise.common.constant;

/**
 * 通用常量
 *
 */
public interface Constants {

    /**
     * 成功标记
     */
    Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    Integer FAIL = 500;

    /**
     * 编码
     */
    String UTF_8 = "UTF-8";

    /**
     * contentType
     */
    String CONTENT_TYPE_NAME = "Content-type";

    /**
     * 业务状态-正常
     */
    int DB_STATUS_NORMAL = 0;

    /**
     * 业务状态-停用
     */
    int DB_STATUS_DISABLE = 1;

    /**
     * 业务状态-锁定
     */
    int DB_STATUS_LOCK = 2;

    /**
     * 管理员ID attribute名称
     */
    String USERID_REQUEST_ATTR = "_USERID_REQUEST_ATTR_";

    /**
     * 管理员名称 attribute名称
     */
    String USERNAME_REQUEST_ATTR = "_USERNAME_REQUEST_ATTR_";

    /**
     * 是（是否）
     */
    int DB_TRUE = 1;

    /**
     * 否（是否）
     */
    int DB_FALSE = 0;


    /**
     * 在线
     */
    int ONLINE = 1;

    /**
     * 默认为空消息
     */
    String DEFAULT_NULL_MESSAGE = "暂无承载数据";

    /**
     * 默认成功消息
     */
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    String DEFAULT_FAILURE_MESSAGE = "操作失败";

    int DB_NOT_DELETED = 0;
}
