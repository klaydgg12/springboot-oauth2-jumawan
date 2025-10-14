package jumawan.com.springbootproject.oauth2_springboot.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String displayName;
    private String avatarUrl;

    @Column(length = 2000)
    private String bio;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist(){ createdAt = Instant.now(); updatedAt = createdAt; }

    @PreUpdate
    public void preUpdate(){ updatedAt = Instant.now(); }
}