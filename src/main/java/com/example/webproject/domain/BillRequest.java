package com.example.webproject.domain;

import javax.persistence.*;

@Entity
public class BillRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String bank;
    @Column
    private String fio;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Double money;

    public BillRequest(String bank, String fio, User user, Double money) {
        this.bank = bank;
        this.fio = fio;
        this.user = user;
        this.money = money;
    }

    public BillRequest() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "BillRequest{" +
                "id=" + id +
                ", bank='" + bank + '\'' +
                ", fio='" + fio + '\'' +
                ", user=" + user +
                ", money=" + money +
                '}';
    }
}
