package net.spectrum.pointcalculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Service
public class TransactionService {

    @Resource
    private TransactionRepository repository;

    PointCalculator pointCalculator = new PointCalculator();

    @Transactional
    public Collection<RewardPointsReport> saveTransaction(Transaction transaction) {
        RewardPointsReport monthlyPointReport = new RewardPointsReport();

        String custid = transaction.getCustid();
        BigDecimal moneyspent = transaction.getMoneyspent();
        LocalDate transdate = transaction.getTransdate();

        // Calculate point for the transaction
        Integer transPoint = pointCalculator.calculateTransactionPoints(moneyspent);

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

        saveTotalPoints(transaction, transPoint);

        return repository.findByCustIdOrderByReportMonth(custid);
    }

    public Collection<RewardPointsReport> getTotalPoint(@PathVariable("custid") String custid) {
        return repository.findByCustIdOrderByReportMonth(custid);
    }

    // Save Total Points Report of a customer
    public void saveTotalPoints(Transaction transaction, Integer transPoint) {

        RewardPointsReport totalPointsReport = new RewardPointsReport();

        if ( !repository.findByCustIdAndReportMonth(transaction.getCustid(), "Total").isEmpty() )  {
            totalPointsReport = repository.findByCustIdAndReportMonth(transaction.getCustid(), "Total").get(0);
        }

        totalPointsReport.setPoints(totalPointsReport.getPoints() + transPoint)
                .setMoneySpent(totalPointsReport.getMoneySpent().add(transaction.getMoneyspent()))
                .setReportMonth("Total")
                .setCustId(transaction.getCustid())
                .setId(totalPointsReport.getId());

        repository.save(totalPointsReport);

    }

    public String cvrtDtToMonth(LocalDate transDate) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("YYYYMM");
        String cvrtDt = formatter.format(transDate);

        return cvrtDt;
    }

}
