package cz.tix.aquapce.crawler;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
public class Traffic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pool;

    private Integer aqua;

    private Integer wellness;

    private Date timestamp;
}
