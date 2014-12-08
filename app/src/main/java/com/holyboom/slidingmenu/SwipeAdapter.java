package com.holyboom.slidingmenu;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;


import java.util.List;


/**
 * Created by flyer on 14-12-4.
 */
public abstract class SwipeAdapter<T> extends BaseAdapter{


//
//   填充list
//
//  public List<String> contentString;
//  public List<Integer> contentInteger;

    public List<T> content;

//
//上下文
//

    private Context context;
    public SwipeAdapter(Context context,List<T> content){
        this.context = context;
        this.content = content;
    }
//    public SwipeAdapter(Context context,List<Integer> contentInteger){
//        this.context = context;
//        this.contentInteger = contentInteger;
//    }

    public abstract void setContent(ViewHolder viewHolder,int position);

    public abstract void setButtonClickListener(ViewHolder viewHolder,final int position);

    public abstract void bindViewHolder(ViewHolder viewHolder,View convertView);

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //创建临时存储view的holder
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            //动态载入视图
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            //绑定视图
            bindViewHolder(viewHolder,convertView);
//            viewHolder.horizonTalScrollView = (HorizontalScrollView)convertView.findViewById(R.id.horizonTalScrollView);
//            viewHolder.textContent = (TextView)convertView.findViewById(R.id.textContent);
//            viewHolder.button = (Button)convertView.findViewById(R.id.button);
            //设置屏幕宽度等于text的宽度，让button挤出手机屏幕
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            viewHolder.textContent.getLayoutParams().width = wm.getDefaultDisplay().getWidth();
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        //设置滑动事件
        convertView.setOnTouchListener(new SwipeViewOnTouchListener(context,position));
//设置监听事件
//        convertView.setOnTouchListener(new View.OnTouchListener()  {
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_UP:
//
//                        //获得ViewHolder
//                        ViewHolder viewHolder = (ViewHolder) v.getTag();
//
//                        //获得HorizontalScrollView滑动的水平方向值.
//                        int scrollX = viewHolder.horizonTalScrollView.getScrollX();
//
//                        //获得操作区域的长度
//                        int actionW = viewHolder.button.getWidth();
//
//
//                        /**
//                         *  注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
//                         ** 如果水平方向的移动值<操作区域的长度的一半,就复原
//                         ** 否则的话显示操作区域
//                         */
//                        if (scrollX < actionW / 3){
//                            viewHolder.horizonTalScrollView.smoothScrollTo(0, 0);
//                        }else{
//                            viewHolder.horizonTalScrollView.smoothScrollTo(actionW, 0);
//                        }
//                        return true;
//                }
//                return false;
//            }
//        });
        Log.d("SwipeAdapter", "" + position);
//viewHolder.textContent.setText("hello");
        //填充viewholder的内容
        //防止删除一条item后,ListView处于操作状态,直接还原
        setContent(viewHolder,position);
        setButtonClickListener(viewHolder,position);
        if (viewHolder.horizonTalScrollView.getScrollX() != 0)  {
            viewHolder.horizonTalScrollView.scrollTo(0, 0);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
