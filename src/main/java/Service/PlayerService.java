package Service;

import dao.PlayerDAO;
import db.DBConnection;
import dto.PositionRespDTO;
import model.Player;

import java.sql.Connection;
import java.util.List;

public class PlayerService {

    private Connection conn;

    public PlayerService(Connection conn) {
        this.conn = conn;
    }

    // 3.4 선수 등록 출력 코드
    public void insert(Integer teamId, String name, String position){
        try{
            PlayerDAO pdao = new PlayerDAO(conn);
            Player player1 = pdao.findByName(name);
            if(player1 == null){
                pdao.insert(teamId, name, position);
                System.out.println("성공");
            } else{
                System.out.println("실패");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // 3.6 팀별 선수 목록 출력 코드
    public void findByTeam(Integer teamId){
        try{
            PlayerDAO pdao = new PlayerDAO(conn);
            List<Player> players = pdao.findByTeam(teamId);
            for (Player player : players) {
//                System.out.println("Player: id=" + player.getId() + ", name=" + player.getName() + ", position=" + player.getPosition() + ", createdAt=" + player.getCreatedAt());
                System.out.print("선수번호 : " + player.getId() + "  " );
                System.out.print("선수이름 : " + player.getName() + "  ");
                System.out.print("포지션 : " + player.getPosition() + "  ");
                System.out.println("입단일 : " + player.getCreatedAt() + "  ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 3.10 포지션 별 야구 선수 페이지
    public void findByPositionPivot(){
        try {
            PlayerDAO playerDAO = new PlayerDAO(conn);
            List<PositionRespDTO> pRdtos = playerDAO.findByPositionPivot();
            System.out.println("");
            for (PositionRespDTO pRdto : pRdtos){
                System.out.println("포지션 : " + pRdto.getPosition() );
                System.out.println("");
                System.out.print("롯데 : " + pRdto.getLotte() + "  ");
                System.out.print("삼성 : " + pRdto.getSamsung() + "  ");
                System.out.println("엔씨 : " + pRdto.getNc());
                System.out.println("--------------------------------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
