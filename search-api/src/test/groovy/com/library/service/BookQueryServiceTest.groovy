package com.library.service

import com.library.repository.BookRepository
import spock.lang.Specification

class BookQueryServiceTest extends Specification {
    BookRepository naverBookRepository = Mock(BookRepository)
    BookRepository kakaoBookRepository = Mock(BookRepository)

    BookQueryService bookQueryService

    void setup(){
        bookQueryService = new BookQueryService(naverBookRepository, kakaoBookRepository)
    }

    def "search 인자가 그대로 넘어가고, naver쪽으로 호출한다."(){
        given:
        def givenQuery="HTTP완벽가이드"
        def givenPage = 1
        def givenSize = 10
        when:
        bookQueryService.search(givenQuery, givenPage, givenSize)
        then:
        1 * naverBookRepository.search(*_) >> {
            String query, int page, int size ->
            assert query == givenQuery
            assert page == givenPage
            assert size == givenSize
        }

        // 카카오 쪽으로는 호출되지 않음
        and:
        0 * kakaoBookRepository.search(*_)
    }
}
