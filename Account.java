package bankAccount;

import java.sql.Timestamp;

public class Account {

	private int accountID ;
	private String accountNo ;
	private String accountHolder ;
	private double balance ;
	private Timestamp ts ;
	private int pin ;
	
	public Account() {
	}

	public Account(int accountID, String accountNo, String accountHolder, double balance, Timestamp ts, int pin) {
		super();
		this.accountID = accountID;
		this.accountNo = accountNo;
		this.accountHolder = accountHolder;
		this.balance = balance;
		this.ts = ts;
		this.pin = pin;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public int getPin()
	{
		return pin ;
	}

	public String toString() {
		return "[accountID=" + accountID + ", accountNo=" + accountNo + ", accountHolder=" + accountHolder
				+ ", balance=" + balance + ", ts=" + ts + "]";
	}
	
	
	
}
