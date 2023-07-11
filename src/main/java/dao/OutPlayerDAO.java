package dao;

import dto.OutPlayerRespDTO;
import model.OutPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    private Connection conn;

    public OutPlayerDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Integer playerId, String reason) {
        String insert = "insert into out_player(player_id, reason, created_at) values (?, ?, now())";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, playerId);
            ps.setString(2, reason);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 퇴출 이유에 대한 변경
    public void update(String reason, Integer playerId) {
        String update = "update out_player set reason =? where player_id = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, reason);
            ps.setInt(2, playerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // playerId로 퇴출 목록에서 선수 삭제
    public void delete(Integer playerId) {
        String delete = "delete from out_player where player_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setInt(1, playerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 퇴출 선수 모두 검색
    public List<OutPlayer> findByAll() {
        List<OutPlayer> outPlayerList = new ArrayList<>();

        String findByAll = "select * from out_player";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OutPlayer outPlayer = new OutPlayer(
                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );
                outPlayerList.add(outPlayer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPlayerList;
    }

    // playerId로 퇴출 선수 검색
    public OutPlayer findByPlayerId(Integer playerId) {
        OutPlayer outPlayer = null;

        String findByOne = "select * from out_player where playerId = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(findByOne);
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                outPlayer = new OutPlayer(
                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPlayer;
    }

    // outer join
    // player과 out_player 모두 검색
    public List<OutPlayerRespDTO> findByAllWithPlayer() {
        List<OutPlayerRespDTO> outPlayerRespDtoList = new ArrayList<>();

        String findByAllWithPlayer = "select pl.id, pl.name, pl.position, opl.reason, opl.created_at\n" +
                "from out_player as opl\n" +
                "right outer join player as pl\n" +
                "on opl.player_id = pl.id\n" +
                "order by id asc;";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAllWithPlayer);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OutPlayerRespDTO outPlayerRespDTO = new OutPlayerRespDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("position"),
                rs.getString("reason"),
                rs.getTimestamp("created_at")

                );
                outPlayerRespDtoList.add(outPlayerRespDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPlayerRespDtoList;
    }

}

