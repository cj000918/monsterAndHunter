package com.chenjian.enums.base;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public interface IEnum<T> {


    T getCode();

    /**
     * 枚举名称
     *
     * @return .
     */
    default String getMsg() {
        return null;
    }

    /**
     * 用于展示的名称
     *
     * @return .
     */
    default String getShowName() {
        return null;
    }

    /**
     * 根据编码获取枚举实例
     *
     * @param code .
     * @return .
     */
    static <X, E extends Enum<E> & IEnum<X>> E getByCode(X code, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst().orElse(null);
    }

    /**
     * 根据编码获取枚举实例
     *
     * @param code .
     * @return .
     */
    static <X, E extends Enum<E> & IEnum<X>> String getMsg(X code, Class<E> enumClass) {
        return Optional.ofNullable(getByCode(code, enumClass))
                // 这个地方不要改成方法引用 jvm有bug
                // .map(IEnum::getMsg)
                .map(item -> item.getMsg())
                .orElse("");
    }


    /**
     * 根据编码获取枚举实例
     *
     * @param code .
     * @return .
     */
    static <X, E extends Enum<E> & IEnum<X>> String getShowName(X code, Class<E> enumClass) {
        return Optional.ofNullable(getByCode(code, enumClass))
                .map(item -> item.getShowName())
                .orElse("");
    }


    default boolean codeEquals(T t) {
        return Objects.equals(getCode(), t);
    }


}
