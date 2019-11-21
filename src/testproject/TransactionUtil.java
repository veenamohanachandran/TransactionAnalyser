package testproject;

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

public class TransactionUtil {

	String fileName;
	String toDate;
	String fromDate;
	String selectedMerchant;
	List<TransactionRecord> list = new ArrayList<>();
	HashMap<String,Double> refMap = new HashMap<String,Double>();
	
	public TransactionUtil(String fileName, String toDate, String fromDate, String selectedMerchant) {
		
		this.fileName = fileName;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.selectedMerchant = selectedMerchant;
				
	}
	
	private void calculateAverage() {
		
		double sum=0,avg=0;
		int count=0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		try {
			Date toDate = dateFormat.parse( this.toDate );
			Date fromDate = dateFormat.parse( this.fromDate );
	
		for(TransactionRecord fin : list){
			
			
				//Double sum1 = 0.0;
				Date dt = dateFormat.parse( fin.getDate() );
				//System.out.println(dt+" "+d1+" "+d2);
				if(fin.getTransactionType().equals(TransactionAnalyserConstants.PAYMENT_CONSTANT) && !refMap.containsKey(fin.getId()))
					if(dt.compareTo(fromDate) >= 0 && dt.compareTo(toDate) <=0) {
						sum+=fin.getAmount();
						count++;
					}
			
		}
		if(sum > 0 && count > 0)
			avg=sum/count;
		
		System.out.println(TransactionAnalyserConstants.NUM_OF_TRANSACTIONS+": "+count);
		System.out.println(TransactionAnalyserConstants.AVG_OF_TRANSACTIONS+": "+avg);
		}
		catch(ParseException pe) {
			pe.printStackTrace();
		}
	}
	private void streamFile() throws Exception{
		
		
		try{
			Stream<String> stream = Files.lines(Paths.get(this.fileName)); 
		
			 //Files.lin
			// 1 filtered flight numbers BA-731 and VA-421
			// 2. then only map filtered result to there airport names only
			// 3. store result into a List
			list = stream.filter(line -> line.contains(this.selectedMerchant)).<TransactionRecord>map(line -> {
				
				String[] str = line.split(",");
				if(str[4].trim().equals(TransactionAnalyserConstants.REVERSAL_CONSTANT)) {
					refMap.put(str[5].trim(),Double.parseDouble(str[2]));	
				}
				return new TransactionRecord(str[0].trim(),str[1].trim(),Double.parseDouble(str[2]),str[3].trim(),str[4].trim());
			}).collect(Collectors.toList());
			
			/*list = stream.filter(line -> !line.contains("Amount")).<FinClass>map(line -> {
				
				String[] str = line.split(",");
				if(str[4].trim().equals("REVERSAL")) {
					refList.add(str[5].trim());	
				}
				return new FinClass(str[0].trim(),str[1].trim(),Double.parseDouble(str[2]),str[3].trim(),str[4].trim());
			}).filter(finObj-> finObj.getMerchant().equalsIgnoreCase(this.selectedMerchant)).collect(Collectors.toList());
 */
			// 4. print result list
	
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
