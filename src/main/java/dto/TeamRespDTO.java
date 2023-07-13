package dto;

import lombok.*;

import java.sql.Connection;
import java.sql.Timestamp;

@Getter
public class TeamRespDTO {

    private Integer id;
    private String stadiumName;
    private Timestamp stadiumCreatedAt;
    private Integer stadiumId;
    private String teamName;
    private Timestamp teamCreatedAt;

    @Builder
    public TeamRespDTO(Integer id, String stadiumName, Timestamp stadiumCreatedAt, Integer stadiumId, String teamName, Timestamp teamCreatedAt) {
        this.id = id;
        this.stadiumName = stadiumName;
        this.stadiumCreatedAt = stadiumCreatedAt;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }

    @Override
    public String toString() {
        return  "id : " + id + "  " +
                "구장명 : " + stadiumName + "  " +
                "구장준공일 : " + stadiumCreatedAt + "  " +
                "구장번호 : " + stadiumId + "  " +
                "팀이름 : " + teamName + "  " +
                "팀창설일 : " + teamCreatedAt;
    }
}
