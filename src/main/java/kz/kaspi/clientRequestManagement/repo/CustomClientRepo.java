package kz.kaspi.clientRequestManagement.repo;

import kz.kaspi.clientRequestManagement.exception.ClientException;
import kz.kaspi.clientRequestManagement.model.Client;
import kz.kaspi.clientRequestManagement.util.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomClientRepo implements ClientRepo {

    @Autowired
    @Qualifier("clientRepo")
    private ClientRepo clientRepo;

    @Override
    public <S extends Client> S save(S s) {
        return clientRepo.save(s);
    }

    public <S extends Client> S clientSave(S s) throws ClientException {
        s.setAbbr(BaseUtils.getAbbr(s.getOrganization())); //Gets the abbreviation from organization and initiating it to abbr field
        if (!BaseUtils.isPhoneValid(s.getPhone())) {//Assuming that valid phone number starts with 87 after has 9 digits
            throw new ClientException(ClientException.INVALID_PHONE);
        }
        s.setHashCode(BaseUtils.getHashCode(s));
        Client client = clientRepo.findByHashCode(s.getHashCode());
        if (client != null) {
            throw new ClientException(ClientException.ALREADY_EXISTS);
        }
        return clientRepo.save(s);
    }

    @Override
    public <S extends Client> Iterable<S> saveAll(Iterable<S> iterable) {
        return clientRepo.saveAll(iterable);
    }

    @Override
    public Optional<Client> findById(Long aLong) {
        return clientRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return clientRepo.existsById(aLong);
    }

    @Override
    public Iterable<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Iterable<Client> findAllById(Iterable<Long> iterable) {
        return clientRepo.findAllById(iterable);
    }

    @Override
    public long count() {
        return clientRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepo.deleteById(aLong);
    }

    @Override
    public void delete(Client client) {
        clientRepo.delete(client);
    }

    @Override
    public void deleteAll(Iterable<? extends Client> iterable) {
        clientRepo.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        clientRepo.deleteAll();
    }

    @Override
    public Client findByHashCode(int hashCode) {
        return clientRepo.findByHashCode(hashCode);
    }
}
