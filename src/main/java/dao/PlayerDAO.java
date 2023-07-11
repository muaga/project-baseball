package dao;

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

    public void insert(Player player) {
        String insert = "insert into player(team_id, name ,position ,created_at) values(?, ?, ?,now())";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, player.getTeamId());
            ps.setString(2, player.getName());
            ps.setString(3, player.getPosition());
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
    public void updateOut(String name) {
        String update = "update player set team_id = null where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, name);
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

        String findByAll = "select * from player";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
                playerList.add(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerList;
    }

    // 팀별 선수 검색
    public Player findByTeam(Integer teamId) {
        Player player = null;

        String findByOne = "select * from player where team_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(findByOne);
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    // 각 선수별 검색
    public Player findByName(String name) {
        Player player = null;

        String findByOne = "select * from player where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(findByOne);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }
}
