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

    public void insert() {
        String insert = "";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            // 여기에 ? 값 넣기
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String update = "";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            // 여기에 ? 값 넣기
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        String delete = "";
        try {
            PreparedStatement ps = conn.prepareStatement(delete);
            // 여기에 ? 값 넣기
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Player> findByAll() {
        List<Player> playerList = new ArrayList<>();

        String findByAll = "";
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

    public Player findOne() {
        Player player = null;

        String findOne = "";
        try {
            PreparedStatement ps = conn.prepareStatement(findOne);
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
