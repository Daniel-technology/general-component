package com.general.component.common.squence.core;


import com.general.component.common.squence.contract.IdGeneratorException;
import com.general.component.common.squence.contract.IdGeneratorOptions;
/**
 * @description: SnowWorkerM2
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public class SnowWorkerM2 extends SnowWorkerM1 {

    public SnowWorkerM2(IdGeneratorOptions options) {
        super(options);
    }

    @Override
    public long nextId() {
        synchronized (_syncLock) {
            long currentTimeTick = GetCurrentTimeTick();

            if (_lastTimeTick == currentTimeTick) {
                if (_currentSeqNumber++ > maxSeqNumber) {
                    _currentSeqNumber = minSeqNumber;
                    currentTimeTick = GetNextTimeTick();
                }
            } else {
                _currentSeqNumber = minSeqNumber;
            }

            if (currentTimeTick < _lastTimeTick) {
                throw new IdGeneratorException("Time error for {0} milliseconds", _lastTimeTick - currentTimeTick);
            }

            _lastTimeTick = currentTimeTick;
            long result = ((currentTimeTick << _timestampShift) + ((long) workerId << seqBitLength) + (int) _currentSeqNumber);

            return result;
        }

    }
}
