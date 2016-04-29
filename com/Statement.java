package com;

import com.account.Account;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private List<Account> accountList;
    public Statement() {accountList = new ArrayList<Account>();}
    public void addAccount(Account account) {
        accountList.add(account);
    }
}