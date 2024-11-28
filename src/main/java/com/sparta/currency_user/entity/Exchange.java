package com.sparta.currency_user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
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
    private Long amount_in_krw;

    // 환전 후 금액
    private Long amount_after_exchange;

    // 상태
    private String status;
}
