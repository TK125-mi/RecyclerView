package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);

        toolbarLayout.setTitle(getString(R.string.toolbar_title));
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);
        FloatingActionButton fab = findViewById(R.id.fabEmail);


        // RecyclerViewを取得。
        RecyclerView lvMenu = findViewById(R.id.lvMenu);
        // LinearLayoutManagerオブジェクトを生成。
        LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);

        // RecyclerViewにレイアウトマネージャーとしてLinearLayoutManagerを設定。
        lvMenu.setLayoutManager(layout);
        // アラームリストデータを生成。
        List<Map<String, Object>> menuList = createTimeList();
        // アダプタオブジェクトを生成。
        RecyclerListAdapter adapter = new RecyclerListAdapter(menuList);
        // RecyclerViewにアダプタオブジェクトを設定。
        lvMenu.setAdapter(adapter);

        // 区切り専用のオブジェクトを生成。
        DividerItemDecoration decorator = new DividerItemDecoration(MainActivity.this, layout.getOrientation());
        // RecyclerViewに区切り線オブジェクトを設定。
        lvMenu.addItemDecoration(decorator);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
                startActivity(intent);
            }
        });
    }

        /**
         * リストビューに表示する時刻のリスト
         *　今後DBから取得予定
         * @return 生成されたアラームリストデータ。
         */
    private List<Map<String, Object>> createTimeList() {
        //アラームリスト用のListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();

        Map<String, Object> menu = new HashMap<>();
        menu.put("time", "11:00");
        menu.put("alarmName", "test");
        menuList.add(menu);
        return menuList;
    }

    private class RecyclerListViewHolder extends RecyclerView. ViewHolder {
        public TextView _AlarmNameRow;
        public TextView _tvMenuPriceRow;

        public RecyclerListViewHolder(@NonNull View itemView) {
            super(itemView);
            _AlarmNameRow = itemView.findViewById(R.id.tvAlarmNameRow);
            _tvMenuPriceRow = itemView.findViewById(R.id.tvAlarmRow);
        }
    }
    /**
     * RecyclerViewのアダプタクラス。
     */
    private class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListViewHolder> {
        /**
         * リストデータを保持するフィールド。
         */
        private List<Map<String, Object>> _listData;

        /**
         * コンストラクタ。
         *
         * @param listData リストデータ。
         */
        public RecyclerListAdapter(List<Map<String, Object>> listData) {
            // 引数のリストデータをフィールドに格納。
            _listData = listData;
        }

        @Override
        public RecyclerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // レイアウトインフレータを取得。
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            // row.xmlをインフレートし、1行分の画面部品とする。
            View view = inflater.inflate(R.layout.row, parent, false);
            // インフレートされた1行分の画面部品にリスナを設定。
            view.setOnClickListener(new ItemClickListener());
            // ビューホルダオブジェクトを生成。
            RecyclerListViewHolder holder = new RecyclerListViewHolder(view);
            // 生成したビューホルダをリターン。
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerListViewHolder holder, int position) {
            // リストデータから該当1行分のデータを取得。
            Map<String, Object> item = _listData.get(position);
            // アラーム名文字列を取得。
            String alarmName = (String) item.get("alarmName");
            // アラーム時間を取得。
            String time = (String) item.get("time");

            // メニュー名と金額をビューホルダ中のTextViewに設定。
            holder._AlarmNameRow.setText(alarmName);
            holder._tvMenuPriceRow.setText(time);
        }

        @Override
        public int getItemCount() {
            // リストデータ中の件数をリターン。
            return _listData.size();
        }
    }

    /**
     * リストをタップした時のリスナクラス。
     */
    private class ItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // タップされたLinearLayout内にあるアラーム名表示TextViewを取得。
            TextView alarmName =  view.findViewById(R.id.tvAlarmNameRow);
            // アラーム時間をTextViewから表示されているメニュー名文字列を取得。
            TextView alarmTime =view.findViewById(R.id.tvAlarmRow);

            String msg = alarmName.getText().toString()+" : "+alarmTime.getText().toString();
            // トースト表示。
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * アクティビティリセット
     * 今後使用するか不明
     *
     * */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}