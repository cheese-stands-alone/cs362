package com.client;

import com.account.Account;
import com.db.Database;
import com.client.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public String name;
    private int clientID;
    private List<Integer> accountList;
    private List<Loan> loanList;
    private boolean freezeStatus;

    public Client() {
        name = "";
        Random random = new Random();
        clientID = random.nextInt();
	System.out.println("\n  Client ID: " + clientID + '\n');
        accountList = new ArrayList<Integer>();
        loanList = new ArrayList<Loan>();
        freezeStatus = false;
    }

    public Client(String name) {
        this.name = name;
        Random random = new Random();
        clientID = random.nextInt();
	System.out.println("\n  Client ID: " + clientID + '\n');
        accountList = new ArrayList<Integer>();
        loanList = new ArrayList<Loan>();
        freezeStatus = false;
    }

    public int getClientID() {
        return clientID;
    }

    public boolean attachAccount(Account account) {
        if (freezeStatus) return false;
        accountList.add(Integer.valueOf(account.getAccountID()));
        return true;
    }

    public List<Integer> getAccounts() {
        return accountList;
    }

    public boolean containsAccounts() {
        return !accountList.isEmpty();
    }

    public boolean removeAccount(Account account) {
        if (freezeStatus) return false;
        accountList.remove(account.getAccountID());
        return true;
    }

    public boolean toogleFreezeStatus() {
        freezeStatus = !freezeStatus;
        return true;
    }

    public boolean getFreezeStatus() {
        return freezeStatus;
    }

    public boolean transferAccount(int accID, int clientIDtoTransferTo, Database db) {
        Client toTransferTo = db.getClient(clientIDtoTransferTo);
        Account acc = db.getAccount(accID);
        removeAccount(acc);
        toTransferTo.attachAccount(acc);
        db.putClient(toTransferTo);
        return true;
    }

    public boolean addLoan(double amount, double rate) {
        Loan toAdd = new Loan(amount, rate);
        loanList.add(toAdd);
        return true;
    }

    public List<Loan> getLoans() {
	return loanList;
    }

    public Loan getLoanFromID(int loanID) {
        for (int i = 0; i < loanList.size(); i++) {
            Loan temp = loanList.get(i);
            if (temp.getLoanID() == loanID) return temp;
        }
        return null;
    }

    public boolean loanPayment(int lID, double payment) {
    	Loan l = this.getLoanFromID(lID);
	if(!l.receivePayment(payment)) return false;
	else if(l.getLoanAmount() <= 0.0) LoanList.remove(l);
	return true;
    }

}
