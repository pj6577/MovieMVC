package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.UserDTO;
import controller.UserController;
import util.ScannerUtil;
import view.MovieViewer;

public class UserViewer {
    private UserDTO logIn;
    private UserController UserController;
    private Scanner scanner;
    private MovieViewer MovieViewer;
  

    public UserViewer() {
        UserController = new UserController();
        scanner = new Scanner(System.in);
    }
    public void setMovieViewer(MovieViewer movieViewr) {
        this.MovieViewer = movieViewr;
    }
    
    public void showMenu() {
        while (true) {

            String message = "1. 로그인 2.번 회원가입 3.종료";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // 로그인
                login();
                if (logIn.getLevel() == 1) {
                    // 일반유저 영화 메소드
                    MovieViewer.showMovie();
                    System.out.println("일반유저");
                } else if (logIn.getLevel() == 2) {
                    // 전문 평론가
                } else if (logIn.getAdminLevel() == 3) {
                    System.out.println("관리자 계정 로그인 성공");
                    UserPrint();
                 
                    //관리자
                }
            } else if (userChoice == 2) {
                // 회원가입
                SignIn();
                System.out.println("회원가입 성공 ");
            } else if (userChoice == 3) {
                System.out.println("종료");
                break;
            }
        }
    }

    private void SignIn() {
        int j=1;
        for(int i =0 ; i<j; i++ ) {
            UserDTO u = new UserDTO();
            u.setNickName("admin");
            u.setUserId("5");
            u.setPassWord("5");
            u.setLevel(3);
            UserController.UserInsert(u);
        }
        UserDTO u = new UserDTO();
        String message = "사용하실 아이디를 입력하세요";
        u.setUserId(ScannerUtil.nextLine(scanner, message));

        message = "사용하실 비밀번호 입력하세요";
        u.setPassWord(ScannerUtil.nextLine(scanner, message));

        message = "사용하실 닉네임을 입력하세요";
        u.setNickName(ScannerUtil.nextLine(scanner, message));
        u.setLevel(1);
        UserController.UserInsert(u);
    
    }
    private void login() {  
        String username = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요");
        String password = ScannerUtil.nextLine(scanner, "비밀번호를 입력해주세요");

        while (UserController.auth(username, password) == null) {
            String yesNo = ScannerUtil.nextLine(scanner, "로그인을 그만하시겠습니까? Y/N");
            if (yesNo.equalsIgnoreCase("Y")) {
                password = null;
                break;
            }
            username = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요");
            password = ScannerUtil.nextLine(scanner, "비밀번호를 입력해주세요");
        }
        logIn = UserController.auth(username, password);
    }

    // 관리자 메뉴
    //유저 리스트 보기
    public void UserPrint() {
        ArrayList<UserDTO> UserList = UserController.selectAll();

        if (UserList.isEmpty()) {
            System.out.println("회원이 없습니다");
        } else {
            for (UserDTO u : UserList) {
                System.out.printf("%d. %s\n", u.getId(), u.getNickName(), u.getLevel());
            }
            String message = "상세보기 할 회원의 아이디 번호를 입력해주세요 뒤로가기는 0번 입력";
            int userChoice = ScannerUtil.nextInt(scanner, message);

            while (userChoice != 0 && UserController.selectOne(userChoice) == null) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
            if (userChoice != 0) {
                printOne(userChoice);
            }  
        }
    }
    //유저 상세 보기
    public void printOne(int id) {
        UserDTO u = UserController.selectOne(id);
        System.out.println("--------------------------");
        if(u.getLevel() == 1) {
            System.out.println("일반회원");
        } else if(u.getLevel()==2 ) {
            System.out.println("평론가");
        } else if(u.getLevel()==3 ) {
            System.out.println("괸리자");
        }
        System.out.println(u.getId() +" 번 닉네임 : "  + u.getNickName()  );
        System.out.println( "유저 아이디 : "+u.getUserId() + " 유저 비밀번호 : " + u.getPassWord());
        System.out.println("--------------------------");
    }

}
