package com.account;

import java.util.Random;

/**
 * Created by Zakk on 4/28/2016.
 */
public class Loan {
    public int loanID;
    public double loanInterest;
    public double loanAmount;

    public Loan(int ammount, double rate){
        Random random = new Random();
        loanInterest = rate;
        loanAmount = ammount;
        loanID = Math.abs(random.nextInt());
    }

    public double calculateIntrestOnLoan(){
        loanAmount *= loanInterest;
        return loanAmount;
    }

    public int getLoanID(){
        return loanID;
    }

}
