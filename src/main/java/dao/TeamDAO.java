package dao;

import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TeamDAO {
    private Connection conn;

    public TeamDAO(Connection conn) {
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

    public List<Team> findByAll() {
        List<Team> teamList = new ArrayList<>();

        String findByAll = "";
        try {
            PreparedStatement ps = conn.prepareStatement(findByAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //    private Integer id;
                //    private Integer stadiumId;
                //    private String name;
                //    private Timestamp createdAt;
                Team team = new Team(
                        rs.getInt("id"),
                        rs.getInt("stadium_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")
                );
                teamList.add(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamList;
    }

    public Team findByOne() {
        Team team = null;

        String findByOne = "";
        try {
            PreparedStatement ps = conn.prepareStatement(findByOne);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                team = new Team(
                        rs.getInt("id"),
                        rs.getInt("stadium_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return team;
    }
}
