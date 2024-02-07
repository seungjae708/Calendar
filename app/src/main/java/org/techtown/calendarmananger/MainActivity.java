package org.techtown.calendarmananger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    // 위젯 전역변수
    CalendarView calendar;
    TextView click_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 연결
        calendar = findViewById(R.id.calendar);
        click_date = findViewById(R.id.click_date);

        // calendar 클릭 이벤트
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                click_date.setText(year + "년 "+ (month + 1) +"월 "+day+"일");
            }
        }); // calendar 클릭 이벤트
    }
}