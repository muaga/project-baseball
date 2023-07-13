package dao;

import dto.PositionRespDTO;
import model.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection conn;

    public PlayerDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Integer teamId, String name, String position) {
        String insert = "insert into player(team_id, name ,position ,created_at) values(?, ?, ?,now())";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, teamId);
            ps.setString(2, name);
            ps.setString(3, position);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 선수 이름 변경
    public void update(String setName, String whereName) {
        String update = "update player set name = ? where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, setName);
            ps.setString(2, whereName);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 퇴출 선수에 대한 update
    // 퇴출 당하는 선수는 player 테이블에서 team_id가 null로 변경된다.
    public void updateOut(Integer id) {
        String update = "update player set team_id = null where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 이름으로 선수목록에서 삭제
    public void delete(String name) {
        String delete = "delete from player where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 선수 모두 검색
    public List<Player> findByAll() {
        List<Player> playerList = new ArrayList<>();

        String findByAll = "select * from player order by id asc";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = Player.builder()
                        .id(rs.getInt("id"))
                        .teamId(rs.getInt("team_id"))
                        .name(rs.getString("name"))
                        .position(rs.getString("position"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                playerList.add(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerList;
    }

    // 팀별 선수 검색
    public List<Player> findByTeam(Integer teamId) {
        List<Player> playerList = new ArrayList<>();

        String findByTeam = "select id, name, position, created_at from player where team_id = ? order by id asc";
        try {
            PreparedStatement ps = conn.prepareStatement(findByTeam);
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = Player.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .position(rs.getString("position"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                playerList.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerList;
    }

    // 각 선수별 검색
    public Player findByName(String name) {
        Player player = null;

        String findByName = "select * from player where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(findByName);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                player = Player.builder()
                        .id(rs.getInt("id"))
                        .teamId(rs.getInt("team_id"))
                        .name(rs.getString("name"))
                        .position(rs.getString("position"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    // 피벗테이블
    // 각 팀별로 포지션을 담당하는 선수를 조회하고 싶을 때
    public List<PositionRespDTO> findByPositionPivot() {
        List<PositionRespDTO> positionRespDTOList = new ArrayList<>();

        String findByPositionPivot = "select 포지션 ,\n" +
                "max(case when tname = '롯데' then pname else null end) as '롯데',\n" +
                "max(case when tname = '삼성' then pname else null end) as '삼성',\n" +
                "max(case when tname = '엔씨' then pname else null end) as '엔씨'\n" +
                "from (\n" +
                "select p.position as '포지션',\n" +
                " t.name as tname, \n" +
                " p.name as pname\n" +
                "from \n" +
                "player as p\n" +
                "inner join\n" +
                " team as t\n" +
                "on p.team_id = t.id) as page\n" +
                "group by 포지션";
        try {
            PreparedStatement ps = conn.prepareStatement(findByPositionPivot);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PositionRespDTO positionRespDTO = PositionRespDTO.builder()
                        .position(rs.getString(1))
                        .lotte(rs.getString(2))
                        .samsung(rs.getString(3))
                        .nc(rs.getString(4))
                        .build();
                positionRespDTOList.add(positionRespDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return positionRespDTOList;
    }



}
