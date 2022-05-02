package com.example.webproject.domain;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String number;
    @Column
    private String fio;
    @Column
    private String CVV;
    @Column
    private String time;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public Card() {
    }

    public Card(String number, String fio, String CVV, String time, Bill bill) {
        this.id = id;
        this.number = number;
        this.fio = fio;
        this.CVV = CVV;
        this.time = time;
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String bank) {
        this.number = bank;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

