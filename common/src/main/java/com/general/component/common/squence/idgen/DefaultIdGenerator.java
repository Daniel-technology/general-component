package com.general.component.common.squence.idgen;


import com.general.component.common.squence.contract.IIdGenerator;
import com.general.component.common.squence.contract.ISnowWorker;
import com.general.component.common.squence.contract.IdGeneratorException;
import com.general.component.common.squence.contract.IdGeneratorOptions;
import com.general.component.common.squence.core.SnowWorkerM1;
import com.general.component.common.squence.core.SnowWorkerM2;

/**
 * @description: DefaultIdGenerator
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public class DefaultIdGenerator implements IIdGenerator {

    private static ISnowWorker _SnowWorker = null;

    public DefaultIdGenerator(IdGeneratorOptions options) throws IdGeneratorException {
        if (options == null) {
            throw new IdGeneratorException("options error.");
        }

        // 1.baseTime
        if (options.baseTime < 315504000000L || options.baseTime > System.currentTimeMillis()) {
            throw new IdGeneratorException("baseTime error.");
        }

        // 2.workerIdBitLength
        if (options.workerIdBitLength <= 0) {
            throw new IdGeneratorException("workerIdBitLength error.(range:[1, 21])");
        }
        if (options.workerIdBitLength + options.seqBitLength > 22) {
            throw new IdGeneratorException("error：workerIdBitLength + seqBitLength <= 22");
        }

        // 3.workerId
        int maxworkerIdNumber = (1 << options.workerIdBitLength) - 1;
        if (maxworkerIdNumber == 0) {
            maxworkerIdNumber = 63;
        }
        if (options.workerId < 0 || options.workerId > maxworkerIdNumber) {
            throw new IdGeneratorException(
                    "workerId error. (range:[0, " + (maxworkerIdNumber > 0 ? maxworkerIdNumber : 63) + "]");
        }

        // 4.seqBitLength
        if (options.seqBitLength < 2 || options.seqBitLength > 21) {
            throw new IdGeneratorException("seqBitLength error. (range:[2, 21])");
        }

        // 5.maxSeqNumber
        int maxSeqNumber = (1 << options.seqBitLength) - 1;
        if (maxSeqNumber == 0) {
            maxSeqNumber = 63;
        }
        if (options.maxSeqNumber < 0 || options.maxSeqNumber > maxSeqNumber) {
            throw new IdGeneratorException("maxSeqNumber error. (range:[1, " + maxSeqNumber + "]");
        }

        // 6.minSeqNumber
        if (options.minSeqNumber < 5 || options.minSeqNumber > maxSeqNumber) {
            throw new IdGeneratorException("minSeqNumber error. (range:[5, " + maxSeqNumber + "]");
        }

        // 7.topOverCostCount
        if (options.topOverCostCount < 0 || options.topOverCostCount > 10000) {
            throw new IdGeneratorException("topOverCostCount error. (range:[0, 10000]");
        }

        switch (options.method) {
            case 2:
                _SnowWorker = new SnowWorkerM2(options);
                break;
            case 1:
            default:
                _SnowWorker = new SnowWorkerM1(options);
                break;
        }

        if (options.method == 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long newLong() {
        return _SnowWorker.nextId();
    }
}
