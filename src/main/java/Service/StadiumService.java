package Service;

import dao.StadiumDAO;
import model.Stadium;

import java.sql.Connection;
import java.util.List;

public class StadiumService {

    private Connection conn;

    public StadiumService(Connection conn) {
        this.conn = conn;
    }

    // 3.1 야구장 등록
    public void insert(String name) {
        try {
            StadiumDAO sdao = new StadiumDAO(conn);
            Stadium stadium = sdao.findByName(name);
            if (stadium == null) {
                sdao.insert(name);
                System.out.println("성공");
            } else {
                System.out.println("실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3.2 전체 야구장 목록보기
    public void findByAll() {
        try {
            StadiumDAO sdao = new StadiumDAO(conn);
            List<Stadium> dtos = sdao.findByAll();
            for (Stadium dto : dtos) {
                System.out.print("id : " + dto.getId() + "  ");
                System.out.print("구장명 : " + dto.getName() + "  ");
                System.out.println("구장준공일 : " + dto.getCreatedAt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}