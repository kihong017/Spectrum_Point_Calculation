package net.spectrum.pointcalculation.services;

import net.spectrum.pointcalculation.PointConditionEnum;

import java.math.BigDecimal;

public class PointCalculator {

    public Integer calculateTransactionPoints(BigDecimal moneySpent) {
        Integer transactionPoint = 0;
        Integer moneySpentConvInt = moneySpent.intValue();

        if (moneySpentConvInt > PointConditionEnum.HUNDREDDOLLARS.getValue()) {
            transactionPoint = calculateOverHundredPoints(moneySpentConvInt);
        } else if (moneySpentConvInt > PointConditionEnum.FIFTYDOLLARS.getValue()) {
            transactionPoint = calculateOverFiftyPoints(moneySpentConvInt);
        }

        return transactionPoint;
    }

    private Integer calculateOverFiftyPoints(Integer moneySpent) {
        if (moneySpent > PointConditionEnum.HUNDREDDOLLARS.getValue()) {
            return PointConditionEnum.FIFTYDOLLARS.getValue() * PointConditionEnum.POINTPEROVERFIFTY.getValue();
        } else {
            return (moneySpent - PointConditionEnum.FIFTYDOLLARS.getValue()) * PointConditionEnum.POINTPEROVERFIFTY.getValue();
        }
    }

    private Integer calculateOverHundredPoints(Integer moneySpent) {
        return calculateOverFiftyPoints(moneySpent) + (moneySpent - PointConditionEnum.HUNDREDDOLLARS.getValue()) * PointConditionEnum.POINTSPEROVERHUNDRED.getValue();
    }
}
