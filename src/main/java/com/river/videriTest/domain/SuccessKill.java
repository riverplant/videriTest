package com.river.videriTest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 
 * @author jli
 *
 */
@Entity
@Table(name="success_kill", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"seckill_id","user_phone"})},
     indexes = {@Index(name="idx_create_time",columnList = "create_time")})
public class SuccessKill extends DomainImpl{

    private static final long serialVersionUID = 1164382651158317079L;
    
    @Column(name = "user_phone", nullable = false)
    private long userPhone;
    
    @Column(name = "state", nullable = false)
    private short state;
    
    @Column(name = "create_time", nullable = false)
    private Date createTime = new Date();
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seckill_id")
    private Seckill seckill;
    
    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }
  
    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   
    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public SuccessKill() {
        super();
    }

    @Override
    public String toString() {
        return "SuccessKill [userPhone=" + userPhone + ", state=" + state + ", createTime=" + createTime + "]";
    }

    public SuccessKill(long userPhone, short state, Date createTime, Seckill seckill) {
        this.userPhone = userPhone;
        this.state = state;
        this.createTime = createTime;
        this.seckill = seckill;
    }
    

}
