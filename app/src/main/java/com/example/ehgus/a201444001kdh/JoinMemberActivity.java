package com.example.ehgus.a201444001kdh;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class JoinMemberActivity extends AppCompatActivity {
    EditText inputName;
    EditText inputID;
    EditText inputPW;
    EditText inputEmail;
    Button btnJoin;
    Button btncancle;
    Button btnback;
    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_member);

    inputName = (EditText)findViewById(R.id.inputName);
    inputID = (EditText)findViewById(R.id.inputID);
    inputPW = (EditText)findViewById(R.id.inputPW);
    inputEmail = (EditText)findViewById(R.id.inputEmail);
    btnJoin = (Button) findViewById(R.id.registerButton);
    btncancle = (Button) findViewById(R.id.cancle);
    btnback = (Button) findViewById(R.id.back);
        


        helper = new DatabaseOpenHelper(JoinMemberActivity.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String name = inputName.getText().toString();
                String id = inputID.getText().toString();
                String pw = inputPW.getText().toString();
                String email = inputEmail.getText().toString();


                if(id.length() == 0 || pw.length() == 0 || name.length() == 0 || email.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(JoinMemberActivity.this, "정보를 입력해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT id FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    //존재하는 아이디입니다.
                    Toast toast = Toast.makeText(JoinMemberActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    helper.insertUser(database,id,pw,name,email);
                    Toast toast = Toast.makeText(JoinMemberActivity.this, name + "님 가입이 완료되었습니다. 로그인을 해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btncancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                inputName.getText().clear();

                inputID.getText().clear();

                inputPW.getText().clear();

                inputEmail.getText().clear();
            }
            });

        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


            // "kdudb" 데이터베이스 연결하여 "member" 테이블 생성
            // 단, 테이블이 없을 경우만 "member" 테이블 생성
            // memId 는 integer PRIMARY KEY autoincrement
            // id 는 text NOT NULL
            // name 는 text NOT NULL
            // pw 는 text NOT NULL
            // email 는 text NOT NULL
            
            // 입력된 데이터를 "member" 테이블에 저장
            // 데이터베이스에 저장이 완료되면 "~~~(이름) 님이 회원가입이 완료되었습니다." 토스트 메세지 출력 후
            // 토스트 메세지 출력 후 이전 액티비티로 이동

}
}
