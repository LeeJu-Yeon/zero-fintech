package com.zerobase.domain.repository

import com.zerobase.domain.domain.UserInfo
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository : JpaRepository<UserInfo, Long> {
    fun findByUserKey(userKey: String): UserInfo
}

/*
fun findByUserKey(매개변수: 매개변수타입): 반환객체
 */
