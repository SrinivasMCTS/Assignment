package com.cts.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.constants.ErrorConstants;
import com.cts.constants.RaboConstants;
import com.cts.generator.CSVFileGenerator;
import com.cts.helper.FileHandlingHelper;
import com.cts.model.CustomerResponse;
import com.cts.model.TransactionRecord;

/**
 * 
 * @author 437977 Service method for Customer services
 *
 */
@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CSVFileGenerator csvFileGenerator;

	@Autowired
	private FileHandlingHelper fileHandlingHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cts.services.ICustomerService#processStatement(java.lang.String)
	 * <p>Read files from path</p><p>Read each file and call respective
	 * helper</p>
	 * 
	 */
	@Override
	public CustomerResponse processStatement(String path) {

		HashMap<Integer, TransactionRecord> resultRecordMap = null;
		HttpServletResponse httpresponse = null;

		try {
			File dir = new File(path);
			File[] files = dir.listFiles();
			if (files.length == 0) {
				return new CustomerResponse(ErrorConstants.ERROR_RB_0, ErrorConstants.DIR_EMPTY_EXCEPTION);
			} else {
				for (File inputFile : files) {
					if (inputFile.toString().toLowerCase().endsWith(RaboConstants.CSV_FORMAT)) {
						byte[] csvFileBytes = FileUtils.readFileToByteArray(inputFile);
						if (csvFileBytes.length == 0) {
							return new CustomerResponse(ErrorConstants.ERROR_RB_1, ErrorConstants.FILE_EMPTY_EXCEPTION);
						} else {
							resultRecordMap = fileHandlingHelper.getTransactionRecordsfromCSV(csvFileBytes);
						}
					} else if (inputFile.toString().toLowerCase().endsWith(RaboConstants.XML_FORMAT)) {
						byte[] xmlFileBytes = FileUtils.readFileToByteArray(inputFile);
						if (xmlFileBytes.length == 0) {
							return new CustomerResponse(ErrorConstants.ERROR_RB_1, ErrorConstants.FILE_EMPTY_EXCEPTION);
						} else {
							resultRecordMap = fileHandlingHelper.getTransactionRecordsfromXML(xmlFileBytes);
						}
					} else {
						return new CustomerResponse(ErrorConstants.ERROR_RB_7, ErrorConstants.INVALID_FORMAT_EXCEPTION);
					}
					csvFileGenerator.constructCSVreport(resultRecordMap);
				}
				return new CustomerResponse(RaboConstants.SUCCESS, RaboConstants.SUCCESS);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new CustomerResponse(ErrorConstants.ERROR_RB_2, ErrorConstants.FILE_PROCESS_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomerResponse(ErrorConstants.ERROR_RB_6, ErrorConstants.GENERIC_EXCEPTION);
		}
	}

}
