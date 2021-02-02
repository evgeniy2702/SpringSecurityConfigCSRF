package zhurenko.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhurenko.ua.model.Owner;

@Repository
public interface OwnerJPA extends JpaRepository<Owner, Long> {
}
