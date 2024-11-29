package com.library.feign

import feign.RequestTemplate
import spock.lang.Specification

class KakaoClientConfigurationTest extends Specification {
    KakaoClientConfiguration configuration

    void setup(){
        configuration = new KakaoClientConfiguration()
    }

    def "requestInterceptor의 header에 key값들이 적용된다" (){
        given:
        def template = new RequestTemplate()
        def restApiKey = "key"

        and: "interceptor를 타기 이전에는 header가 존재하지 않는다."
        template.headers()["Authorization"] == null

        when:
        def interceptor = configuration.requestInterceptor(restApiKey)
        interceptor.apply(template)

        then: "interceptor를 탄 이후에는 header가 추가된다."
        template.headers()["Authorization"].contains("KakaoAK" + restApiKey)
    }
}
