package android.com.inclass03;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by murali101002 on 9/5/2016.
 */
public class Student implements Parcelable {

    public Student(String stdnt_dept, String stdnt_email, String stdnt_mood, String stdnt_name) {
        this.stdnt_dept = stdnt_dept;
        this.stdnt_email = stdnt_email;
        this.stdnt_mood = stdnt_mood;
        this.stdnt_name = stdnt_name;
    }

    String stdnt_name,stdnt_email, stdnt_dept, stdnt_mood;

    protected Student(Parcel in) {
        stdnt_name = in.readString();
        stdnt_email = in.readString();
        stdnt_dept = in.readString();
        stdnt_mood = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getStdnt_dept() {
        return stdnt_dept;
    }

    public void setStdnt_dept(String stdnt_dept) {
        this.stdnt_dept = stdnt_dept;
    }

    public String getStdnt_email() {
        return stdnt_email;
    }

    public void setStdnt_email(String stdnt_email) {
        this.stdnt_email = stdnt_email;
    }

    public String getStdnt_mood() {
        return stdnt_mood;
    }

    public void setStdnt_mood(String stdnt_mood) {
        this.stdnt_mood = stdnt_mood;
    }

    public String getStdnt_name() {
        return stdnt_name;
    }

    public void setStdnt_name(String stdnt_name) {
        this.stdnt_name = stdnt_name;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stdnt_name);
        dest.writeString(stdnt_email);
        dest.writeString(stdnt_dept);
        dest.writeString(stdnt_mood);
    }
}
