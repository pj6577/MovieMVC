package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MovieDTO;
import model.UserDTO;
import controller.UserController;
import util.ScannerUtil;
import view.MovieViewer;

public class UserViewer {
    private UserDTO logIn;
    private UserController UserController;
    private Scanner scanner;
    private MovieViewer MovieViewer;
    private ReplyViewer ReplyViewer;

    public UserViewer() {
        UserController = new UserController();
        scanner = new Scanner(System.in);
    }

    public void setMovieViewer(MovieViewer movieViewr) {
        this.MovieViewer = movieViewr;
    }

    public void setReplyViewer(ReplyViewer replyViewer) {
        this.ReplyViewer = replyViewer;
    }

    public void showMenu() {
        while (true) {

            String message = "1. 로그인 2.번 회원가입 3.종료";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // 로그인
                logIn = null;
                login();
                // 일반유저 영화 메소드
                if (logIn.getLevel() == 1) {

                    MovieViewer.showMovie();
                    // 전문 평론가
                    System.out.println("일반유저");
                } else if (logIn.getLevel() == 2) {
                    // 관리자
                } else if (logIn.getAdminLevel() == 3) {
                    adminMenu();
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
        int j = 1;
        for (int i = 0; i < j; i++) {
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
    // 유저 리스트 보기
    public void adminMenu() {
        while (true) {
            System.out.println("--------------------------");
            System.out.println("관리자 계정 페이지 입니다 ");
            System.out.println("1번 유저 관리 2번 영화 관리 3번 극장 관리 4번 로그아웃");
            System.out.println("--------------------------");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 1) {
                UserPrint();
            } else if (adminChoice == 2) {
                MovieViewer.showMoiveMenu();
            } else if (adminChoice == 4) {
                showMenu();

            }
        }
    }
    //유저 상세보기
    public void UserPrint() {
        ArrayList<UserDTO> UserList = UserController.selectAll();

        if (UserList.isEmpty()) {
            System.out.println("회원이 없습니다");
        } else {
            for (UserDTO u : UserList) {
                System.out.printf("%d. %s\n", u.getId(), u.getNickName(), u.getLevel());
                System.out.println("--------------------------");
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

    // 유저 상세 보기
    public void printOne(int id) {
        UserDTO u = UserController.selectOne(id);
        System.out.println("--------------------------");
        if (u.getLevel() == 1) {
            System.out.println("일반회원");
        } else if (u.getLevel() == 2) {
            System.out.println("평론가");
        } else if (u.getLevel() == 3) {
            System.out.println("관리자");
        }
        System.out.println("--------------------------");
        System.out.println(u.getId() + " 번 닉네임 : " + u.getNickName());
        System.out.println("유저 아이디 : " + u.getUserId() + " 유저 비밀번호 : " + u.getPassWord());
        System.out.println("--------------------------");
        System.out.println("등급 수정을 원하시면 1번 뒤로가기 0번");
        int adminChoice = scanner.nextInt();
        if (adminChoice == 1) {
            classUp(id);
        } else if (adminChoice == 2) {
            UserPrint();
        }
    }

    public void classUp(int id) {
        UserDTO u = UserController.selectOne(id);
        System.out.println("--------------------------");
        System.out.println("회원 정보 수정/ 등급 올리기 페이지 입니다");
        System.out.println("번 회원 정보 수정 / ");
        System.out.println("--------------------------");
        String message = id + "번 회원 ";
        System.out.println("등급 설정 1번 일반유저 2번 평론가 3번 어드민");
        u.setLevel(ScannerUtil.nextInt(scanner, message));
        if (u.getLevel() == 1) {
            System.out.println("일반회원 변경 완료");
        } else if (u.getLevel() == 2) {
            System.out.println("평론가 변경 완료");
        } else if (u.getLevel() == 3) {
            System.out.println("어드민 변경 완료");
        }
        System.out.println(u.getLevel());
        UserController.update(u);
        adminMenu();
    }

}
