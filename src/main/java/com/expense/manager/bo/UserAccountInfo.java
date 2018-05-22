package com.expense.manager.bo;

import java.io.Serializable;
import java.util.Date;

public class UserAccountInfo implements Serializable {

    private long id;
    private long userId;
    private Bank bank;
    private float amount;
    private String accountNumber;
    private Date createdAt;
    private Date updatedAt;


    public UserAccountInfo() {
    }

    public UserAccountInfo(long id, long userId, Bank bank, float amount, Date createdAt, Date updatedAt, String accountNumber) {
        this.id = id;
        this.userId = userId;
        this.bank = bank;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accountNumber = accountNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "UserAccountInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", bank=" + bank +
                ", amount=" + amount +
                ", accountNumber=" + accountNumber +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
