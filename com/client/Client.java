package com.client;

import com.account.Account;
import com.db.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public String name;
    private int clientID;
    private List<Integer> accountList;
    private boolean freezeStatus;
    private Database db;

    public Client(Database data) {
        name = "";
        Random random = new Random();
        clientID = random.nextInt();
        accountList = new ArrayList<Integer>();
        freezeStatus = false;
        db = data;
    }

    public Client(String name) {
        this.name = name;
        Random random = new Random();
        clientID = random.nextInt();
        accountList = new ArrayList<Integer>();
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

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        for (int i = 0; i < accountList.size(); i++) {
            Account a = db.getAccount(accountList.get(i).intValue());
            accounts.add(a);
        }
        return accounts;
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

    public boolean transferAccount(int accID, int clientIDtoTransferTo) {
        Client toTransferTo = db.getClient(clientIDtoTransferTo);
        Account acc = db.getAccount(accID);
        removeAccount(acc);
        toTransferTo.attachAccount(acc);
        db.putClient(toTransferTo);
        return true;
    }

    /*
    public boolean LoanPayment(int lID, double payment) {
    	Loan l = getLoanByID(lID);
	if(!l.receivePayment(payment)) return false;
	if(l.getLoanAmount() == 0) LoanList.remove(l);
	return true;
    }
    */
}
