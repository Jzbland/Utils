设计思路
把置入适配器的list追加数据记录，再使用适配器的notifyDataSetChanged()刷新。
 
方法案例
以http://blog.csdn.net/jueblog/article/details/12114513的ListView为例，对Activity作如下改进。
[java]  
package com.app.test01;  
  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
  
import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject;  
  
import android.R.integer;  
import android.app.Activity;  
import android.os.Bundle;  
import android.view.ContextMenu;  
import android.view.LayoutInflater;  
import android.view.MenuItem;  
import android.view.View;  
import android.view.ContextMenu.ContextMenuInfo;  
import android.widget.AbsListView;  
import android.widget.AbsListView.OnScrollListener;  
import android.widget.AdapterView;  
import android.widget.AdapterView.AdapterContextMenuInfo;  
import android.widget.BaseAdapter;  
import android.widget.ListView;  
import android.widget.TextView;  
import android.widget.Toast;  
  
import com.app.adapter.MyWeixinJSON;  
import com.app.adapter.MyWeixinList;  
  
/** 
 * 点击  追加数据的ListView 
 * @author 402-9 
 * 
 */  
public class ListViewPage extends Activity {  
    private ListView lv;  
    private BaseAdapter mJson;  
    private JSONArray mData = new JSONArray();// JSON数据源  
    private View view_page_footer;// 底部视图  
    private int num = 1;// 加载数据计数  
    private int count = 50;// 总数据  
  
      
//  private boolean flag;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.weixin);  
        lv = (ListView) findViewById(R.id.lv);  
        getJSONArray(mData);  
          
        mJson = new MyWeixinJSON(mData, this);  
  
        view_page_footer = LayoutInflater.from(this).inflate(  
                R.layout.view_page_footer, null);  
        lv.addFooterView(view_page_footer);// 添加底部视图  
        TextView text_page = (TextView) view_page_footer.findViewById(R.id.text_page);  
        text_page.setOnClickListener(new View.OnClickListener() {  
            // 点击按钮 追加数据 并通知适配器  
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub  
                TextView tv = (TextView) v;  
                tv.setText("正在加载中...");  
                getJSONArray(mData);  
                tv.setText("下一页");  
                mJson.notifyDataSetChanged();  
            }  
        });  
  
        lv.setAdapter(mJson);// 绑定适配器  
  
    }  
  
    /** 数据源JSONArray */  
    private void getJSONArray(JSONArray jArray) {  
        try {  
            for (int i = 1; i <= 5; i++) {  
                JSONObject jsonObject = new JSONObject();  
                jsonObject.put("title", "姓名" + num++);  
                jsonObject.put("time", "9月29日");  
                jsonObject.put("info", "我通过了你的好友验证请求，现在我们可以开始对话啦");  
                jsonObject.put("img", R.drawable.special_spring_head2);  
                jArray.put(jsonObject);  
                if (num == count) {  
                    lv.removeFooterView(view_page_footer);  
                    Toast.makeText(this, "没有更多数据了...", Toast.LENGTH_LONG)  
                            .show();  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
    }  
      
}  
 
其中，所添加的底部视图，只有一个供点击追加的按钮：
[html] 
<?xml version="1.0" encoding="utf-8"?>  
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
    android:layout_width="match_parent"  
    android:layout_height="wrap_content"  
    android:orientation="vertical"   
    android:padding="5dp">  
  
    <TextView  
        android:id="@+id/text_page"  
        android:layout_width="fill_parent"  
        android:layout_height="wrap_content"  
        android:text="下一页"   
        android:gravity="center"/>  
  
</LinearLayout>  
 