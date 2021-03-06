package net.spectrum.pointcalculation;


import net.spectrum.pointcalculation.entities.Transaction;
import net.spectrum.pointcalculation.entities.RewardPointsReport;
import net.spectrum.pointcalculation.repositories.TransactionRepository;
import net.spectrum.pointcalculation.services.PointCalculator;
import net.spectrum.pointcalculation.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


import javax.annotation.Resource;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class TransactionPointCalculatorTest {

    @Resource
    private TransactionRepository transactionRepo;

    @Resource
    private TransactionService transactionService;

    @Test
    public void shoudRecieveNoPointsForSpending50() {
        RewardPointsReport rewardPointsReport = new RewardPointsReport();
        rewardPointsReport.setCustId("underTest").setReportMonth("202002").setPoints(100).setMoneySpent(new BigDecimal(100));

        transactionRepo.save(rewardPointsReport);

        Transaction transaction1 = new Transaction("underTest", new BigDecimal(50), LocalDate.now() );
        transactionService.saveTransaction(transaction1);

        PointCalculator pointCalcRepo = new PointCalculator();
        int pointsEarned = pointCalcRepo.calculateTransactionPoints(transaction1.getMoneyspent());

        Assert.isTrue(pointsEarned == 0, "Success");

    }

}