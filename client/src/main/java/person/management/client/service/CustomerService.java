package person.management.client.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import person.management.client.model.Customer;
import person.management.client.repository.CustomerRepository;
import person.management.client.repository.PersonRepository;


@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    // Método para obtener todos los clientes 
    public Flux<Customer> getAll() { 
        return Flux.fromIterable(customerRepository.findAll()); 
    }
 
    public CustomerService(CustomerRepository customerRepository, PersonRepository personRepository) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
      
    }

    public Mono<Object> create(Customer customer) {
        // Verificar si la persona existe
        return Mono.justOrEmpty(personRepository.findById(customer.getIdentification()))
            .flatMap(existingPerson -> {
                // Verificar si ya existe un cliente con la misma identificación
                log.info("Person exists with id: {}", customer.getIdentification());

                return Mono.justOrEmpty(customerRepository.findById(customer.getIdentification()))
                    .flatMap(existingCustomer -> {
                        // Si ya existe un cliente, devolver un error
                        log.warn("Ya existe un cliente registrado con la identificacion: {}", customer.getIdentification());

                        return Mono.error(new IllegalArgumentException("Ya existe un cliente registrado : " + customer.getIdentification()));
                    })
                    .switchIfEmpty(
                        // Si la persona existe y no existe el cliente, guardar el nuevo cliente
                        Mono.just(customerRepository.save(customer))
                        .doOnSuccess(savedCustomer -> log.info("Cliente se creo con exito: {}", savedCustomer.getIdentification()))

                    );
            })
            .switchIfEmpty(
                // Si la persona no existe, devolver un error
                Mono.error(new IllegalArgumentException("Person does not exist with id: " + customer.getIdentification()))
            );
    }


    public Mono<Customer> getById(int id) {
        return Mono.justOrEmpty(customerRepository.findById(id));
    }

    public Mono<Customer> update(int id, Customer customer) {
        if (customerRepository.existsById(id)) {
            return Mono.just(customerRepository.save(customer));
        } else {
            return Mono.empty();
        }
    }

    public Mono<Void> delete(int id) {
        customerRepository.deleteById(id);
        return Mono.empty();
    }
}
