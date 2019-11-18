package com.river.videriTest.service.exception;

/**
 * 秒杀相关业务异常
 * 
 * @author jli
 *
 */
public class SeckillException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }



}
