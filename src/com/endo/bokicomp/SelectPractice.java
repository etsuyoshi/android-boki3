package com.endo.bokicomp;




import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

public class SelectPractice extends Activity implements OnClickListener{
		
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("SELECT_STORY_OR_PRACTICE!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_practice);
		

		//まだ開発中なので、ダイアログを表示して終了する
		new AlertDialog.Builder(this)
			.setMessage("申し訳ございません。ライト版ではご使用になれません。")
			.setCancelable(true)
			.setPositiveButton("閉じる", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							finish();
							return;
						}
			})
		.show();
		return;
	}
	public void onClick(View v){
		//ToDo Auto-generated method stub
		//次画面のアクティビティ終了
		if (((Button)v).getId()==R.id.s_p_bt_Q1){
			System.out.println("pressed practice1");
			Intent intent=new Intent(SelectPractice.this, PracticeModel0.class);
			startActivity(intent);
			
		}else if(((Button)v).getId()==R.id.s_p_bt_Q2){
			System.out.println("pressed practice2!->finish!");
			//未作成
			finish();
		}else{
			System.out.println("maintainance now ...");
			
			//工事中。。
			new AlertDialog.Builder(SelectPractice.this)
				.setTitle("川崎君、関根君、すみません！")
				.setMessage("maintainance now ....")
				.show();
		}
	}	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }    
    
}
