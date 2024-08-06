package takayama.edu.design_pattern_challenge.service;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import takayama.edu.design_pattern_challenge.domain.interfaces.ClientService;
import takayama.edu.design_pattern_challenge.domain.model.Account;
import takayama.edu.design_pattern_challenge.domain.model.Client;
import takayama.edu.design_pattern_challenge.domain.repository.AccountRepository;
import takayama.edu.design_pattern_challenge.domain.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
    private final boolean SUCCESS = true;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByCpf(String cpf) throws InvalidParameterException {
        Client client = clientRepository.findByCpf(cpf);

        if(client != null) {
            return client;
        } else {
            throw new InvalidParameterException("There is no client with cpf: " + cpf);
        }
    }

    @Override
    public void insert(String cpf, String name) throws InvalidParameterException {
        Client client = clientRepository.findByCpf(cpf);

        if(client != null) {
            throw new InvalidParameterException("Cpf already exists in database: " + cpf);
        }
        Account account = new Account();
        account.setBalance(0.0);
        accountRepository.save(account);

        client = new Client(cpf, name, account);
        clientRepository.save(client);
    }

    @Override
    public void update(Client client) throws InvalidParameterException {
        Client clientDb = findByCpf(client.getCpf());

        clientDb.setName(client.getName());
        clientRepository.save(clientDb);
    }

    @Override
    public void delete(String cpf) throws InvalidParameterException {
        Client client = findByCpf(cpf);

        clientRepository.delete(client);
    }

    @Override
    public Double checkBalance(String cpf) throws InvalidParameterException {
        Client client = findByCpf(cpf);

        return client.getAccount().getBalance();
    }

    @Override
    public boolean deposit(String cpf, Double value) throws InvalidParameterException {
        Client client = findByCpf(cpf);
        Double balance = client.getAccount().getBalance();

        if(value > 0) {
            client.getAccount().setBalance(balance + value);
            accountRepository.save(client.getAccount());
            return SUCCESS;
        } else {
            throw new InvalidParameterException("Invalid value");
        }
    }

    @Override
    public boolean withdraw(String cpf, Double value) throws InvalidParameterException {
        Client client = findByCpf(cpf);
        Double balance = client.getAccount().getBalance();

        if(value > 0  && balance >= value) {
            client.getAccount().setBalance(balance - value);
            accountRepository.save(client.getAccount());
            return SUCCESS;
        } else {
            throw new InvalidParameterException("Invalid value or insufficient balance");
        }
    }
}
