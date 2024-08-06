package takayama.edu.design_pattern_challenge.domain.interfaces;

import java.security.InvalidParameterException;

import takayama.edu.design_pattern_challenge.domain.model.Client;

/**
 * Interface which defines Client's Strategy design pattern.
 * 
 * @author takayama
 */
public interface ClientService {

	Iterable<Client> findAll();
	Client findByCpf(String name) throws InvalidParameterException;
	void insert(String cpf, String name) throws InvalidParameterException;
	void update(Client client) throws InvalidParameterException;
	void delete(String cpf) throws InvalidParameterException;

	Double checkBalance(String cpf) throws InvalidParameterException;
    boolean deposit(String cpf, Double value) throws InvalidParameterException;
    boolean withdraw(String cpf, Double value) throws InvalidParameterException;
}