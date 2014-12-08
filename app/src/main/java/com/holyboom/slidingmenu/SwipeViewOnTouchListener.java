package com.holyboom.slidingmenu;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by flyer on 14-12-5.
 */
public class SwipeViewOnTouchListener implements View.OnTouchListener{

    Context context;
    int position;

    SwipeViewOnTouchListener(Context context,int position){
        this.context =context;
        this.position = position;
    }

    public boolean onTouch(View v, MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:

                //获得ViewHolder
                ViewHolder viewHolder = (ViewHolder) v.getTag();

                //获得HorizontalScrollView滑动的水平方向值.
                int scrollX = viewHolder.horizonTalScrollView.getScrollX();

                //获得操作区域的长度
//                int actionA = viewHolder.button.getWidth();
//                int actionB = viewHolder.buttonTest.getWidth();
//                int actionW = actionA+actionB;
                int actionW = viewHolder.action.getWidth();
                /**
                 *  注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
                 ** 如果水平方向的移动值<操作区域的长度的一半,就复原
                 ** 否则的话显示操作区域
                 */
                if (scrollX < actionW / 3){
                    viewHolder.horizonTalScrollView.smoothScrollTo(0, 0);
                }else{
                    viewHolder.horizonTalScrollView.smoothScrollTo(actionW, 0);
                }
                return true;
        }
        return false;
    }
//    /**
//     * Called when a touch event is dispatched to a view. This allows listeners to
//     * get a chance to respond before the target view.
//     *
//     * @param v     The view the touch event has been dispatched to.
//     * @param event The MotionEvent object containing full information about
//     *              the event.
//     * @return True if the listener has consumed the event, false otherwise.
//     */
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        switch (event.getAction()){
//            //手指离开的时候判断是否到1/3
//            case MotionEvent.ACTION_UP:
//                /**
//                 * 获取viewHolder
//                 * 获得HorizontalScrollView滑动的水平方向值.
//                 * 获得操作区域的长度
//                 */
//                ViewHolder viewHolder = (ViewHolder) v.getTag();
//                int scrollX = viewHolder.horizonTalScrollView.getScrollX();
//                int actionW = viewHolder.button.getWidth();
//                /**
//                 *  注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
//                 ** 如果水平方向的移动值<操作区域的长度的一半,就复原
//                 ** 否则的话显示操作区域
//                 */
//                if (scrollX < actionW / 3){
//                    viewHolder.horizonTalScrollView.smoothScrollTo(0, 0);
//                }else{
//                    viewHolder.horizonTalScrollView.smoothScrollTo(actionW, 0);
//                }
//                return true;
//        }
//        return false;
//    }
}
