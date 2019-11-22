package transactionanalyser;

import transactionanalyser.TransactionUtil;
import java.lang.Exception;
/*This is the main class, which is used to drive the program.
  It expects the file name, from and to dates and the merchant name as inputs.
  This can be provided in the main() function or as command line arguments.*/
public class TransactionAnalyser {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		String fileName = "", selectedMerchant = "";
		String fromDate = "", toDate = "";
		try {

			if(args.length > 0 ) {

				fileName = args[0];
				fromDate = args[1];
				toDate = args[2];
				selectedMerchant = args[3];
			}
			/*Uncomment the following code if the inputs are being provided in the main() instead of using command 
			 * line arguments. The format of inputs is as per the values specified here. */
			/* 
		  	fileName = "C:\\Users\\TestUser\\Documents\\sampleData.csv";
			fromDate="20/08/2018 12:00:02";
			toDate="20/08/2018 13:00:02";
			selectedMerchant = "Kwik-E-Mart";
			 */
			if(fileName.length() == 0 )
				System.out.println("Please provide an input .csv file.");
			if(selectedMerchant.length() == 0 )
				System.out.println("Please provide the name of the merchant whose transactions need to be viewed.");
			if(fromDate.length() == 0 || toDate.length() == 0)
				System.out.println("Please provide the date range for the transactions.");
			TransactionUtil scv = new TransactionUtil(fileName,toDate,fromDate,selectedMerchant);
			try {
				scv.execute();
			}catch(Exception e) {

				System.out.println("Failure occurred. "+e);
			}
		}catch(Exception e) {
			System.out.println("Not enough arguments. Please make sure that there are 4 arguments.");
		}
	}
}


