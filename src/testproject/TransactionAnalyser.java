package testproject;


public class TransactionAnalyser {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String txtFileName = "C:\\Users\\veena\\OneDrive\\Documents\\sampletxt1.csv";
		
		String fromDate="20/08/2018 12:00:02", toDate="20/08/2018 13:00:02";
		String selectedMerchant = "Kwik-E-Mart";
		
		TransactionUtil scv = new TransactionUtil(txtFileName,toDate,fromDate,selectedMerchant);
		try {
			scv.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
