package com.zerobase.api.test

class TestDto {
    data class UserInfoDto(
            val userKey: String,
            val userRegistrationNumber: String,
            val userName: String,
            val userIncomeAmount: Long
    )
}

/*
data class 는 생성자, equals(), hashCode(), toString() 등을 자동 생성
 */
