package com.cs362;

import com.cs362.account.Account;
import com.cs362.client.Client;
import com.cs362.db.Database;

public class Bank {
    Database database;

    public Bank() {
        database = new Database();
    }

    public boolean createClient(String name) {
        Client client = new Client(name);
        return database.putClient(client);
    }

    public boolean delieteClient(int clientID) {
        Client client;
        try {
            client = database.getClient(clientID);
        } catch (Exception e) {
            return false;
        }
        if (client.containsAccounts()) {
            return false;
        }
        return database.deleteClient(clientID);
    }

    public boolean addAccount(int clientID, String type) {
        //TODO for more accout types
        Client client;
        try {
            client = database.getClient(clientID);
        } catch (Exception e) {
            return false;
        }
        Account account = new Account();
        if (!account.addClient(client)) {
            return false;
        }
        if (!client.attachAccount(account)) {
            return false;
        }
        if (!database.updateClient(client)) {
            return false;
        }
        return database.putAccount(account);
    }

    public boolean deleteAccount(int accountID) {
        Account account = database.getAccount(accountID);
        for (Client client : account.getClientList()) {
            if (!client.removAccount(account)) {
                return false;
            }
            if (!database.updateClient(client)) {
                return false;
            }
        }
        return database.deleteAccount(account.getAccountID());
    }

    public double checkBalance(int accountID) {
        Account account = database.getAccount(accountID);
        return account.getBalance();
    }

    public boolean modifyAccount() {
        //TODO for account types
        return false;
    }

    public boolean depositFunds(int accountID, double ammount) {
        Account account = database.getAccount(accountID);
        if (!account.deposit(ammount)) {
            return false;
        }
        return database.updateAccount(account);
    }

    public boolean withdrawFunds(int accountID, double ammount) {
        Account account = database.getAccount(accountID);
        if (!account.withdraw(ammount)) {
            return false;
        }
        return database.updateAccount(account);
    }

    public boolean transferFunds(int accountA, int accountB, int ammount) {
        if (!withdrawFunds(accountA, ammount)) {
            return false;
        }
        return depositFunds(accountB, ammount);
    }

}
