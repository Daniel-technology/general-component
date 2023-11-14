package com.general.component.common.squence.contract;

/**
 * @description: IIdGenerator
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public interface IIdGenerator {
    long newLong() throws IdGeneratorException;
}
