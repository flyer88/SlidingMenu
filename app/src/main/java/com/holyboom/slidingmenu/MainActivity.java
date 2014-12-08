package com.holyboom.slidingmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView mListView;

    private SwipeAdapter mAdapter;
    private List<String> a;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.listView);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        a = new ArrayList<String>();
        for(int i = 0 ;i<6 ;i++){
            a.add("Hello world!!");
        }
        mAdapter = new MyAdapter(MainActivity.this,a);
        mListView.setAdapter(mAdapter);
    }

    class MyAdapter extends SwipeAdapter {
        Context context;
        MyAdapter(Context context,List<String> content){
            super(context,content);
            this.content =content;
            this.context =context;
        }

        @Override
        public void setContent(ViewHolder viewHolder , int position) {
            viewHolder.textContent.setText(a.get(position)+"");
        }

        @Override
        public void setButtonClickListener(ViewHolder viewHolder,final int position) {

            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void bindViewHolder(ViewHolder viewHolder, View convertView) {
            viewHolder.action = convertView.findViewById(R.id.action);
            viewHolder.horizonTalScrollView = (HorizontalScrollView)convertView.findViewById(R.id.horizonTalScrollView);
            viewHolder.textContent = (TextView)convertView.findViewById(R.id.textContent);
            viewHolder.button = (Button)convertView.findViewById(R.id.button);
            viewHolder.buttonTest = (Button)convertView.findViewById(R.id.buttonTest);

        }
    }
}
