package net.spectrum.pointcalculation;

public enum PointConditionEnum {
    ZERODOLLAR(0)
    , FIFTYDOLLARS(50)
    , HUNDREDDOLLARS(100)
    , POINTPEROVERFIFTY(1)
    , POINTSPEROVERHUNDRED(2);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    private PointConditionEnum(Integer value) {
        this.value = value;
    }
}
