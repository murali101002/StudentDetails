package android.com.inclass03;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    public static final int REQ_CODE1 = 101;
    public static final int REQ_CODE2 = 102;
    public static final int REQ_CODE3 = 103;
    public static final int REQ_CODE4 = 104;
    public static final String UPDATE_KEY = "Update";
    TextView name, email, dept, mood;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
//        Drawable bg = findViewById(R.id.mainLayout).getBackground();
//        bg.setAlpha(50);

        name = (TextView)findViewById(R.id.stdnt_name);
        email = (TextView)findViewById(R.id.stdnt_email);
        dept = (TextView)findViewById(R.id.stdnt_dept);
        mood = (TextView)findViewById(R.id.stdnt_mood);
        if(getIntent().getExtras()!=null){
            student = getIntent().getExtras().getParcelable(MainActivity.STUDENT);
            assert student != null;
            name.setText(student.stdnt_name);
            email.setText(student.stdnt_email);
            dept.setText(student.stdnt_dept);
            mood.setText(student.stdnt_mood+" "+getResources().getString(R.string.moodStatus));
        }else{
            name.setText("");
            email.setText("");
            dept.setText("");
            mood.setText("0 "+getResources().getString(R.string.moodStatus));
        }


    }
    Intent intent = new Intent("android.com.inclass03.intent.action.VIEW");
    Bundle extras = new Bundle();
    public void updateMood(View view) {
        String editField = "mood";
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        extras.putString(UPDATE_KEY,editField);
        extras.putParcelable(MainActivity.STUDENT,student);
        intent.putExtras(extras);
        startActivityForResult(intent,REQ_CODE1);

    }


    public void updateDepartment(View view) {
        String editField = "department";
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        extras.putString(UPDATE_KEY,editField);
        extras.putParcelable(MainActivity.STUDENT,student);
        intent.putExtras(extras);
        startActivityForResult(intent,REQ_CODE2);
    }

    public void updateName(View view) {
        String editField = "name";
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        extras.putString(UPDATE_KEY,editField);
        extras.putParcelable(MainActivity.STUDENT,student);
        intent.putExtras(extras);
        startActivityForResult(intent,REQ_CODE3);

    }

    public void updateEmail(View view) {
        String editField = "email";
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        extras.putString(UPDATE_KEY,editField);
        extras.putParcelable(MainActivity.STUDENT,student);
        intent.putExtras(extras);
        startActivityForResult(intent,REQ_CODE4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE1 || requestCode == REQ_CODE2 || requestCode == REQ_CODE3 ||requestCode == REQ_CODE4){
            if(resultCode == RESULT_OK){
                Student newStudent = data.getExtras().getParcelable(EditActivity.RESULT);
                student = newStudent;
                switch (requestCode){
                    case REQ_CODE1:
                        mood.setText(newStudent.getStdnt_mood()+" "+getResources().getString(R.string.moodStatus));
                        break;
                    case REQ_CODE2:
                        dept.setText(newStudent.getStdnt_dept());
                        break;
                    case REQ_CODE3:
                        name.setText(newStudent.getStdnt_name());
                        break;
                    case REQ_CODE4:
                        email.setText(newStudent.getStdnt_email());
                        break;
                }
            }
        }
    }
}
