package transactionanalyser;

/* Objects of this class are used to store the transaction data of the merchant specified by the user.*/
public class TransactionRecord {

	private String id;
	private String date;
	private double amount;
	private String merchant;
	private String transactionType;

	public TransactionRecord(String id, String date, double value, String merchant, String transactionType) {

		this.id = id;
		this.date = date;
		this.amount = value;
		this.merchant = merchant;
		this.transactionType = transactionType;

	}

	//Returns the transaction id
	public String getId() {

		return id;
	}
	//Returns the transaction date
	public String getDate() {

		return date;
	}
	//Returns the merchant name
	public String getMerchant(){

		return merchant;
	}
	//Returns the transaction amount
	public double getAmount() {

		return amount;
	}
	//Returns the type of transaction, namely PAYMENT/REVERSAL
	public String getTransactionType() {

		return transactionType;
	}

}
