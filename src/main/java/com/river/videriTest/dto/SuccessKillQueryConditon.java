package com.river.videriTest.dto;

import com.river.videriTest.domain.Seckill;

public class SuccessKillQueryConditon {

    private Long userPhone;
    
    private Short state;

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

}
