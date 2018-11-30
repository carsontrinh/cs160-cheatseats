package com.cc.mad.cheatseats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FloorViewActivity extends BaseActivity{

    EditText student_id, student_name;
    TextView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);
        student_id = (EditText) findViewById(R.id.student_id);
        student_name = (EditText) findViewById(R.id.student_name);
        lst = (TextView) findViewById(R.id.lst);
    }

    public void loadStudents(View view) {
        DBHandler dbHandler = new DBHandler(this);
        lst.setText(dbHandler.loadHandler());
        student_id.setText("");
        student_name.setText("");
    }

    public void addStudent(View view) {
        DBHandler dbHandler = new DBHandler(this);
        int id = Integer.parseInt(student_id.getText().toString());
        String name = student_name.getText().toString();
        Student student = new Student(id, name);
        dbHandler.addHandler(student);
        student_id.setText("");
        student_name.setText("");
    }

    public void findStudent(View view) {
        DBHandler dbHandler = new DBHandler(this);
        Student student =
                dbHandler.findHandler(student_name.getText().toString());
        if (student != null) {
            lst.setText(String.valueOf(student.getID()) + " " + student.getStudentName() + System.getProperty("line.separator"));
            student_id.setText("");
            student_name.setText("");
        } else {
            lst.setText("No Match Found");
            student_id.setText("");
            student_name.setText("");
        }
    }

    public void updateStudent(View view) {
        DBHandler dbHandler = new DBHandler(this);
        boolean result = dbHandler.updateHandler(Integer.parseInt(
                student_id.getText().toString()), student_name.getText().toString());
        if (result) {
            student_id.setText("");
            student_name.setText("");
            lst.setText("Record Updated");
        } else
            student_id.setText("No Match Found");
    }
}
