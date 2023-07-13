import Service.OutPlayerService;
import Service.PlayerService;
import Service.StadiumService;
import Service.TeamService;
import db.DBConnection;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 야구장목록
// 야구장등록?name=잠실야구장
// 선수등록?teamId=1&name=이대호&position=1루수
public class BaseBallApp {

    // body(queryString) 파싱
    public static List<String> parsing(String keyboardInput) {
        List<String> values = new ArrayList<>(); // 매개변수 값을 받을 공간
        String [] queryData = keyboardInput.split("\\?");
        try{
            if(queryData.length == 2) {
                String body = queryData[1];
                String[] params = body.split("&");
                for (String param : params) {
                    String v = param.split("=")[1];
                    values.add(v);
                }
            }
        } catch (Exception e){
            values = null;
        }
        return values;
    }


    public static void main(String[] args) {
        // DB 연결
        Connection conn = DBConnection.getInstance();

        // 스캐너 입력
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("야구장등록 | 야구장목록 | 팀등록 | 팀목록");
        System.out.println("선수등록 | 선수목록 | 포지션별목록 | 퇴출등록 | 퇴출목록 |");
        System.out.println("");
        System.out.print("원하시는 메뉴를 선택해 주세요 : ");

        String keyboardInput = sc.nextLine();

        // head & body 구분
        String [] queryData = keyboardInput.split("\\?");
        String method = queryData[0];

        // body 파싱 실행
        List<String> values = parsing(keyboardInput);

        //////////////////////////////////////////////////////////////////////////////////////////////////

        // stadium 실행
        StadiumService ss = new StadiumService(conn);
        try {
            // 야구장등록?name=잠실야구장
            if (method.equals("야구장등록")) {
                ss.insert(values.get(0));
            // 야구장목록
            } else if (method.equals("야구장목록")) {
                ss.findByAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // team 실행
        TeamService ts = new TeamService(conn);
        try{
            // 팀등록?stadiumId=1&name=NC
            if(method.equals("팀등록")){
                ts.insert(Integer.parseInt(values.get(0)), values.get(1));
            // 팀목록
            } else if (method.equals("팀목록")) {
                ts.findByAllWithStadium();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // player 실행
        PlayerService ps = new PlayerService(conn);
        try{
            // 선수등록?teamId=1&name=이대호&position=1루수
            if(method.equals("선수등록")){
                ps.insert(Integer.parseInt(values.get(0)),values.get(1),values.get(2));
            // 선수목록?teamId=1
            } else if(method.equals("선수목록")){
                ps.findByTeam(Integer.parseInt(values.get(0)));
            // 퇴출등록?playerId=1&reason=도박
            } else if(method.equals("포지션별목록")){
                ps.findByPositionPivot();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // out_player 실행
        OutPlayerService ops = new OutPlayerService(conn);
        try{
            if(method.equals("퇴출등록")){
                ops.oplWithPlInsert(Integer.parseInt(values.get(0)), values.get(1));
                // 퇴출목록
            } else if(method.equals("퇴출목록")){
                ops.findByAllWithPlayer();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }
    }



