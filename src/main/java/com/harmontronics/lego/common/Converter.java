package com.harmontronics.lego.common;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-08-15 11:49
 */
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
