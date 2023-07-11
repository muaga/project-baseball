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

    public void insert(String name) {
        String insert = "insert into stadium(name,created_at) values (?,now())";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 다른 이름으로 변경
    public void update(String name, Integer id) {
        String update = "update stadium set name =? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // id로 stadium 삭제
    public void delete(Integer id) {
        String delete = "delete from stadium where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // stadium 모두 검색
    public List<Stadium> findByAll() {
        List<Stadium> stadiumList = new ArrayList<>();

        String findByAll = "select * from stadium";
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

    // id로 stadium 검색
    public Stadium findById(Integer id) {
        Stadium stadium = null;

        String findByOne = "select * from stadium where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(findByOne);
            ps.setInt(1, id);
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
