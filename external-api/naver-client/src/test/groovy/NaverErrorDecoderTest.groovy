import com.fasterxml.jackson.databind.ObjectMapper
import com.library.NaverErrorResponse
import com.library.feign.NaverErrorDecoder
import feign.Request
import feign.RequestTemplate
import feign.Response
import spock.lang.Specification

class NaverErrorDecoderTest extends Specification {
    ObjectMapper objectMapper = Mock();
    NaverErrorDecoder errorDecoder = new NaverErrorDecoder(objectMapper);

    def "에러디코더에서 에러 발생시, RuntimeException에서 예외가 throw 된다"(){
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
        RuntimeException e = thrown()
        e.message == "error!!"
    }
}
