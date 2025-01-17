package takayama.edu.design_pattern_challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import takayama.edu.design_pattern_challenge.domain.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
