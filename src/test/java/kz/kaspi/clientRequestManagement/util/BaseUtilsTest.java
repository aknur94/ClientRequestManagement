package kz.kaspi.clientRequestManagement.util;

import kz.kaspi.clientRequestManagement.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseUtilsTest {

    @Test
    void getAbbr() {
        assertEquals("SDU", BaseUtils.getAbbr("Suleymen Demirel University"));
        assertEquals("NBOK", BaseUtils.getAbbr("National Bank of Kazakhstan"));
        assertEquals("KB", BaseUtils.getAbbr("Kaspi Bank"));
    }

    @Test
    void isPhoneValid() {
        assertEquals(true, BaseUtils.isPhoneValid("87001231212"));
        assertEquals(false, BaseUtils.isPhoneValid("8(707)1233412"));
        assertEquals(false, BaseUtils.isPhoneValid("phone number"));
        assertEquals(false, BaseUtils.isPhoneValid("8700123123123"));
        assertEquals(false, BaseUtils.isPhoneValid("89081231233"));
    }

    @Test
    void getHashCode() {
        Client clientA = new Client();
        clientA.setPhone("87001231212");
        clientA.setFio("Ivanov Ivan Ivanovich");
        clientA.setBin("911216301213");
        clientA.setOrganization("Test Organization");

        Client clientB = new Client();
        clientB.setPhone("87001231212");
        clientB.setFio("Ivanov Ivan Ivanovich");
        clientB.setBin("911216301213");
        clientB.setOrganization("Test Organization");

        assertEquals(BaseUtils.getHashCode(clientA), BaseUtils.getHashCode(clientB));

        clientB.setPhone("87001231213");
        assertNotEquals(BaseUtils.getHashCode(clientA), BaseUtils.getHashCode(clientB));

        clientB.setPhone("87001231212");
        assertEquals(BaseUtils.getHashCode(clientA), BaseUtils.getHashCode(clientB));

        clientB.setOrganization("Kaspi Bank");
        clientB.setBin("830313831284");
        clientB.setPhone("87001234312");
        clientB.setFio("Name Surname");
        assertNotEquals(BaseUtils.getHashCode(clientA), BaseUtils.getHashCode(clientB));
    }
}