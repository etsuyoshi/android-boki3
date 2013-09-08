package com.endo.bokicomp;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView tvTitle = (TextView)findViewById(R.id.tx_title);
        tvTitle.setText("簿記３級(試験対策)\n");
        
        TextView tvSub = (TextView)findViewById(R.id.tx_sub_title);
        tvSub.setText("presented by 金融経済教育研究所");
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    public void onClick(View v){
    	if (((Button)v).getId()==R.id.bt_start){
    		System.out.println("pressed start!!");
    		//execute!!

    		Intent intent = new Intent(MainActivity.this, SelectStory.class);
    		//次画面のアクティビティ起動
    		startActivity(intent);

    		
    	}else if(((Button)v).getId()==R.id.bt_exit){
    		System.out.println("exit");
    		
    		new AlertDialog.Builder(this)
			.setTitle("おつかれさまでした！！")
			.setMessage("終了しますか？")
			.setPositiveButton("終了",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* 押下時の処理 */
	    			System.out.println("問題番号が最後に達し、終了を選択したので章選択画面に戻ります");
	    			//exit!
	    			finish();
				}
			})
			.setNegativeButton("取り消し", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					/* 押下時の処理 */
					System.out.println("取り消し処理を押下しました！");
					//do nothing!
				}
			})
			.show();
    	}
    }

    
}
