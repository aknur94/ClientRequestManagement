package kz.kaspi.clientRequestManagement.repo;

import kz.kaspi.clientRequestManagement.exception.ClientException;
import kz.kaspi.clientRequestManagement.model.Client;
import kz.kaspi.clientRequestManagement.util.BaseUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(CustomClientRepo.class)
class CustomClientRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomClientRepo clientRepo;

    @Test
    void getByHashCode() {
        Client client = new Client();
        client.setPhone("87001231231");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940912234123");
        client.setAbbr("STO");
        int hash = BaseUtils.getHashCode(client);
        client.setHashCode(hash);
        entityManager.persist(client);
        Client found = clientRepo.findByHashCode(hash);
        assertEquals(client.getFio(), found.getFio());
    }

    @Test
    void checkAbbr() {
        Client client = new Client();
        client.setPhone("87001231231");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940909133123");
        try {
            Client saved = clientRepo.clientSave(client);
            assertEquals("STO", saved.getAbbr());
        } catch (ClientException e) {
            assert(false);
        }
    }

    @Test
    void checkHashCode() {
        Client client = new Client();
        client.setPhone("87001231231");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940909133123");
        int hash = BaseUtils.getHashCode(client);
        try {
            Client saved = clientRepo.clientSave(client);
            assertEquals(hash, saved.getHashCode());
        } catch (ClientException e) {
            assert(false);
        }
    }

    @Test
    void checkPhoneValidationExceedDigit() {
        Client client = new Client();
        client.setPhone("870012312311");//exceed 1 in the end
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940909133123");
        try {
            Client saved = clientRepo.clientSave(client);
            assert(false);
        } catch (ClientException e) {
            assert(true);
        }
    }

    @Test
    void checkPhoneValidationHasLetter() {
        Client client = new Client();
        client.setPhone("870o1231231");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940909133123");
        try {
            Client saved = clientRepo.clientSave(client);
            assert(false);
        } catch (ClientException e) {
            assert(true);
        }
    }

    @Test
    void checkPhoneValidationHasLessDigits() {
        Client client = new Client();
        client.setPhone("870012312");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940909133123");
        try {
            Client saved = clientRepo.clientSave(client);
            Client duplicate = clientRepo.clientSave(client);
            assert(false);
        } catch (ClientException e) {
            assert(true);
        }
    }

    @Test
    void checkAlreadyHasClientException() {
        Client client = new Client();
        client.setPhone("87001231212");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940909133123");
    }
}