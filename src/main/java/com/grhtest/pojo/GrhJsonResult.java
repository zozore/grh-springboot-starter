package com.grhtest.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @Title GrhJSONResult.java
 * @Package com.grhtest.pojo
 * @Description:    自定义响应数据结构
 *                      这个类是提供给门户，ios，安卓，微信商城用的
 *                      门户接受此类数据后需要使用本类的方法转换成对于（类或者List）的数据类型格式
 *                      其他自行处理
 *                      200：表示成功
 *                      500：表示错误，错误信息再msg字段中
 *                      501：bean验证错误，不管多少个错误都以map形式返回
 *                      502：拦截器拦截到用户token出错
 *                      555：异常抛出信息
 * Copyright: Copyright(c) 2020
 * Company: 暂无
 *
 * @author grh
 * @date   2020年12月30日 下午19:58
 * @version V1.0
 */
public class GrhJsonResult {
    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 响应业务状态
     */
    private Integer status;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应中的数据
     */
    private Object data;
    /**
     * 暂不使用
     */
    private String ok;

    public static GrhJsonResult build(Integer status, String msg, Object data) {
        return new GrhJsonResult(status, msg, data);
    }

    public static GrhJsonResult ok(Object data) {
        return new GrhJsonResult(data);
    }

    public static GrhJsonResult ok() {
        return new GrhJsonResult(null);
    }

    public static GrhJsonResult errorMsg(String msg) {
        return new GrhJsonResult(500, msg, null);
    }

    public static GrhJsonResult errorMap(Object data) {
        return new GrhJsonResult(501, "error", data);
    }

    public static GrhJsonResult errorTokenMsg(String msg) {
        return new GrhJsonResult(502, msg, null);
    }

    public static GrhJsonResult errorException(String msg) {
        return new GrhJsonResult(555, msg, null);
    }

    public GrhJsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public GrhJsonResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public GrhJsonResult() {

    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    /**
     * @Description: 将json结果转化为GrhJsonRsult对象
     *                  需要转换的对象是一个类
     * @param jsonData
     * @param clazz
     * @return
     */
    public static GrhJsonResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, GrhJsonResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Description: 没有object对象的转化
     * @param json
     * @return
     */
    public static GrhJsonResult format(String json) {
        try {
            return MAPPER.readValue(json, GrhJsonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @DEscription: Object是集合转化
     *                  需要转换的对象是一个List
     * @param jsonData
     * @param clazz
     * @return
     */
    public static GrhJsonResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }

    }
}

