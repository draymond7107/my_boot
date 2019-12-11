package com.draymond.my_boot.spring.exception;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/11 19:17
 */
public class ErrorException extends Exception {
    
    public ErrorException() {
    }

    public ErrorException(String message) {
        super(message);
    }
}