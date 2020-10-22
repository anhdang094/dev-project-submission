package dev.remitano.core.repository;

import dev.remitano.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstById(long userId);

    User findFirstByEmail(String email);

}
