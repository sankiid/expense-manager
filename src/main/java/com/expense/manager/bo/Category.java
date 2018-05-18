package com.expense.manager.bo;

import com.expense.manager.enums.Type;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {

    private long id;
    private String name;
    private Date createdAt;
    private Type type;

    public Category() {
    }

    public Category(long id, String name, Date createdAt, Type type) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", type=" + type +
                '}';
    }
}
