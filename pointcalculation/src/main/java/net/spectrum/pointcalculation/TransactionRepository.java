package net.spectrum.pointcalculation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@RepositoryRestResource
interface TransactionRepository extends JpaRepository<RewardPointsReport, Long> {

    public List<RewardPointsReport> findByCustId(String custid);
    public List<RewardPointsReport> findByCustIdOrderByReportMonth(String custid);
    public List<RewardPointsReport> findByCustIdAndReportMonth(String custId, String reportmonth);
    public List<RewardPointsReport> findByReportMonth(String reportmonth);

}
