package person.management.client.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import person.management.client.repository.PersonRepository;
import person.management.client.model.Person;

@Service
public class PersonService {
    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> create(Person person) {
        return Mono.just(personRepository.save(person));
    }

    public Mono<Person> getById(int id) {
        return Mono.justOrEmpty(personRepository.findById(id));
    }

    public Mono<Person> update(int id, Person person) {
        if (personRepository.existsById(id)) {
            return Mono.just(personRepository.save(person));
        } else {
            return Mono.empty();
        }
    }

    public Mono<Void> delete(int id) {
        personRepository.deleteById(id);
        return Mono.empty();
    }

    public Flux<Person> getAll() {
        return Flux.fromIterable(personRepository.findAll());
    }
}
