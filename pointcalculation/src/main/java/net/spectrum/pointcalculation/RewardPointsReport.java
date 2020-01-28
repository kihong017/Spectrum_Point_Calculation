package net.spectrum.pointcalculation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class RewardPointsReport {

    @Id
    @GeneratedValue
    private Long id;
    private String custId;
    private String reportMonth;
    private BigDecimal moneySpent;
    private Integer points;

    public RewardPointsReport() {
        super();
        this.reportMonth = cvrtDtToMonth(new Date());
        this.moneySpent = new BigDecimal(0);
        this.points = 0;
    }

    public Long getId() {
        return id;
    }

    public RewardPointsReport setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCustId() {
        return custId;
    }

    public RewardPointsReport setCustId(String custId) {
        this.custId = custId;
        return this;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public RewardPointsReport setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
        return this;
    }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }
    public RewardPointsReport setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
        return this;
    }
    public Integer getPoints() {
        return points;
    }
    public RewardPointsReport setPoints(Integer points) {
        this.points = points;
        return this;
    }

    @Override
    public String toString() {
        return "RewardPointsReport{" +
                "id=" + id +
                ", reportMonth='" + reportMonth + '\'' +
                ", moneySpent=" + moneySpent +
                ", point=" + points +
                '}';
    }

    public String cvrtDtToMonth(Date transDate) {
        DateFormat dateFormat = new SimpleDateFormat("YYYYMM");
        String cvrtDt = dateFormat.format(transDate);

        return cvrtDt;
    }
}
