package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MovieDTO;
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
                }
            }
        }
        return null;
    }

    // 유저 업데이트 메소드
    public void update(UserDTO u) {
        int index = UserList.indexOf(u);
        UserList.set(index, u);
    }

}
