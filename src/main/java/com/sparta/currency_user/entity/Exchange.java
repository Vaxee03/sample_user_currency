package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Entity
@Table(name = "exchange")
public class Exchange extends BaseEntity {

    // Key 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 id 연결하기
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 통화 id 연결하기
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    // 환전 전 금액
    private BigDecimal amountInKrw;

    // 환전 후 금액
    private BigDecimal amountAfterExchange;

    // 상태
    private String status;

    public Exchange() {}

    public Exchange(BigDecimal amountInKrw, String status, BigDecimal exchangeRate) {
       this.amountInKrw = amountInKrw;
       this.amountAfterExchange = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_EVEN);
       this.status = status;
    }

    public void updateStatus(String status) {
    }

    public void setUser(User findUser) {

    }

    public void setCurrency(Currency findCurrency) {
    }
}
