package ru.egar.enums;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public enum Month {
    JANUARY("Январь", new BigDecimal(1)),
    FEBRUARY("Февраль", new BigDecimal(2)),
    MARCH("Март", new BigDecimal(3)),
    APRIL("Апрель", new BigDecimal(4)),
    MAY("Май", new BigDecimal(5)),
    JUNE("Июнь", new BigDecimal(6)),
    JULY("Июль", new BigDecimal(7)),
    AUGUST("Август", new BigDecimal(8)),
    SEPTEMBER("Сентябрь", new BigDecimal(9)),
    OCTOBER("Октябрь", new BigDecimal(10)),
    NOVEMBER("Ноябрь", new BigDecimal(11)),
    DECEMBER("Декабрь", new BigDecimal(12));

    private String name;
    private BigDecimal number;

    Month(String name, BigDecimal number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getNumber() {
        return number;
    }

    //поиск названия месяца по номеру
    public static Optional<String> getName(BigDecimal value){
         return Arrays.stream(Month.values())
                .filter(m -> m.number.equals(value))
                 .map(Month::getName).findFirst();
    }


}
