package com.tject.common;

/**
 * 统一返回类型
 *
 * @param <T> T泛型，表示返回前端数据类型
 */
public class JsonResult<T> {
    //状态码： -1 表示未登录或者登录过期  0 表示业务上成功   1(大于0) 表示业务上失败
    private int state;
    //业务上失败信息
    private String message;
    //业务数据
    private T data;

    //定义私有的构造方法目的，不允许使用new对象
    private JsonResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    //成功
    public static <E> JsonResult success(String message, E data) {
        return new JsonResult(0, message, data);
    }

    public static <E> JsonResult success(E data) {
        return new JsonResult(0, null, data);
    }

    //失败
    public static JsonResult fail(String message) {
        return new JsonResult(1, message, null);
    }

    public static <E> JsonResult fail(String message, E data) {
        return new JsonResult(1, message, data);
    }

    public static JsonResult fail(int state, String message) {
        return new JsonResult(state, message, null);
    }
}
