package pavlo.restserver.repository;

import pavlo.restserver.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByTabelIDAndBeginPeriodAndEndPeriod(Long tabelID, String beginPeriod, String endPeriod);

    List<Report> findByTabelID(Long tabelID);
}
