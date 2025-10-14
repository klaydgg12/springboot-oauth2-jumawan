package jumawan.com.springbootproject.oauth2_springboot.repository;

import jumawan.com.springbootproject.oauth2_springboot.model.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long> {
    Optional<AuthProvider> findByProviderAndProviderUserId(AuthProvider.Provider provider, String providerUserId);
}
