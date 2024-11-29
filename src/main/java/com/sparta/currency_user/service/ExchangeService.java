package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.error.errorcode.ErrorCode;
import com.sparta.currency_user.error.exception.CustomException;
import com.sparta.currency_user.repository.ExchangeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    // 의존성 부여
    private final ExchangeRepository exchangeRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    public ExchangeResponseDto save(Long userId, Long currencyId, BigDecimal amountInKrw) {

        // Id로 유저 데이터 가져오기
        User findUser = userService.findUserById(userId);

        // Id로 통화 데이터 가져오기
        Currency findCurrency = currencyService.findCurrencyById(currencyId);

        // 환전 객체 생성
        Exchange exchange = new Exchange(amountInKrw, "normal", findCurrency.getExchangeRate());

        // 가져온 유저로 지정
        exchange.setUser(findUser);

        // 가져온 통화로 지정
        exchange.setCurrency(findCurrency);

        // 객체 데이터 DB에 저장
        exchangeRepository.save(exchange);

        // Dto 값 반환
        return new ExchangeResponseDto(exchange.getId(), exchange.getUser().getId(), exchange.getCurrency().getId(), exchange.getAmountInKrw(), exchange.getAmountAfterExchange(), exchange.getStatus());
    }

    public Exchange findExchangeById(Long id) {

        // ID로 조회
       return exchangeRepository.findById(id).orElseThrow(() ->  new CustomException(ErrorCode.EXCHANGE_ID_NOT_FOUND));
    }

    @Transactional
    public void cancelledExchange(Long id) {
        findExchangeById(id).updateStatus("cancelled");
    }
}
