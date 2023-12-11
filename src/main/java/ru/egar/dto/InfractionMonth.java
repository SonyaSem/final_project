package ru.egar.dto;

import ru.egar.enums.Month;
import java.math.BigDecimal;

// Для вывод статистики нарушений по месяцам
public class InfractionMonth {

    private String month;
    private Integer count;
    private BigDecimal percentage;

    public InfractionMonth(BigDecimal month, Integer count, BigDecimal percentage) {
        this.month = Month.getName(month).get();
        this.count = count;
        this.percentage = percentage;
    }

    public void setMonth(BigDecimal month) {
        this.month = Month.getName(month).get();
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String getMonth() {
        return month;
    }

    public Integer getCount() {
        return count;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

}
