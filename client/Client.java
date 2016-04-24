package com.cs362.client;

import com.cs362.account.Account;
import com.cs362.db.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    private String name;
    private int clientID;
    private List<Integer> accountList;
    private boolean freezeStatus;

    public Client() {
        name = "";
        Random random = new Random();
        clientID = random.nextInt(Integer.MAX_VALUE + 1);
        accountList = new ArrayList<Integer>();
	freezeStatus = false;
    }

    public Client(String name) {
        this.name = name;
        Random random = new Random();
        clientID = random.nextInt(Integer.MAX_VALUE + 1);
        accountList = new ArrayList<Integer>();
	freezeStatus = false;
    }

    public int getClientID() {
        return clientID;
    }

    public boolean attachAccount(Account account) {
        if(freezeStatus) return false;
        accountList.add(Integer.valueOf(account.getAccountID()));
        return true;
    }

    public List<Account> getAccounts() {
    	List<Account> accounts = new ArrayList<Account>();
	for(int i = 0; i < accountList.size(); i++) {
		Account a = db.getAccount(accountList.get(i).intValue());
		accounts.add(a);
	}
	return accounts;
    }

    public boolean containsAccounts() {
        return !accountList.isEmpty();
    }

    public boolean removeAccount(Account account) {
        if(freezeStatus) return false;
        return accountList.remove(account.getAccountID());
    }

    public boolean toogleFreezeStatus() {
    	freezeStatus = !freezeStatus;
	return true;
    }

    public boolean getFreezeStatus() {
    	return freezeStatus;
    }
}
