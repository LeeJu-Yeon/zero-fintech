package com.zerobase.domain.domain

import javax.persistence.*

@Entity
@Table(name = "USR_INFO")
class UserInfo(   // 생성자

        @Column(name = "usr_key")   // 컬럼명은 줄여서 쓰고
        val userKey: String,        // 변수명은 풀어서 쓰는거 선호

        @Column(name = "usr_reg_num")
        val userRegistrationNumber: String,

        @Column(name = "usr_nm")
        val userName: String,

        @Column(name = "usr_icm_amt")
        val userIncomeAmount: Long

) {   // 본문

    // PK 는 mysql 에서 알아서 생성하니까, 생성자쪽에 작성 x
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null   // id 는 null 가능, 초기값은 null 이란 의미

}

/*
코틀린에서 변수를 선언할 때
val 로 선언된 변수는 변경 불가능
var 로 선언된 변수는 변경 가능

val id: Long? = null 코틀린에서 변수를 선언하고 초기화하는 문법
? 는 널 가능성(nullability)을 나타내는 접미사
? 가 변수 타입 뒤에 붙으면 해당 변수는 null 값을 가질 수 있다는 의미
= null 은 변수의 초기값을 null 로 설정한다는 의미
 */
