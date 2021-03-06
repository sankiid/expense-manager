package com.expense.manager.bo;

import java.io.Serializable;
import java.util.Date;

public class Income implements Serializable {

    private long id;
    private String notes;
    private float amount;
    private Date date;
    private Category category;
    private UserAccountInfo account;

    public Income() {
    }

    public Income(long id, String notes, float amount, Date date, Category category, UserAccountInfo account) {
        this.id = id;
        this.notes = notes;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UserAccountInfo getAccount() {
        return account;
    }

    public void setAccount(UserAccountInfo account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", notes='" + notes + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
