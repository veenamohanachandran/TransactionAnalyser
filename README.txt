README - Transaction Analyser

This is a small project that analyses transactions of various merchants.

A transaction record will contain the following fields:
ID - A string representing the transaction id
Date - The date and time when the transaction took place (format "DD/MM/YYYY hh:mm:ss")
Amount - The value of the transaction (dollars and cents)
Merchant - The name of the merchant this transaction belongs to
Type - The type of the transaction, which could be either PAYMENT or REVERSAL
Related Transaction - (Optional) - In the case a REVERSAL transaction, this field will contain the ID of the transaction it is reversing.

The system will be Initialised with an input file in CSV format containing a list of transaction records.
Once initialised, the system should report the total number of transactions and the average transaction value for a specific merchant in a specific date range.
If a transaction record has a REVERSAL transaction, then it should not be included in the computed statistics, even if the reversing transaction is outside of the requested date range.

Input CSV Example:
ID, Date, Amount, Merchant, Type, Related Transaction
WLMFRDGD, 20/08/2018 12:45:33, 59.99, Kwik-E-Mart, PAYMENT,
YGXKOEIA, 20/08/2018 12:46:17, 10.95, Kwik-E-Mart, PAYMENT,
LFVCTEYM, 20/08/2018 12:50:02, 5.00, MacLaren, PAYMENT,
SUOVOISP, 20/08/2018 13:12:22, 5.00, Kwik-E-Mart, PAYMENT,
AKNBVHMN, 20/08/2018 13:14:11, 10.95, Kwik-E-Mart, REVERSAL, YGXKOEIA
JYAPKZFZ, 20/08/2018 14:07:10, 99.50, MacLaren, PAYMENT,

Given the above CSV file and the following input arguments:
fileName: C:\OS\Transactions\RetailData.csv
fromDate: 20/08/2018 12:00:00
toDate: 20/08/2018 13:00:00
selectedMerchant: Kwik-E-Mart

The output will be:
Number of transactions = 1
Average Transaction Value = 59.99

Assumptions:
The input file is well formed and is not missing data.


Steps:
1. Make sure that Java 8 or a higher version is installed.
2.Once the code has been downloaded from the repository, extract the files from the zip.
2. From command line, navigate to <LOCAL_COMP>\TransactionAnalyser-master\target
3. Run the following command :
java -jar transactionAnalyser.jar "C:\OS\Transactions\RetailData.csv" "20/08/2018 12:00:00" "20/08/2018 13:00:00" "Kwik-E-Mart"

This executes the program with the command line arguments as inputs, in the following order:
1. Input csv file path
2. From Date
3. To Date
4. Merchant Name

4. The source code provided in the repository doesn't use command line arguments, instead has variables to give the input values.
If that is a preferred method, navigate to <LOCAL_COMP>\TransactionAnalyser-master\src\transactionanalyser\TransactionAnalyser.java
Change the values of the required input parameters in the 'main()' function and run the following commands:
javac transactionanalyser.TransactionAnalyser.java
java transactionanalyser.TransactionAnalyser

This should execute the program with the input parameters specified in the main function.
C:\Users\veena\Downloads\TransactionAnalyser-master.zip\TransactionAnalyser-master\target