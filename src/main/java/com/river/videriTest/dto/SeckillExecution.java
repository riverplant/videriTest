package com.river.videriTest.dto;

import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.enums.SeckillStatEnum;

/**
 * 
 * @author jli
 *封装秒杀执行后结果
 */

public class SeckillExecution {

    private long seckillId;
    
    //秒杀执行结果状态
    private int state;
    
    //状态信息
    private String stateInfo;
    
    //秒杀成功对象
    private SuccessKill successkilled;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKill getSuccesskilled() {
        return successkilled;
    }

    public void setSuccesskilled(SuccessKill successkilled) {
        this.successkilled = successkilled;
    }
    /**
     * 秒杀成功调用的构造方法
     * @param seckillId
     * @param state 秒杀执行结果状态
     * @param stateInfo 秒杀执行结果详细信息
     * @param successkilled 秒杀对象详细信息
     */
    public SeckillExecution(long seckillId, SeckillStatEnum state, SuccessKill successkilled) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
        this.successkilled = successkilled;
    }
    /**
     * 秒杀失败调用的构造方法
     * @param seckillId
     * @param state: 秒杀执行结果状态
     * @param stateInfo: 秒杀执行结果详细信息
     */
    public SeckillExecution(long seckillId, SeckillStatEnum state) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo + ", successkilled=" + successkilled + "]";
    } 
    
}
