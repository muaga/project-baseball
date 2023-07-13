package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
public class OutPlayerRespDTO {

    private Integer id;
    private String name;
    private String position;
    private String reason;
    private Timestamp createdAt;


}
