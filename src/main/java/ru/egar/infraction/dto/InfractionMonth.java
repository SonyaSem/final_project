package ru.egar.infraction.dto;

import lombok.Data;
import ru.egar.enums.Month;
import java.math.BigDecimal;

// Для вывод статистики нарушений по месяцам
@Data
public class InfractionMonth {

    private String month;
    private Integer count;
    private BigDecimal percentage;

    public InfractionMonth(BigDecimal month, Integer count, BigDecimal percentage) {
        this.month = Month.getName(month).get();
        this.count = count;
        this.percentage = percentage;
    }

}
