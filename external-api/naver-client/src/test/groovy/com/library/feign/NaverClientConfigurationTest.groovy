package com.library.feign

import com.library.feign.NaverClientConfiguration
import feign.RequestTemplate
import spock.lang.Specification

class NaverClientConfigurationTest extends Specification {
    NaverClientConfiguration configuration

    void setup(){
        configuration = new NaverClientConfiguration()
    }

    def "requestInterceptor의 header에 key값들이 적용된다" (){
        given:
        def requestTemplate = new RequestTemplate()
        def clientId = "id"
        def clientSecret = "secret"

        and: "interceptor를 타기 이전에는 header가 존재하지 않는다."
        requestTemplate.headers()["X-Naver-Client-Id"] == null
        requestTemplate.headers()["X-Naver-Client-Secret"] == null

        when:
        def interceptor = configuration.requestInterceptor(clientId, clientSecret)
        interceptor.apply(requestTemplate)

        then: "interceptor를 탄 이후에는 header가 추가된다."
        requestTemplate.headers()["X-Naver-Client-Id"].contains(clientId)
        requestTemplate.headers()["X-Naver-Client-Secret"].contains(clientSecret)
    }
}
