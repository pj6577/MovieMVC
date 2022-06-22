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

            String message = "1. �α��� 2.�� ȸ������ 3.����";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // �α���
                logIn = null;
                login();
                // �Ϲ����� ��ȭ �޼ҵ�
                if (logIn.getLevel() == 1) {

                    MovieViewer.showMovie();
                    // ���� ��а�
                    System.out.println("�Ϲ�����");
                } else if (logIn.getLevel() == 2) {
                    // ������
                } else if (logIn.getAdminLevel() == 3) {
                    adminMenu();
                }
            } else if (userChoice == 2) {
                // ȸ������
                SignIn();
                System.out.println("ȸ������ ���� ");
            } else if (userChoice == 3) {
                System.out.println("����");
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
        String message = "����Ͻ� ���̵� �Է��ϼ���";
        u.setUserId(ScannerUtil.nextLine(scanner, message));

        message = "����Ͻ� ��й�ȣ �Է��ϼ���";
        u.setPassWord(ScannerUtil.nextLine(scanner, message));

        message = "����Ͻ� �г����� �Է��ϼ���";
        u.setNickName(ScannerUtil.nextLine(scanner, message));
        u.setLevel(1);
        UserController.UserInsert(u);

    }

    private void login() {
        String username = ScannerUtil.nextLine(scanner, "���̵� �Է����ּ���");
        String password = ScannerUtil.nextLine(scanner, "��й�ȣ�� �Է����ּ���");

        while (UserController.auth(username, password) == null) {
            String yesNo = ScannerUtil.nextLine(scanner, "�α����� �׸��Ͻðڽ��ϱ�? Y/N");
            if (yesNo.equalsIgnoreCase("Y")) {
                password = null;
                break;
            }
            username = ScannerUtil.nextLine(scanner, "���̵� �Է����ּ���");
            password = ScannerUtil.nextLine(scanner, "��й�ȣ�� �Է����ּ���");
        }
        logIn = UserController.auth(username, password);
    }

    // ������ �޴�
    // ���� ����Ʈ ����
    public void adminMenu() {
        while (true) {
            System.out.println("--------------------------");
            System.out.println("������ ���� ������ �Դϴ� ");
            System.out.println("1�� ���� ���� 2�� ��ȭ ���� 3�� ���� ���� 4�� �α׾ƿ�");
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
    //���� �󼼺���
    public void UserPrint() {
        ArrayList<UserDTO> UserList = UserController.selectAll();

        if (UserList.isEmpty()) {
            System.out.println("ȸ���� �����ϴ�");
        } else {
            for (UserDTO u : UserList) {
                System.out.printf("%d. %s\n", u.getId(), u.getNickName(), u.getLevel());
                System.out.println("--------------------------");
            }
            String message = "�󼼺��� �� ȸ���� ���̵� ��ȣ�� �Է����ּ��� �ڷΰ���� 0�� �Է�";
            int userChoice = ScannerUtil.nextInt(scanner, message);

            while (userChoice != 0 && UserController.selectOne(userChoice) == null) {
                System.out.println("�߸� �Է��ϼ̽��ϴ�.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    // ���� �� ����
    public void printOne(int id) {
        UserDTO u = UserController.selectOne(id);
        System.out.println("--------------------------");
        if (u.getLevel() == 1) {
            System.out.println("�Ϲ�ȸ��");
        } else if (u.getLevel() == 2) {
            System.out.println("��а�");
        } else if (u.getLevel() == 3) {
            System.out.println("������");
        }
        System.out.println("--------------------------");
        System.out.println(u.getId() + " �� �г��� : " + u.getNickName());
        System.out.println("���� ���̵� : " + u.getUserId() + " ���� ��й�ȣ : " + u.getPassWord());
        System.out.println("--------------------------");
        System.out.println("��� ������ ���Ͻø� 1�� �ڷΰ��� 0��");
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
        System.out.println("ȸ�� ���� ����/ ��� �ø��� ������ �Դϴ�");
        System.out.println("�� ȸ�� ���� ���� / ");
        System.out.println("--------------------------");
        String message = id + "�� ȸ�� ";
        System.out.println("��� ���� 1�� �Ϲ����� 2�� ��а� 3�� ����");
        u.setLevel(ScannerUtil.nextInt(scanner, message));
        if (u.getLevel() == 1) {
            System.out.println("�Ϲ�ȸ�� ���� �Ϸ�");
        } else if (u.getLevel() == 2) {
            System.out.println("��а� ���� �Ϸ�");
        } else if (u.getLevel() == 3) {
            System.out.println("���� ���� �Ϸ�");
        }
        System.out.println(u.getLevel());
        UserController.update(u);
        adminMenu();
    }

}
