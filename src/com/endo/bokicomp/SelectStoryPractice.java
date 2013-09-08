package com.endo.bokicomp;



import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;

import android.content.SharedPreferences;
import java.text.DecimalFormat;

public class SelectStoryPractice extends Activity 
	implements OnClickListener
	/**,
				DialogInterface.OnClickListener,
				DialogInterface.OnCancelListener **/
	{

	//解答成績、解答履歴取得用の箱(ダイアログボックス内でしか使用していないが、内部で以下のfinalフィールドを定義できないグローバルにする)
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("SELECT_STORY_OR_PRACTICE!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_story_practice);
		
		this.pref = PreferenceManager.getDefaultSharedPreferences(this);
		this.editor = pref.edit();
		
	}
	public void onClick(View v){
		//ToDo Auto-generated method stub
		//次画面のアクティビティ終了
		if (((Button)v).getId()==R.id.bt_story){
			//未作成
			Intent intent = new Intent(SelectStoryPractice.this, SelectStory.class);
			startActivity(intent);
			
		/*
		}else if(((Button)v).getId()==R.id.bt_practice){
			System.out.println("pressed bt_practice!");

			Intent intent = new Intent(SelectStoryPractice.this, SelectPractice.class);
    		//次画面のアクティビティ起動
    		startActivity(intent);
    		*/
		}else if(((Button)v).getId()==R.id.bt_record){
			//成績選択画面に移行
			Intent intent = new Intent(SelectStoryPractice.this, SelectRecord.class);
			startActivity(intent);
			
		}else if(((Button)v).getId()==R.id.bt_exit){
			System.out.println("pressed bt_exit!");
			finish();
			/**
			AlertDialog.show(SelectStoryPractice.this,
                    "Alert Test", 
                    "Hello, This is Alert Dialog.", 
                    "yes",
					SelectStoryPractice.this,
                    "no",
					SelectStoryPractice.this,
                    Boolean.TRUE,
					SelectStoryPractice.this);
			**/
			/*
			new AlertDialog.Builder(SelectStoryPractice.this)
				.setMessage("Are you sure to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes", 
						new  DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int id){
								SelectStoryPractice.this.finish();
							}
				})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int id){
								dialog.cancel();
					}
				})
			.show();
			*/
		}
	}	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

    
}
