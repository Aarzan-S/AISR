package com.aisr.initial.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdminStaffTest {

    @Test
    void setPositionType() {
        AdminStaff adminStaff = new AdminStaff();
        adminStaff.setPositionType("Full Time");
        Assertions.assertEquals("Full Time", adminStaff.getPositionType());
    }

    @Test
    void testToString() {
        AdminStaff adminStaff = new AdminStaff("Alex Brown","Maryland",4515356878L,
                "alex.com","alex.brown","lex","11","Full Time");
        String csvFormat = "Admin,Alex Brown,Maryland,4515356878,alex.com,alex.brown,lex,11,Full Time,N/A,N/A";
        Assertions.assertEquals(csvFormat, adminStaff.toString());
    }
}