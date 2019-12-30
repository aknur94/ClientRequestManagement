package kz.kaspi.clientRequestManagement.repo;

import kz.kaspi.clientRequestManagement.model.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepo extends CrudRepository<Client, Long> {
     Client findByHashCode(int hashCode);
}
