package com.river.videriTest.service.exception;
/**
 * 重复秒杀异常(运行期异常)
 * @author jli
 *
 */
public class RepeatKillException extends SeckillException{

    private static final long serialVersionUID = 1L;

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatKillException(String message) {
        super(message);
    }

    
}
