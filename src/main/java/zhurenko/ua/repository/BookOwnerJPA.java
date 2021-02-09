package zhurenko.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhurenko.ua.model.OwnerBook;

import java.util.List;

@Repository
public interface BookOwnerJPA extends JpaRepository<OwnerBook,Long> {


}
