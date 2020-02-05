package net.spectrum.pointcalculation;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


import javax.annotation.Resource;
import javax.validation.constraints.AssertTrue;

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