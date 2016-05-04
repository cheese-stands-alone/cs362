package com;

import com.account.Account;
import com.client.Loan;
import com.account.RecurringPayment
import com.client.Client;
import com.db.Database;

import java.util.List;
import java.util.ArrayList;

public class Bank {
    Database database;

    public Bank() {
        database = new Database();
    }

    public boolean createClient(String name) {
        Client client = new Client(name);
        return database.putClient(client);
    }

    public boolean deleteClient(int clientID) {
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
        //TODO for more account types
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
            if (!client.removeAccount(account)) {
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

    public boolean setRecurringPayment(int accountID1, double payment, int accountID2) {
        Account account = database.getAccount(accountID1);
        Account account2 = database.getAccount(accountID2);
        if (account2 == null) return false;
        account.addRecurringPayment(payment, accountID2);
        return database.updateAccount(account);
    }

    public boolean cancelRecurringPayment(int accountID, int rpID) {
        Account account = database.getAccount(accountID);
        account.deleteRecurringPayment(rpID);
        return database.updateAccount(account);
    }

    public boolean issueDebitCard(int accountID) {
        Account account = database.getAccount(accountID);
        account.issueDebitCard();
        return true;
    }

    public boolean changeDebitPin(int accountID, int currentPin, int requestedPin) {
        Account account = database.getAccount(accountID);
        boolean toReturn;
        toReturn = account.changeDebitPin(currentPin, requestedPin);
        return toReturn;
    }

    public boolean setDebitPin(int accountID, int pinToSet) {
        Account account = database.getAccount(accountID);
        boolean toReturn;
        toReturn = account.setDebitPin(pinToSet);
        return toReturn;
    }

    public boolean calculateInterest(int accountID) {
        Account account = database.getAccount(accountID);
        account.calculateInterest();
        return database.updateAccount(account);
    }

    public Statement getStatement(int clientID) {
        Client client = database.getClient(clientID);
        List<Integer> list = client.getAccounts();
        List<Account> accounts = new ArrayList<Account>();
        for (Integer i : list) {
            Account a = db.getAccount(list.get(i));
            accounts.add(a);
        }

        Statement statement = new Statement();
        for (Account account : accounts) {
            statement.addAccount(account);
        }
        return statement;
    }

    public boolean deleteDebit(int accountID) {
        Account account = database.getAccount(accountID);
        account.deleteDebit();
        return database.updateAccount(account);
    }

    public boolean toggleFreeze(int clientID) {
        Client client = database.getClient(clientID);
        client.toogleFreezeStatus();
        boolean f = client.getFreezeStatus();
        List<Integer> list = client.getAccounts();
        int size = list.size();
        int n = 0;
        while (n < size) {
            Account account = database.getAccount(list.get(n));
            account.setFreeze(f);
            database.updateAccount(account);
        }
        return database.updateClient(client);
    }

    public boolean addLoan(double amount, double interest, int clientID) {
        Client c = database.getClient(clientID);
        c.addLoan(amount, interest);
        return database.updateClient(c);
    }

    public boolean payLoan(int cID, int lID, double payment) {
        Client c = database.getClient(cID);
        if (!c.loanPayment(lID, payment)) return false;
        return database.updateClient(c);
    }

    public List<Loan> viewLoans(int cID) {
    	Client c = database.getClient(cID);
	return c.getLoans();
    }

    public double calculateInterestOnLoan(int clientID, int loanID) {
        Client c = database.getClient(clientID);
        Loan toCalc = c.getLoanFromID(loanID);

        return toCalc.calculateIntrestOnLoan();
    }

    public double calculateFees(int cID) {
        Client c = database.getClient(cID);
        List<Integer> accL = c.getAccounts();
        int i = 0;
        double total = 0.0;
        Account a;
        while (i < accL.size()) {
            a = database.getAccount(accL.get(i));
            total += a.calculatePayments();
            i++;
        }
        return total;
    }

    public boolean transferAccount(int clientID1, int clientID2, int accID) {
        Client toTransferFrom = database.getClient(clientID1);

        return toTransferFrom.transferAccount(accID, clientID2, database);
    }

}
