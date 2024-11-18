package com.library.controller

import com.library.service.BookApplicationService
import com.library.service.BookQueryService
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class BookControllerTest extends Specification {
    BookApplicationService bookApplicationService = Mock(BookApplicationService)

    BookController bookController
    // 웹서버 구동하지 않고도 웹 애플리케이션 컨트롤러 테스트
    // 실제 서블릿 컨테이너 구동시키지 않음
    MockMvc mockMvc

    void setup(){
        bookController = new BookController(bookApplicationService)
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build()
    }

    def "search"(){
        given:
        def givenQuery="HTTP"
        def givenPage = 1
        def givenSize = 10

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/books?query=${givenQuery}&page=${givenPage}&size=${givenSize}"))
                .andReturn()
                .response
        then:
        response.status == HttpStatus.OK.value()

        1* bookApplicationService.search(*_) >> {
            String query, int page, int size ->
                assert query == givenQuery
                assert page == givenPage
                assert size == givenSize
        }
    }

}
