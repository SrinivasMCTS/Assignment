Customer Statement Processor - Instructions

Pre-requisites before assignment execution:
1. Create Folder InputFiles and OutputFiles in D: as per below. This is configured in application.properties file present in classpath
	D:\InputFiles\
	D:\OutputFiles\
2. Place input files of xml or csv format in InputFiles directory "D:\InputFiles\". Sample Input files present in https://github.com/SrinivasMCTS/Assignment/tree/master/SampleInputFiles
3. Note that after program execution, generated Transaction Report will be available in D:\OutputFiles\

Steps for assignment execution:
1. Clone the project from https://github.com/SrinivasMCTS/Assignment into Eclipse IDE
2. Build the project after running Maven Update. Use JDK8 for build. 
3. Run the application as a spring boot application. Spring Boot Main App will be in StatementProcessorApplication.java
	set goal as 'spring-boot:run' in Run Configuration
4. Access the Application URL http://localhost:8085/customer/process 
5. Failed Transaction Report will be generated in D:\OutputFiles\TransactionReport.csv

Application Logic:
1. Read files from path
2. Read each file and call respective helper
3. Validate end balance logic
4. Validate Transaction references to be unique
5. Generate report in csv which will display both the transaction reference and description of each of the failed records

	


