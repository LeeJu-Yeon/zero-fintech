package com.zerobase.api.loan.request

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/api/v1")
class LoanRequestController(
        private val loanRequestServiceImpl: LoanRequestServiceImpl
) {

    @PostMapping("/request")
    fun loanRequest(
            @RequestBody loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): ResponseEntity<LoanRequestDto.LoanRequestResponseDto> {
        return ResponseEntity.ok(
                loanRequestServiceImpl.loanRequestMain(loanRequestInputDto)
        )
    }

}

/*
fun 메소드명(
        @RequestBody 매개변수명: 매개변수객체타입
): ResponseEntity<LoanRequestDto.LoanRequestResponseDto> {
    로직
}

LoanRequestDto.LoanRequestResponseDto 객체를 반환하는데,
ResponseEntity 로 감싸서 HTTP 응답을 전송
 */
