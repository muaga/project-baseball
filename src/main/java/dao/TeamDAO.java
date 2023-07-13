package dao;

import dto.TeamRespDTO;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class TeamDAO {
    private Connection conn;

    public TeamDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Integer stadiumId, String name) {
        String insert = "insert into team(stadium_id, name ,created_at) values (?, ?,now())";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, stadiumId);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 팀 이름 변경
    public void update(String SetName, String whereName) {
        String update = "update team set name = ? where name = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, SetName);
            ps.setString(2, whereName);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 팀 이름으로 팀 삭제
    // stadium_id는 외래키로, 삭제할 수 없다.
    public void delete(String name) {
        String delete = "delete from team where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // team 모두 검색
    public List<Team> findByAll() {
        List<Team> teamList = new ArrayList<>();

        String findByAll = "select * from team";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Team team = Team.builder()
                        .id(rs.getInt("id"))
                        .stadiumId(rs.getInt("stadium_id"))
                        .name(rs.getString("name"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                teamList.add(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamList;
    }

    // stadiumId로 team 검색
    public Team findByStadiumId(Integer stadiumId) {
        Team team = null;
        String findByStadiumId = "select* from team where stadium_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(findByStadiumId);
            ps.setInt(1, stadiumId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                team = Team.builder()
                        .id(rs.getInt("id"))
                        .stadiumId(rs.getInt("stadium_id"))
                        .name(rs.getString("name"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return team;
    }

    // join
    // stadium + team 목록 모두 검색
    public List<TeamRespDTO> findByAllWithStadium() {
        List<TeamRespDTO> teamRespDTOList = new ArrayList<>();

        String findByAllWithStadium = "select s.id, s.name as stadium_name, s.created_at as stadium_created_at, t.stadium_id, t.name as team_name, t.created_at as team_created_at\n" +
                "from team as t\n" +
                "right outer join stadium as s\n" +
                "on t.stadium_id = s.id\n" +
                "order by id asc";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAllWithStadium);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TeamRespDTO teamRespDTO = TeamRespDTO.builder()
                        .id(rs.getInt("id"))
                        .stadiumName(rs.getString("stadium_name"))
                        .stadiumCreatedAt(rs.getTimestamp("stadium_created_at"))
                        .stadiumId(rs.getInt("stadium_id"))
                        .teamName(rs.getString("team_name"))
                        .teamCreatedAt(rs.getTimestamp("team_created_at"))
                        .build();

                teamRespDTOList.add(teamRespDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamRespDTOList;
    }
}

