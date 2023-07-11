package com.zerobase.domain.repository

import com.zerobase.domain.domain.LoanReview
import org.springframework.data.jpa.repository.JpaRepository

interface LoanReviewRepository : JpaRepository<LoanReview, Long> {
    fun findByUserKey(userKey: String): LoanReview?
}

/*
코틀린에서 ? 는 nullable 의미
예외 처리 테스트 하려고 ? 붙인거
 */
