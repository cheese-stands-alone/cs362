package com.account;

import java.util.Random;

/**
 * Created by Zakk on 4/28/2016.
 * Updated by Austin on 5/3/2016.
 */
public class Loan {
    public int loanID;
    public double loanInterest;
    public double loanAmount;

    public Loan(double ammount, double rate){
        Random random = new Random();
        loanInterest = rate;
        loanAmount = ammount;
        loanID = Math.abs(random.nextInt());
	System.out.println("\n  Loan ID: " + loanID + '\n');
    }

    public double calculateIntrestOnLoan(){
        loanAmount *= loanInterest;
        return loanAmount;
    }

    public int getLoanID(){
        return loanID;
    }

    public double getLoanAmount(){
	return loanAmount;
    }

    public boolean receivePayment(double payment){
	if(payment >= loanAmount) {
		if(payment > loanAmount) {
			double over = payment - loanAmount;
			System.out.println(over + " was returned, since payment was over remaining loan amount");
		}
		loanAmount = 0.0;
	}
	else loanAmount -= payment;
	return true;
    }

}
