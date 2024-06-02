package com.aisr.initial.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This test class will test different methods of AdminStaff class
 */
class AdminStaffTest {

    /**
     * Tests getter and setter of AdminStaff class
     */
    @Test
    void setPositionType() {
        AdminStaff adminStaff = new AdminStaff();
        adminStaff.setPositionType("Full Time");
        Assertions.assertEquals("Full Time", adminStaff.getPositionType());
    }

    /**
     * Tests toString method
     */
    @Test
    void testToString() {
        AdminStaff adminStaff = new AdminStaff("Alex Brown", "Maryland", 4515356878L,
                "alex.com", "alex.brown", "lex", "11", "Full Time");
        String csvFormat = "Alex Brown,Maryland,4515356878,alex.com,alex.brown,lex,11,Full Time,N/A,N/A";
        Assertions.assertEquals(csvFormat, adminStaff.toString());
    }
}