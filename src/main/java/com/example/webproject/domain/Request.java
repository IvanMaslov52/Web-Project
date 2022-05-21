package com.example.webproject.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String user_username;
    @Column
    private String status;
    @Column
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "bill_request_id")
    private BillRequest billRequest;
    @ManyToOne
    @JoinColumn(name = "card_request_id")
    private CardRequest cardRequest;

    public CardRequest getCardRequest() {
        return cardRequest;
    }

    public BillRequest getBillRequest() {
        return billRequest;
    }

    public Request()
    {
    }

    public Request(String user_username, String status, LocalDateTime time, BillRequest billRequest) {
        this.user_username = user_username;
        this.status = status;
        this.time = time;
        this.billRequest = billRequest;
    }

    public Request(String user_username, String status, LocalDateTime time, CardRequest cardRequest) {
        this.user_username = user_username;
        this.status = status;
        this.time = time;
        this.cardRequest = cardRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setBillRequest(BillRequest billRequest) {
        this.billRequest = billRequest;
    }

    public void setCardRequest(CardRequest cardRequest) {
        this.cardRequest = cardRequest;
    }


    public String toStringForBill() {
        return "Request{" +
                "id=" + id +
                ", user_username='" + user_username + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", billRequest=" + billRequest +
                '}';
    }
    public String toStringForCard() {
        return "Request{" +
                "id=" + id +
                ", user_username='" + user_username + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", cardRequest=" + cardRequest +
                '}';
    }
    public boolean isAgreed()
    {
        return this.status.equals("Agreed");
    }
    public boolean isCreated()
    {
        return this.status.equals("Сreated");
    }
}
