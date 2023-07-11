import dao.StadiumDAO;
import db.DBConnection;
import model.Stadium;

import java.sql.Connection;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) {

        // 입력받기
//        Scanner sc = new Scanner(System.in);
//        String quest = sc.nextLine();

        Connection conn = DBConnection.getInstance();
        StadiumDAO sdao = new StadiumDAO(conn);

        try{

//           Stadium stadium = sdao.findByOne(1);
//            System.out.println("statium 번호 : " + stadium.getId());
//            System.out.println("statium 이름 : " + stadium.getName());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
