package net.spectrum.pointcalculation;

import java.math.BigDecimal;

public class TransactionPointCalculator {

    public Integer calculateTransactionPoint(BigDecimal moneySpent) {
        Integer transactionPoint = 0;
        Integer moneySpentConvInt = moneySpent.intValue();

        if (moneySpentConvInt > PointConditionEnum.HUNDREDDOLLARS.getValue()) {
            transactionPoint = calculateOverHundredPoint(moneySpentConvInt);
        } else if (moneySpentConvInt > PointConditionEnum.FIFTYDOLLARS.getValue()) {
            transactionPoint = calculateOverFiftyPoint(moneySpentConvInt);
        }

        return transactionPoint;
    }

    private Integer calculateOverFiftyPoint(Integer moneySpent) {
        if (moneySpent > 100) {
            return PointConditionEnum.FIFTYDOLLARS.getValue() * PointConditionEnum.POINTPEROVERFIFTY.getValue();
        } else {
            return (moneySpent - PointConditionEnum.FIFTYDOLLARS.getValue()) * PointConditionEnum.POINTPEROVERFIFTY.getValue();
        }
    }


    private Integer calculateOverHundredPoint(Integer moneySpent) {
        return calculateOverFiftyPoint(moneySpent) + (moneySpent - PointConditionEnum.HUNDREDDOLLARS.getValue()) * PointConditionEnum.POINTSPEROVERHUNDRED.getValue();
    }
}
