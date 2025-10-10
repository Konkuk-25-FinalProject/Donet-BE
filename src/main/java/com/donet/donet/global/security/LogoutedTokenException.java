package com.donet.donet.global.security;

public class LogoutedTokenException extends RuntimeException{
    LogoutedTokenException(){}

    LogoutedTokenException(String message) {
        super(message); // RuntimeException 클래스의 생성자를 호출합니다.
    }
}
