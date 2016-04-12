package com.cs362.account;

import com.cs362.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {
    private Double Balance;
    private int accountID;
    private List<Client> clientList;
    private int pin;

    public Account(double balance) {
        Balance = balance;
        Random random = new Random();
        accountID = random.nextInt(Integer.MAX_VALUE + 1);
        clientList = new ArrayList<Client>();
    }

    public Account() {
        Balance = 0.0;
        Random random = new Random();
        accountID = random.nextInt(Integer.MAX_VALUE + 1);
        clientList = new ArrayList<Client>();
    }

    public double getBalance() {
        return Balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public boolean addClient(Client client) {
        clientList.add(client);
        return true;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public boolean deposit(double ammount) {
        Balance += ammount;
        return true;
    }

    public boolean withdraw(double ammount) {
        Balance -= ammount;
        return true;
    }

    public int issueDebitCard(){
        pin = 0; //sets pin to invalid call setDebit to make valid
        return pin;
    }

    public boolean setDebitPin(int pinToSet){
        if(pinToSet>=1000 && pinToSet<=9999){
            pin = pinToSet;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean changeDebitPin(int currentPin, int pinToSet){
        if(currentPin == pin){
            pin = pinToSet;
            return true;
        }
        else{
            return false;
        }
    }

}
