package com.cs362.client;

import com.cs362.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    private String name;
    private int clientID;
    private List<Account> accountList;

    public Client() {
        name = "";
        Random random = new Random();
        clientID = random.nextInt(Integer.MAX_VALUE + 1);
        accountList = new ArrayList<Account>();
    }

    public Client(String name) {
        this.name = name;
        Random random = new Random();
        clientID = random.nextInt(Integer.MAX_VALUE + 1);
        accountList = new ArrayList<Account>();
    }

    public int getClientID() {
        return clientID;
    }

    public boolean attachAccount(Account account) {
        accountList.add(account);
        return true;
    }

    public boolean containsAccounts() {
        return !accountList.isEmpty();
    }

    public boolean removAccount(Account account) {
        return accountList.remove(account);
    }
}
