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



public class MainActivity extends AppCompatActivity {
    // 위젯 전역변수
    Button addButton;
    CalendarView calendar;
    TextView click_date;
    TextView memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 연결
        addButton = findViewById(R.id.addButton);
        calendar = findViewById(R.id.calendar);
        click_date = findViewById(R.id.click_date);
        memo = findViewById(R.id.memo);

        addButton.setOnClickListener(new View.OnClickListener(){

            // 팝업 버튼
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setMessage("일정을 적어주세요!");
                ad.setTitle("띠용");
                final EditText et = new EditText(MainActivity.this);
                ad.setView(et);

                // 팝업 저장 버튼
                ad.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String result = et.getText().toString();
                        if (memo != null) {  // null 체크 추가
                            memo.setText(result);
                        }
                        dialogInterface.dismiss();
                    }
                });

                // 팝업 취소 버튼
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
}