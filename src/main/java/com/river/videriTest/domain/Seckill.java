package com.river.videriTest.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 
 * @author jli
 *
 */
@Entity
@Table(name="seckill", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id","name"})},
     indexes = {@Index(name="idx_start_time",columnList = "startTime"),@Index(name="idx_end_time",columnList = "endTime"),@Index(name="idx_create_time",columnList = "createTime")})
public class Seckill extends DomainImpl{

    private static final long serialVersionUID = 1L;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "number", nullable = false)
    private int number;
    
    @Column(name = "start_time", nullable = false)
    private Date startTime;
    
    
    @Column(name = "end_time", nullable = false)
    private Date endTime;
    

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @OneToMany(mappedBy ="seckill",cascade = CascadeType.ALL)
    private Set<SuccessKill> successKills = new HashSet<>();
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }


    public Date getStartTime() {
        return startTime;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getEndTime() {
        return endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Seckill(String name, int number, Date startTime, Date endTime, Date createTime) {
        super();
        this.name = name;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
    }


    public Seckill() {
        super();
    }


    @Override
    public String toString() {
        return "Seckill [name=" + name + ", number=" + number + ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + "]";
    }
    
    
    
}
