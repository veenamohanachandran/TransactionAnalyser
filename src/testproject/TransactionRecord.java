package testproject;

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
	
	public String getId() {
		
		return id;
	}
	
	public String getDate() {
		
		return date;
	}
	
	public String getMerchant(){
		
		return merchant;
	}

	public double getAmount() {
		
		return amount;
	}
	
	public String getTransactionType() {
		
		return transactionType;
	}
	
}
