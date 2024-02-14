package org.techtown.calendarmananger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    // 위젯 전역변수
    Button addButton;
    CalendarView calendar;
    TextView click_date;

    TextView memo;
    ArrayList<String> memoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 연결
        addButton = findViewById(R.id.addButton);
        calendar = findViewById(R.id.calendar);
        click_date = findViewById(R.id.click_date);
        memo = findViewById(R.id.memo);
        memoList = new ArrayList<>(); // ArrayList 초기화


        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setMessage("일정을 적어주세요!");
                ad.setTitle("띠용");
                final EditText et = new EditText(MainActivity.this);
                ad.setView(et);

                ad.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String result = et.getText().toString();
                        memoList.add(result); // 새로운 메모를 ArrayList에 추가
                        updateMemo(); // 메모를 업데이트
                        dialogInterface.dismiss();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });

                ad.show();
            }
        });
        // calendar 클릭 이벤트
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                click_date.setText(year + "년 "+ (month + 1) +"월 "+day+"일");
            }
        }); // calendar 클릭 이벤트
    }
    // 메모를 업데이트하는 메서드
    private void updateMemo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String memoItem : memoList) {
            stringBuilder.append(memoItem).append("\n"); // 각 메모를 줄바꿈과 함께 추가
        }
        memo.setText(stringBuilder.toString());
    }
}