package com.udemycourse.aopdemo.dao;

import com.udemycourse.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vipFlag);
    boolean doWork();
}
