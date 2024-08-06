package takayama.edu.design_pattern_challenge.controller;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import takayama.edu.design_pattern_challenge.domain.interfaces.ClientService;
import takayama.edu.design_pattern_challenge.domain.model.Client;

/*
 * Rest API controller for client. This class acts as a facade to other subsystems.
*/
@RestController
@RequestMapping("clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
	public ResponseEntity<Iterable<Client>> findAll() {
		return ResponseEntity.ok(clientService.findAll());
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(clientService.findByCpf(cpf));
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        } 
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Client client) {
        try {
            clientService.insert(client.getCpf(), client.getName());
            return ResponseEntity.ok(client);
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        }
	}

	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Client client) {
        try {
            clientService.update(client);
            return ResponseEntity.ok(client);    
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        }
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<?> deletar(@PathVariable String cpf) {
        try {
            clientService.delete(cpf);
		return ResponseEntity.ok().build();
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        }		
	}
    
    @GetMapping("/balance/{cpf}")
	public ResponseEntity<?> checkBalanceByCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(clientService.checkBalance(cpf));
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        } 
	}

    @PutMapping("/deposit/{value}")
	public ResponseEntity<?> deposit(@PathVariable Double value, @RequestBody Client client) {
        try {
            clientService.deposit(client.getCpf(), value);
            return ResponseEntity.ok(client);    
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        }
	}

    @PutMapping("/withdraw/{value}")
	public ResponseEntity<?> withdraw(@PathVariable Double value, @RequestBody Client client) {
        try {
            clientService.withdraw(client.getCpf(), value);
            return ResponseEntity.ok(client);    
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request error: " + e.getMessage());
        }
	}

}
