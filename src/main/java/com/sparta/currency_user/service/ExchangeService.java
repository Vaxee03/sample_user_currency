package com.sparta.currency_user.service;

import com.sparta.currency_user.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    // 의존성 부여
    private final ExchangeRepository exchangeRepository;

}
