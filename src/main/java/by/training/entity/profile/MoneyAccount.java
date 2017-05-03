package by.training.entity.profile;

import by.training.entity.Entity;

import java.math.BigDecimal;

/**
 * Created by angelina on 12.04.2017.
 */
public class MoneyAccount extends Entity {
    private BigDecimal standartRate;
    private BigDecimal dollar;
    private BigDecimal euro;
    private BigDecimal byn;

    public MoneyAccount (){}

    public MoneyAccount(BigDecimal standartRate, BigDecimal dollar, BigDecimal euro, BigDecimal byn) {
        this.standartRate = standartRate;
        this.dollar = dollar;
        this.euro = euro;
        this.byn = byn;
    }

    public BigDecimal getStandartRate() {
        return standartRate;
    }

    public void setStandartRate(BigDecimal standartRate) {
        this.standartRate = standartRate;
    }

    public BigDecimal getDollar() {
        return dollar;
    }

    public void setDollar(BigDecimal dollar) {
        this.dollar = dollar;
    }

    public BigDecimal getEuro() {
        return euro;
    }

    public void setEuro(BigDecimal euro) {
        this.euro = euro;
    }

    public BigDecimal getByn() {
        return byn;
    }

    public void setByn(BigDecimal byn) {
        this.byn = byn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyAccount)) return false;

        MoneyAccount that = (MoneyAccount) o;

        if (getStandartRate() != null ? !getStandartRate().equals(that.getStandartRate()) : that.getStandartRate() != null)
            return false;
        if (getDollar() != null ? !getDollar().equals(that.getDollar()) : that.getDollar() != null) return false;
        if (getEuro() != null ? !getEuro().equals(that.getEuro()) : that.getEuro() != null) return false;
        return getByn() != null ? getByn().equals(that.getByn()) : that.getByn() == null;
    }

    @Override
    public int hashCode() {
        int result = getStandartRate() != null ? getStandartRate().hashCode() : 0;
        result = 31 * result + (getDollar() != null ? getDollar().hashCode() : 0);
        result = 31 * result + (getEuro() != null ? getEuro().hashCode() : 0);
        result = 31 * result + (getByn() != null ? getByn().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MoneyAccount{" +
                "standartRate=" + standartRate +
                ", dollar=" + dollar +
                ", euro=" + euro +
                ", byn=" + byn +
                '}';
    }
}
