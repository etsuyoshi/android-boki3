package com.endo.bokicomp;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

//import android.content.Context;
import android.graphics.Color;


public class DisplayRecord extends Activity implements OnClickListener{
	private SharedPreferences pref;
	private int sectNo;
	private String sectNoXXX;
	private TextView tv;
	
	private int NUMOFRANKING = 5;//ランキングを表示する要素数
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * 
	 * rankingを作成し、不正解率トップ５をグラフで表示
	 */

	public void onCreate(Bundle savedInstanceState) {
    	System.out.println("display record constructor start!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_record);
        
        System.out.println("complete setcontent");
        
        //Recordクラスから渡された章番号を取得
        Intent intent = getIntent();
        sectNo = (Integer)intent.getSerializableExtra("SectNo");
        
        DecimalFormat df = new DecimalFormat("000");
        //sectNoXXX = new String("");
        sectNoXXX = df.format(sectNo);
        System.out.println("章番号は" + sectNo);
        //渡された章番号をもとにXMLファイル内の該当データをを取得する
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        
        
        this.tv = (TextView)this.findViewById(R.id.disp_title);
        //sect004が格納されていない
        if(this.pref.getString("sect" + sectNoXXX, "").equals("")){
        	System.out.println("まだ全問正解の記録はありません");
        	this.tv.setText("第" + sectNo  + "章の弱点分析\n" +
        			"まだ全問正解の記録はありません。");
        }else{
        	System.out.println("全問正解の記録があります");
        	System.out.println(pref.getString("sect" + sectNoXXX, ""));
	        this.tv.setText("第" + sectNo + "章の弱点ランキング\n(最速タイム:" + 
	        		(new DecimalFormat("0.00").format(Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000)) + "秒)");
        }
        /*
         * 戦略的イメージ
         * 誤答率(不正解数/(正解数＋不正解数))を問題毎にランキングし、
         * 上位５問の問題文章と解答を表示(別画面)
         * a/////////////////////sectXXXtimeを呼び出すとエラー発生
         */
        
        
    	this.onDraw();
        
        
        
    	
	}
	public void onClick(View v){
		if(((Button)v).getId() == R.id.disp_bt_return){
			System.out.println("成績画面を閉じます。");
			finish();
			return;
		}
	}
	private CombValueRank[] getRanking(){
		System.out.println("start getRanking method");
		//XMLファイルに格納されている問題の不正解率ランキングを作成する
		DecimalFormat df000 = new DecimalFormat("000");
		long[] array = new long[30];//不正解となった回数を格納する配列
		int cnt = 0;
		/*
		 * 不正解となった回数をarray配列に格納する
		 * ※格納されている問題数は分からない(R.id.sect001を取得するならコードが長くなる)。
		 */
		for(int i = 0;i<array.length;i++){
			System.out.println(i + "問目");
			//System.out.println(pref.getString("sect004ques000right",""));
			try{
				if(pref.getString(
						"sect" + df000.format(sectNo) + "ques" + df000.format(i) + "wrong", "").equals("")){
					//問題がない、もしくは不正解の回答数が格納されていなければ何もしない
					System.out.println(i + "問目には格納されていません。");
					
					//配列の長さを変更する(未作成)
					
					
				}else{
					
					array[cnt] = Long.parseLong(pref.getString("sect" + this.sectNoXXX + "ques" + df000.format(i) + "wrong", 0 +""));
					
					System.out.println("array = " + array[cnt]);
					System.out.print("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "right = ");
					System.out.println(pref.getString("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "right", ""));
					System.out.print("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "wrong = ");
					System.out.println(pref.getString("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "wrong", ""));
					System.out.println(cnt + ":" + array[cnt]);
					
					cnt++;
				}
			}catch(Exception e){
				System.out.println(e);
				System.out.println(i + "問目はnull");
			}
		}
		
		
		System.out.println("アルゴリズムソート開始");
		/*
		 * 不正解の回数を多い順に並び替え
		 * アルゴリズム：最大値を取得して、その次の最大値を取得して、・・・繰り返す
		 */
		CombValueRank[] cvr = new CombValueRank[this.NUMOFRANKING];
		for(int i = 0;i<this.NUMOFRANKING;i++){
			cvr[i] = new CombValueRank();
		}
		
		for(int i = 0;i<cvr.length;i++){
			cvr[i].setQNo(9999999);
			cvr[i].setNumOfWrong(0);//さすがにここまでの回数試行しないだろう(楽観的値)
		}
		int maxOfNo = 0;//最大値となる配列要素番号
		//long max = 0;//最大値
		long tempMax = 0;//探索時一時的最大値
		for(int j = 0;j<cvr.length;j++){
			System.out.println("j = " + j);
			tempMax = 0;
			//既にnumOfWrongに選択されていない中で最大値tempMaxの要素maxOfNoを探索する
			for(int i = 0;i<array.length;i++){//比較対象i番目要素を取得
				if(array[i] >= tempMax){//array[i]が現段階最大値よりも大きければ
					//過去に格納されてないか確認↓
					int k = 0;//下のループの外でもイテレーションを使うため外だし
					for(k = 0;k < cvr.length;k++){
						System.out.println(i + ", " + cvr[k].getQNo());
						if(i == cvr[k].getQNo())break;//→i+1番目最大値を検索するのではなく、格納されたインデックス(要素番号)で検索する必要
					}
					//最後まで探索しても過去にnumOfWrongに格納されていないとき
					if(k == cvr.length){
						tempMax = array[i];//(この時点での)j+1番目に大きい最大値
						maxOfNo = i;//(この時点での)j+1番目に大きい最大値の要素番号
						cvr[j].setQNo(i);
						cvr[j].setNumOfWrong(array[i]);
						System.out.println(j + "番目要素探索中:" + maxOfNo + "に" + tempMax + "を格納する予定です");
					}
				}//if
			}//for i
			System.out.println("cvrの" + cvr[j].getQNo() + "に" + cvr[j].getNumOfWrong() + "を格納しました");
		}//for j
		
		
		//test
		for(int i = 0;i < array.length;i++){
			System.out.println("array[" + i + "]=" + array[i]);
		}
		
		System.out.println("complete getRanking");
		return cvr;
	}
	private void onDraw(){
		
		/*
		//テキストの表示とタイム、成績の変数格納
        try{
        	if(pref.getString("sect" + sectNoXXX,"")==null){
	        	System.out.println("finish");
	        	finish();
	        }else{
		        //System.out.println("1." + pref.getString("sect" + this.sectNo,"")+"");
		    	//System.out.println("2." + pref.getString("sect" + this.sectNoXXX,"") + "");
		    	
		    	
		    	TextView tv = (TextView)this.findViewById(R.id.disp_record_title);
		    	System.out.println(tv.toString());
		    	tv.setText("第" + sectNo + "問解答状況");
		    	
	        }
        }catch(NullPointerException e){
        	System.out.println("XMLファイルに指定したタグデータが格納されていません。");
        }
		
		*/
		
		
		
		
		
		
		
		
		CombValueRank[] cvr;
		System.out.println("start getRanking");
		cvr = getRanking();
		System.out.println("ranking 格納済");
		
		//不正解が一つもゼロなら描画はせずに、ダイアログのみ表示して抜ける
		boolean drawFlag = false;//許可がおりるまで描画禁止
		for(int i = 0;i<cvr.length;i++){
			System.out.println("判定中" + cvr[i].getNumOfWrong());
			if(cvr[i].getNumOfWrong() != 0){
				drawFlag = true;//描画を許可
				break;
			}
		}
		if(!drawFlag){//描画禁止の指示があるなら
			System.out.println("不正解が一つもありません=>描画禁止");
			
			//ダイアログ表示
			
			new AlertDialog.Builder(this)
				.setMessage("不正解の解答が一問もありません")
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
		}else{
			System.out.println("描画開始");
		}
		
        CategorySeries series = new CategorySeries(null);
        DefaultRenderer renderer = new DefaultRenderer();
        //int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA,
        //                           Color.YELLOW, Color.CYAN, Color.parseColor("#ffffff")};//, Color.RED };
        
        int[] colors = new int[]{ Color.parseColor("#ffff00"),//黄色
        						  Color.parseColor("#00ff7f"),//黄緑or 00ff00, 00fa9a
        						  Color.parseColor("#00ffff"),//空
        						  Color.parseColor("#8a2be2"),//パープル
        						  Color.parseColor("#ff1493")//ピンク
        						};
        /*
         * http://www.colordic.org/
         * Color.parseColor("#0000ff"),//青 or 00bfff
         * 黄色ffff00
         */
        
        System.out.println("start グラフデータ格納");
        for(int i = 0;i < cvr.length; i++){
        	if(cvr[i].getNumOfWrong() == 0){
        		//不正解数がなければそこで終了(大きい順になっているので値がゼロならそこで終了)
        		break;
        	}else{
        		System.out.println("グラフ要素" + cvr[i].getQNo() + "に" + cvr[i].getNumOfWrong() + "を追加");
        		//series.add(i);
        		//項目ラベルは問題番号(不正解数)
        		//問題の順番は最後に解答した問題が優先される。
        		series.add((cvr[i].getQNo() + 1) + "問目" + 
        				"(" + cvr[i].getNumOfWrong() + "回)", 
        				new Long(cvr[i].getNumOfWrong()));
        		//series.add("Cupcake", new Integer(40));
        	}
        }
        System.out.println("end グラフデータ格納");
        
        renderer.setLabelsTextSize(15); //ラベルの文字サイズ
//      renderer.setShowLabels(false); //ラベルを表示するか
        renderer.setLegendTextSize(24); //凡例の文字サイズ
//      renderer.setShowLegend(false);  //凡例を表示するか
        //for (int color : colors) {
        for(int i = 0;i < this.NUMOFRANKING;i++){
        	System.out.println(i + ", " + i % colors.length);
        	int color = colors[i % colors.length];
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            System.out.println(i + ", " + i % colors.length);
        }

        GraphicalView pie_chart = ChartFactory.getPieChartView(this, series, renderer);
        LinearLayout chart_area = (LinearLayout) findViewById(R.id.chart_area);
        chart_area.addView(pie_chart);
        System.out.println("円グラフ表示完了");
	}

}

/**
 * 問題番号と不正解となった回答数を格納するオブジェクト
 * @author oqo52025257
 *
 */
class CombValueRank{
	
	private int combQNo;
	private long combNumOfWrong;
	public CombValueRank(){
		this.combQNo = 0;
		this.combNumOfWrong = 0;
	}
	
	public void setQNo(int value){
		this.combQNo = value;
	}
	public void setNumOfWrong(long value){
		this.combNumOfWrong = value;
	}
	public int getQNo(){
		return combQNo;
	}
	public long getNumOfWrong(){
		return this.combNumOfWrong;
	}
}


/*
String quesNoXXX = new String("");
quesNoXXX = df.format(1);
((TextView)findViewById(R.id.disp_record001)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(2);
((TextView)findViewById(R.id.disp_record002)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(3);
((TextView)findViewById(R.id.disp_record003)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(4);
((TextView)findViewById(R.id.disp_record004)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(5);
((TextView)findViewById(R.id.disp_record005)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(6);
((TextView)findViewById(R.id.disp_record006)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(7);
((TextView)findViewById(R.id.disp_record007)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(8);
((TextView)findViewById(R.id.disp_record008)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(9);
((TextView)findViewById(R.id.disp_record009)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(10);
((TextView)findViewById(R.id.disp_record010)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
*/
