package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
public class Stadium {

    private Integer id;
    private String name;
    private Timestamp createdAt;
}
