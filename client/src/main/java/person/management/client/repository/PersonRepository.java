package person.management.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import person.management.client.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
   

}
