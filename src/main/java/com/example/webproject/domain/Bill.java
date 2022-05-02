package com.example.webproject.domain;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotEmpty(message = "bank should not be empty")
    @Size(min = 4 , message =" bank should be more then 4  symbols")
    private String bank;
    @Column
    @NotEmpty(message = "FIO should not be empty")
    private String fio;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Double money;


    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }

    public String getFio() {
        return fio;
    }

    public Bill() {}



    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }



    public Bill(String bank, String fio, User user, Double money) {

        this.bank = bank;
        this.fio = fio;
        this.user = user;
        this.money = money;
    }

    public void setFio(String bank) {
        this.fio = bank;
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

    public void setBank(String check) {
        this.bank = check;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", bank='" + bank + '\'' +
                ", fio='" + fio + '\'' +
                ", money=" + money +
                '}';
    }
}
