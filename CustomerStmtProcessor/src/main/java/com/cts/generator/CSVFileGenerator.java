package com.cts.generator;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cts.constants.ErrorConstants;
import com.cts.constants.RaboConstants;
import com.cts.exception.ApplicationException;
import com.cts.model.TransactionRecord;
import com.cts.model.TransactionStatus;

/**
 * @author 437977
 *
 */
@Component
public class CSVFileGenerator {

	@Value("${app.output}")
	private String outputPath;

	/**
	 * @param resultRecordMap
	 *            <p>
	 *            resultRecordMap has map of transactions, ref id as KEY
	 *            </p>
	 * 
	 *            <p>
	 *            method is to manipulate the transaction map and list the
	 *            failed record separately to construct responseCSV
	 *            </p>
	 */
	public void constructCSVreport(HashMap<Integer, TransactionRecord> resultRecordMap) {

		try {
			String recordAsCsv = null;
			if (resultRecordMap != null && !resultRecordMap.isEmpty()) {

				List<TransactionRecord> recordList = new ArrayList<TransactionRecord>(resultRecordMap.values());

				List<TransactionRecord> failedReportList = recordList.stream()
						.filter(record -> record.getTransactionStatus() != TransactionStatus.PASSED)
						.collect(Collectors.toList());
				if (!failedReportList.isEmpty()) {

					List<TransactionRecord> duplicateTransactions = failedReportList.stream()
							.filter(record -> record.getTransactionStatus() == TransactionStatus.DUPLICATED)
							.collect(Collectors.toList());

					List<TransactionRecord> duplicatedReportList = new ArrayList<>();
					duplicateTransactions.forEach(record -> {
						if (record.getDuplicateRecords() != null) {
							duplicatedReportList.addAll(record.getDuplicateRecords());
						}
					});

					recordAsCsv = constructCSVResponse(failedReportList, duplicatedReportList);
				} else {
					recordAsCsv = RaboConstants.NO_FAILED_RECORDS;
				}
			} else {
				recordAsCsv = RaboConstants.NO_RECORDS_TO_SHOW;
			}

			try {

				FileWriter fileWriter = new FileWriter(outputPath);
				fileWriter.write(recordAsCsv);
				fileWriter.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorConstants.ERROR_RB_5, ErrorConstants.CSV_FILE_GENERATOR_EXCEPTION);
		}
	}

	/**
	 * @param failedReportList
	 *            <p>
	 *            failedReportList has failed and duplicate[only count with
	 *            reference] transactions
	 *            </p>
	 * @param duplicatedReportList
	 *            <p>
	 *            duplicatedReportList has only duplicate transaction details
	 *            </p>
	 * 
	 *            <p>
	 *            method is to construct CSV response
	 *            </p>
	 *
	 * 
	 * @return
	 */
	private String constructCSVResponse(List<TransactionRecord> failedReportList,
			List<TransactionRecord> duplicatedReportList) {
		StringBuilder resultcsv = new StringBuilder();

		resultcsv.append(RaboConstants.COLUMN_METADATA);
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(failedReportList.stream().map(TransactionRecord::toCsvRow)
				.collect(Collectors.joining(System.getProperty(RaboConstants.LINE_SEPARATOR))));
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(RaboConstants.DUPLICATE_RECORDS);
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(RaboConstants.COLUMN_METADATA);
		resultcsv.append(RaboConstants.NEW_LINE);
		resultcsv.append(duplicatedReportList.stream().map(TransactionRecord::toCsvRow)
				.collect(Collectors.joining(System.getProperty(RaboConstants.LINE_SEPARATOR))));
		String recordAsCsv = resultcsv.toString();
		return recordAsCsv;
	}
}
