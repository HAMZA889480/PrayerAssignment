package Model;

public class Activity {

    private int id;
    private String date;
    private int user_id;
    private int fajar;
    private int zohar;
    private int asar;
    private int maghrib;
    private int aisha;

    users user=new users();

    public Activity(String date, int fajar, int zohar, int asar,int maghrib, int aisha, int user_id)
    {
        this.date=date;
        this.user_id=user_id;
        this.fajar=fajar;
        this.zohar=zohar;
        this.asar=asar;
        this.maghrib=maghrib;
        this.aisha=aisha;
    }

    public Activity()
    {
        this.date="";
        this.fajar=-99;
        this.zohar=-99;
        this.asar=-99;
        this.maghrib=-99;
        this.aisha=-99;

    }
    public Activity(String date)
    {
        this.date=date;
        this.user_id=-99;
        this.fajar=-99;
        this.zohar=-99;
        this.maghrib=-99;
        this.aisha=-99;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int isAisha() {
        return aisha;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



    public int isFajar() {
        return fajar;
    }

    public int isMaghrib() {
        return maghrib;
    }

    public int isZohar() {
        return zohar;
    }

    public String getDate() {
        return date;
    }

    public void setAisha(int aisha) {
        this.aisha = aisha;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFajar(int fajar) {
        this.fajar = fajar;
    }

    public void setMaghrib(int maghrib) {
        this.maghrib = maghrib;
    }

    public void setZohar(int zohar) {
        this.zohar = zohar;
    }

    public int isAsar() {
        return asar;
    }

    public void setAsar(int asar) {
        this.asar = asar;
    }

    public int getUser_id() {
        return user_id;
    }
}
