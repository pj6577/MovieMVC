package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.MovieDTO;
import model.UserDTO;
import view.UserViewer;
import controller.MovieController;
import controller.ReplyController;
import util.ScannerUtil;
import view.ReplyViewer;
import view.UserViewer;
import model.UserDTO;
import model.ReplyDTO;
import view.ReplyViewer;

public class MovieViewer {
    private MovieController MovieController;
    private Scanner scanner;
    private UserViewer UserViewer;
    private ReplyViewer ReplyViewer;
    private UserDTO login;
    private ReplyDTO ReplyDTO;
    ReplyDTO r = new ReplyDTO();

    public MovieViewer() {
        MovieController = new MovieController();
        scanner = new Scanner(System.in);
    }

    public void setUserViewer(UserViewer UserViewer) {
        this.UserViewer = UserViewer;
    }

    public void setReplyViewer(ReplyViewer ReplyViewer) {
        this.ReplyViewer = ReplyViewer;
    }

    public void showMovie() {
        while (true) {
            System.out.println("영화 페이지 입니다");

            String message = "1. 영화 목록 보기 2. 영화별 전체 평점 보기 0번 뒤로가기";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // 영화 목록 보기
                showMoive();
            } else if (userChoice == 2) {
                // 전체 평점 보기
                // showStar();
            } else if (userChoice == 0) {
                UserViewer.showMenu();
            }
        }
    }

    // 영화 목록 전체 보여주기
    public void showMoive() {
        ArrayList<MovieDTO> MovieList = MovieController.selectAll();
        if (MovieList.isEmpty()) {
            System.out.println("상영중인 영화가 없습니다");
        } else {
            for (MovieDTO m : MovieList) {
                System.out.println("--------------------------");
                System.out.println("상영중인 영화 제목 >" + m.getMovieName());
                System.out.println("--------------------------");
                System.out.println("자세히 볼 영화 번호 입력 // 뒤로가기 0번");
                int userChoice = scanner.nextInt();
                if (userChoice != 0) {
                    printMoive(userChoice);
                } else {
                    showMovie();
                }
            }
        }
    }

    public void printMoive(int movieNum) {
        MovieDTO m = MovieController.selectOne(movieNum);

        // ArrayList<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
        System.out.println("................................");
        System.out.println("영화 제목 > " + m.getMovieName());
        System.out.println("................................");
        System.out.println("영화 줄거리 > " + m.getSummary());
        System.out.println("................................");
        System.out.println("영화 관람 가능 나이 > " + m.getMovieLevel());
        System.out.println("................................");
        System.out.println("==================== 댓글==================== ");
        
        
        ReplyViewer.writeReply();
        //댓글 출력 메소드
//        ReplyViewer.printReplyAll(movieNum);
    }

    // 관리자 메뉴
    public void showMoiveMenu() {
        while (true) {
            System.out.println("--------------------------");
            System.out.println("영화 태그 관리자 등록/수정/삭제 페이지");
            System.out.println("현재 영화 목록");
            System.out.println("--------------------------");
            ArrayList<MovieDTO> MovieList = MovieController.selectAll();
            if (MovieList.isEmpty()) {
                System.out.println("상영중인 영화가 없습니다");
            } else {
                for (MovieDTO m : MovieList) {
                    System.out.println("--------------------------");
                    System.out.println("번호 > " + m.getMovieNum() + " / 제목 > " + m.getMovieName());
                    System.out.println("--------------------------");

                }
            }
            System.out.println("1번 영화 등록하기 2번 수정하기/삭제 0번 뒤로가기");
            System.out.println("--------------------------");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 1) {
                insertMovie();
            } else if (adminChoice == 2) {
                System.out.println("--------------------------");
                System.out.println("수정할 영화 번호를 입력해주세요");
                moiveUpdate(scanner.nextInt());
                System.out.println("--------------------------");
            } else if (adminChoice == 0) {
                UserViewer.adminMenu();
            }
        }
    }

    // 관리자 영화 등록
    public void insertMovie() {
        System.out.println("영화 등록 페이지");
        while (true) {
            MovieDTO m = new MovieDTO();
            System.out.println("--------------------------");
            String message = "등록할 영화 제목 :";
            m.setMovieName(ScannerUtil.nextLine(scanner, message));
            message = "등록할 영화 소개 :";
            m.setSummary(ScannerUtil.nextLine(scanner, message));
            message = "등록할 영화 등급";
            m.setMovieLevel(ScannerUtil.nextLine(scanner, message));

            MovieController.MovieInsert(m);
            System.out.println("--------------------------");
            System.out.println("뒤로가기 0번 ");
            System.out.println("--------------------------");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 0) {
                showMoiveMenu();
            }
        }

    }

    // 영화 정보 수젝/삭제
    public void moiveUpdate(int movieNum) {
        MovieDTO m = MovieController.selectOne(movieNum);
        System.out.println("--------------------------");
        System.out.println("영화 수정/ 삭제 페이지 입니다");
        System.out.println("1번 해당 영화 수정 2번 삭제하기 0번 뒤로가기");
        System.out.println("--------------------------");
        int adminChoice = scanner.nextInt();
        if (adminChoice == 1) {
            System.out.println(m.getMovieNum() + " 번 영화  제목 : " + m.getMovieName());

            String message = "영화 제목 수정";
            m.setMovieName(ScannerUtil.nextLine(scanner, message));

            message = "영화 제목 수정";
            m.setSummary(ScannerUtil.nextLine(scanner, message));

            message = "영화 등급 수정";
            m.setMovieLevel(ScannerUtil.nextLine(scanner, message));

            MovieController.update(m);

        } else if (adminChoice == 2) {
            movieRemove(movieNum);
        } else if (adminChoice == 3) {
            showMoiveMenu();
        }

    }

    // 영화 삭제
    public void movieRemove(int movieNum) {
        String yesNo = "정말 삭제 하시겠습니까 ? Y/N";
        yesNo = ScannerUtil.nextLine(scanner, yesNo);
        if (yesNo.equalsIgnoreCase("Y")) {
            MovieController.removeMovie(movieNum);
            System.out.println("영화 삭제 완료");
            showMoiveMenu();
        } else {
            moiveUpdate(movieNum);
        }
    }

    // 영화 별점 전부 보여주기
//   private void showStar() {
//        ArrayList<MovieDTO> movieStar = MovieController.selectAll();
//        
//        if(MovieStar.isEmpty()) {
//            
//        }
//    }
}
