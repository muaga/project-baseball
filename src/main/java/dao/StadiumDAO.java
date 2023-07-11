package dao;

import model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {
    private Connection conn;

    public StadiumDAO(Connection conn) {
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

    public List<Stadium> findByAll() {
        List<Stadium> stadiumList = new ArrayList<>();

        String findByAll = "";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Stadium stadium = new Stadium(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")
                );
                stadiumList.add(stadium);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stadiumList;
    }

    public Stadium findOne() {
        Stadium stadium = null;

        String findOne = "";
        try {
            PreparedStatement ps = conn.prepareStatement(findOne);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                stadium = new Stadium(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stadium;
    }
}
