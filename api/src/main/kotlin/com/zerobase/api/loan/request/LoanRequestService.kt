package com.zerobase.api.loan.request

import com.zerobase.domain.domain.UserInfo

interface LoanRequestService {
    fun loanRequestMain(
            loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto

    fun saveUserInfo(
            userInfoDto: UserInfoDto
    ): UserInfo

    fun loanRequestReview(userInfoDto: UserInfoDto)
}

/*
fun 메소드명(
        매개변수명: 매개변수객체타입
): 반환객체타입
 */
