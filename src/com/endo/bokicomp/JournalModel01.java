package com.endo.bokicomp;

import java.io.Serializable;

public class JournalModel01 implements Serializable{
	/**
	 * 仕訳表の正しい貸方(credit)と借方(debit)の配置
	 */
	private String[] creditName;//貸方項目名称
	private String[] debitName;//借方項目名称
	private long[] creditAmount;//貸方項目残高
	private long[] debitAmount;//借方項目残高
	
	public JournalModel01(){
		//デフォルトの配列長は４とする(最大４)
		int itemLength = 4;
		this.creditName = new String[itemLength];
		this.debitName = new String[itemLength];
		this.creditAmount = new long[itemLength];
		this.debitAmount = new long[itemLength];
	}
	
	public void setCreditName(String[] creditName){
		this.creditName = creditName;
	}
	public void setCreditName(int no, String creditName){
		System.out.println("setCreditName");
		this.creditName[no] = creditName;
	}
	
	public String[] getCreditName(){
		return this.creditName;
	}
	public String getCreditName(int no){
		return this.creditName[no];
	}
	
	public void setDebitName(String[] debitName){
		this.debitName = debitName;
	}
	public void setDebitName(int no, String debitName){
		this.debitName[no] = debitName;
	}
	public String[] getDebitName(){
		return this.debitName;
	}
	public String getDebitName(int no){
		return this.debitName[no];
	}
	
	public void setCreditAmount(long[] creditAmount){
		this.creditAmount = creditAmount;
	}
	public void setCreditAmount(int no, long creditAmount){
		this.creditAmount[no] = creditAmount;
	}
	public long[] getCreditAmount(){
		return this.creditAmount;
	}
	public long getCreditAmount(int no){
		return this.creditAmount[no];
	}
	
	public void setDebitAmount(long[] debitAmount){
		this.debitAmount = debitAmount;
	}
	public void setDebitAmount(int no, long debitAmount){
		this.debitAmount[no] = debitAmount;
	}
	public long[] getdebitAmount(){
		return this.debitAmount;
	}
	public long getDebitAmount(int no){
		return this.debitAmount[no];
	}

}
