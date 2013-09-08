package com.endo.bokicomp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;



public class SelectStory extends Activity implements OnClickListener{
	private StoreData sData;
	private Resources res01;
	private BufferedReader br;
	private StringTokenizer st;
	private String sectNo[];
	private String qNo[];
	private String category;
	private String question[];
	private JournalModel01[] journalModel01;//正解仕訳表オブジェクト->answer
	private String strCSV[][];//csvファイルの内容を全部二次元の文字列行列に変換
	private String selection[][];
	private String explain[];
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("SELECT_STORY!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_story);
		
		int readingRowCount = 50;
		sectNo = new String[readingRowCount];
		qNo = new String[readingRowCount];
		category = new String();
		question = new String[qNo.length];
		journalModel01 = new JournalModel01[qNo.length];
		selection = new String[qNo.length][12];
		explain = new String[qNo.length];
		
		res01 = this.getResources();
		System.out.println("selectStory内格納完了");
		
	}

	public void dispAlert(){
		//まだ開発中なので、ダイアログを表示して終了する
		new AlertDialog.Builder(this)
			.setMessage("申し訳ございません。無料版ではご使用になれません。")
			.setCancelable(true)
			.setPositiveButton("閉じる", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							return;
						}
			})
		.show();
	}
	public void onClick(View v){
		/*
		 * 注意：この画面を全部ImageButtonのみにする必要
		 * 
		 * 【理由】viewを一度ButtonにキャストしてしまうとImageButtonに直せないので
		 * 　　　　下のImageButtonの判定に使えない！！
		 * 
		 */
		
		System.out.println("original view = " + v.toString());
		
		/*
		View viewImageButton = v;
		View viewButton = v;
		//viewがButtonオブジェクトである場合、以下の格納をする際にエラーが発生
		viewImageButton = (ImageButton)viewImageButton;
		viewButton = (Button)viewButton;
		System.out.println("１original view = " + v.toString());
		System.out.println("１image button = " + viewImageButton.toString());
		System.out.println("１button = " + viewButton.toString());
	 	*/
		
		
		//以下判定(try-errorで捉えても良い）
		//参考：substring(start, end)は文字列を０からカウントして[start]番目から[end-1]番目までを抽出
		System.out.println(((v.toString())).substring(0,21));
		if(((Button)v).getId() == R.id.s_s_bt_return){
			System.out.println("pressed finish button!");
			finish();
			return;
		}else if (((Button)v).getId()==R.id.s_st_bt_Q1){
			System.out.println("pressed story0");
			InputStream is = res01.openRawResource(R.raw.sect001);
			if (setQSA2(is)){//ファイルの読み込みに成功したらStoreDataオブジェクトのフィールドにcsv情報が格納される。
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, StoryModel1.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sDataはsetQSA内で格納されている
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//ダイアログで閉じる
			}
			return;
			
		}else if(((Button)v).getId()==R.id.s_st_bt_Q2){
			System.out.println("pressed story1");
			InputStream is = res01.openRawResource(R.raw.sect002);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}else if(((Button)v).getId()==R.id.s_st_bt_Q3){
			System.out.println("pressed story2");
			InputStream is = res01.openRawResource(R.raw.sect003);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}else if(((Button)v).getId()==R.id.s_st_bt_Q4){
			System.out.println("pressed story3");
			InputStream is = res01.openRawResource(R.raw.sect004);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}else if(((Button)v).getId()==R.id.s_st_bt_Q5){
			System.out.println("pressed story4");
			InputStream is = res01.openRawResource(R.raw.sect005);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}else if(((Button)v).getId()==R.id.s_st_bt_Q6){
			System.out.println("pressed story5");
			InputStream is = res01.openRawResource(R.raw.sect006);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}else if(((Button)v).getId()==R.id.s_st_bt_Q7){
			System.out.println("pressed story6");
			InputStream is = res01.openRawResource(R.raw.sect007);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}else if(((Button)v).getId()==R.id.s_st_bt_Q8){
			System.out.println("pressed story7");
			InputStream is = res01.openRawResource(R.raw.sect008);
			if(setQSA2(is)){
				System.out.println("reading csv file succeed!!");
				Intent intent = new Intent(SelectStory.this, StoryModel1.class);
				intent.putExtra("StoreData", sData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}
		
		/*else if(((Button)v).getId()==R.id.s_s_bt_return){
		
			System.out.println("pressed return -> finish!");
			finish();
		}*/
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }    
    
	public boolean setQSA2(InputStream is){
		/**
		 * 以下の三項目をStoreData(グローバル)インスタンスに格納
		 * Question
		 * Selection
		 * Answer
		 */
		//sDataに渡すための質問配列
		String[] outputQuestion = null;
		//InputStream is = res01.openRawResource(R.raw.sect001);
		br = new BufferedReader(new InputStreamReader(is));
		
		//strCSV配列(二次元)に格納
		strCSV = new String[50][21];//40x20次元マトリクス
		
		try{
			for(int row = 0;row<strCSV.length;row++){
				//一行読み込んでトークンに分解
				StringTokenizer st = new StringTokenizer(br.readLine(), ",");
				for(int col =0;col < strCSV[row].length;col++){
					//strCsvの各要素に格納
					strCSV[row][col] = st.nextToken();
					System.out.println("strCSV[" + row + "][" + col + "] = " + strCSV[row][col]);
					if(!st.hasMoreTokens()){
						break;
					}
				}
				if(strCSV[row][0].equals("[EOF]")){
					break;
				}
			}
			System.out.println("all CSV-word is completed reading!!");
			/*
			for(int row = 0;row < strCSV.length;row ++){
				for(int col = 0; col<strCSV[row].length;col ++){
					System.out.print(strCSV[row][col] + ",");
				}
				System.out.println("\n");
			}
			*/
			
		}catch(Exception e){
			System.out.println("CSV読み込み時にエラー発生！！");
			System.out.println("エラーは" + e);
		}
		
		int rowCSV = 1;//読込用カウンター(0行目はラベル)
		int noArray = 0;//格納用カウンター
		category = strCSV[rowCSV][2];//カテゴリー
		
		do{//カウンターrowCSVでループ
			System.out.println("rowCSV =  " + rowCSV);
			if(strCSV[rowCSV][1] == null){
				break;
			}else{
				//デバッグの必要
				System.out.println("strCSV = " + strCSV[rowCSV][1]);
			}
			//現在の行の問題文がハイフンであるならば次の行を読み込む
			if((!strCSV[rowCSV][3].equals("-"))){
				/*
				 * 章番号の格納
				 */
				sectNo[noArray] = strCSV[rowCSV][0];
				/*
				 *問題番号の格納 
				 */
				qNo[noArray] = strCSV[rowCSV][1];
				//category = strCSV[rowCSV][2];←ループ内で格納する必要がないので外出し済
				/*
				 * 質問文の格納
				 */
				question[noArray] = strCSV[rowCSV][3];
				System.out.println("noArray = " + noArray);
				/*
				 * 正解仕訳表
				 */
				journalModel01[noArray] = new JournalModel01();
				//仕訳表の作成
				//一行目だけ最初に読み取り、次の行の質問文(３列目)を確認してハイフンなら次の行も読み込む
				journalModel01[noArray].setDebitName(0,strCSV[rowCSV][4]);//貸方項目名称
				System.out.println(strCSV[rowCSV][4]);
				journalModel01[noArray].setDebitAmount(0,Long.valueOf(strCSV[rowCSV][5]));//貸方金額
				System.out.println(strCSV[rowCSV][5]);
				journalModel01[noArray].setCreditName(0,strCSV[rowCSV][6]);//借方項目名称
				System.out.println(strCSV[rowCSV][6]);
				journalModel01[noArray].setCreditAmount(0,Long.valueOf(strCSV[rowCSV][7]));//借方金額
				System.out.println(strCSV[rowCSV][7]);
				
				
				//この下に次の行の質問文がハイフン(もしくはqNoの右一文字目が0でない←falseで代用＠未完成)なら,
				//次の行をsetXXXXName,Amountする。
				
				for(int i = 1;i+rowCSV<strCSV.length;i++){//rowCSV以下の行を検索して３列目がハイフンならその行は同じ問題で仕訳表の貸方もしくは借方の項目が複数存在している
					System.out.println("i = " + i);
					System.out.println("next row's question = " + strCSV[rowCSV + i][3]);
					if(!strCSV[rowCSV + i][0].equals("[EOF]")){
						if(strCSV[rowCSV + i][3].equals("-")){
							System.out.println(strCSV[rowCSV + i][4]);
							if(!strCSV[rowCSV + i][4].equals("-")){
								journalModel01[noArray].setDebitName(i,strCSV[rowCSV + i][4]);
							}
							System.out.println(strCSV[rowCSV + i][5]);
							if(!strCSV[rowCSV + i][5].equals("-")){
								journalModel01[noArray].setDebitAmount(i,Long.valueOf(strCSV[rowCSV + i][5]));
							}
							System.out.println(strCSV[rowCSV + i][6]);
							if(!strCSV[rowCSV + i][6].equals("-")){
								journalModel01[noArray].setCreditName(i, strCSV[rowCSV + i][6]);
							}
							System.out.println(strCSV[rowCSV + i][7]);
							if(!strCSV[rowCSV + i][7].equals("-")){
								journalModel01[noArray].setCreditAmount(i, Long.valueOf(strCSV[rowCSV + i][7]));
							}
							System.out.println("next row is being read... ");
						}else{
							//次の行の質問文列がハイフンでなければ終了
							break;
						}
					}else{
						//次の行の０列目がEnd of Fileなら終了
						
						System.out.println("問題文の格納完了");
						
						/*
						 * 重要！！！
						 * 配列の要素を問題の格納数と同じにする
						 * 配列question配列の長さを変える(格納されているquestion要素は0番目からnoArray番目まで)
						 */
						outputQuestion = new String[noArray+1];
						System.arraycopy(question, 0, outputQuestion, 0, outputQuestion.length);
						
						for(int no = 0;no<outputQuestion.length;no++){
							System.out.println(outputQuestion[no]);
						}
						//System.arraycopy(inArray, x1 , outArray, x2,count)->inArrayのx1から、outArray[x2:x2+count-1]をcopyする
						break;
					}
				}//for i
				System.out.println("complete input & initiate JournalModel01");
				/*
				 * 選択肢の作成
				 */
				for(int colCSV = 8;colCSV<20;colCSV++){//csvファイルの８列目から２０列目までが選択肢(strCSV[rowCSV].lengthは２１個存在し、２１番目は解説文)
					System.out.println("colCSV = " + colCSV);
					
					selection[noArray][colCSV-8] = strCSV[rowCSV][colCSV];
					System.out.println("selection = " + selection[noArray][colCSV - 8]);
				}
				
				System.out.println("selection complete initiating!");
				
				
				/*
				 * 解説文の取り込み
				 */
				explain[noArray] = strCSV[rowCSV][20];
				System.out.println("explain complete initiating");
				
				noArray++;
			}else if(strCSV[rowCSV][0].equals("[EOF]")){
				//常に次の行の一列目に[EOF]があるか確認しているため不必要な処理？
				System.out.println("ここに制御フローが流れることはない");
				
				break;
			}
			rowCSV ++;
		}while(true);
		//選択肢selectionは９列目から１４列目(col=8-13)を読み込んで格納
		
		System.out.println("complete initiating Array!!");
		
		sData = new StoreData();
		System.out.println("complete initiating StoreData!!");
		//問題の章番号はSECTを除いた数値部分のみ渡す
		System.out.println(sectNo[0].replaceAll("SECT", ""));
		sData.setSectNo(Integer.valueOf(sectNo[0].replaceAll("SECT","")).intValue());
		System.out.println("complete setSectNo");
		sData.setCategory(category);
		System.out.println("complete setCategory");
		sData.setQuestion(outputQuestion);
		System.out.println("complete setQuestion");
		sData.setJournalModel01(journalModel01);//仕訳表の正解パターンの格納
		System.out.println("complete setJournal");
		sData.setSelection(selection);
		System.out.println("complete setSelection");
		sData.setExplain(explain);
		System.out.println("complete setExplain");
	
		return true;
	}
}
