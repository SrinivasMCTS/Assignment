package com.cts.model;

public class CustomerResponse extends BaseResponse {

	/**
	 * @param status
	 * @param message
	 */
	public CustomerResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
}
