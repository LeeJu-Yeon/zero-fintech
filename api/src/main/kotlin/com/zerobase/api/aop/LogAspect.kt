package com.zerobase.api.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LogAspect {

    val logger = KotlinLogging.logger { }

    /*
    특정 시점(join point)을 가져옴
    Ex. 해당 패키지 내의 클래스의 특정 메서드 호출 전, 후 또는 예외 발생 시점
     */
    @Pointcut("within(com.zerobase.api..*)")   // api 패키지와 하위 전부가 대상
    private fun isApi() {
    }

    @Around("isApi()")   // 위의 시점이 발생했을시
    fun loggingAspect(joinPoint: ProceedingJoinPoint): Any {

        // 성능 검사
        val stopWatch = StopWatch()
        stopWatch.start()

        val result = joinPoint.proceed()

        stopWatch.stop()

        // 로그 찍기
        logger.info { "${joinPoint.signature.name} ${joinPoint.args[0]} ${stopWatch.lastTaskTimeMillis}" }

        return result
    }

}
