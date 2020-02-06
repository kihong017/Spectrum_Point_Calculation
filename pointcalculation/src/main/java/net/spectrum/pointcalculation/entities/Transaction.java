package net.spectrum.pointcalculation.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private String custid;
    private BigDecimal moneyspent;
    private LocalDate transdate;

    public Transaction() {
    }

    public Transaction(String custid, BigDecimal moneyspent, LocalDate transdate) {
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

    public LocalDate getTransdate() {
        return transdate;
    }

    public void setTransdate(LocalDate transdate) {
        this.transdate = transdate;
    }
}
