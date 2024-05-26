package com.aisr.initial.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class RecruitTest {

    @Test
    void setEmail() {
        Recruit recruit = new Recruit();
        recruit.setEmail("test@test.com");
        Assertions.assertEquals("test@test.com", recruit.getEmail());
    }

    @Test
    void testToString() {
        Recruit recruit = new Recruit("Alex Brown", "Maryland", 4515356878L,
                "alex.com", "alex.brown", "lex", LocalDate.now(), "Masters",
                "Software", "Brisbane", "Alex", LocalDate.now());
        String csvFormat = "Alex Brown,Maryland,4515356878,alex.com,alex.brown,lex,".concat(LocalDate.now().toString())
                .concat(",Masters,Software,Brisbane,Alex,").concat(LocalDate.now().toString());
        Assertions.assertEquals(csvFormat, recruit.toString());
    }
}