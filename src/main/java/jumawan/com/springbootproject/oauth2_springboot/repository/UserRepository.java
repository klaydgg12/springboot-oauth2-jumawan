package jumawan.com.springbootproject.oauth2_springboot.repository;

import jumawan.com.springbootproject.oauth2_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
