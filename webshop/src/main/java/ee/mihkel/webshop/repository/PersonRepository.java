package ee.mihkel.webshop.repository;

import ee.mihkel.webshop.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Person getByEmail(String email);

    List<Person> getAllByRole(String role);
}
