package com.endo.bokicomp;


import java.text.DecimalFormat;
import java.util.StringTokenizer;

import android.content.Context;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class StoryModel1 extends Activity implements OnClickListener{
	/**
	 * 【完了事項】
	 * 問題文や選択肢、仕訳表を表示し、その反応を定義。
	 * 戻るボタンの反応定義
	 * 解説ボタンの反応定義
	 * 次へボタンの反応定義
	 * 
	 * 【未完了事項】
	 * 
	 * １問題番号表示→済
	 * ２判定機構→一行問題(貸方に二つ以上の項目がある場合に判定が出来なかった問題)は解決済
	 * ３解説ボタン押下時の詳細定義→未解決(selectstory.java内で解説文章を読み込む@strCSVの修正→済
	 * ４ボタンをグローバルに定義し、viewUpdateで統一された(分かりやすい)名前の配列に格納。-> 済
	 * 　viewUpdateメソッド内や他メソッド内で配列として使用できるようにする。→機能だけは解決済
	 * ５正解仕訳表の格納が誤っている→解決済
	 * ６ボタンを何度か押すと無反応→【現状確認】一度選択された項目を取り消した後に更に項目を選択しようとすると二度押ししないと格納されない。→【解決策】再現性検討中(or解決済)
	 * ７仕訳表項目ボタンの値を""にする→解決済
	 * ８最終問題後の処理→intentでstartactivityでSelectStory画面に戻らない。。－>済
	 * ９次の問題にリスナーがついていない→解決済
	 * 10戻るボタン押下時に「問題選択」に行くのではなく、前のページに行く→解決済
	 * 11解説文の表示はダイアログではなく、普通のActivityページにする予定(理由：文字数に対してダイアログ画面の大きさが小さい、かつ、改行が読み取れないため。->済
	 * 12(済)sharedPreferencesで成績表を保存(最速時間と正答率ランキング)
	 * 13(未対応)何も入力せずに次の問題に行こうとすると注意ダイアログが表示→逆に使い勝手が悪いので、もしやるのであれば解答されていればアラート実行するようにする。
	 * 14(済)問題の最初に説明文章を表示し、その際にフリックアクションについての操作方法も表示する->画面下部テキスト表示
	 * 15(未対応)課金型プログラムの作成->http://www.atmarkit.co.jp/fsmart/articles/android_billing02/01.html
	 * 16(済)成績結果xmlファイルの保存->12に等しい
	 * 17(済)サムネイルの作成
	 * 18(済)イメージボタン作成
	 * 19(済)不正解になった部分は再度やりなおし【済】->誤答一覧の表示＆正解文章の表示＆誤答やり直し機能追加
	 * 　　　　　->不正解になった回数の格納(xmlファイルとして保存)
	 * 		   ->クラスを用意して誤答回数フィールドを作成
	 * 		　　->不正解ランキングの順番に実行する機能
	 * 20(済)選択肢の文字数が多過ぎて文字サイズを小さくする
	 * 21(未対応)二章の問題で項目だけ入力して解答ボタンを押すと強制終了。->仕訳表の片側がnullの時のanswerJudgeのロジック確認ー＞再現性再度確認
	 * 22(済)再度やり直し時に時間が加算されてしまう。
	 * 23(済)成績削除をメニュー画面に配置し、誤答のみ再度実行ボタンを問題集領事に表示。
	 * ->誤答のみ再度実行は１最終問題で次へボタンを押下されたとき(finalView)、正誤格納配列(既存)を使って誤り番号だけ誤った回数を加算してshared...に格納する→オプションボタンで表示、削除する。
	 * ->通常解答時に「正解」「誤答」フラグ(既存)を表示する(優先順位劣位)
	 * 24(済）問題選択画面でfinishボタンがエラー->ButtonとImageButtonがキャストできない。オブジェクトは代入すると代入元も影響を植える
	 * 25(済)問題文章を改行する
	 * 26(未対応)計測時間が一定程度大きくなったら計測終了(long passTimeの格納許容量次第)
	 * 27(未対応)問題選択画面でオプション設定？？
	 * 28(済)問題文の文字数が多い場合は文字サイズを縮小する
	 * 29(済)sectXXXtimeとsectXを前者に統一
	 * 30(済)displayRecordクラスで誤答率を表示(円グラフ)→表示したが更新されない→【本日対応】
	 * 31(？未対応)SharedPreferencesを使用する前に、必ず何らかの値を格納すること！->nullPointerExceptionとなる
	 * 32(未対応)問題文章、及び解説文章の大文字このカンマ「，」を小文字カンマ「,」に変換
	 * 33(最優先事項！未対応)貸方の数字のみ非表示にして解答すると落ちる。
	 * 34(済)不正解再実行モードで最後まで行っても終了しない
	 * 35(済)ボタンの文字が切れている
	 * 36(済)最終問題でnextボタンを連続して押せない様にする
	 */
	
	
	/*
	 * 時間計測はコンストラクタ呼び出し時と最初からやり直しボタン押下時のみ開始する(startTime初期化)
	 */
	private long startTime;
	private long endTime;
	private long passTime;
	
	//問題終了時に誤答のみ再実行ボタンを押下されたらtrueにする
	private boolean REPEAT_MODE;
	//不正解問題のみtrueを格納する->全問正解になったら全部falseになって問題選択画面に戻る
	private boolean[] RepeatQuesNumberArray;
	
	private SharedPreferences pref;
	
	
	private boolean bt_status[] = new boolean[16];
	private boolean s_bt_status[] = new boolean[16];
	private int qNo;//ゼロから始まる
	private StoreData sData;
	private ResultData rData;
	private String strSelected;
	private int rowNum = 4;
	private Button st1_bCredit_num[];//仕訳表の数値ボタン(貸方)
	private int status_st1_bCredit[];//仕訳表の数値ボタンの数値ステータス(貸方)
	private Button st1_bDebit_num[];//仕訳表の数値ボタン(借方)
	private int status_st1_bDebit[];//仕訳表の数値ボタンの数値ステータス(借方)
	
	private boolean finalUpdateFlag;//finalUpdateメソッドを実行できる状態
	
	private Button st1_bCredit_name[];//仕訳表の名前ボタン(借方)
	private Button st1_bDebit_name[];//仕訳表の名前ボタン(貸方)
	
	private TextView st01_title;//質問文章 
	
	private Context myContext;
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("START STORY MODEL1!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.story_model2);
		//setContentView(R.layout.story_model1);
		
		//このクラスのインスタンスを作成(誤答をXMLファイルに記憶させる際に活用＠finalUpdate)
		myContext = this;
		
		
		//最初の画面描画時に操作方法をダイアログで表示

		new AlertDialog.Builder(this)
			.setTitle("初めに(操作方法)")
			.setMessage("まず最初に問題文を読んでから、適切な仕訳表を作成して下さい。\n" +
					"仕訳表に項目を入力するには、最初に画面中央の６つの選択肢の中から語句を選び、" +
					"続いて仕訳表の適切な位置をタップして下さい。" + 
					"金額はタップもしくは右フリックで増加、左フリックで減少します。")
			.setPositiveButton("閉じる",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* 押下時の処理 */
					//do nothing...
				}
			}).show();
		
		
		
		Intent intent = getIntent();
		//問題データと正解データの受け取り用オブジェクト
		sData = (StoreData)intent.getSerializableExtra("StoreData");
		//System.out.println("the number of question sentence = " + sData.getQuestion().length);
		strSelected = new String("");
		
		//sData内に回答文章が格納されている個数だけ、rDataにも判定結果を格納する配列を用意する
		rData = new ResultData(sData.getQuestionNo());
		

		//仕訳表の数値ボタンの初期化
		st1_bCredit_num = new Button[rowNum];
		st1_bDebit_num = new Button[rowNum];
		System.out.println("complete initating button");
		
		
		//時間計測開始
		startTime = System.currentTimeMillis();
		
		//画面更新
		this.viewUpdate();
		
		//成績表格納用のxmlファイル保存用インスタンスの設定
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		System.out.println("complete executing constructor");
		
		this.RepeatQuesNumberArray = new boolean[sData.getQuestionNo()];
		
		//finalUpdateフラグをtrueにしてメソッドを受け付けられる状態にする
		finalUpdateFlag = true;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick(View v){
    	/**
    	 * 以下の初期化は別メソッドにする(将来的に)
    	 */
    	//選択肢の初期化
    	if(v.getId()==R.id.st1_s_b00 | v.getId()==R.id.st1_s_b01 | 
    	   v.getId()==R.id.st1_s_b10 | v.getId()==R.id.st1_s_b11 | 
    	   v.getId()==R.id.st1_s_b20 | v.getId()==R.id.st1_s_b21){
    		this.findViewById(R.id.st1_s_b00).setBackgroundColor(Color.parseColor("#fff8dc"));
    		this.findViewById(R.id.st1_s_b01).setBackgroundColor(Color.parseColor("#fff8dc"));
    		this.findViewById(R.id.st1_s_b10).setBackgroundColor(Color.parseColor("#fff8dc"));
    		this.findViewById(R.id.st1_s_b11).setBackgroundColor(Color.parseColor("#fff8dc"));
    		this.findViewById(R.id.st1_s_b20).setBackgroundColor(Color.parseColor("#fff8dc"));
    		this.findViewById(R.id.st1_s_b21).setBackgroundColor(Color.parseColor("#fff8dc"));
    	}
    	//仕訳表の初期化←過去のボタン押下時にボタン背景色を黄色にされているため、別のボタン押下時はすべて灰色に戻す。
    	if(
			    v.getId()==R.id.st1_b00 | v.getId()==R.id.st1_b01 | v.getId() == R.id.st1_b02 | v.getId() == R.id.st1_b03
    	   // | v.getId()==R.id.st1_b10 | v.getId()==R.id.st1_b11 | v.getId() == R.id.st1_b12 | v.getId() == R.id.st1_b13
    	      | v.getId()==R.id.st1_b20 | v.getId()==R.id.st1_b21 | v.getId() == R.id.st1_b22 | v.getId() == R.id.st1_b23
    	   // | v.getId()==R.id.st1_b30 | v.getId()==R.id.st1_b31 | v.getId() == R.id.st1_b32 | v.getId() == R.id.st1_b33
    	    ){
    		
    		//何も選ばれていない旨、警告して閉じる
        	if(strSelected.equals("")){
    			new AlertDialog.Builder(this)
    				.setTitle("すみません。")
    				.setMessage("先に選択肢を選んで下さい。\n選択肢を選んでから対応する仕訳表を対応づけると黄色くなります。")
    				.setPositiveButton("終了",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int whichButton) {
    						/* 押下時の処理 */
    		    			System.out.println("選択肢が何も選択されていない状態で仕訳項目が押されました");
    	    			
    					}
    				}).show();
        		
        		//処理終了
        		return;
    		}
    		
    		
    		
    		//項目選択肢(借方)
    		this.findViewById(R.id.st1_b00).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b01).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b02).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b03).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	/*数値選択肢(借方)
	    	this.findViewById(R.id.st1_b10).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b11).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b12).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b13).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	*/
	    	//項目選択肢(貸方)
	    	this.findViewById(R.id.st1_b20).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b21).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b22).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b23).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	/*数値選択肢(貸方）
	    	this.findViewById(R.id.st1_b30).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b31).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b32).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b33).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	*/
	    	
	    	if(strSelected.length()>5){
    			((Button)v).setTextSize(10.0f);
    		}else{
    			((Button)v).setTextSize(15.0f);
    		}
	    	
    	}
		switch(v.getId()){
			//反応
	    	case R.id.st1_b00:
	    		if (! bt_status[0] || ((Button)v).getText().equals("")){
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		//((Button)v).setTextColor(Color.BLACK);
		    		bt_status[0]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			((Button)v).setText("");
	    			bt_status[0]=false;
	    		}
	    		break;
	    	case R.id.st1_b01:
	    		if (! bt_status[1] || ((Button)v).getText().equals("")){
		    		System.out.println("b01 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[1]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			((Button)v).setText("");
	    			bt_status[1]=false;
	    		}
	    		
	    		break;
	    	case R.id.st1_b02:
	    		if (! bt_status[2] || ((Button)v).getText().equals("")){
		    		System.out.println("b02 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[2]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[2]=false;
	    		}
	    		
	    		break;
	    	case R.id.st1_b03:
	    		if (! bt_status[3] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[3]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[3]=false;
	    		}
	    		break;
	    		
	    	case R.id.st1_b10:
	    		if (! bt_status[4] || ((Button)v).getText().equals("")){
		    		System.out.println("b10 pressed");
		    		((Button)v).setText(strSelected);
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[4]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[4]=false;
	    		}
	    		
	    		break;
	    	case R.id.st1_b11:
	    		if (! bt_status[5] || ((Button)v).getText().equals("")){
		    		System.out.println("b11 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[5]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[5]=false;
	    		}
	    		
	    		break;
	    	case R.id.st1_b12:
	    		if (! bt_status[6] || ((Button)v).getText().equals("")){
		    		System.out.println("b12 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[6]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[6]=false;
	    		}
	    		
	    		break;
	    	case R.id.st1_b13:
	    		if (! bt_status[7] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setText(strSelected);
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[7]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[7]=false;
	    		}
	    		
	    		break;
	    		
			case R.id.st1_b20:
	    		if (! bt_status[8] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setText(strSelected);
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[8]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[8]=false;
	    		}
				
				break;
			case R.id.st1_b21:
	    		if (! bt_status[9] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setText(strSelected);
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[9]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[9]=false;
	    		}
				
				break;
			case R.id.st1_b22:
	    		if (! bt_status[10] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[10]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[10]=false;
	    		}
				
				break;
			case R.id.st1_b23:
	    		if (! bt_status[11] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[11]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[11]=false;
	    		}
				break;
				/*
	    	case R.id.st1_b30:
	    		if (! bt_status[12] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[12]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[12]=false;
	    		}
	    		break;
	    	case R.id.st1_b31:
	    		if (! bt_status[13] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[13]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[13]=false;
	    		}
	    		break;
	    	case R.id.st1_b32:
	    		if (! bt_status[14] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[14]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[14]=false;
	    		}
	    		break;
	    	case R.id.st1_b33:
	    		if (! bt_status[15] || ((Button)v).getText().equals("")){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		((Button)v).setText(strSelected);
		    		bt_status[15]=true;
	    		}else{
	    			((Button)v).setText("");
	    			((Button)v).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    			bt_status[15]=false;
	    		}
	    		break;
	    		*/
	    		
	    		
	    		
	    		
	    		/**
	    		 * 選択肢のリアクション定義
	    		 */
	    		
		    	case R.id.st1_s_b00:
		    		if (! s_bt_status[0]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		strSelected = (String)(((Button)v).getText());
			    		s_bt_status[0]=true;
		    		}else{
		    			System.out.println("b00 is free!");
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			strSelected = new String("");
		    			s_bt_status[0]=false;
		    		}
		    		break;
		    	case R.id.st1_s_b01:
		    		if (! s_bt_status[1]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b01 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		strSelected = (String)(((Button)v).getText());
			    		s_bt_status[1]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			strSelected = new String("");
		    			s_bt_status[1]=false;
		    		}
		    		
		    		break;
		    	/*
		    	case R.id.st1_s_b02:
		    		if (! s_bt_status[2]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b02 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[2]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[2]=false;
		    		}
		    		
		    		break;
		    	case R.id.st1_s_b03:
		    		if (! s_bt_status[3]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[3]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[3]=false;
		    		}
		    		break;
		    		**/
		    	
		    		
		    	case R.id.st1_s_b10:
		    		if (! s_bt_status[4]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b10 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		strSelected = (String)(((Button)v).getText());
			    		s_bt_status[4]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			strSelected = new String("");
		    			s_bt_status[4]=false;
		    		}
		    		
		    		break;
		    	case R.id.st1_s_b11:
		    		if (! s_bt_status[5]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b11 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		strSelected = (String)(((Button)v).getText());
			    		s_bt_status[5]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			strSelected = new String("");
		    			s_bt_status[5]=false;
		    		}
		    		
		    		break;
		    		
		    		/**
		    	case R.id.st1_s_b12:
		    		if (! s_bt_status[6]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b12 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[6]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[6]=false;
		    		}
		    		
		    		break;
		    	case R.id.st1_s_b13:
		    		if (! s_bt_status[7]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[7]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[7]=false;
		    		}
		    		
		    		break;
		    		**/
				case R.id.st1_s_b20:
		    		if (! s_bt_status[8]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		strSelected = (String)(((Button)v).getText());
			    		s_bt_status[8]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			strSelected = new String("");
		    			s_bt_status[8]=false;
		    		}
					
					break;
				case R.id.st1_s_b21:
		    		if (! s_bt_status[9]){
		    			this.ArrayAllFalse(s_bt_status);
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		strSelected = (String)(((Button)v).getText());
			    		s_bt_status[9]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			strSelected = new String("");
		    			s_bt_status[9]=false;
		    		}
					
					break;
					/**
				case R.id.st1_s_b22:
		    		if (! s_bt_status[10]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[10]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[10]=false;
		    		}
					
					break;
				case R.id.st1_s_b23:
		    		if (! s_bt_status[11]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[11]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[11]=false;
		    		}
					break;
		    	case R.id.st1_s_b30:
		    		if (! s_bt_status[12]){
			    		System.out.println("b30 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[12]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[12]=false;
		    		}
		    		break;
		    	case R.id.st1_s_b31:
		    		if (! s_bt_status[13]){
			    		System.out.println("b31 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[13]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[13]=false;
		    		}
		    		break;
		    	case R.id.st1_s_b32:
		    		if (! s_bt_status[14]){
			    		System.out.println("b32 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[14]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[14]=false;
		    		}
		    		break;
		    	case R.id.st1_s_b33:
		    		if (! s_bt_status[15]){
			    		System.out.println("b33 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[15]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#fff8dc"));
		    			s_bt_status[15]=false;
		    		}
		    		break;
		    		**/
					
					
					
					////////////////////何かがおかしい(不正解のみサイド実行後もドルボタンで！
		    	case R.id.st1_previous:
		    		if(this.REPEAT_MODE){
	    				//不正解だった問題まで戻る
		    			//最初まで戻っても不正解問題がなければ問題選択画面に戻る
		    			if(qNo == 0){
		    				finish();
		    			}else{
		    				System.out.println("qNo = " + qNo);
		    				do {
		    					qNo--;
		    					System.out.println(qNo);
		    					if(qNo < 0){
		    						System.out.println("finish");
		    						finish();
		    						break;//←これがないとエラー(IllegalStateException発動)になる！！
		    					}
		    					
		    				}while(!this.RepeatQuesNumberArray[qNo]);
		    				
		    				System.out.println("qNo = " + qNo);
		    				if(qNo >= 0){
		    					this.viewUpdate();
		    				}else{
		    					finish();
		    				}
		    				
		    				
		    			}
	    				break;
	    			}else{
			    		if(qNo==0){
		    				//最初の問題で戻るボタンが押されたら問題選択画面に戻る(このActivityを閉じる)
		    				finish();
			    		}else{
			    			qNo --;
			    			this.viewUpdate();
			    			break;
			    		}
					}
		    		break;
		    	case R.id.st1_kaisetu:
		    		System.out.println("pressed kaisetu");
		    		Intent intent = new Intent(this, ExplainView.class);
		    		System.out.println("complete newing intent");
		    		intent.putExtra("explainView",sData.getExplain(qNo));
		    		System.out.println("complete putting extra");
		    		
		    		startActivity(intent);
		    		break;
		    	case R.id.st1_exit:
		    		System.out.println("pressed exit!");
		    		//終了確認のダイアログ表示後、終了

		    		//問題が最後まで達したので成績の結果をダイアログで表示します。
		    		 AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

	    	        // ダイアログの設定
	    	        alertDialog.setTitle("ご確認");			//タイトル
	    	        alertDialog.setMessage("終了ボタンが押されました。\n問題を終了しても宜しいですか？");
	    	        //alertDialog.setIcon(R.drawable.icon);	//アイコン設定

	    	        alertDialog.setPositiveButton("終了", new DialogInterface.OnClickListener() {

	    				public void onClick(DialogInterface dialog, int which) {
	    					// TODO 自動生成されたメソッド・スタブ
	    					System.out.println("終了ボタンが押されたので終了します。");
	    					finish();
	    				}
	    			});
	    	        alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener(){
	    	        	public void onClick(DialogInterface dialog, int which){
	    	        		System.out.println("終了ボタン押下後、キャンセルされました");
	    	        	}
	    	        });
	    	        // ダイアログの作成と表示
	    	        alertDialog.create();
	    	        alertDialog.show();
	    	        break;
		    	    

		    	case R.id.st1_answering:
		    		System.out.println("pressed answering!");
		    		//まず仕訳表に誤り(片方のみ入力されている)がないかチェック
		    		if(!journalCheck()){
		    			//片方だけ入力されているので終了
		    			new AlertDialog.Builder(this)
			    			.setTitle("仕訳表を見直して下さい。")
			    			.setMessage("数値が未入力もしくは項目名が未入力です!")
			    			.setPositiveButton("終了",new DialogInterface.OnClickListener() {
			    				public void onClick(DialogInterface dialog, int whichButton) {
			    					/* 押下時の処理 */
			    	    			System.out.println("システムチェック仕訳表エラーなので何もせずダイアログを表示");
			    				}
			    			})
			    			.show();
		    			return;
		    		}else{//未入力項目がなければ
			    		dialogEither(answerJudge());
			    		break;
		    		}
		    	case R.id.st1_next:
		    		System.out.println("pressed next!");
		    		if(this.REPEAT_MODE){//不正解問題実行モードONならば
						//前回不正解問題までqNo++;を繰り返す
						for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
							System.out.println("check" + qNo);
							if(this.RepeatQuesNumberArray[qNo]){
								System.out.println(qNo + "は不正解問題なので繰り返します。");
								break;//不正解問題ならばfor文を抜ける
							}else{
								//不正解問題ではないので、何もしないで次の問題を探索する
							}
						}//for qNo
						//繰り返し実行問題を探索した結果、問題文が最後まで行ったら終了
						if(!(qNo<sData.getQuestionNo())){
							//finalUpdateメソッドを呼び出して、成績表を書きにいっても良いが、他の問題の判定が出来ていない。
							System.out.println("繰り返しモードで最終問題を終えたのでシステム終了します。");
							finish();
							return;
						}
					}else{
						qNo++;
					}
		    		System.out.println(qNo + "を表示します。");
	    			if(qNo < sData.getQuestionNo()){//問題番号が格納数以下なら
	    				if(!(sData.getQuestion(qNo).equals("-"))){//質問文にハイフンがあることはないが念のため
	    					//通常は以下処理が実行されるだけで、条件分岐はされないはず(ハイフンが入ることは無いため)
	    					System.out.println("call viewUpDate by next button!");
	    					//可能なら何も選択されていない場合はalertDialogを発生させる->仕訳表を作成しても次の問題に行ってから戻ると仕訳表が未入力の状態になってしまう。
			    			this.viewUpdate();
	    				}else{//ハイフンになることはないが、念のためリスクヘッジ
			    			System.out.println("恐らくここは制御フローに流れてこない");
			    			
			    		}
	    			}else{// if(qNo >= sData.getQuestion().length)
	    				/**
	    				 * 問題が最後まで達したので成績の結果をダイアログで表示する
	    				 */
	    				//不正解問題再実行モードではここに制御フローは流れない(上のブロックで定義済)
	    				if(finalUpdateFlag){
	    					finalUpdateFlag = false;//連続してfinalUpdateflagを呼び出せない様にする
	    					this.finalUpdate();
	    				}
	    			}
	    			break;
		}
    }
    /**
     * 引数の全てのboolean配列をfalseにする
     * 用途：一つのボタンが押されたとき、他の全てのボタンを解放(false)にする
     *  ※当該ボタンの値はその制御フローの中でtrueにされる。
     *      
     */
    private void ArrayAllFalse(boolean[] args){
    	for(int i = 0;i < args.length;i++){
    		args[i] = false;
    	}
    }
    
    /*
     * 問題が最後まで表示された後にnextボタンを押下されたときに呼び出される
     */
    
    private void finalUpdate(){
    	//正誤結果を結果履歴ファイル(XML)に出力するためのフィールドを持つインスタンス
    	//->コンストラクタを呼び出すだけで格納される
    	AnswerContext ac = new AnswerContext(myContext);
    	ac.inputXML(rData, sData.getSectNo());
    	
    	/*
    	System.out.println(ac.outputXML(sData.getSectNo(), 0, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 1, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 2, true));
    	*/
    	
    	//ダイアログタイトル
    	String dialogTitle = new String("");
    	//ダイアログテキスト
    	String dialogText = new String("");
    	
    	//時間の計測
    	endTime = System.currentTimeMillis();
		passTime = endTime - startTime;
		
		//「全問正解の場合」もしくは「初回時」のみ、
		//今回かかった時間が前回よりも早ければ出力を書き出す。
		System.out.println("全問完了");
		System.out.println("回答数=" + qNo);
		System.out.println("うち、正答数=" + rData.getSumRightAnswer());
		
		
		
		DecimalFormat df000 = new DecimalFormat("000");
		/*
		 * 全問正解し、かつ、次の【１】または【２】の場合にのみ今回の時間情報を格納する
		 * 【１】SharedPreferences(XMLファイル)にsectXXXタグのデータが格納されていない(初めて第XXX章を解答した)場合
		 * もしくは
		 * 【２】前回解答よりも早く解答した場合
		 */
		int sectNo = sData.getSectNo();
		String sectNoXXX = df000.format(sectNo);
		if(rData.getSumRightAnswer() == sData.getQuestionNo() &&
				((pref.getString("sect" + sectNoXXX, "")).equals("") || 
				passTime < Long.parseLong(pref.getString("sect" + sectNoXXX, "")))){
			
			
			System.out.println("成績を格納");
			SharedPreferences.Editor editor = pref.edit();
			System.out.println("代入前格納値" + pref.getString("sect" + sectNoXXX,""));
			
			
			dialogTitle = "全問正解！\nタイムも更新しました！";
			dialogText = "正解数 : " + rData.getSumRightAnswer() + 
					"\n正答率 : " + 
					(new DecimalFormat("0.0%")).format(
							((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo())) + 
					"\n今回タイムは" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "秒です";
			
			if(pref.getString("sect" + sectNoXXX,"").equals("")){
				
				dialogText += "\n※前回の記録はありません。";
				
			}else{
				dialogText += "\n前回タイムは" +
						(new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "秒でした。";
			}
			
			//成績の格納
			//editor.putString("sect" + (new DecimalFormat("000")).format(sData.getSectNo()) + "time", passTime + "");
			editor.putString("sect" + sectNoXXX ,passTime + "");
			editor.commit();
			System.out.println("成績格納完了->" + pref.getString("sect" + sectNoXXX, ""));

		}else{
			System.out.println("成績を格納しません。その理由は...");
			if(rData.getSumRightAnswer() != sData.getQuestionNo()){
				System.out.println("全問正解でないためです");
				dialogTitle="【残念！】一部解答に誤りがありました＞＜";
				dialogText = "正解数 : " + rData.getSumRightAnswer() + 
						"\n正答率 : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo()) 
						) + 
						"\n今回タイムは" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "秒です";
				
			}else if(passTime >= Long.parseLong(pref.getString("sect" + sectNoXXX,""))){
				System.out.println("前回タイムを上回らなかったためです");
				System.out.println("ちなみに前回タイム" + pref.getString("sect" + sectNoXXX,"") + 
						"今回タイム：" + (new DecimalFormat("0.000")).format(passTime/1000));
				
				dialogTitle="【おしい！】全問正解ですが、タイム更新ならず...";
//						Long.parseLong(pref.getString("sect" + sData.getSectNo(), ""))/1000 + "秒)を上回りませんでした＞＜";
				dialogText = "正解数 : " + rData.getSumRightAnswer() + 
						"\n正答率 : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestion().length) 
						) + 
						"\n今回タイムは" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "秒です" + 
						"\n前回タイムは" + (new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "秒でした。";
			}
			
			System.out.println("経過時間 = " + passTime);
			System.out.println("未格納判定：" + (pref.getString("sect" + sectNoXXX, "")).equals(""));
			System.out.println("最終格納値 = " + pref.getString("sect" + sectNoXXX,""));
			
			
		}
		
		//問題が最後まで達したので成績の結果をダイアログで表示します。
		new AlertDialog.Builder(this)
			.setTitle(dialogTitle)
			.setMessage(dialogText)
			.setPositiveButton("終了",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* 押下時の処理 */
					finalUpdateFlag = true;//次にfinalUpdateflagを呼び出せる状態にする
	    			System.out.println("問題番号が最後に達し、終了を選択したので章選択画面に戻ります");
	    			finish();
				}
			})
			.setNegativeButton("もう一度\n最初から", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					/* 押下時の処理 */
					finalUpdateFlag = true;//次にfinalUpdateflagを呼び出せる状態にする
					System.out.println("問題番号が最後に達し、最初から選択をしました。");
					REPEAT_MODE = false;//不正解問題実行モードOFF
					qNo = 0;
					//時間計測は問題起動時(コンストラクタ起動時)と再実行時(今)！
					startTime = System.currentTimeMillis();
					viewUpdate();
				}
			})
			
			.setNeutralButton("誤答のみ再実行", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					finalUpdateFlag = true;//次にfinalUpdateflagを呼び出せる状態にする
					System.out.println("誤答のみ再度実行");
					//////////////////
					//System.out.println(new AnswerContext(myContext, rData, sData.getSectNo()).getString());
					//不正解問題の格納
					for(int i = 0;i<RepeatQuesNumberArray.length;i++){
						if(rData.getJudgeArray(i)){
							//今回、正解だった場合
							System.out.println("問題" + i + "は正解");
							RepeatQuesNumberArray[i] = false;
						}else{
							//今回、不正解だった場合
							System.out.println("問題" + i + "は不正解なので繰り返し問題に追加します。");
							RepeatQuesNumberArray[i] = true;
						}
					}
					//不正解問題実行モードON
					REPEAT_MODE = true;
					for(qNo = 0;qNo<sData.getQuestionNo();qNo++){
						//最初に間違えた問題でループを抜ける
						if(RepeatQuesNumberArray[qNo]){
							break;
						}
					}
					if(qNo < sData.getQuestionNo()){
						startTime=System.currentTimeMillis();
						viewUpdate();
					}else{
						System.out.println("全問正解なので繰り返し処理は実行せず終了します。");
						finish();
					}
				}
			})
		.show();
		//http://techbooster.jpn.org/andriod/ui/1140/

    }
    
    private void viewUpdate(){
    	System.out.println("start view update!");
    	int questionNo = qNo;
    	
    	strSelected = "";
    	/*
		System.out.println("qNo = " + qNo);
		
		System.out.println("問題文章 ; " + sData.getQuestion(qNo));
		
		System.out.println("sData.getQuestion().length = " + sData.getQuestion().length);
		System.out.println("sData.getQuestion(" + questionNo + ") = " + sData.getQuestion(qNo));
		*/
    	if(questionNo < sData.getQuestion().length &&
    			sData.getQuestion(questionNo) != null){
    		this.setContentView(R.layout.story_model2);
    		//this.setContentView(R.layout.story_model1);
    		//章番号の設定
    		st01_title = (TextView)findViewById(R.id.st01_title);
    		st01_title.setText("【第" + sData.getSectNo() + "章】" + sData.getCategory());
    		
    		//質問文テキストビューの初期化
    		TextView question = (TextView)this.findViewById(R.id.st1_question);
    		//テキストビューに改行されたテキストを設定
    		question.setText("(第" + (qNo + 1) + "問) " + question.getText() + 
    				insertParagraph(sData.getQuestion(questionNo)));
    		/*
    		 * 画面再調整機能(問題文の直下に選択肢を配置)により従来の文字列長によるサイズ調整は不要になった
    		if(sData.getQuestion(questionNo).length() > 100){
    			question.setTextSize(12.0f);
    		}else if(sData.getQuestion(questionNo).length() > 70){
    			question.setTextSize(13.0f);
    		}else{
    			question.setTextSize(15.0f);
    		}
    		*/
    		
    		//選択肢の定義
    		System.out.println("選択肢の個数：" + sData.getSelectionCnt(questionNo));
    		Button button[] = new Button[sData.getSelectionCnt(questionNo)];//ローカルで定義
    		button[0] = (Button)this.findViewById(R.id.st1_s_b00);
    		button[1] = (Button)this.findViewById(R.id.st1_s_b01);
    		button[2] = (Button)this.findViewById(R.id.st1_s_b10);
    		button[3] = (Button)this.findViewById(R.id.st1_s_b11);
    		button[4] = (Button)this.findViewById(R.id.st1_s_b20);
    		button[5] = (Button)this.findViewById(R.id.st1_s_b21);
    		int buttonLimitCnt = 6;//この画面上には選択肢は最大６個まで。(応急処置！！)
    		
    		/*
    		 * <選択肢にsDataに格納された選択肢文字列を代入>
    		 * 【注意：作成中！！】buttonの数は１２個(selection配列の個数←SelectStoryでsDataに対してselectionを格納する時に
    		 * 他のパターンを考慮して最大１２個までの選択肢を格納可能な様に配列を準備しているため)
    		 * 改良可能なら改良すること！！→ただし、改良するなら他のStoryModelXXで選択肢の個数が多くなる可能性も考慮すること)
    		 * 改良候補場所：if(i < sData.getSelectionCnt(questionNo) && i<buttonLimitCnt){
    		 */
    		for(int i = 0;i < button.length;i++){
    			if(i < sData.getSelectionCnt(questionNo) && i<buttonLimitCnt){
    				System.out.println("setting...selection " + i + " = " + sData.getSelection(questionNo, i));
    				System.out.println("size=" + button[i].getTextSize());
    				if((sData.getSelection(questionNo,i)).length()>7){
    					button[i].setTextSize(10.0f);
    					//button[i].setTextSize(size)
    				}else{
    					button[i].setTextSize(13.0f);
    				}
    				
    				button[i].setText(sData.getSelection(questionNo,i));
    			}
    		}
    		
    		

    		//ボタン変数と画面上のXMLボタンの対応付け
    		//画面上の数値ボタンは２列目(st1_b1X)と４列目(st1_b3X)の二列のみ
    		//借方
    		st1_bDebit_num[0] = (Button)findViewById(R.id.st1_b10);//二列目(借方：debit)
    		st1_bDebit_num[1] = (Button)findViewById(R.id.st1_b11);//二列目(借方：debit)
    		st1_bDebit_num[2] = (Button)findViewById(R.id.st1_b12);//二列目(借方：debit)
    		st1_bDebit_num[3] = (Button)findViewById(R.id.st1_b13);//二列目(借方：debit)
    		//貸方
    		st1_bCredit_num[0] = (Button)findViewById(R.id.st1_b30);//四列目(貸方：credit)
    		st1_bCredit_num[1] = (Button)findViewById(R.id.st1_b31);//四列目(貸方：credit)
    		st1_bCredit_num[2] = (Button)findViewById(R.id.st1_b32);//四列目(貸方：credit)
    		st1_bCredit_num[3] = (Button)findViewById(R.id.st1_b33);//四列目(貸方：credit)
    		System.out.println("complete corresponding button to xml");
    		
    		/**
    		 * 貸方ボタンの詳細設定
    		 */
    		//貸方ボタンにフリックリスナーを付与&&格納文字を空白にする
    		for(int i = 0;i<st1_bCredit_num.length;i++){
    			st1_bCredit_num[i].setText("");
    			//st1_bCredit_name[i].setText("");
    			st1_bCredit_num[i].setOnTouchListener(new SampleTouchListener());
    			
    		}
    		//System.out.println("complete setting Listener to credit button");
    		status_st1_bCredit = new int[st1_bCredit_num.length];
    		//フリックした時に変化させる値の初期化(貸方)
    		for(int i = 0;i<status_st1_bCredit.length;i++){
    			status_st1_bCredit[i] = 0;
    		}
    		
    		/**
    		 * 借方ボタンの詳細設定
    		 */
    		//借方ボタンにフリックリスナーを付与&格納文字を空白にする
    		for(int i = 0;i<st1_bDebit_num.length;i++){
    			st1_bDebit_num[i].setText("");
    			st1_bDebit_num[i].setOnTouchListener(new SampleTouchListener());
    		}
    		//System.out.println("complete setting Listener to debit button");
    		status_st1_bDebit = new int[st1_bDebit_num.length];
    		for(int i = 0;i<status_st1_bDebit.length;i++){
    			status_st1_bDebit[i] = 0;
    		}
    		

    		//仕訳表の名前ボタンの初期化
    		st1_bDebit_name = new Button[rowNum];
    		st1_bCredit_name= new Button[rowNum];
    		System.out.println("complete initiating name button");
    		System.out.println(st1_bDebit_name.length);
    		System.out.println(st1_bCredit_name.length);
    		
    		//画面上の名前ボタンは１列目と３列目のみ
    		st1_bDebit_name[0] = (Button)findViewById(R.id.st1_b00);//一列目
    		st1_bDebit_name[1] = (Button)findViewById(R.id.st1_b01);//一列目
    		st1_bDebit_name[2] = (Button)findViewById(R.id.st1_b02);//一列目
    		st1_bDebit_name[3] = (Button)findViewById(R.id.st1_b03);//一列目
    		st1_bCredit_name[0] = (Button)findViewById(R.id.st1_b20);//三列目
    		st1_bCredit_name[1] = (Button)findViewById(R.id.st1_b21);//三列目
    		st1_bCredit_name[2] = (Button)findViewById(R.id.st1_b22);//三列目
    		st1_bCredit_name[3] = (Button)findViewById(R.id.st1_b23);//三列目
    		
    		/**
    		 * 仕訳表の文字列の空白化
    		 */
    		for(int i = 0;i<this.st1_bCredit_num.length;i++){
    			st1_bCredit_num[i].setText("");
    		}
    		for(int i = 0;i<this.st1_bDebit_num.length;i++){
    			st1_bDebit_num[i].setText("");
    		}
    		
    		
    		
    		System.out.println("complete input selection");
    	}else{
    		/**
    		 * 問題番号がsDataの問題配列の長さより大きくなることもないし、
    		 * 問題文章がnull値になることもないので、ここに制御フローが流れることはない。
    		 */
    		System.out.println("例外発生　＠　updateView");
    		//this.setContentView(R.layout.story_model1);
    		//最後の問題を終えた場合、問題終了の旨のダイアログを表示した後、成績表画面(未作成)に移行
    		//Intent intent=new Intent(this, SelectStory.class);
			////intent.putExtra("StoreData", rData);//sDataはsetQSA内で格納されている
			////intent.setAction(Intent.ACTION_VIEW);
			//startActivity(intent);
			
    		finish();
    	}
    	
    }
    
    /*
     * 
	 * 20121218システムチェック追加
	 * 項目名もしくは数値の片方だけ入力されている場合、エラーを表示する。
	 * 
     */
    private boolean journalCheck(){
    	for(int i = 0;i<this.st1_bDebit_name.length;i++){
    		if(
    				((!this.st1_bDebit_name[i].getText().equals(""))&&   this.status_st1_bDebit[i]==0)  ||
    				(  this.st1_bDebit_name[i].getText().equals("") &&(!(this.status_st1_bDebit[i]==0)))
    		){
    			System.out.print("ボタン名称" + this.st1_bDebit_name[i].getText() + "->");
    			System.out.println("数値" + this.status_st1_bDebit[i]);
    			return false;
    		}
    	}
    	
    	for (int i= 0;i<this.st1_bCredit_name.length;i++){
    		if(
    				((!this.st1_bCredit_name[i].getText().equals(""))&&   this.status_st1_bCredit[i]==0)  ||
    				(  this.st1_bCredit_name[i].getText().equals("") &&(!(this.status_st1_bCredit[i]==0)))
    		){
    			System.out.print("ボタン名称" + this.st1_bCredit_name[i].getText() + "->");
    			System.out.println("数値" + this.status_st1_bCredit[i]);
    			return false;
    		}
    	}
    	return true;
    }
    
    
    private boolean answerJudge(){
    	/**
    	 * 回答ボタンが押された時に呼び出され、
    	 * ダイアログボックスで仕訳表の正解／不正解を判定
    	 * 
    	 * (完成)judgeCredit(Debit)配列要素に対して
    	 * 画面上の各ボタン行の組合せを探索し、
    	 * 項目名と数値の組合せが正しいか判定。
    	 * 具体的には、探索行の項目名をsData内のJournalModel内credit,debitフィールドと対応させ、
    	 * 等しい項目名称であれば、その数値が等しいか判定する。
    	 * 
    	 * judgeCredit(Debit)の初期値はfalseであるため、
    	 * 画面上ボタンに格納されている項目とJournalModel内フィールドに格納されている項目が過不足なく正しいことを判定する
    	 * １案、両方の個数が一致してかつ一方の項目名が他方にあるかどうかで判定する、
    	 * もしくは２案、両方の項目名が互いに存在するか、を判定する。
    	 * →とりあえず、１案採用で作成済
    	 * 
    	 * 
    	 */
    	System.out.println("pressed Judgement!");
    	boolean judgeCredit[] = new boolean[rowNum];
    	boolean judgeDebit[] = new boolean[rowNum];
    	int judgeDebitButtonNum = 0;//画面上仕訳ボタンに格納されている項目数->貸方、借方それぞれに対して併用している
    	int judgeDebitModelNum = 0;//正解仕訳表に格納されている項目数
    	int judgeCreditButtonNum = 0;
    	int judgeCreditModelNum = 0;
    	int sumDebitRightAnswer = 0;//画面上のボタンに借方の正しい項目と数値が格納されている個数
    	int sumCreditRightAnswer = 0;//画面上のボタンに貸方の正しい項目と数値が格納されている個数
    	boolean judgeCreditAll = false;
    	boolean judgeDebitAll = false;
    	if(sData!=null){
    		
    		//借方(debit)
    		
    		judgeDebitModelNum = 0;//初期化されているが、念のため
    		judgeDebitButtonNum = 0;
    		for(int j = 0;j<this.sData.getJournalModel01(qNo).getDebitName().length;j++){
    			if(this.sData.getJournalModel01(qNo).getDebitName(j) != null){
    				if(!( this.sData.getJournalModel01(qNo).getDebitName(j).equals(""))){
    					judgeDebitModelNum ++;
    				}
    			}
    		}
    		for(int i = 0;i<this.st1_bDebit_name.length;i++){
    			if(!this.st1_bDebit_name[i].getText().equals("")){
    				if((this.st1_bDebit_name[i].getText()+"").indexOf("b")<0){
    					//indexOfメソッドはデフォルト文字列「bXX」が検出されなければ-1が出力される
    					judgeDebitButtonNum ++;
    				}
    				System.out.println(this.st1_bDebit_name[i].getText() + ", " + judgeDebitButtonNum);
    			}
    		}
    		System.out.println(judgeDebitModelNum + ", " + judgeDebitButtonNum);
    		System.out.println(sData.getJournalModel01(qNo).getDebitName(0) + ", " + sData.getJournalModel01(qNo).getDebitAmount(0));
    		System.out.println(sData.getJournalModel01(qNo).getDebitName(1) + ", " + sData.getJournalModel01(qNo).getDebitAmount(1));
    		System.out.println(sData.getJournalModel01(qNo).getCreditName(0) + ", " + sData.getJournalModel01(qNo).getCreditAmount(0));
    		System.out.println(sData.getJournalModel01(qNo).getCreditName(1) + ", " + sData.getJournalModel01(qNo).getCreditAmount(1));
    		
    		if(judgeDebitButtonNum == judgeDebitModelNum){
    			System.out.println("ボタンと正解モデルの格納データ数の一致を確認。");
	    		for(int j = 0;j<this.sData.getJournalModel01(qNo).getDebitName().length;j++){
	    			judgeDebit[j] = false;//デフォルトでfalseだが、一応念のため明示。
	    			
					System.out.println("j = " + j);
					if(this.sData.getJournalModel01(qNo).getDebitName(j) != null){
			    		for(int i = 0;i<this.st1_bDebit_name.length;i++){
			    			if(!this.st1_bDebit_name[i].equals("")){
			    				System.out.println("i = " + i);
								System.out.println(st1_bDebit_name[i].getText());
								/*
								 * 探索アイテムst1_Debit_name[i]と等しい名称が正解仕訳表の借方debitにないか確認
								 * かつ、無格納値""でもないか、も判定(無格納で一致しても正解としないように)
								 */
			    				if(
			    					(!this.sData.getJournalModel01(qNo).getDebitName(j).equals("")) &&
			    					(!this.st1_bDebit_name[i].getText().equals("")) &&
			    					(this.sData.getJournalModel01(qNo).getDebitName(j).equals(this.st1_bDebit_name[i].getText()))
			    						){
			    					System.out.println(i + ", " + j + " => string match at " + this.sData.getJournalModel01(qNo).getDebitName(j));
			    					
			    					//そのiとjの組合せの時の格納されている数値が等しいかどうか
			    					if(this.sData.getJournalModel01(qNo).getDebitAmount(j) == 
			    							Long.parseLong((String)(this.st1_bDebit_num[i].getText()))){
			    						System.out.println("-------------------正解！！！------------");
			    						System.out.println(i + ", " + j + " => number match at " + this.sData.getJournalModel01(qNo).getDebitAmount(j));
			    						judgeDebit[j] = true;
			    					}//数値判定
			    				}//項目名判定
							}
						}//for i
					}else{
						//正解仕訳表の探索対象項目がnullの場合は探索せず、judgeDebitの該当要素はfalseを返す
						judgeDebit[j] = false;
					}
	    		}//for j
	    		sumDebitRightAnswer = 0;
	    		//Debit項目内の正解の個数をカウント
	    		for(int i = 0;i<judgeDebit.length;i++){
	    			if(judgeDebit[i]){
	    				System.out.println("借方debitの" + i + "番目は正解");
	    				sumDebitRightAnswer++;
	    			}else{
	    				System.out.println("借方debitの" + i + "番目は不正解");
	    			}
	    		}
	    		//Debitの項目の中で正解の数がjudgeDebitModelNum(ボタンと正解モデルで一致している格納データ数)と等しければ正解とする。
	    		if(sumDebitRightAnswer == judgeDebitModelNum){
	    			System.out.println("借方(Debit)は過不足なく正解！！");
	    			judgeDebitAll = true;
	    		}else{
	    			System.out.println("the number of Debit right answer : " + sumDebitRightAnswer + " of total : " + judgeDebitModelNum);
	    			
	    		}
	    		
    		}//if(judgeDebitButtonNum == judgeDebitModelNum)
    		
    		
    		
    		/**
    		 * 貸方Creditに対しても同じ様に判定する(借方Debitと全く同じ方法)
    		 */
    		
    		judgeCreditModelNum = 0;//初期化されているが、念のため
    		judgeCreditButtonNum = 0;
    		for(int j = 0;j<this.sData.getJournalModel01(qNo).getCreditName().length;j++){
    			if(this.sData.getJournalModel01(qNo).getCreditName(j) != null){
    				if(!( this.sData.getJournalModel01(qNo).getCreditName(j).equals(""))){
    					judgeCreditModelNum ++;
    				}
    			}
    		}
    		for(int i = 0;i<this.st1_bCredit_name.length;i++){
    			if(!this.st1_bCredit_name[i].getText().equals("")){
    				if((this.st1_bCredit_name[i].getText()+"").indexOf("b")<0){
    					//indexOfメソッドはデフォルト文字列「bXX」が検出されなければ-1が出力される
    					judgeCreditButtonNum ++;
    				}
    				System.out.println(this.st1_bCredit_name[i].getText() + ", " + judgeCreditButtonNum);
    			}
    		}
    		System.out.println(judgeCreditModelNum + ", " + judgeCreditButtonNum);
    		
    		
    		if(judgeCreditButtonNum == judgeCreditModelNum){
    			System.out.println("ボタンと正解モデルの格納データ数の一致を確認。");
	    		for(int j = 0;j<this.sData.getJournalModel01(qNo).getCreditName().length;j++){
	    			judgeCredit[j] = false;//デフォルトでfalseだが、一応念のため明示。
	    			
					System.out.println("j = " + j);
					if(this.sData.getJournalModel01(qNo).getCreditName(j) != null){
			    		for(int i = 0;i<this.st1_bCredit_name.length;i++){
			    			if(!this.st1_bCredit_name[i].equals("")){
			    				System.out.println("i = " + i);
								System.out.println(st1_bCredit_name[i].getText());
								/*
								 * 探索アイテムst1_Credit_name[i]と等しい名称が正解仕訳表の貸方Creditにないか確認
								 * かつ、無格納値""でもないか、も判定(無格納で一致しても正解としないように)
								 */
			    				if(
			    					(!this.sData.getJournalModel01(qNo).getCreditName(j).equals("")) &&
			    					(!this.st1_bCredit_name[i].getText().equals("")) &&
			    					(this.sData.getJournalModel01(qNo).getCreditName(j).equals(this.st1_bCredit_name[i].getText()))
			    						){
			    					System.out.println(i + ", " + j + " => string match at " + this.sData.getJournalModel01(qNo).getCreditName(j));
			    					
			    					//そのiとjの組合せの時の格納されている数値が等しいかどうか
			    					if(this.sData.getJournalModel01(qNo).getCreditAmount(j) == 
			    							Long.parseLong((String)(this.st1_bCredit_num[i].getText()))){
			    						System.out.println("-------------------正解！！！------------");
			    						System.out.println(i + ", " + j + " => number match at " + this.sData.getJournalModel01(qNo).getCreditAmount(j));
			    						judgeCredit[j] = true;
			    					}//数値判定
			    				}//項目名判定
							}
						}//for i
					}else{
						//正解仕訳表の探索対象項目がnullの場合は探索せず、judgeCreditの該当要素はfalseを返す
						judgeCredit[j] = false;
					}
	    		}//for j
	    		sumCreditRightAnswer = 0;
	    		//Credit項目内の正解の個数をカウント
	    		for(int i = 0;i<judgeCredit.length;i++){
	    			if(judgeCredit[i]){
	    				System.out.println("貸方Creditの" + i + "番目は正解");
	    				sumCreditRightAnswer++;
	    			}else{
	    				System.out.println("貸方Creditの" + i + "番目は不正解");
	    			}
	    		}
	    		//Creditの項目の中で正解の数がjudgeCreditModelNum(ボタンと正解モデルで一致している格納データ数)と等しければ正解とする。
	    		if(sumCreditRightAnswer == judgeCreditModelNum){
	    			System.out.println("貸方(Credit)は過不足なく正解！！");
	    			judgeCreditAll = true;
	    		}else{
	    			System.out.println("the number of Credit right answer : " + sumCreditRightAnswer + " of total : " + judgeCreditModelNum);
	    			
	    		}
	    		
    		}//if(judgeCreditButtonNum == judgeCreditModelNum)
    	}else{
    		System.out.println("sData is null!");
    		//sDataがnullの場合は不一致フラグを返す
			return false;
		}
    	
    	

		System.out.println("正解数-> debit : " + sumDebitRightAnswer + ", credit:" + sumCreditRightAnswer);
		System.out.println("【total judgement】 debit : " + judgeDebitAll + ", credit:" + judgeCreditAll);
		
    	
    	//正解の場合
    	if(judgeDebitAll && judgeCreditAll){
    		System.out.println("貸方と借方両方とも正解");
	    	//全ての判定をクリアして
	    	return true;
    	}else if(judgeDebitAll){//借方(Debit)が正しいので、貸方が間違っているケース
    		System.out.println("Credit is InCorrectAnswer");			
    		return false;
    	}else if(judgeCreditAll){//貸方(Credit)が正しいので、借方が間違っているケース
    		System.out.println("Debit is InCorrectAnswer");
    		return false;
    	}else{
    		//ここに制御が移る事はない(文法上の制約)
    		return false;
    	}
    }
    
    //画面上の解答ボタンが押下されたときにanswerJudgement関数の返り値(正誤判断)を表示する
    private void dialogEither(boolean judgement){
    	
    	String strJudgement = null;
    	System.out.println("called dialog!!");
    	if(judgement){
    		strJudgement = "正解！";
    		rData.setJudgement(qNo, true);
    		System.out.println("正解！：今までの正解数" + rData.getSumRightAnswer());
    	}else{
    		strJudgement = "不正解!";
    		rData.setJudgement(qNo, false);
    		System.out.println("不正解！：今までの正解数" + rData.getSumRightAnswer());
    	}
		new AlertDialog.Builder(StoryModel1.this)
			.setMessage(strJudgement)
			.setCancelable(false)
			.setPositiveButton("次の問題へ", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							//次の問題へ
							if(REPEAT_MODE){//不正解問題実行モードONならば
								//前回不正解問題までqNo++;を繰り返す
								for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
									System.out.println("Repeat = " + RepeatQuesNumberArray[qNo]);
									if(RepeatQuesNumberArray[qNo]){
										break;//不正解問題ならばfor文を抜ける
									}
								}
							}else{
								qNo++;
							}
							if(qNo < sData.getQuestion().length){//問題番号が格納数以下なら
								//質問番号をインプルメント(＋１)した上で画面更新
			    				viewUpdate();
							}else{
								//それ以上問題数がない場合
								finalUpdate();
							}
						}
			})
			.setNegativeButton("解説へ",
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							System.out.println("pressed kaisetu");
				    		Intent intent = new Intent(StoryModel1.this, ExplainView.class);
				    		System.out.println("complete newing intent");
				    		intent.putExtra("explainView",sData.getExplain(qNo));
				    		System.out.println("complete putting extra");
				    		
				    		startActivity(intent);
				}
			}).show();
    }
    /*
    private void dialogCorrect(){
		new AlertDialog.Builder(this)
		.setMessage("正解！！")
		.setCancelable(false)
		.setPositiveButton("解説を表示", 
				new  DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						StoryModel1.this.finish();
					}
		})
		.setNegativeButton("次の問題へ",
				new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						dialog.cancel();
			}
		}).show();
    	
    }
    
    private void dialogWrong(){
		new AlertDialog.Builder(this)
		.setMessage("残念！！")
		.setCancelable(false)
		.setPositiveButton("正解を表示", 
				new  DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						StoryModel1.this.finish();
					}
		})
		.setNegativeButton("解説を表示",
				new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						dialog.cancel();
			}
		}).show();
    	
    }*/
    
    

    /**
     * フリックリスナーを実装したインナークラス
     * @author oqo52025257
     *
     */
    class SampleTouchListener implements OnTouchListener{
    	
    	private int pressedX;
    	private int releasedX;
    	private int SelectedButtonID;
    	private String deb_cre;
    	public boolean onTouch(View v, MotionEvent e){
    		
    		System.out.println("onTouch");
    		if(e.getAction()==MotionEvent.ACTION_DOWN){
    			System.out.println("drive ActionDown");
    			((Button)v).setText("selected!");
    			pressedX = (int)e.getX();//押された位置は使わない
    			
    			//押されたボタンIDの取得
    			for(int i = 0;i<rowNum;i++){
    				if ((Button)v == st1_bDebit_num[i]){
    					SelectedButtonID = i;
    					deb_cre = "debit";
    				}else if((Button)v == st1_bCredit_num[i]){
    					SelectedButtonID = i;
    					deb_cre = "credit";
    				}
    			}
    			
    		}else if(e.getAction() == MotionEvent.ACTION_UP){
    			System.out.println("released!! @ " + e.getX());
    			releasedX = (int)e.getX();
    			//if(releasedX < 0){//左フリックの場合
				if(releasedX < -10.0){//左フリックの場合
    				if(deb_cre.equals("debit")){
    					status_st1_bDebit[SelectedButtonID] -= 100;
	    				if(status_st1_bDebit[SelectedButtonID] < 0){
	    					status_st1_bDebit[SelectedButtonID] = 0;
	    				}
    				}else if(deb_cre.equals("credit")){
    					status_st1_bCredit[SelectedButtonID] -= 100;
	    				if(status_st1_bCredit[SelectedButtonID] < -30){
	    					status_st1_bCredit[SelectedButtonID] = 0;
	    				}
    				}
    			}else if(releasedX > 10){
    				System.out.println(releasedX);
    				if(deb_cre.equals("debit")){
    					status_st1_bDebit[SelectedButtonID] += 100;
    				}else if(deb_cre.equals("credit")){
    					status_st1_bCredit[SelectedButtonID] += 100;
    				}
    			}
    			//bt.setText("pressed from" + pressedX + "to " + releasedX);
    			//System.out.println("bt[" + SelectedButtonID + "] = " + status_st1_b[SelectedButtonID]);
    			if(deb_cre.equals("debit")){
    				if(status_st1_bDebit[SelectedButtonID] != 0){
    					st1_bDebit_num[SelectedButtonID].setText("" + status_st1_bDebit[SelectedButtonID]);
    				}else{
    					st1_bDebit_num[SelectedButtonID].setText("");
    				}
    			}else{
    				if(status_st1_bCredit[SelectedButtonID] != 0){
    					st1_bCredit_num[SelectedButtonID].setText("" + status_st1_bCredit[SelectedButtonID]);
    				}else{
    					st1_bCredit_num[SelectedButtonID].setText("");
    				}
    			}
    		}
    		return true;
    	}
    }
    
    private String insertParagraph(String arg){
    	System.out.println("complete reading givenData");
    	StringTokenizer st = null;
    	String line2[] = null;
        try{
        	st = new StringTokenizer(arg, "#");
	        
	        String line[] = new String[10];//最大10行程度とする
	        //#トークンで分解する
	        int cnt = 0;
	        while(st.hasMoreTokens()){
	        	line[cnt]=st.nextToken();
	        	cnt++;
	        }
	        //分解されたトークンの数だけの要素を持つ文字列配列を作成し、分解されたトークンを格納
	        line2 = new String[cnt];
	        System.arraycopy(line, 0, line2, 0, cnt);
	        
        }catch(Exception e){
        	System.out.println("解説文章表示ページでトークン分解エラーが発生しました。");
        	System.out.println(e);
        }
        
        System.out.println("complete reconstructing givenData");
        //とりあえずは解説文を一括表示
        
        String out = "";
        for(int i = 0;i<line2.length;i++){
        	out += "\n" + line2[i];
        }
    	
    	return out;
    }

}
