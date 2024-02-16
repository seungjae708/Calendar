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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // 위젯 전역변수
    Button addButton;
    CalendarView calendar;
    TextView click_date;

    TextView memo;
    ArrayList<String> memoList;
    String memoFileName;

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
                        //memoList.add(result); // 새로운 메모를 ArrayList에 추가
                        saveMemo(result);
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
                memoFileName = getMemoFileName(year, month, day);
                loadMemo(memoFileName);
            }
        }); // calendar 클릭 이벤트
    }
    // 파일 이름 생성
    private String getMemoFileName(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date(year - 1900, month, day); // Date의 년도는 1900을 기준으로 계산
        return sdf.format(date) + ".txt";
    }

    // 메모 저장
    private void saveMemo(String memoContent) {
        if (memoFileName == null) {
            Toast.makeText(this, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileOutputStream fos = openFileOutput(memoFileName, MODE_PRIVATE);
            fos.write(memoContent.getBytes());
            fos.close();
            Toast.makeText(this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "메모를 저장하는 데 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // 메모 불러오기
    private void loadMemo(String fileName) {
        try {
            FileInputStream fis = openFileInput(fileName);
            byte[] memoData = new byte[fis.available()];
            fis.read(memoData);
            fis.close();
            String memoContent = new String(memoData);
            memo.setText(memoContent);
        } catch (Exception e) {
            e.printStackTrace();
            memo.setText(""); // 파일이 없을 경우 메모 텍스트뷰를 비움
        }
    }
}