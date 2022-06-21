package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.UserDTO;

public class UserController {

    ArrayList<UserDTO> UserList;
    Scanner scanner = new Scanner(System.in);
    int nextId;


    public UserController() {
        UserList = new ArrayList<>();
        nextId = 1;
    }

    public void UserInsert(UserDTO u) {
        u.setId(nextId++);
        UserList.add(u);
       
    }

    public ArrayList<UserDTO> selectAll() {
        ArrayList<UserDTO> temp = new ArrayList<>();

        for (UserDTO u : UserList) {
            temp.add(new UserDTO(u));
        }
        return temp;
    }

    public UserDTO selectOne(int id) {
        for (UserDTO u : UserList) {
            if (u.getId() == id) {
                return new UserDTO(u);
            }
        }
        return null;
    }

    public UserDTO auth(String username, String password) {
        for (UserDTO u : UserList) {
            if (u.getUserId().equalsIgnoreCase(username)) {
                if (u.getPassWord().equals(password)) {
                
                    return new UserDTO(u);
                } else {
                    System.out.println("비밀번호 오류");
                }
            } else {
                System.out.println("아이디 오류");
            }
        }
        return null;
    }

}
