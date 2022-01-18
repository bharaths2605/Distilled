package ie.dsch.be.security;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidUserRepository extends CrudRepository<Users,String>{

	Optional<Users> findByUser(String s);
}
