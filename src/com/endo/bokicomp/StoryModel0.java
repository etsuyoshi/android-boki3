package com.endo.bokicomp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StoryModel0 extends Activity implements OnClickListener{
	private boolean bt_status[] = new boolean[16];
	private boolean s_bt_status[] = new boolean[16];
	private int qNo;
	private StoreData sData;

	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("START STORY MODEL0!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.story_model0);
		
		Intent intent = getIntent();
		sData = (StoreData)intent.getSerializableExtra("StoreData");
		this.viewUpdate(qNo++);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick(View v){
		switch(v.getId()){
	    	case R.id.st0_b00:
	    		if (! bt_status[0]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[0]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[0]=false;
	    		}
	    		break;
	    	case R.id.st0_b01:
	    		if (! bt_status[1]){
		    		System.out.println("b01 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[1]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[1]=false;
	    		}
	    		
	    		break;
	    	case R.id.st0_b02:
	    		if (! bt_status[2]){
		    		System.out.println("b02 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[2]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[2]=false;
	    		}
	    		
	    		break;
	    	case R.id.st0_b03:
	    		if (! bt_status[3]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[3]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[3]=false;
	    		}
	    		break;
	    	case R.id.st0_b10:
	    		if (! bt_status[4]){
		    		System.out.println("b10 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[4]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[4]=false;
	    		}
	    		
	    		break;
	    	case R.id.st0_b11:
	    		if (! bt_status[5]){
		    		System.out.println("b11 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[5]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[5]=false;
	    		}
	    		
	    		break;
	    	case R.id.st0_b12:
	    		if (! bt_status[6]){
		    		System.out.println("b12 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[6]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[6]=false;
	    		}
	    		
	    		break;
	    	case R.id.st0_b13:
	    		if (! bt_status[7]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[7]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[7]=false;
	    		}
	    		
	    		break;
			case R.id.st0_b20:
	    		if (! bt_status[8]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[8]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[8]=false;
	    		}
				
				break;
			case R.id.st0_b21:
	    		if (! bt_status[9]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[9]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[9]=false;
	    		}
				
				break;
			case R.id.st0_b22:
	    		if (! bt_status[10]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[10]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[10]=false;
	    		}
				
				break;
			case R.id.st0_b23:
	    		if (! bt_status[11]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[11]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[11]=false;
	    		}
				break;
	    	case R.id.st0_b30:
	    		if (! bt_status[12]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[12]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[12]=false;
	    		}
	    		break;
	    	case R.id.st0_b31:
	    		if (! bt_status[13]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[13]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[13]=false;
	    		}
	    		break;
	    	case R.id.st0_b32:
	    		if (! bt_status[14]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[14]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[14]=false;
	    		}
	    		break;
	    	case R.id.st0_b33:
	    		if (! bt_status[15]){
		    		System.out.println("b00 pressed");
		    		((Button)v).setBackgroundColor(Color.YELLOW);
		    		bt_status[15]=true;
	    		}else{
	    			((Button)v).setBackgroundColor(Color.parseColor("#ff8080"));
	    			bt_status[15]=false;
	    		}
	    		break;
	    		
	    		
	    		
	    		
	    		
	    		////////////////////////////
	    		
	    		
	    		
		    	case R.id.st0_s_b00:
		    		if (! s_bt_status[0]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[0]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[0]=false;
		    		}
		    		break;
		    	case R.id.st0_s_b01:
		    		if (! s_bt_status[1]){
			    		System.out.println("b01 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[1]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[1]=false;
		    		}
		    		
		    		break;
		    	case R.id.st0_s_b02:
		    		if (! s_bt_status[2]){
			    		System.out.println("b02 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[2]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[2]=false;
		    		}
		    		
		    		break;
		    	case R.id.st0_s_b03:
		    		if (! s_bt_status[3]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[3]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[3]=false;
		    		}
		    		break;
		    	case R.id.st0_s_b10:
		    		if (! s_bt_status[4]){
			    		System.out.println("b10 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[4]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[4]=false;
		    		}
		    		
		    		break;
		    	case R.id.st0_s_b11:
		    		if (! s_bt_status[5]){
			    		System.out.println("b11 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[5]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[5]=false;
		    		}
		    		
		    		break;
		    	case R.id.st0_s_b12:
		    		if (! s_bt_status[6]){
			    		System.out.println("b12 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[6]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[6]=false;
		    		}
		    		
		    		break;
		    	case R.id.st0_s_b13:
		    		if (! s_bt_status[7]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[7]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[7]=false;
		    		}
		    		
		    		break;
				case R.id.st0_s_b20:
		    		if (! s_bt_status[8]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[8]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[8]=false;
		    		}
					
					break;
				case R.id.st0_s_b21:
		    		if (! s_bt_status[9]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[9]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[9]=false;
		    		}
					
					break;
				case R.id.st0_s_b22:
		    		if (! s_bt_status[10]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[10]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[10]=false;
		    		}
					
					break;
				case R.id.st0_s_b23:
		    		if (! s_bt_status[11]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[11]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[11]=false;
		    		}
					break;
		    	case R.id.st0_s_b30:
		    		if (! s_bt_status[12]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[12]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[12]=false;
		    		}
		    		break;
		    	case R.id.st0_s_b31:
		    		if (! s_bt_status[13]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[13]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[13]=false;
		    		}
		    		break;
		    	case R.id.st0_s_b32:
		    		if (! s_bt_status[14]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[14]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[14]=false;
		    		}
		    		break;
		    	case R.id.st0_s_b33:
		    		if (! s_bt_status[15]){
			    		System.out.println("b00 pressed");
			    		((Button)v).setBackgroundColor(Color.YELLOW);
			    		s_bt_status[15]=true;
		    		}else{
		    			((Button)v).setBackgroundColor(Color.parseColor("#ffffff"));
		    			s_bt_status[15]=false;
		    		}
		    		break;
		    	case R.id.st0_next:
		    		System.out.println("pressed next!");
		    		qNo++;
		    		viewUpdate(qNo);
		    		break;
		    	case R.id.st0_previous:
		    		finish();
		    		break;
		}
    }
    
    private void viewUpdate(int questionNo){
    	//System.out.println("called viewUpdate method!!");
    	if(questionNo < sData.getQuestionNo()){
    		//System.out.println("called viewUpdate method1!!");
    		this.setContentView(R.layout.story_model0);
    		//System.out.println("called viewUpdate method2!!");
    		TextView question = (TextView)this.findViewById(R.id.st0_question);
    		//System.out.println("called viewUpdate method3!!");
    		question.setText(sData.getQuestion(questionNo));
    	}else{
    		
    	}
    }
}