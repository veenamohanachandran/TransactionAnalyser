package transactionanalyser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* The operations involved are performed in this class*/
public class TransactionUtil {

	String fileName;
	String toDate;
	String fromDate;
	String selectedMerchant;
	List<TransactionRecord> selectedTransactionList = new ArrayList<>();
	HashMap<String,Double> reversalDataMap = new HashMap<String,Double>();

	public TransactionUtil(String fileName, String toDate, String fromDate, String selectedMerchant) {

		this.fileName = fileName;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.selectedMerchant = selectedMerchant;

	}
	/*Calculates the average of the transactions of the specified merchant*/
	private void calculateAverage() throws Exception{

		double sumOfTransactions = 0, avgOfTransactions = 0;
		int transactionCount = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			Date toDate = dateFormat.parse( this.toDate );
			Date fromDate = dateFormat.parse( this.fromDate );

			for(TransactionRecord tRecord : selectedTransactionList){

				Date dt = dateFormat.parse( tRecord.getDate() );
				/* The transaction date is compared with the user-specified date-range to pick only those transactions which satisfy the criteria. */
				if(tRecord.getTransactionType().equals(TransactionAnalyserConstants.PAYMENT_CONSTANT) && !reversalDataMap.containsKey(tRecord.getId()))
					if(dt.compareTo(fromDate) >= 0 && dt.compareTo(toDate) <=0) {
						sumOfTransactions+=tRecord.getAmount();
						transactionCount++;
					}

			}
			if(sumOfTransactions > 0 && transactionCount > 0)
				avgOfTransactions = sumOfTransactions/transactionCount;

			System.out.println(TransactionAnalyserConstants.NUM_OF_TRANSACTIONS+": "+transactionCount);
			System.out.println(TransactionAnalyserConstants.AVG_OF_TRANSACTIONS+": "+avgOfTransactions);
		}
		catch(Exception e) {
			throw e;
		}
	}

	/*Streams the input file to filter out the transaction records of the selected merchant.*/ 
	private void streamFile() throws Exception{


		try{
			Stream<String> stream = Files.lines(Paths.get(this.fileName)); 
			/*The 'selectedTransactionList'stores the transaction records of the specified merchant which are filtered using stream.*/
			selectedTransactionList = stream.filter(line -> line.toLowerCase().contains(this.selectedMerchant.toLowerCase())).<TransactionRecord>map(line -> {

				String[] str = line.split(",");
				/*The 'reversalDataMap'stores the ids of those transactions which have been reversed.*/
				if(str[4].trim().equals(TransactionAnalyserConstants.REVERSAL_CONSTANT)) {
					reversalDataMap.put(str[5].trim(),Double.parseDouble(str[2]));	
				}
				return new TransactionRecord(str[0].trim(),str[1].trim(),Double.parseDouble(str[2]),str[3].trim(),str[4].trim());
			}).collect(Collectors.toList());

		}catch(Exception e) {
			throw e;
		}
	}	

	public void execute() throws Exception{

		try {
			this.streamFile();
			this.calculateAverage();
		}
		catch(Exception e) {
			throw e;
		}

	}
}
