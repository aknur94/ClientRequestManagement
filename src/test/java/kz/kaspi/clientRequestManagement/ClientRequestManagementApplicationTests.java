package kz.kaspi.clientRequestManagement;

import kz.kaspi.clientRequestManagement.controller.ClientController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientRequestManagementApplicationTests {

	@Autowired
	private ClientController controller;

	@Test
	void contextLoads() {
		assert(controller != null);
	}



}
