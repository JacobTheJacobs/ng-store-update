package com.example.demo.model;

public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "New OrderMain"),
    FINISHED(1, "Finished"),
    CANCELED(2, "Canceled")
    ;

    private  int code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}
    
    
}
