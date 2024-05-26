package com.aisr.initial.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagementStaffTest {

    @Test
    void getLevel() {
        ManagementStaff managementStaff = new ManagementStaff();
        managementStaff.setLevel("Supervisor");
        Assertions.assertEquals("Supervisor", managementStaff.getLevel());
    }
    @Test
    void testToString() {
        ManagementStaff managementStaff = new ManagementStaff("Alex Brown","Maryland",4515356878L,
                "alex.com","alex.brown","lex","11","Senior Manager","Sydney");
        String csvFormat = "Management,Alex Brown,Maryland,4515356878,alex.com,alex.brown,lex,11,N/A,Senior Manager,Sydney";
        Assertions.assertEquals(csvFormat, managementStaff.toString());
    }
}