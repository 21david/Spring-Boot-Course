package com.udemycourse.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Doing my DB work: adding a membership account.");
    }

    @Override
    public boolean addDummy() {
        System.out.println(getClass() + ": addDummy()");
        return false;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": goToSleep()");

    }
}
