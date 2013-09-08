package com.endo.bokicomp;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectRecord extends Activity implements OnClickListener{
	//全レコード削除用
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private Intent intent;
	public void onCreate(Bundle savedInstanceState){

		System.out.println("SELECT_STORY_OR_PRACTICE!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_record);
		
		this.pref = PreferenceManager.getDefaultSharedPreferences(this);
		this.editor = pref.edit();
		
		
	}
	
	public void onClick(View v){
		//1.成績の削除
		//2.誤答履歴の削除
		/*
		 * 画面を生成して解答表示ボタンと成績削除ボタンを作成
		 */
		
//		if((v.toString()).substring(0,21).equals("android.widget.Button")){
//		}else if((v.toString()).substring(0,26).equals("android.widget.ImageButton")){
		if((v.toString()).substring(0,21).equals("android.widget.Button")){
			if(((Button)v).getId() == R.id.layout_st_bt_return){
				System.out.println("return");
				finish();
			}else if(((Button)v).getId() == R.id.layout_st_remove){
				new AlertDialog.Builder(this)
					.setTitle("【注意！】解答時間及び解答履歴を削除します！")
					.setMessage("「はい」を選択すると、過去の成績及び履歴は初期化されます。よろしいですか？")
					.setPositiveButton("はい(削除)",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							/* 押下時の処理 */
							//do nothing...
							String sectNoXXX = null;
							String quesNoXXX = null;
							DecimalFormat df = new DecimalFormat("000");
							//xmlの項目が存在してもいなくても削除指示だしてもエラーは出ない。
							//editor.clear().commit();//←これでも大丈夫
							editor.clear();//全部クリア
							editor.commit();
							/*
							for(int sectNo = 0;sectNo < 99;sectNo++){
								sectNoXXX = df.format(sectNo);
								//正解数、不正解数の履歴削除
								for(int quesNo = 0;quesNo < 99;quesNo++){
									quesNoXXX = df.format(quesNo);
									editor
										.remove("sect" + sectNoXXX + "ques" + quesNoXXX + "right")
										.commit();
								}
								//解答タイムの削除
								editor.remove("sect" + sectNoXXX + "time").commit();
								//過去の作成した時間記録データの削除
								editor.remove("sect" + sectNo).commit();
							}
							*/
							
						}
					})
					.setNegativeButton("いいえ", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int whichButton){
							System.out.println("キャンセル押下。解答履歴も成績は削除しません。");
						}
					})
				.show();
			}else{
		//}else if((v.toString()).substring(0,26).equals("android.widget.ImageButton")){
				//表示すべき問題の章番号
				int sectNo = 0;
				switch(((Button)v).getId()){
					case R.id.layout_st_bt_Q1:
						sectNo = 1;
						break;
					case R.id.layout_st_bt_Q2:
						sectNo = 2;
						break;
		
					case R.id.layout_st_bt_Q3:
						sectNo = 3;
						break;
					case R.id.layout_st_bt_Q4:
						sectNo = 4;
						break;
		
					case R.id.layout_st_bt_Q5:
						sectNo = 5;
						break;
				}//catch
				
				this.intent = new Intent(SelectRecord.this, DisplayRecord.class);
				intent.putExtra("SectNo", sectNo);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}
	}

}
