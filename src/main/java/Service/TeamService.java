package Service;

import dao.TeamDAO;
import dto.TeamRespDTO;
import model.Team;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

public class TeamService {
    private Connection conn;

    public TeamService(Connection conn) {
        this.conn = conn;
    }

    // 3.3 팀 등록
    public void insert(Integer stadiumId,String name){
        try {
            TeamDAO tdao = new TeamDAO(conn);
            Team team = tdao.findByStadiumId(stadiumId);
            if(team == null) {
                tdao.insert(stadiumId, name);
                System.out.println("성공");
            } else {
                System.out.println("실패");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 3.4 전체 팀 목록
    public void findByAllWithStadium(){
        try {
            TeamDAO tDAO = new TeamDAO(conn);
            List<TeamRespDTO> dtos = tDAO.findByAllWithStadium();
            for (TeamRespDTO dto : dtos) {
                System.out.println(dto);
            }
            System.out.println("성공");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
