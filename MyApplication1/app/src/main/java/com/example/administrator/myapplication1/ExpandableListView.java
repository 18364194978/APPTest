package com.example.administrator.myapplication1;
//(#118)
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class ExpandableListView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_expandlistview);
        //创建一个BaseExpandableListAdapter对象
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] logos = new int[]{
                    R.drawable.ebook_128x128_32,
                    R.drawable.voice_128x128_32,
                    R.drawable.gallery_128x128_32
            };
            private String[] armTypes = new String[]{
                    "神族兵种","虫族兵种","人族兵种"
            };
            private String[][] arms = new String[][]{
                    {"狂战士","龙骑士","黑暗骑士","电兵"},
                    {"响尾蛇","黑寡妇","千足虫","天蝎"},
                    {"机枪兵","步兵","小护士","幽灵"}
            };
            //获取指定组位置、指定子列表想出的子列表数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }
            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }
            private TextView getTextView(){
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView textView = new TextView(ExpandableListView.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;
            }
            //该方法决定每个子选项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView =  getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                return textView;
            }
            //获取指定组位置处的数据
            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }
            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }
            //该方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableListView.this);
                ll.setOrientation(0);
                ImageView logo = new ImageView(ExpandableListView.this);
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
    }
}
