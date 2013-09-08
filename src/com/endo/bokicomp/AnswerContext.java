package com.endo.bokicomp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.text.DecimalFormat;

import android.content.Context;
/**
 * 全問終了後に正誤答の履歴を格納
 * ->全問終了後だからrDataに正誤履歴が格納されているため、これをXMLに保存する
 * 格納するデータ：章番号、問題番号、正解数、誤答数
 * @author oqo52025257
 *
 */
public class AnswerContext {
	/**
	 * XMLファイルを用いてデータを保存、取得する
	 */
	private SharedPreferences pref;
	private ResultData rData;
	private String aaa;
	private String sectNoXXX;
	private String quesNoXXX;
	
	
	public AnswerContext(Context context){
		this.pref = PreferenceManager.getDefaultSharedPreferences(context);
		this.rData = rData;
	}
	public void inputXML(ResultData rData, int sectNo){
		DecimalFormat df = new DecimalFormat("000");
		sectNoXXX = df.format(sectNo);
		
		//SharedPreferencesに章番号、問題番号の正解数、誤答数、を格納
		SharedPreferences.Editor editor = pref.edit();
		
		//元々格納されていた正解数
		long originRightAnswerNum = 0;
		//新しく格納される正解数
		long rightAnswerNum = 0;
		
		//元々格納されていた誤答数
		long originWrongAnswerNum = 0;
		//新しく格納される誤答数
		long wrongAnswerNum = 0;
		
		//今終了した全問題について、正しいものと誤ったものに分けて、XMLに回数を追加
		for(int qNo = 0; qNo < (rData.getJudgeArray()).length;qNo++){
			quesNoXXX = df.format(qNo);
			
			//System.out.print("answerContext : " + qNo);
			if(rData.getJudgeArray(qNo)){
				//System.out.println(" => " + true);
				//System.out.println(pref.getString("sect" + sData.getSectNo(),""));
				originRightAnswerNum = Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
				rightAnswerNum = originRightAnswerNum + 1;
				editor.putString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", rightAnswerNum + "");
				
				System.out.println("xml;" + "sect" + sectNoXXX + "ques" + quesNoXXX + "right" + " = " + 
							 pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
			}else{
				System.out.println(" => " + false);
				//誤答数を格納
				originWrongAnswerNum = Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "wrong", 0 + ""));
				wrongAnswerNum = originWrongAnswerNum + 1;
				editor.putString("sect" + sectNoXXX + "ques" + quesNoXXX + "wrong", wrongAnswerNum + "");
			}
		}
		editor.commit();
		
		//test
		System.out.println("xml;" + "sect" + sectNoXXX + "ques" + quesNoXXX + "right" + " = " + 
					 pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
	}
	
	
	public String getString(){
		return this.aaa + this.rData.getSumRightAnswer();
	}
	
	public long outputXML(int sectNo, int quesNo, boolean RightWrong){
		DecimalFormat df = new DecimalFormat("000");
		String sectNoXXX = df.format(sectNo);
		String quesNoXXX = df.format(quesNo); 
		if(RightWrong){//正解数を求める場合
			return Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
		}else{//誤答数を求める場合
			return Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "wrong", 0 + ""));
		}
	}
	
}
