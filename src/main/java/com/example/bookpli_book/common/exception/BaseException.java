package com.example.bookpli_book.common.exception;

import com.example.bookpli_book.common.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}
