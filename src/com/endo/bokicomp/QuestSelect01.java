package com.endo.bokicomp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Activityを継承している理由：
 * androidファイルシステム/res/raw/内部のファイルを
 * 読み込んでいるため。
 * @author oqo52025257
 *
 */
public class QuestSelect01 extends Activity{
	private String[] question;//問題文
	private String[][] selection;//選択肢
	private String[][] answer;//正答

    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("Start1!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("start2");
        System.out.println(setQuestAns());
        //setQuestAns();
    }
	
	//https://ssl.aiosl-tec.co.jp/java-start/chap06.html
	private boolean setQuestAns(){
		BufferedReader br = null;
		StringTokenizer st = null;
		Resources res = this.getResources();
		//ファイル読み込み時に一行ずつ読み込む際の一時入力用配列
		String inputLine[] = new String[50];
		System.out.println("a");
		try{
			InputStream is = res.openRawResource(R.raw.sect001);
			br = new BufferedReader(new InputStreamReader(is));
			
			System.out.println("b");
			
			int i = 0;
			//ファイルの件数分ループして配列に格納します。
			while (br.ready()){
				//一行読み込む
				String line = br.readLine();
				inputLine[i] = line;//一行ずつ格納
				i++;
			}
			System.out.println("c");
			//http://eprostation.jpn.org/java/jacsv.html
			for(int j = 0;j < inputLine.length;j++){
				System.out.println("d" + j);
				
				System.out.println(inputLine[j] + " = ");
				st = new StringTokenizer(inputLine[j], ",");
				while(st.hasMoreTokens());{
					System.out.print(st.nextToken() + "->");
				}
			}

			//一列目はセクション(章番号)
			
			//二列目は問題番号
			
		}catch(FileNotFoundException e){
			System.out.println("ファイルが見つかりません!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("入出力エラー");
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("Other Error Occured!");
			e.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println("入出力エラー");
				e.printStackTrace();
			}
		}
		return false;//正常に取得できればtrueを返す
	}
	
}