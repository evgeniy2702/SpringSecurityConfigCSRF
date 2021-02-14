package zhurenko.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhurenko.ua.model.User;

@Repository
public interface UserJPA extends JpaRepository<User, Long> {

    User getByEmail(String email);

    User getById(Long id);
}
