package com.library.entity

import spock.lang.Specification

import java.time.LocalDateTime

class DailyStatTest extends Specification {
    def "create"(){
        given:
        def givenQuery = "HTTP"
        def givenLocalDateTime = LocalDateTime.of(2024, 1, 1, 1, 1, 1)

        when:
        def result = new DailyStat(query: givenQuery, eventDateTime: givenLocalDateTime)

        then:
        verifyAll(result){
            query == givenQuery
            eventDateTime == givenLocalDateTime
        }
    }
}
