package model;

public class TheaterDTO {
    private int theaterNum;
    private int theaterPhoneNum;
    private String theaterName;
    private String theaterWhere;

    public int getTheaterNum() {
        return theaterNum;
    }

    public void setTheaterNum(int theaterNum) {
        this.theaterNum = theaterNum;
    }

    public int gettheaterPhoneNum() {
        return theaterPhoneNum;
    }

    public void settheaterPhoneNum(int phoneNum) {
        this.theaterPhoneNum = phoneNum;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterWhere() {
        return theaterWhere;
    }

    public void setTheaterWhere(String theaterWhere) {
        this.theaterWhere = theaterWhere;
    }

    public TheaterDTO() {

    }

    public TheaterDTO(int theaterNum) {

    }

    public TheaterDTO(String theaterName, String theaterWhere, int theaterNum, int theaterPhoneNum) {
        this.theaterName = new String();
        this.theaterWhere = new String();
        this.theaterNum = theaterNum;
        this.theaterPhoneNum = theaterPhoneNum;
    }

    public TheaterDTO(TheaterDTO t) {
        this.theaterName = t.theaterName;
        this.theaterWhere = t.theaterWhere;
        this.theaterNum = t.theaterNum;
        this.theaterPhoneNum = t.theaterPhoneNum;
    }

    public boolean equlas(Object o) {
        if (o instanceof TheaterDTO) {
            TheaterDTO t = (TheaterDTO) o;
            return theaterNum == t.theaterNum;
        }
        return false;
    }

}
