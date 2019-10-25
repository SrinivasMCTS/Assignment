package com.cts.services;

import com.cts.model.CustomerResponse;

/**
 * @author 437977
 *
 */
public interface ICustomerService {

	/**
	 * @param path
	 * @return
	 */
	public CustomerResponse processStatement(String path);

}