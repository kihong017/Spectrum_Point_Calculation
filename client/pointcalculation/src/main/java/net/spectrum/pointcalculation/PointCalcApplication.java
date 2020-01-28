package net.spectrum.pointcalculation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PointCalcApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointCalcApplication.class, args);
    }
}

@RepositoryRestResource
interface RewardPointRepository extends JpaRepository<RewardPointsReport, Long> {

    public List<RewardPointsReport> findByCustId(String custid);
    public List<RewardPointsReport> findByCustIdOrderByReportMonth(String custid);
    public List<RewardPointsReport> findByCustIdAndReportMonth(String custId, String reportmonth);
    public List<RewardPointsReport> findByReportMonth(String reportmonth);

}

@Component
class PointCommandLineRunner implements CommandLineRunner {
    private final RewardPointRepository repository;

    public PointCommandLineRunner(RewardPointRepository repository) {
        this.repository = repository;
    }

    // Sample Data Insert
    @Override
    public void run (String[] strings) throws Exception{
        PointCalcController pointCalcController = new PointCalcController(repository);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, +1);
        Date month2 = cal.getTime();
        cal.add(Calendar.MONTH, +1);
        Date month3 = cal.getTime();


        TransactionPointsVO transactionPointsVO1 = new TransactionPointsVO("example1", new BigDecimal(120), new Date());
        TransactionPointsVO transactionPointsVO2= new TransactionPointsVO("example1", new BigDecimal(90), month2);
        TransactionPointsVO transactionPointsVO3= new TransactionPointsVO("example1", new BigDecimal(300), month3);

        pointCalcController.saveTransaction(transactionPointsVO1);
        pointCalcController.saveTransaction(transactionPointsVO2);
        pointCalcController.saveTransaction(transactionPointsVO3);
    }

}

@RestController
class PointCalcController {
    private RewardPointRepository repository;

    public PointCalcController (RewardPointRepository repository) {
        this.repository = repository;
    }

    /*
     * Add a Transaction of a Customer
     * @RequestBody TransactionPointsVO transactionpointsvo
     * @return Collection<RewardPointsReport>
     */
    @PostMapping(value="/customerTransaction")
    @CrossOrigin(origins = "http://localhost:3000")
    public Collection<RewardPointsReport> saveTransaction(@RequestBody TransactionPointsVO transactionpointsvo) {
        RewardPointsReport monthlyPointReport = new RewardPointsReport();

        String custid = transactionpointsvo.getCustid();
        BigDecimal moneyspent = transactionpointsvo.getMoneyspent();
        Date transdate = transactionpointsvo.getTransdate();

        // Calculate point for the transaction
        TransactionPointCalculator transactionPointCalculation = new TransactionPointCalculator();
        Integer transPoint = transactionPointCalculation.calculateTransactionPoint(moneyspent);

        // Convert date to year and month format
        String transDateMonth = cvrtDtToMonth(transdate);


        // If exists, get the current month's point report
        if ( !repository.findByCustIdAndReportMonth(custid, transDateMonth).isEmpty() )  {
            monthlyPointReport = repository.findByCustIdAndReportMonth(custid, transDateMonth).get(0);
        }

        // Add to transaction only if spent more than $0
        if (moneyspent.compareTo(new BigDecimal(PointConditionEnum.ZERODOLLAR.getValue())) > 0 ) {
            monthlyPointReport.setPoints(monthlyPointReport.getPoints() + transPoint)
                              .setMoneySpent(monthlyPointReport.getMoneySpent().add(moneyspent))
                              .setReportMonth(transDateMonth)
                              .setCustId(custid)
                              .setId(monthlyPointReport.getId());
        }

        repository.save(monthlyPointReport);

        saveTotalPoints(transactionpointsvo, transPoint);

        return repository.findByCustIdOrderByReportMonth(custid);
    }

    /*
     * Get Points Report of a customer
     * @Param custid
     * @return Collection<RewardPointsReport>
     */
    @GetMapping("/totalpoint/{custid}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Collection<RewardPointsReport> getTotalPoint(@PathVariable("custid") String custid) {

        return repository.findByCustIdOrderByReportMonth(custid);
    }

    // Save Total Points Report of a customer
    public void saveTotalPoints(TransactionPointsVO transactionPointsVO, Integer transPoint) {

        RewardPointsReport totalPointsReport = new RewardPointsReport();

        if ( ! repository.findByCustIdAndReportMonth(transactionPointsVO.getCustid(), "Total").isEmpty() )  {
            totalPointsReport = repository.findByCustIdAndReportMonth(transactionPointsVO.getCustid(), "Total").get(0);
        }

        totalPointsReport.setPoints(totalPointsReport.getPoints() + transPoint)
                         .setMoneySpent(totalPointsReport.getMoneySpent().add(transactionPointsVO.getMoneyspent()))
                         .setReportMonth("Total")
                         .setCustId(transactionPointsVO.getCustid())
                         .setId(totalPointsReport.getId());

        repository.save(totalPointsReport);

    }

    public String cvrtDtToMonth(Date transDate) {
        DateFormat dateFormat = new SimpleDateFormat("YYYYMM");
        String cvrtDt = dateFormat.format(transDate);

        return cvrtDt;
    }
}