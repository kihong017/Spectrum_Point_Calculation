package net.spectrum.pointcalculation.repositories;

import net.spectrum.pointcalculation.entities.RewardPointsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<RewardPointsReport, Long> {

    public List<RewardPointsReport> findByCustId(String custid);
    public List<RewardPointsReport> findByCustIdOrderByReportMonth(String custid);
    public List<RewardPointsReport> findByCustIdAndReportMonth(String custId, String reportmonth);
    public List<RewardPointsReport> findByReportMonth(String reportmonth);

}
