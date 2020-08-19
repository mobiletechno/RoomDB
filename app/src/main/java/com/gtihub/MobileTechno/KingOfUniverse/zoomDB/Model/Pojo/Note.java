package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Pojo;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */
@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String studentname;
    private String fathername;
    private String date;
    private String mothername;
    private String imgurl;

    public Note(String studentname, String fathername, String mothername, String date, String imgurl) {
        this.studentname = studentname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.date = date;
        this.imgurl = imgurl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getFathername() {
        return fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public String getDate() {
        return date;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}