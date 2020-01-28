package net.spectrum.pointcalculation;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionPointsVO {

    String custid;
    BigDecimal moneyspent;
    Date transdate;

    public TransactionPointsVO(String custid, BigDecimal moneyspent, Date transdate) {
        this.custid = custid;
        this.moneyspent = moneyspent;
        this.transdate = transdate;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public BigDecimal getMoneyspent() {
        return moneyspent;
    }

    public void setMoneyspent(BigDecimal moneyspent) {
        this.moneyspent = moneyspent;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }
}
