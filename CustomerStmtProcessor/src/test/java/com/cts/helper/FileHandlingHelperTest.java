package com.cts.helper;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.TransactionRecord;

@RunWith(SpringRunner.class)
public class FileHandlingHelperTest {

	@InjectMocks
	private FileHandlingHelper fileHandlingHelper;

	@InjectMocks
	private FileUtils fileUtils;

	@Test
	public void testGetTransactionRecordsfromCSV() throws IOException {
		int expectedSize = 8;
		File path = new File("src\\test\\resources\\mockdata\\duplicateRecords\\TestRecords.csv");
		byte[] csvFileBytes = FileUtils.readFileToByteArray(path);
		HashMap<Integer, TransactionRecord> actual = fileHandlingHelper.getTransactionRecordsfromCSV(csvFileBytes);
		assertEquals(expectedSize, actual.size());

	}

	@Test
	public void testGetTransactionRecordsfromXML() throws IOException {
		int expectedSize = 10;
		File path = new File("src\\test\\resources\\mockdata\\validFile\\TestRecords_SuccessCase.xml");
		byte[] xmlFileBytes = FileUtils.readFileToByteArray(path);
		HashMap<Integer, TransactionRecord> actual = fileHandlingHelper.getTransactionRecordsfromXML(xmlFileBytes);
		assertEquals(expectedSize, actual.size());
	}

}
