package com.general.component.common.squence.idgen;


import com.general.component.common.squence.contract.IIdGenerator;
import com.general.component.common.squence.contract.IdGeneratorException;
import com.general.component.common.squence.contract.IdGeneratorOptions;

/**
 * @description: 这是一个调用的例子，默认情况下，单机集成者可以直接使用 nextId()。
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public class IdHelper {

//    static {
//        // 创建 IdGeneratorOptions 对象，可在构造函数中输入 WorkerId：
//        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
//        // options.WorkerIdBitLength = 10; // 默认值6，限定 WorkerId 最大值为2^6-1，即默认最多支持64个节点。
//        // options.SeqBitLength = 6; // 默认值6，限制每毫秒生成的ID个数。若生成速度超过5万个/秒，建议加大 SeqBitLength 到 10。
//        // options.BaseTime = Your_Base_Time; // 如果要兼容老系统的雪花算法，此处应设置为老系统的BaseTime。
//        // ...... 其它参数参考 IdGeneratorOptions 定义。
//        // 保存参数（务必调用，否则参数设置不生效）：
//        IdHelper.setIdGenerator(options);
//    }

    private static IIdGenerator idGenInstance = null;

    public static IIdGenerator getIdGenInstance() {
        return idGenInstance;
    }

    /**
     * 设置参数，建议程序初始化时执行一次
     */
    public static void setIdGenerator(IdGeneratorOptions options) throws IdGeneratorException {
        idGenInstance = new DefaultIdGenerator(options);
    }

    /**
     * 生成新的Id
     * 调用本方法前，请确保调用了 SetIdGenerator 方法做初始化。
     *
     * @return
     */
    public static long nextId() throws IdGeneratorException {
        // if (idGenInstance == null) {
        // idGenInstance = new DefaultIdGenerator(new IdGeneratorOptions((short) 1));
        // }

        if (idGenInstance == null) {
            throw new IdGeneratorException("Please initialize Yitter.IdGeneratorOptions first.");
        }

        return idGenInstance.newLong();
    }

    /**
     * @return
     * @throws IdGeneratorException
     */
    public static long getNextId() throws IdGeneratorException {
        if (idGenInstance == null) {
            IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
            IdHelper.setIdGenerator(options);
        }
        return nextId();
    }

    //
//    public static void main(String[] args) {
//        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
//
//        IdHelper.setIdGenerator(options);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(nextId());
//        }
//
////        soutnextId();
//
//    }
}
