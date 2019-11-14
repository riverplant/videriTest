package com.river.videriTest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="success_kill", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id","name"})},
     indexes = {@Index(name="idx_create_time",columnList = "createTime")})
public class SuccessKill extends DomainImpl{

    private static final long serialVersionUID = 1164382651158317079L;
    
    @Column(name = "user_phone", nullable = false)
    private long userPhone;
    
    @Column(name = "state", nullable = false)
    private int state;
    
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SuccessKill(long userPhone, int state, Date createTime) {
        super();
        this.userPhone = userPhone;
        this.state = state;
        this.createTime = createTime;
    }

    public SuccessKill() {
        super();
    }
    

}
