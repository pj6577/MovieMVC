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
            System.out.println("��ȭ ������ �Դϴ�");

            String message = "1. ��ȭ ��� ���� 2. ��ȭ�� ��ü ���� ���� 0�� �ڷΰ���";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // ��ȭ ��� ����
                showMoive();
            } else if (userChoice == 2) {
                // ��ü ���� ����
                // showStar();
            } else if (userChoice == 0) {
                UserViewer.showMenu();
            }
        }
    }

    // ��ȭ ��� ��ü �����ֱ�
    public void showMoive() {
        ArrayList<MovieDTO> MovieList = MovieController.selectAll();
        if (MovieList.isEmpty()) {
            System.out.println("������ ��ȭ�� �����ϴ�");
        } else {
            for (MovieDTO m : MovieList) {
                System.out.println("--------------------------");
                System.out.println("������ ��ȭ ���� >" + m.getMovieName());
                System.out.println("--------------------------");
                System.out.println("�ڼ��� �� ��ȭ ��ȣ �Է� // �ڷΰ��� 0��");
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
        System.out.println("��ȭ ���� > " + m.getMovieName());
        System.out.println("................................");
        System.out.println("��ȭ �ٰŸ� > " + m.getSummary());
        System.out.println("................................");
        System.out.println("��ȭ ���� ���� ���� > " + m.getMovieLevel());
        System.out.println("................................");
        System.out.println("==================== ���==================== ");
        
        
        ReplyViewer.writeReply();
        //��� ��� �޼ҵ�
//        ReplyViewer.printReplyAll(movieNum);
    }

    // ������ �޴�
    public void showMoiveMenu() {
        while (true) {
            System.out.println("--------------------------");
            System.out.println("��ȭ �±� ������ ���/����/���� ������");
            System.out.println("���� ��ȭ ���");
            System.out.println("--------------------------");
            ArrayList<MovieDTO> MovieList = MovieController.selectAll();
            if (MovieList.isEmpty()) {
                System.out.println("������ ��ȭ�� �����ϴ�");
            } else {
                for (MovieDTO m : MovieList) {
                    System.out.println("--------------------------");
                    System.out.println("��ȣ > " + m.getMovieNum() + " / ���� > " + m.getMovieName());
                    System.out.println("--------------------------");

                }
            }
            System.out.println("1�� ��ȭ ����ϱ� 2�� �����ϱ�/���� 0�� �ڷΰ���");
            System.out.println("--------------------------");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 1) {
                insertMovie();
            } else if (adminChoice == 2) {
                System.out.println("--------------------------");
                System.out.println("������ ��ȭ ��ȣ�� �Է����ּ���");
                moiveUpdate(scanner.nextInt());
                System.out.println("--------------------------");
            } else if (adminChoice == 0) {
                UserViewer.adminMenu();
            }
        }
    }

    // ������ ��ȭ ���
    public void insertMovie() {
        System.out.println("��ȭ ��� ������");
        while (true) {
            MovieDTO m = new MovieDTO();
            System.out.println("--------------------------");
            String message = "����� ��ȭ ���� :";
            m.setMovieName(ScannerUtil.nextLine(scanner, message));
            message = "����� ��ȭ �Ұ� :";
            m.setSummary(ScannerUtil.nextLine(scanner, message));
            message = "����� ��ȭ ���";
            m.setMovieLevel(ScannerUtil.nextLine(scanner, message));

            MovieController.MovieInsert(m);
            System.out.println("--------------------------");
            System.out.println("�ڷΰ��� 0�� ");
            System.out.println("--------------------------");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 0) {
                showMoiveMenu();
            }
        }

    }

    // ��ȭ ���� ����/����
    public void moiveUpdate(int movieNum) {
        MovieDTO m = MovieController.selectOne(movieNum);
        System.out.println("--------------------------");
        System.out.println("��ȭ ����/ ���� ������ �Դϴ�");
        System.out.println("1�� �ش� ��ȭ ���� 2�� �����ϱ� 0�� �ڷΰ���");
        System.out.println("--------------------------");
        int adminChoice = scanner.nextInt();
        if (adminChoice == 1) {
            System.out.println(m.getMovieNum() + " �� ��ȭ  ���� : " + m.getMovieName());

            String message = "��ȭ ���� ����";
            m.setMovieName(ScannerUtil.nextLine(scanner, message));

            message = "��ȭ ���� ����";
            m.setSummary(ScannerUtil.nextLine(scanner, message));

            message = "��ȭ ��� ����";
            m.setMovieLevel(ScannerUtil.nextLine(scanner, message));

            MovieController.update(m);

        } else if (adminChoice == 2) {
            movieRemove(movieNum);
        } else if (adminChoice == 3) {
            showMoiveMenu();
        }

    }

    // ��ȭ ����
    public void movieRemove(int movieNum) {
        String yesNo = "���� ���� �Ͻðڽ��ϱ� ? Y/N";
        yesNo = ScannerUtil.nextLine(scanner, yesNo);
        if (yesNo.equalsIgnoreCase("Y")) {
            MovieController.removeMovie(movieNum);
            System.out.println("��ȭ ���� �Ϸ�");
            showMoiveMenu();
        } else {
            moiveUpdate(movieNum);
        }
    }

    // ��ȭ ���� ���� �����ֱ�
//   private void showStar() {
//        ArrayList<MovieDTO> movieStar = MovieController.selectAll();
//        
//        if(MovieStar.isEmpty()) {
//            
//        }
//    }
}
