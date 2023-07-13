package Service;

import dao.OutPlayerDAO;
import dao.PlayerDAO;
import dto.OutPlayerRespDTO;
import model.OutPlayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OutPlayerService {

    private Connection conn;

    public OutPlayerService(Connection conn) {
        this.conn = conn;
    }

    // 3.8 선수 퇴출 목록 출력 코드
    public void findByAllWithPlayer(){
        try{
            OutPlayerDAO oPdao = new OutPlayerDAO(conn);
            List<OutPlayerRespDTO> oPdtos = oPdao.findByAllWithPlayer();
            for(OutPlayerRespDTO oPdto : oPdtos){
                System.out.print("id : " + oPdto.getId() + "  ");
                System.out.print("선수이름 : " + oPdto.getName() + "  ");
                System.out.print("퇴출이유 : " + oPdto.getReason() + "  ");
                System.out.println("퇴출일 : " + oPdto.getCreatedAt());
                System.out.println("-----------------------------------------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 3.7 퇴출 등록
    public void oplWithPlInsert(Integer playerId, String reason){

        // ------ 트랜잭션 시작
        try {
            conn.setAutoCommit(false);

            PlayerDAO pdao = new PlayerDAO(conn);
            OutPlayerDAO oPdao = new OutPlayerDAO(conn);
            OutPlayer outPlayer = oPdao.findByPlayerId(playerId);

            if (outPlayer == null) {
                // 데이터 그 자체가 존재하지 않다는 가정을 하면 된다.
                oPdao.insert(playerId, reason);
                pdao.updateOut(playerId);
                System.out.println("성공");
            } else {
                System.out.println("실패");
            }

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("rollback 실패");
            }
        }
    }
}
