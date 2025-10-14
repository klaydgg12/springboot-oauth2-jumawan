package jumawan.com.springbootproject.oauth2_springboot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auth_providers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider", "providerUserId"})
})
@Data
public class AuthProvider {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerUserId;
    private String providerEmail;

    public enum Provider { GOOGLE, GITHUB }
}