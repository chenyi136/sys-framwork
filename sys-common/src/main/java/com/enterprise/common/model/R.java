package com.enterprise.common.model;


import com.enterprise.common.constant.Constants;
import com.enterprise.common.enums.HttpCodeMsg;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = Constants.SUCCESS;

    /** 失败 */
    public static final int FAIL = Constants.FAIL;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty(value = "提示消息", required = true)
    private String msg;

    @ApiModelProperty(value = "是否成功", required = true)
    private Boolean success;

    @ApiModelProperty(value = "返回数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private R(ICodeMessage resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private R(ICodeMessage resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private R(ICodeMessage resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(ICodeMessage resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 判断返回是否为成功
     * @return
     */
    public Boolean getSuccess() {
        return code == HttpCodeMsg.SUCCESS.getCode();
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(int code, T data, String msg) {
        return new R<>(code, data, data == null ? Constants.DEFAULT_NULL_MESSAGE : msg);
    }

    /**
     * 返回R
     *
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success() {
        return new R<>(HttpCodeMsg.SUCCESS);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success(String msg) {
        return new R<>(HttpCodeMsg.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(ICodeMessage resultCode) {
        return new R<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(ICodeMessage resultCode, String msg) {
        return new R<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> success(T data) {
        return success(data, Constants.DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> success(T data, String msg) {
        return data(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(HttpCodeMsg.FAILURE, msg);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(ICodeMessage resultCode) {
        return new R<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(ICodeMessage resultCode, String msg) {
        return new R<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return R
     */
    public static <T> R<T> status(boolean flag) {
        return flag ? success(Constants.DEFAULT_SUCCESS_MESSAGE) : fail(Constants.DEFAULT_FAILURE_MESSAGE);
    }
}