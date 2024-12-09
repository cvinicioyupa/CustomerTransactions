package person.management.client.controller;

import org.springframework.web.bind.annotation.*;

import person.management.client.model.Customer;
import person.management.client.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Mono<Object> createCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomer(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable int id) {
        return customerService.delete(id);
    }

    // Método para listar todos los clientes
    @GetMapping
    public Flux<Customer> listAllCustomers() {
        return customerService.getAll();
    }
}
