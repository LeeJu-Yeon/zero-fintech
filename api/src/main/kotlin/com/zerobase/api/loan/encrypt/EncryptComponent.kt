package com.zerobase.api.loan.encrypt

import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class EncryptComponent {

    // 키값 원래는 따로 관리해야
    companion object {
        private const val secretKey = "12345678901234561234567890123456"
    }

    private val encoder = Base64.getEncoder()
    private val decoder = Base64.getDecoder()

    // 암호화
    fun encryptString(encryptString: String): String {
        val encryptedString = cipherPkcs5(Cipher.ENCRYPT_MODE, secretKey)
                .doFinal(encryptString.toByteArray(Charsets.UTF_8))

        return String(encoder.encode(encryptedString))
        // 암호화된 결과를 인코딩하여 문자열로 변환
    }

    // 복호화
    fun decryptString(decryptString: String): String {
        // 디코딩 먼저하고, 복호화
        val byteString = decoder.decode(decryptString.toByteArray(Charsets.UTF_8))

        return String(cipherPkcs5(Cipher.DECRYPT_MODE, secretKey).doFinal(byteString))
    }

    // 암호화 복호화에 필요한 Cipher 객체 생성
    fun cipherPkcs5(opMode: Int, secretKey: String): Cipher {
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opMode, sk, iv)
        return c
    }

}
