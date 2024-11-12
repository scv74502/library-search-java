package com.library.feign

import com.fasterxml.jackson.databind.ObjectMapper
import com.library.ApiException
import com.library.ErrorType
import com.library.NaverErrorResponse
import com.library.feign.NaverErrorDecoder
import feign.Request
import feign.Response
import org.springframework.http.HttpStatus
import spock.lang.Specification

class NaverErrorDecoderTest extends Specification {
    ObjectMapper objectMapper = Mock();
    NaverErrorDecoder errorDecoder = new NaverErrorDecoder(objectMapper);

    def "ApiException이 throw가 된다"(){
        given:
        def responseBody = Mock(Response.Body)
        def inputStream = new ByteArrayInputStream()
        def response = Response.builder()
            .status(400)
        .request(Request.create(Request.HttpMethod.GET, "testUrl", [:], null as Request.Body, null))
            .body(responseBody)
            .build( )

        1 * responseBody.asInputStream() >> inputStream
        1 * objectMapper.readValue(*_) >> new NaverErrorResponse("error!!", "SE03")

        when:
        errorDecoder.decode(_ as String, response)

        then:
        ApiException e = thrown()
        verifyAll {
            e.errorMsg == "error!!"
            e.httpStatus == HttpStatus.BAD_REQUEST
            e.errorType == ErrorType.EXTERNAL_API_ERROR
        }
    }
}
