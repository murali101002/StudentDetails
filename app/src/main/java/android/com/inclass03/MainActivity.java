package android.com.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    public static final String STUDENT = "Student";
    EditText name, email;
    RadioGroup depts;
    SeekBar seekbar;
    int prog = 0;
    String dept = null, mood;
    Button submit;
    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.student_name);
        email = (EditText) findViewById(R.id.student_email);
        depts = (RadioGroup) findViewById(R.id.rg_dept);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        submit = (Button) findViewById(R.id.btn_submit);
        depts.setOnCheckedChangeListener(this);
        seekbar.setOnSeekBarChangeListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (name.getText().toString().trim().equals("") && email.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter Name and Email fields", Toast.LENGTH_SHORT).show();
        } else if (name.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().trim().equals("") || (!email.getText().toString().contains("@")) || (!email.getText().toString().contains("."))) {
            Toast.makeText(getApplicationContext(), "Please enter valid Email", Toast.LENGTH_SHORT).show();
        } else if (!isChecked) {
            Toast.makeText(getApplicationContext(), "Please select your department", Toast.LENGTH_SHORT).show();
        } else {
            mood = String.valueOf(prog);
            Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
            Student student = new Student(dept, email.getText().toString(), mood, name.getText().toString());
            intent.putExtra(STUDENT, student);
            startActivity(intent);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        dept = ((RadioButton) findViewById(checkedId)).getText().toString();
        isChecked = true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        prog = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
