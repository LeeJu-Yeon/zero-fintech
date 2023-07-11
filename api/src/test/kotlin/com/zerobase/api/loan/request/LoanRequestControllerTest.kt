package com.zerobase.api.loan.request

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.zerobase.api.loan.GenerateKey
import com.zerobase.api.loan.encrypt.EncryptComponent
import com.zerobase.domain.domain.UserInfo
import com.zerobase.domain.repository.UserInfoRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/*
@WebMvcTest 를 사용하면 특정 컨트롤러를 테스트할 수 있다
테스트 대상 컨트롤러와 관련된 빈(bean)들만 자동으로 생성된다 -> 서비스, 컴포넌트는 생성 x -> 그래서 가볍다
 */
@WebMvcTest(LoanRequestController::class)
internal class LoanRequestControllerTest {

    /*
    lateinit 은 변수를 선언하고 초기화하는 시점이 다를 때 유용
    예를 들어, 의존성 주입(Dependency Injection)을 통해 변수를 초기화할 때 사용
     */

    private lateinit var mockMvc: MockMvc

    private lateinit var loanRequestController: LoanRequestController

    private lateinit var generateKey: GenerateKey

    private lateinit var encryptComponent: EncryptComponent

    private val userInfoRepository: UserInfoRepository = mockk()

    private lateinit var mapper: ObjectMapper

    @MockBean
    private lateinit var loanRequestServiceImpl: LoanRequestServiceImpl

    // 컨트롤러의 공통 URL
    companion object {
        private const val baseUrl = "/fintech/api/v1"
    }

    @BeforeEach
    fun init() {
        generateKey = GenerateKey()

        encryptComponent = EncryptComponent()

        loanRequestServiceImpl = LoanRequestServiceImpl(
                generateKey, userInfoRepository, encryptComponent
        )

        loanRequestController = LoanRequestController(loanRequestServiceImpl)

        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController).build()

        mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
    }

    @Test
    @DisplayName("유저 요청이 들어오면 정상 응답을 주어야 한다.")
    fun testNormalCase() {
        // given
        val loanRequestInfoDto: LoanRequestDto.LoanRequestInputDto =
                LoanRequestDto.LoanRequestInputDto(
                        userName = "TEST",
                        userIncomeAmount = 10000,
                        userRegistrationNumber = "000101-1234567"
                )

        every { userInfoRepository.save(any()) } returns UserInfo("", "", "", 1)

        // when
        // then

        mockMvc.post(
                "$baseUrl/request"
        ) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(loanRequestInfoDto)
        }.andExpect {
            status { isOk() }
        }
    }

}
