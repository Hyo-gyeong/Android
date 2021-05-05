package ddwucom.mobile.week07.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final static int MENU_FIRST = 100;
    final static int MENU_SECOND = 200;

    PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);//해당view에

        popup = new PopupMenu(this, textView); //popup생성
        popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu()); //메뉴객체 얻어와서 메뉴랑 연결
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { //이벤트 처리부분
            @Override
            public boolean onMenuItemClick(MenuItem item) { //item구분
                Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //text view를 touch했을 때 뜨도록 팝업메뉴 만들기 - touch event구현 & 이름없는 객체로 구현
        textView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                popup.show();
                return true;
            }
        });
        //context view
//        registerForContextMenu(textView); //context menu 생성
    }

    //롱클릭시 메뉴 띄우기위한 코드 - 메뉴 생성
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.textView:
                //코드로 만든 메뉴
                menu.setHeaderTitle("Context Menu"); //header title 별도로 출력가능
                menu.add(0, MENU_FIRST, 0,"FIRST");
                menu.add(0, MENU_SECOND, 0,"SECOND");
//                        //그룹id, item id, 순서(0은 입력순)
                getMenuInflater().inflate(R.menu.menu_context, menu); //xml에 설계한대로 보여줘라(추가), 이 방법이 더 관리하기 좋음
                //이전거 없애고 싶으면 menu.clear();
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { //메뉴 하나씩 들어옴
        switch (item.getItemId()){
            case MENU_FIRST: //상수라서 변수이름 가져온 것
                Log.i(TAG,"context 1");
                break;
            case MENU_SECOND:
                Log.i(TAG,"context 2");
                break;
            case R.id.thrid: //view에서 id를 가져와야하니까
                Log.i(TAG,"context 3");
                break;
            case R.id.fourth:
                Log.i(TAG,"context 4");
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_menu, menu);
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if(true){
//            menu.clear();
//            getMenuInflater().inflate(R.menu.group_menu, menu);
//        }
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gitem01:
                if (item.isChecked())
                    item.setChecked(false);
                else item.setChecked(true);
                break;
            case R.id.gitem02:
                if (item.isChecked())
                    item.setChecked(false);
                else item.setChecked(true);
                break;
            case R.id.gitem03:
                item.setChecked(true); //라디오버튼이니까 무조건 다른건 false가 됨
                break;
            case R.id.gitem04:
                item.setChecked(true);
                break;
        }
        return true;
    }

    public void onMenuItemClick(MenuItem item){
        Log.i(TAG, "item01 is clicked!!!");
    }
}