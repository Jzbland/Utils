���˼·
��������������list׷�����ݼ�¼����ʹ����������notifyDataSetChanged()ˢ�¡�
 
��������
��http://blog.csdn.net/jueblog/article/details/12114513��ListViewΪ������Activity�����¸Ľ���
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
 * ���  ׷�����ݵ�ListView 
 * @author 402-9 
 * 
 */  
public class ListViewPage extends Activity {  
    private ListView lv;  
    private BaseAdapter mJson;  
    private JSONArray mData = new JSONArray();// JSON����Դ  
    private View view_page_footer;// �ײ���ͼ  
    private int num = 1;// �������ݼ���  
    private int count = 50;// ������  
  
      
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
        lv.addFooterView(view_page_footer);// ��ӵײ���ͼ  
        TextView text_page = (TextView) view_page_footer.findViewById(R.id.text_page);  
        text_page.setOnClickListener(new View.OnClickListener() {  
            // �����ť ׷������ ��֪ͨ������  
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub  
                TextView tv = (TextView) v;  
                tv.setText("���ڼ�����...");  
                getJSONArray(mData);  
                tv.setText("��һҳ");  
                mJson.notifyDataSetChanged();  
            }  
        });  
  
        lv.setAdapter(mJson);// ��������  
  
    }  
  
    /** ����ԴJSONArray */  
    private void getJSONArray(JSONArray jArray) {  
        try {  
            for (int i = 1; i <= 5; i++) {  
                JSONObject jsonObject = new JSONObject();  
                jsonObject.put("title", "����" + num++);  
                jsonObject.put("time", "9��29��");  
                jsonObject.put("info", "��ͨ������ĺ�����֤�����������ǿ��Կ�ʼ�Ի���");  
                jsonObject.put("img", R.drawable.special_spring_head2);  
                jArray.put(jsonObject);  
                if (num == count) {  
                    lv.removeFooterView(view_page_footer);  
                    Toast.makeText(this, "û�и���������...", Toast.LENGTH_LONG)  
                            .show();  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
    }  
      
}  
 
���У�����ӵĵײ���ͼ��ֻ��һ�������׷�ӵİ�ť��
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
        android:text="��һҳ"   
        android:gravity="center"/>  
  
</LinearLayout>  
 