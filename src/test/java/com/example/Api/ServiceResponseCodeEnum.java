package com.example.Api;

public enum ServiceResponseCodeEnum {
	
	FAILURE("0", "失败"),  
	
	SUCCESS("1", "成功");

    private String code;
	private String msg;

    ServiceResponseCodeEnum(String code, String msg)
    {
      this.code = code;
      this.msg = msg;
    }

    public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
