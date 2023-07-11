package dao;

import model.OutPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    private Connection conn;

    public OutPlayerDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert() {
        String insert = "insert into out_player(name,created_at) values (?,now())";
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

    public List<OutPlayer> findByAll() {
        List<OutPlayer> outPlayerList = new ArrayList<>();

        String findByAll = "";
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

    public OutPlayer findByOne() {
        OutPlayer outPlayer = null;

        String findByOne = "";
        try {
            PreparedStatement ps = conn.prepareStatement(findByOne);
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
}

