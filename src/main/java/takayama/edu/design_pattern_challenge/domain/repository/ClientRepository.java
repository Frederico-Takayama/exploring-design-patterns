package takayama.edu.design_pattern_challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import takayama.edu.design_pattern_challenge.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

    Client findByCpf(String cpf);
}
