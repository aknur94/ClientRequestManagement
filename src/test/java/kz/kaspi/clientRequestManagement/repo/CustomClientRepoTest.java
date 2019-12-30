package kz.kaspi.clientRequestManagement.repo;

import kz.kaspi.clientRequestManagement.ClientRequestManagementApplication;
import kz.kaspi.clientRequestManagement.model.Client;
import kz.kaspi.clientRequestManagement.util.BaseUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

class CustomClientRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    @Qualifier("customClientRepo")
    private CustomClientRepo clientRepo;

    @Test
    void clientSaveAndHashCode() {
        Client client = new Client();
        client.setPhone("87001231231");
        client.setFio("Name Surname");
        client.setOrganization("Some Test Organization");
        client.setBin("940912234123");
        int hash = BaseUtils.getHashCode(client);
        entityManager.persist(client);

        Client found = clientRepo.findByHashCode(hash);

        assertEquals(client.getFio(), found.getFio());
    }
}