package pavlo.restserver.repository;

import pavlo.restserver.model.StatusWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusWorkRepository extends JpaRepository<StatusWork, Long> {

    StatusWork findByName(String name);
}
