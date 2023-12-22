package com.enterprise.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@ToString
@ApiModel(description = "返回信息")
@Data
public class Result<T> implements Serializable {

    public static final int SUCCESS = 1; //成功
    public static final int FAIL = 0; // 失败

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty(value = "subcode", required = true)
    private String subcode;

    @ApiModelProperty(value = "提示消息", required = true)
    private String msg;

    @ApiModelProperty(value = "返回数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @ApiModelProperty(value = "当前页码")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long current;

    @ApiModelProperty(value = "总页数")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long pages;

    public Result() {
    }

    private Result(ICodeMessage resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private Result(ICodeMessage resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(ICodeMessage resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private Result(ICodeMessage resultCode, T data, String msg) {
        this(resultCode.getCode(), null, data, msg, null, null);
    }

    private Result(int code, String subcode, T data, String msg, Long current, Long pages) {
        this.code = code;
        this.subcode = subcode;
        this.data = data;
        this.msg = msg;
    }

    /**
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> data(int code, String subcode, T data, String msg) {
        return new Result<>(code, subcode, data, msg, null, null);
    }

    public static <T> Result<T> data(int code, T data, String msg) {
        return new Result<>(code, null, data, msg, null, null);
    }

    /**
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success() {
        return data(SUCCESS, null, null, "OK");
    }

    /**
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success(String msg) {
        return data(SUCCESS, null, null, msg);
    }

    /**
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return success(data, "OK");
    }

    /**
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success(T data, String msg) {
        return data(SUCCESS, null, data, msg);
    }

    /**
     * @param data
     * @param msg
     * @param current
     * @param pages
     * @param <T>
     * @return Result
     */
    public static <T> Result<T> success(T data, String msg, Long current, Long pages) {
        return new Result<>(SUCCESS, null, data, msg, current, pages);
    }

    /**
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail() {
        return data(FAIL, null, null, "FAIL");
    }

    /**
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(String msg) {
        return data(FAIL, null, null, msg);
    }

    /**
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(int code, String msg) {
        return data(code, null, null, msg);
    }

    /**
     * @param code    状态码
     * @param subcode
     * @param msg     消息
     * @param <T>     T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(int code, String subcode, String msg) {
        return data(code, subcode, null, msg);
    }

    /**
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(ICodeMessage resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(ICodeMessage resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

}
