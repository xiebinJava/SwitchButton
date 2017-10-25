# SwitchButton

仿IOS风格的开关，简洁易懂，只有一个类文件。

如何添加控件到项目

  1:下载此项目，拿到SwitchButton这个类，复制到你的项目中。然后把自定义属性添加到你的attrs文件中
  
  ![Aaron Swartz](https://github.com/xiebinJava/SwitchButton/blob/master/switchbutton.png?raw=true)
  
  2:在你的布局文件中添加
  
  
                    <com.xie.brad.myswitchbutton.MyButton.SwitchButton
                    android:id="@+id/switchbutton"
                    android:layout_width="100dp"
                    android:layout_height="wrap_content"
                    android:layout_centerInParent="true"
                    app:switch_state="true"/>
                    
                    
 
   switch_state 设置按钮的开关状态。
   
   3：设置回调
   
   
          package com.xie.brad.myswitchbutton;
          import android.os.Bundle;
          import android.support.v7.app.AppCompatActivity;
          import android.widget.Toast;
          import com.xie.brad.myswitchbutton.MyButton.SwitchButton;
          public class MainActivity extends AppCompatActivity implements SwitchButton.OnSwitchListener {

              @Override
              protected void onCreate(Bundle savedInstanceState) {
                  super.onCreate(savedInstanceState);
                  setContentView(R.layout.activity_main);
                  SwitchButton switchButton = (SwitchButton) findViewById(R.id.switchbutton);
                  switchButton.setOnSwitchListener(this);//设置button的点击回调
              }

              @Override
              public void openbutton() {
                  Toast.makeText(this,"开",Toast.LENGTH_SHORT).show();
              }

              @Override
              public void closebutton() {
                  Toast.makeText(this,"关",Toast.LENGTH_SHORT).show();
              }
          }


   4:如何调整控件的高度？
   ![Aaron Swartz]()
