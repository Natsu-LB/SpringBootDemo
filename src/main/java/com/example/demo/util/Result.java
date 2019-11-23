package com.example.demo.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    @Getter
    @Setter
    private Integer status;

    // 响应消息
    @Getter
    @Setter
    private String msg;

    // 响应中的数据
    @Getter
    @Setter
    private Object data;

    @NotNull
    @Contract("_, _, _ -> new")
    public static Result build(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok() {
        return new Result(null);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static Result build(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public Result(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    /**
     * 将json结果集转化为Result对象
     *
     * @param jsonData json数据 传的是Result的对象的Json字符串
     * @param clazz    TaotaoResult中的object类型
     * @return
     */
    @Nullable
    public static Result formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            Result result = build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return Result
     */
    @Nullable
    public static Result format(String json) {
        try {
            return MAPPER.readValue(json, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData 传的是Result的对象的Json字符串
     *                 json数据
     * @param clazz    集合中的类型
     * @return Result  返回data为List的Result对象
     */
    @Nullable
    public static Result formatToList(String jsonData, Class<?> clazz) {
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
