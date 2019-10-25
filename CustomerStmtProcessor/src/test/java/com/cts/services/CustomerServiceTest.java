package com.cts.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.constants.ErrorConstants;
import com.cts.helper.FileHandlingHelper;
import com.cts.model.CustomerResponse;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@InjectMocks
	private FileHandlingHelper fileHandlingHelper;

	@Test
	public void emptyDirectoryTest() {
		CustomerResponse expected = new CustomerResponse(ErrorConstants.ERROR_RB_0, ErrorConstants.DIR_EMPTY_EXCEPTION);
		String path = "src/test/resources/mockdata/emptyDir";
		CustomerResponse actual = customerService.processStatement(path);
		assertEquals(expected.getStatus(), actual.getStatus());
	}

	@Test
	public void emptyFileTest() {
		CustomerResponse expected = new CustomerResponse(ErrorConstants.ERROR_RB_1,
				ErrorConstants.FILE_EMPTY_EXCEPTION);
		String path = "src/test/resources/mockdata/emptyFile";
		CustomerResponse actual = customerService.processStatement(path);
		assertEquals(expected.getStatus(), actual.getStatus());
	}

	@Test
	public void emptyFileTestCsv() {
		CustomerResponse expected = new CustomerResponse(ErrorConstants.ERROR_RB_7,
				ErrorConstants.INVALID_FORMAT_EXCEPTION);
		String path = "src/test/resources/mockdata/invalidFormat/";
		CustomerResponse actual = customerService.processStatement(path);
		assertEquals(expected.getStatus(), actual.getStatus());
	}

}
