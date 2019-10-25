package com.cts.constants;

/**
 * @author 437977
 *         <p>
 *         Interface to hold Error constants and messages
 *         </p>
 *
 */
public interface ErrorConstants {

	// Error Codes
	String ERROR_RB_0 = "RABOERROR0";
	String ERROR_RB_1 = "RABOERROR1";
	String ERROR_RB_2 = "RABOERROR2";
	String ERROR_RB_3 = "RABOERROR3";
	String ERROR_RB_4 = "RABOERROR4";
	String ERROR_RB_5 = "RABOERROR5";
	String ERROR_RB_6 = "RABOERROR6";
	String ERROR_RB_7 = "RABOERROR7";

	// Error Messages
	String DIR_EMPTY_EXCEPTION = "Input Directory is empty, Please upload correctly";
	String FILE_EMPTY_EXCEPTION = "Input File is Empty, Please upload file with contents";
	String FILE_PROCESS_EXCEPTION = "Unable to Process the Uploaded File";
	String XML_FILE_PROCESS_EXCEPTION = "Unable to Process the Uploaded XML File";
	String CSV_FILE_PROCESS_EXCEPTION = "Unable to Process the Uploaded CSV File";
	String CSV_FILE_GENERATOR_EXCEPTION = "Unable to generate failed Transcation Report";
	String GENERIC_EXCEPTION = "Generic Exception occured. Please contact system admin";
	String INVALID_FORMAT_EXCEPTION = "Input File Format is incorrect, Please upload correctly";

}
