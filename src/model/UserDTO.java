package model;

public class UserDTO {
    private int id;
    private int level;
    private String userId;
    private String passWord;
    private String nickName;
    private String adminId = "adminid";
    private String adminPw = "adminpw";
    private int adminLevel = 3;

    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = "adminid";
    }

    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = "adminpw";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserDTO() {

    }

    public UserDTO(String nickName, String userId, String passWord, int id, int level, String adminId, String adminPw) {
        this.nickName = new String();
        this.userId = new String();
        this.passWord = new String();
        this.id = id;
        this.level = level;
        this.adminId = "adminid";
        this.adminPw = "adminpw";
    }

    public UserDTO(UserDTO u) {
        this.nickName = u.nickName;
        this.userId = u.userId;
        this.passWord = u.passWord;
        this.id = u.id;
        this.level = u.level;
        this.adminId = "adminid";
        this.adminPw = "adminpw";
    }

    public boolean equals(Object o) {
        if (o instanceof UserDTO) {
            UserDTO u = (UserDTO) o;
            return id == u.id;
        }
        return false;
    }

}
