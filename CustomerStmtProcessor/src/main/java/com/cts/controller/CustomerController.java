package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.constants.RaboConstants;
import com.cts.model.CustomerResponse;
import com.cts.services.CustomerService;

/**
 * @author 437977
 *
 */
@RestController
@RequestMapping(value = RaboConstants.CUSTOMER)
public class CustomerController {

	@Value("${app.inputpath}")
	private String path;

	@Autowired
	CustomerService customerService;

	/**
	 * @return ResponseEntity
	 */
	@GetMapping(value = RaboConstants.PROCESS)
	public ResponseEntity<CustomerResponse> processStatement() {
		CustomerResponse stmtProcessorResponse;
		stmtProcessorResponse = customerService.processStatement(path);
		return (new ResponseEntity<>(stmtProcessorResponse, HttpStatus.OK));
	}

}
