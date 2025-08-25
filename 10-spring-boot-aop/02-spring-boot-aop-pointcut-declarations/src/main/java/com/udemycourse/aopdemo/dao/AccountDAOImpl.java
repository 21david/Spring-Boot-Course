package com.udemycourse.aopdemo.dao;

import com.udemycourse.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my DB work: adding an account.");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println("Account.getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println("Account.setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("Account.getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("Account.setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
