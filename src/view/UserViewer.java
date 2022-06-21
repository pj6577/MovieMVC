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

            String message = "1. �α��� 2.�� ȸ������ 3.����";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // �α���
                login();
                if (logIn.getLevel() == 1) {
                    // �Ϲ����� ��ȭ �޼ҵ�
                    MovieViewer.showMovie();
                    System.out.println("�Ϲ�����");
                } else if (logIn.getLevel() == 2) {
                    // ���� ��а�
                } else if (logIn.getAdminLevel() == 3) {
                    System.out.println("������ ���� �α��� ����");
                    UserPrint();
                 
                    //������
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
    //���� ����Ʈ ����
    public void UserPrint() {
        ArrayList<UserDTO> UserList = UserController.selectAll();

        if (UserList.isEmpty()) {
            System.out.println("ȸ���� �����ϴ�");
        } else {
            for (UserDTO u : UserList) {
                System.out.printf("%d. %s\n", u.getId(), u.getNickName(), u.getLevel());
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
    //���� �� ����
    public void printOne(int id) {
        UserDTO u = UserController.selectOne(id);
        System.out.println("--------------------------");
        if(u.getLevel() == 1) {
            System.out.println("�Ϲ�ȸ��");
        } else if(u.getLevel()==2 ) {
            System.out.println("��а�");
        } else if(u.getLevel()==3 ) {
            System.out.println("������");
        }
        System.out.println(u.getId() +" �� �г��� : "  + u.getNickName()  );
        System.out.println( "���� ���̵� : "+u.getUserId() + " ���� ��й�ȣ : " + u.getPassWord());
        System.out.println("--------------------------");
    }

}
