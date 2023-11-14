
package com.general.component.common.squence.contract;


/**
 * @description: ISnowWorker
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public interface ISnowWorker {
    long nextId() throws IdGeneratorException;
}
