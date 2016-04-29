package com.account;


    public class RecurringPayment {
    	
	private int rpID;
	private int receivingAccount;
	private double payment;

	public RecurringPayment() {
		rpID = 0;
		receivingAccount = 0;
		payment = 0;
	}

	public RecurringPayment(int aID, double amount, int id) {
		rpID = id;
		receivingAccount = aID;
		payment = amount;
	}

	public int getID() {return rpID;}
	public int getReceivingAccount() {return receivingAccount;}
	public double getPayment() {return payment;}
    }
