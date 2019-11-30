package com.springboot.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你找到问题不存在了，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请先登录"),
    SystemError(2004,"发生错误"),
    Type_PARAM_WRONG(2005,"评论类型错误，或不存在"),
    COMMENT_NOT_FOUND(2005, "回复的评论不存在，要不换个试试？");

    private String message;
    private Integer code;
    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode(){
        return code;
    }
    CustomizeErrorCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
