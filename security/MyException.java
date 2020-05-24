package com.example.demo.security;

import com.example.demo.model.ResultEnum;

public class MyException extends RuntimeException {

    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
    
    
}