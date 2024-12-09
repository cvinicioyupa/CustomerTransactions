package person.management.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import person.management.client.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
