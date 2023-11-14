package com.general.component.common.squence.core;


import com.general.component.common.squence.contract.ISnowWorker;
import com.general.component.common.squence.contract.IdGeneratorException;
import com.general.component.common.squence.contract.IdGeneratorOptions;
import com.general.component.common.squence.contract.OverCostActionArg;

/**
 * @description: SnowWorkerM1
 * @author: liuyan
 * @create: 2022−10-21 10:23 AM
 */
public class SnowWorkerM1 implements ISnowWorker {

    /**
     * 基础时间
     */
    protected final long baseTime;

    /**
     * 机器码
     */
    protected final short workerId;

    /**
     * 机器码位长
     */
    protected final byte workerIdBitLength;

    /**
     * 自增序列数位长
     */
    protected final byte seqBitLength;

    /**
     * 最大序列数（含）
     */
    protected final int maxSeqNumber;

    /**
     * 最小序列数（含）
     */
    protected final short minSeqNumber;

    /**
     * 最大漂移次数（含）
     */
    protected final int topOverCostCount;

    protected final byte _timestampShift;
    protected final static byte[] _syncLock = new byte[0];

    protected short _currentSeqNumber;
    protected long _lastTimeTick = 0;
    protected long _turnBackTimeTick = 0;
    protected byte _turnBackIndex = 0;

    protected boolean _isOverCost = false;
    protected int _overCostCountInOneTerm = 0;
    protected int _genCountInOneTerm = 0;
    protected int _termIndex = 0;

    public SnowWorkerM1(IdGeneratorOptions options) {
        baseTime = options.baseTime != 0 ? options.baseTime : 1582136402000L;
        workerIdBitLength = options.workerIdBitLength == 0 ? 6 : options.workerIdBitLength;
        workerId = options.workerId;
        seqBitLength = options.seqBitLength == 0 ? 6 : options.seqBitLength;
        maxSeqNumber = options.maxSeqNumber <= 0 ? (1 << seqBitLength) - 1 : options.maxSeqNumber;
        minSeqNumber = options.minSeqNumber;
        // topOverCostCount = options.topOverCostCount == 0 ? 2000 : options.topOverCostCount;
        topOverCostCount = options.topOverCostCount;
        _timestampShift = (byte) (workerIdBitLength + seqBitLength);
        _currentSeqNumber = minSeqNumber;
    }

    private void DoGenIdAction(OverCostActionArg arg) {

    }

    private void BeginOverCostAction(long useTimeTick) {

    }

    private void EndOverCostAction(long useTimeTick) {
        // if (_termIndex > 10000) {
        //     _termIndex = 0;
        // }
    }

    private void BeginTurnBackAction(long useTimeTick) {

    }

    private void EndTurnBackAction(long useTimeTick) {

    }

    private long NextOverCostId() {
        long currentTimeTick = GetCurrentTimeTick();

        if (currentTimeTick > _lastTimeTick) {
            EndOverCostAction(currentTimeTick);

            _lastTimeTick = currentTimeTick;
            _currentSeqNumber = minSeqNumber;
            _isOverCost = false;
            _overCostCountInOneTerm = 0;
            _genCountInOneTerm = 0;

            return CalcId(_lastTimeTick);
        }

        if (_overCostCountInOneTerm >= topOverCostCount) {
            EndOverCostAction(currentTimeTick);

            _lastTimeTick = GetNextTimeTick();
            _currentSeqNumber = minSeqNumber;
            _isOverCost = false;
            _overCostCountInOneTerm = 0;
            _genCountInOneTerm = 0;

            return CalcId(_lastTimeTick);
        }

        if (_currentSeqNumber > maxSeqNumber) {
            _lastTimeTick++;
            _currentSeqNumber = minSeqNumber;
            _isOverCost = true;
            _overCostCountInOneTerm++;
            _genCountInOneTerm++;

            return CalcId(_lastTimeTick);
        }

        _genCountInOneTerm++;
        return CalcId(_lastTimeTick);
    }

    private long NextNormalId() throws IdGeneratorException {
        long currentTimeTick = GetCurrentTimeTick();

        if (currentTimeTick < _lastTimeTick) {
            if (_turnBackTimeTick < 1) {
                _turnBackTimeTick = _lastTimeTick - 1;
                _turnBackIndex++;
                // 每毫秒序列数的前5位是预留位，0用于手工新值，1-4是时间回拨次序
                // 支持4次回拨次序（避免回拨重叠导致ID重复），可无限次回拨（次序循环使用）。
                if (_turnBackIndex > 4) {
                    _turnBackIndex = 1;
                }
                //BeginTurnBackAction(_turnBackTimeTick);
            }

            // try {
            // Thread.sleep(1);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }

            return CalcTurnBackId(_turnBackTimeTick);
        }

        // 时间追平时，_turnBackTimeTick清零
        if (_turnBackTimeTick > 0) {
            EndTurnBackAction(_turnBackTimeTick);
            _turnBackTimeTick = 0;
        }

        if (currentTimeTick > _lastTimeTick) {
            _lastTimeTick = currentTimeTick;
            _currentSeqNumber = minSeqNumber;

            return CalcId(_lastTimeTick);
        }

        if (_currentSeqNumber > maxSeqNumber) {
            BeginOverCostAction(currentTimeTick);

            _termIndex++;
            _lastTimeTick++;
            _currentSeqNumber = minSeqNumber;
            _isOverCost = true;
            _overCostCountInOneTerm = 1;
            _genCountInOneTerm = 1;

            return CalcId(_lastTimeTick);
        }

        return CalcId(_lastTimeTick);
    }

    private long CalcId(long useTimeTick) {
        long result = ((useTimeTick << _timestampShift) +
                ((long) workerId << seqBitLength) +
                (int) _currentSeqNumber);

        _currentSeqNumber++;
        return result;
    }

    private long CalcTurnBackId(long useTimeTick) {
        long result = ((useTimeTick << _timestampShift) +
                ((long) workerId << seqBitLength) + _turnBackIndex);

        _turnBackTimeTick--;
        return result;
    }

    protected long GetCurrentTimeTick() {
        long millis = System.currentTimeMillis();
        return millis - baseTime;
    }

    protected long GetNextTimeTick() {
        long tempTimeTicker = GetCurrentTimeTick();

        while (tempTimeTicker <= _lastTimeTick) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempTimeTicker = GetCurrentTimeTick();
        }

        return tempTimeTicker;
    }

    @Override
    public long nextId() {
        synchronized (_syncLock) {
            return _isOverCost ? NextOverCostId() : NextNormalId();
        }
    }
}
