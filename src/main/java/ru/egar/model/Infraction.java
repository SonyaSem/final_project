package ru.egar.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.dto.InfractionMonth;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


//Запрос на статистику по нарушениям по месяцам
@NamedNativeQuery(name = "Infraction.listInfractionMonth",
query = "SELECT month, COUNT(*) as count, round(count(id) * 100/ SUM(count(*)) Over(), 2) as percentage " +
        "FROM infraction i JOIN LATERAL EXTRACT(MONTH FROM i.date) month ON TRUE " +
        "WHERE i.date IS NOT null and (:year is null or EXTRACT(year FROM i.date) =:year)" +
        "GROUP BY month  ORDER BY month",
resultSetMapping = "Mapping.InfractionMonth")
@SqlResultSetMapping(name = "Mapping.InfractionMonth",
                    classes = @ConstructorResult(targetClass = InfractionMonth.class,
                                    columns = {@ColumnResult(name = "month", type = BigDecimal.class),
                                            @ColumnResult(name="count", type = Integer.class),
                                            @ColumnResult(name = "percentage", type = BigDecimal.class)}))
@Entity
@Data
@Table(name="infraction")

//Нарушения сотрудников
public class Infraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 200)
    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @Column(length = 100)
    private String punishment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", foreignKey = @ForeignKey(name = "infraction_fk"))
    private Employee idEmployee;

    @Override
    public String toString() {
        return "Infraction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", punishment='" + punishment + '\'' +
                ", idEmployee=" + idEmployee.getId() +
                '}';
    }
}
