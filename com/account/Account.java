package com.account;

import com.client.Client;
import com.db.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {
    private Double Balance;
    private int accountID;
    private List<Integer> clientList;
    private List<Loan> loanList;
    private List<RecurringPayment> rpList;
    private boolean freezeStatus;
    private int pin;
    private double interest;
    private Database db;

    public Account(double balance, Database data) {
        Balance = balance;
        Random random = new Random();
        accountID = random.nextInt(Integer.MAX_VALUE + 1);
        clientList = new ArrayList<Integer>();
        loanList = new ArrayList<Loan>();
        rpList = new ArrayList<RecurringPayment>();
        freezeStatus = false;
        interest = 1.01;
        db = data;
    }

    public Account() {
        Balance = 0.0;
        Random random = new Random();
        accountID = random.nextInt(Integer.MAX_VALUE + 1);
        clientList = new ArrayList<Integer>();
        loanList = new ArrayList<Loan>();
        rpList = new ArrayList<RecurringPayment>();
        freezeStatus = false;
    }

    public double getBalance() {
        return Balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public boolean addClient(Client client) {
        if (freezeStatus) return false;
        clientList.add(client.getClientID());
        return true;
    }

    public List<Client> getClientList() {
        List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < clientList.size(); i++) {
            Client c = db.getClient(clientList.get(i));
            clients.add(c);
        }
        return clients;
    }

    public boolean deposit(double ammount) {
        if (freezeStatus) return false;
        Balance += ammount;
        return true;
    }

    public boolean withdraw(double ammount) {
        if (freezeStatus) return false;
        Balance -= ammount;
        return true;
    }

    public boolean addRecurringPayment(RecurringPayment r) {
        if (freezeStatus) return false;
        rpList.add(r);
        return true;
    }

    public boolean deleteRecurringPayment(int rpID) {
        if (freezeStatus) return false;
        int s = rpList.size();
        int n = 0;
        int ID = 0;
        boolean done = false;
        while (n<s && !done){
            ID = rpList.get(n).getID();
            if (ID == rpID) {
                rpList.remove(n);
                done = true;
            } else n++;
        }
        if (n == s) {
            return false;
        }
        return true;
    }

    public int issueDebitCard() {
        if (freezeStatus) return -1;
        pin = 0; //sets pin to invalid call setDebit to make valid
        return pin;
    }

    public boolean setDebitPin(int pinToSet) {
        if (freezeStatus) return false;
        if (pinToSet >= 1000 && pinToSet <= 9999) {
            pin = pinToSet;
            return true;
        } else {
            return false;
        }
    }

    public boolean changeDebitPin(int currentPin, int pinToSet) {
        if (freezeStatus) return false;
        if (currentPin == pin) {
            pin = pinToSet;
            return true;
        } else {
            return false;
        }
    }

    public void calculateInterest() {
        Balance *= interest;
    }

    public boolean deleteDebit() {
        if (freezeStatus) return false;
        pin = 0;
        return true;
    }

    public boolean setFreeze(boolean f) {
        freezeStatus = f;
        return true;
    }

    public boolean addLoan(int amount, int rate){
        Loan toAdd = new Loan(amount, rate);
        loanList.add(toAdd);
        return true;
    }

    public Loan getLoanFromID(int loanID){
        for(int i = 0; i < loanList.size(); i++){
            Loan temp = loanList.get(i);
            if(temp.getLoanID() == loanID) return temp;
        }
        return null;
    }


}
