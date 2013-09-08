package com.endo.bokicomp;

import java.io.Serializable;




public class StoreData implements Serializable{
	/**
	 * 現状はResourcesを格納してgetResources()で吐き出すだけの機能
	 * 将来的には、このクラス内で入力されたResourcesインスタンスを加工して
	 * 章毎の問題に分解してStoryModel「X」クラスで問題毎に取り出す仕組みにする
	 */
	private int sectNo;
	private String category;
	private String[] question;
	private JournalModel01 journalModel01[];
	private String[][] selection;
	private String[] explain;
	//private Resources res;
	//private BufferedReader br;
	protected StoreData(){
		this.sectNo = 0;
		this.category = null;
		this.question = null;
		this.selection = null;
		this.journalModel01 = null;
		this.explain = null;
	}
	//問題番号
	public int getSectNo(){
		return this.sectNo;
	}
	public void setSectNo(int val){
		this.sectNo = val;
	}
	//問題カテゴリ
	public String getCategory(){
		return this.category;
	}
	public void setCategory(String cate){
		this.category = cate;
	}
	//問題文
	public String[] getQuestion(){
		return this.question;
	}
	public String getQuestion(int row){
		return this.question[row];
	}
	public void setQuestion(String[] ques){
		this.question = ques;
	}
	//選択肢
	public void setSelection(String[][] selection){
		this.selection = selection;
	}
	public String[][] getSelection(){
		return this.selection;
	}
	public String getSelection(int row, int col){
		return this.selection[row][col];
	}
	//row番目の質問の選択肢の個数
	public int getSelectionCnt(int row){
		return this.selection[row].length;
	}
	public int getQuestionNo(){
		System.out.println("getQuestion no!");
		return this.question.length;
	}
	
	
	public void setJournalModel01(JournalModel01[] journalModel01){
		this.journalModel01 = journalModel01;
	}
	
	public JournalModel01 getJournalModel01(int no){
		return this.journalModel01[no];
	}
	public String getCreditName(int qNo, int itemNo){
		return this.journalModel01[qNo].getCreditName(itemNo);
	}
	public String getDebitName(int qNo, int itemNo){
		return this.journalModel01[qNo].getDebitName(itemNo);
	}
	public Long getCreditAmount(int qNo, int itemNo){
		return this.journalModel01[qNo].getDebitAmount(itemNo);
	}
	public Long getDebitAmount(int qNo, int itemNo){
		return this.journalModel01[qNo].getDebitAmount(itemNo);
	}
	
	public void setExplain(String[] arExplain){
		this.explain = arExplain;
	}
	public void setExplain(int qNo, String inExplain){
		this.explain[qNo] = inExplain;
	}
	public String getExplain(int qNo){
		return this.explain[qNo];
	}
	public String[] getExplain(){
		return this.explain;
	}
}
