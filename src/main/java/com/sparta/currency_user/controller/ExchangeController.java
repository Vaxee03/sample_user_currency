package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    // 의존성 부여
    private final ExchangeService exchangeService;

    // 환전 요청 수행
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchange(@RequestBody ExchangeRequestDto Dto){
        return ResponseEntity.ok().body(exchangeService.save(Dto.getUserId(),Dto.getCurrencyId(),Dto.getAmountInKrw()));
    }

    // 고객 ID를 입력하여 환전 조회
    @GetMapping("/{id}")
    public ResponseEntity<Exchange> findExchange(@PathVariable Long id){
        return ResponseEntity.ok().body(exchangeService.findExchangeById(id));
    }


}
