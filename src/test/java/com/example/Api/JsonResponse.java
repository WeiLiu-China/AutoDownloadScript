package com.example.Api;

/**
 * JsonResponse
 * 
 * @author jhm
 * @apiDefine Response
 * @apiSuccess {String} code 0: fail, 1: success
 * @apiSuccess {String} message the error message when code= 0, "成功" when code= 1
 */
public class JsonResponse {

	private String code;
	private String message;
	private Object result;

	public static JsonResponse createMessage(ServiceResponseCodeEnum e, Object result) {
		JsonResponse res = new JsonResponse(e.getCode(), e.getMsg());
		if ("1".equals(e.getCode())) {
			res.setResult(result);
		} else {
			res.setMessage(result.toString());
		}
		return res;
	}

	public JsonResponse() {
		this.code = ServiceResponseCodeEnum.SUCCESS.getCode();
		this.message = ServiceResponseCodeEnum.SUCCESS.getMsg();
	}

	public JsonResponse(ServiceResponseCodeEnum codeEnum) {
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMsg();
	}

	public JsonResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "JsonResponse [code=" + code + ", message=" + message + ", result=" + result + "]";
	}
}
