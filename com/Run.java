package com;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Bank bank = new Bank();
        Integer i,x,c;
        Double d;
        while(true) {
            String s = input.nextLine();
            switch (s) {
                case "q":
                    return;
                case "addAccount":
                    System.out.println("Input ClientID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.addAccount(i, "");
                    break;
                case "calculateInterest":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.calculateInterest(i);
                    break;
                case "cancelRecurringPayment":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input rpID");
                    s = input.nextLine();
                    c = Integer.parseInt(s);
                    bank.cancelRecurringPayment(i, c);
                    break;
                case "changeDebitPin":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input currentPin");
                    s = input.nextLine();
                    x = Integer.parseInt(s);
                    System.out.println("Input requestedPin");
                    s = input.nextLine();
                    c = Integer.parseInt(s);
                    bank.changeDebitPin(i, x, c);
                    break;
                case "checkBalance":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.checkBalance(i);
                    break;
                case "createClient":
                    System.out.println("Input name");
                    s = input.nextLine();
                    bank.createClient(s);
                    break;
                case "deleteAccount":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.deleteAccount(i);
                    break;
                case "deleteClient":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.deleteClient(i);
                    break;
                case "deleteDebit":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.deleteDebit(i);
                    break;
                case "depositFunds":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input ammount");
                    s = input.nextLine();
                    d = Double.parseDouble(s);
                    bank.depositFunds(i, d);
                    break;
                case "getStatement":
                    System.out.println("Input ClientID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.getStatement(i);
                    break;
                case "issueDebitCard":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.issueDebitCard(i);
                    break;
                case "setDebitPin":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input new pin");
                    s = input.nextLine();
                    c = Integer.parseInt(s);
                    bank.setDebitPin(i, c);
                    break;
                case "setRecurringPayment":
                    System.out.println("Input first AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input ammount");
                    s = input.nextLine();
                    d = Double.parseDouble(s);
                    System.out.println("Input second AccountID");
                    s = input.nextLine();
                    c = Integer.parseInt(s);
                    bank.setRecurringPayment(i,d,c);
                    break;
                case "toggleFreeze":
                    System.out.println("Input first ClientID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    bank.toggleFreeze(i);
                    break;
                case "transferFunds":
                    System.out.println("Input first AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input ammount");
                    s = input.nextLine();
                    x = Integer.parseInt(s);
                    System.out.println("Input second AccountID");
                    s = input.nextLine();
                    c = Integer.parseInt(s);
                    bank.transferFunds(i,x,c);
                    break;
                case "withdrawFunds":
                    System.out.println("Input AccountID");
                    s = input.nextLine();
                    i = Integer.parseInt(s);
                    System.out.println("Input ammount");
                    s = input.nextLine();
                    d = Double.parseDouble(s);
                    bank.withdrawFunds(i, d);
                    break;
            }
        }
    }

}