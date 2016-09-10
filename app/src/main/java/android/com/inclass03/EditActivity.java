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
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    public static final String RESULT = "Result";
    EditText name, email;
    RadioGroup depts;
    TextView deptLbl, moodLbl;
    SeekBar seekbar;
    String dept = null, mood;
    Button save;
    Student student;
    String updateField, updateName, updateEmail, updateMood, updateDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name = (EditText) findViewById(R.id.student_name_edit);
        deptLbl = (TextView) findViewById(R.id.rg_lbl);
        moodLbl = (TextView) findViewById(R.id.mood_lbl);
        email = (EditText) findViewById(R.id.student_email_edit);
        depts = (RadioGroup) findViewById(R.id.rg_dept_edit);
        seekbar = (SeekBar) findViewById(R.id.seekbar_edit);
        save = (Button) findViewById(R.id.btn_submit);
        depts.setOnCheckedChangeListener(this);
        seekbar.setOnSeekBarChangeListener(this);
        hideAllComponents();
        save.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            student = getIntent().getExtras().getParcelable(MainActivity.STUDENT);
            updateField = getIntent().getExtras().getString(DisplayActivity.UPDATE_KEY);

            switch (updateField) {
                case "name":
                    hideOtherComponents(name);
                    name.setText(student.stdnt_name);
//                    student.setStdnt_name(name.getText().toString());
                    break;
                case "email":
                    hideOtherComponents(email);
                    email.setText(student.stdnt_email);
//                    student.setStdnt_email(email.getText().toString());
                    break;
                case "department":
                    hideOtherComponents(depts);
                    int count = depts.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View o = depts.getChildAt(i);
                        if (o instanceof RadioButton) {
                            RadioButton selectedAnswer = (RadioButton) o;
                            if (selectedAnswer.getText().equals(student.stdnt_dept)) {
                                selectedAnswer.setChecked(true);
                            }
                        }
                    }
                    break;
                case "mood":
                    hideOtherComponents(seekbar);
                    seekbar.setProgress(Integer.parseInt(student.stdnt_mood));
                    break;
            }

        }
    }

    private void hideOtherComponents(View component) {
        component.setVisibility(View.VISIBLE);
        if (component.getId() == R.id.rg_dept_edit) {
            deptLbl.setVisibility(View.VISIBLE);
        } else if (component.getId() == R.id.seekbar_edit) {
            moodLbl.setVisibility(View.VISIBLE);
        }
    }

    private void hideAllComponents() {
        name.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        deptLbl.setVisibility(View.GONE);
        depts.setVisibility(View.GONE);
        moodLbl.setVisibility(View.GONE);
        seekbar.setVisibility(View.GONE);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        student.setStdnt_dept(((RadioButton) findViewById(checkedId)).getText().toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        student.setStdnt_mood(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        if (getIntent().getExtras() != null) {
            updateField = getIntent().getExtras().getString(DisplayActivity.UPDATE_KEY);
            Intent intent = new Intent();
            switch (updateField) {
                case "name":
                    if (!name.getText().toString().trim().equals("")) {
                        student.setStdnt_name(name.getText().toString());
                        intent.putExtra(RESULT, student);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        name.setError("Please enter a valid Name");
                    }

                    break;
                case "email":
                    if (email.getText().toString().trim().equals("") || (!email.getText().toString().contains("@")) || (!email.getText().toString().contains("."))) {
                        email.setError("Please enter a valid email");
                    } else {
                        student.setStdnt_email(email.getText().toString());
                        intent.putExtra(RESULT, student);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    break;
                case "department":
                    intent.putExtra(RESULT, student);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case "mood":
                    intent.putExtra(RESULT, student);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }
        }

    }
}
