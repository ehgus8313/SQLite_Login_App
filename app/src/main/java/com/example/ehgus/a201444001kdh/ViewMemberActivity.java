package com.example.ehgus.a201444001kdh;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMemberActivity extends AppCompatActivity {


    EditText editText;

    ListView listView;
    MemberAdapter adapter;

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new MemberAdapter();
        listView.setAdapter(adapter);
        helper = new DatabaseOpenHelper(ViewMemberActivity.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

    }

    public void onViewAllClicked(View view) {
        try {

            sql = "select id, pw, name, email "
                    + " from " + helper.tableName
                    + " where email > ?";
            String[] args= {"100"};
            Cursor c1 = database.rawQuery(sql, args);

            int recordCount = c1.getCount();
            for (int i = 0; i < recordCount; i++) {
                c1.moveToNext();
                //int vieMemID = c1.getInt(c1.getColumnIndex("memid"));
                String viewID = c1.getString(c1.getColumnIndex("id"));
                String viewPw = c1.getString(c1.getColumnIndex("pw"));
                String viewName = c1.getString(c1.getColumnIndex("name"));
                String viewEmail = c1.getString(c1.getColumnIndex("email"));
                adapter.addItem(new MemberItem(viewID, viewPw, viewName, viewEmail));
                adapter.notifyDataSetChanged();
            }
            c1.close();

            Toast toast = Toast.makeText(ViewMemberActivity.this, "회원정보 조회가 완료되었습니다.", Toast.LENGTH_SHORT);
            toast.show();
            // 반복문을 이용하여 Cursor의 내용을 추출
            // Cursor를 이동
            // cursor의 0번째 있는 내용을 vieMemID에 저장
            // cursor의 1번째 있는 내용을 viewID에 저장
            // cursor의 2번째 있는 내용을 viewName에 저장
            // cursor의 3번째 있는 내용을 viewPw에 저장
            // cursor의 4번째 있는 내용을 viewEmail에 저장
            
            // 추출된 정보를 가지고 MemberItem 객체
            // 생성된 객체를 adater 객체에 추가
            // listView에 adapter 객체 설정
            
            // Cursor 객체 종료
            // listView에 정보를 출력하면 "회원정보 조회가 완료되었습니다." 토스트 메세지 출력
            


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onBackClicked(View view) {
        finish();
    }

    class MemberAdapter extends BaseAdapter {
        ArrayList<MemberItem> items = new ArrayList<MemberItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MemberItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            MemberView view = new MemberView(getApplicationContext());

            MemberItem item = items.get(position);
            view.setViewName(item.getName());
            view.setViewID(item.getId());
            view.setViewEmail(item.getEmail());

            return view;
        }
    }

}
