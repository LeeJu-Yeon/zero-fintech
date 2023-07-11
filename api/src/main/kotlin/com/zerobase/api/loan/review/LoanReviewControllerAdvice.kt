package com.zerobase.api.loan.review

import com.zerobase.api.exception.CustomException
import com.zerobase.api.exception.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// 컨트롤러 지정 안하면 여러 컨트롤러의 예외를 처리
@RestControllerAdvice(basePackageClasses = [LoanReviewController::class])
class LoanReviewControllerAdvice {

    @ExceptionHandler(CustomException::class)   // 해당 Exception 만 처리
    fun customExceptionHandler(customException: CustomException) =
            ErrorResponse(customException).toResponseEntity()

}
