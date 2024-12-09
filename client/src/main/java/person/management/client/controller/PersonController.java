package person.management.client.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import person.management.client.service.PersonService;
import person.management.client.model.Person;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Mono<Person> createPerson(@RequestBody Person person) {
        return personService.create(person);
    }

    @GetMapping("/{id}")
    public Mono<Person> getPerson(@PathVariable int id) {
        return personService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePerson(@PathVariable int id) {
        return personService.delete(id);
    }

    @GetMapping
    public Flux<Person> listAllPersons() {
        return personService.getAll();
    }
}
