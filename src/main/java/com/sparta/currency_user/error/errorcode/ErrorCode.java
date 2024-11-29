package com.sparta.currency_user.error.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    CURRENCY_ID_NOT_FOUND(NOT_FOUND,"해당 통화 ID를 찾을 수 없습니다."),
    EXCHANGE_ID_NOT_FOUND(NOT_FOUND,"해당 환전 ID를 찾을 수 없습니다."),
    USER_ID_NOT_FOUND(NOT_FOUND,"해당 유저 ID를 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다");

    private final HttpStatus httpStatus;
    private final String detail;
}
