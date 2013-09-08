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
	 * �y���������z
	 * ��蕶��I�����A�d��\��\�����A���̔������`�B
	 * �߂�{�^���̔�����`
	 * ����{�^���̔�����`
	 * ���փ{�^���̔�����`
	 * 
	 * �y�����������z
	 * 
	 * �P���ԍ��\������
	 * �Q����@�\����s���(�ݕ��ɓ�ȏ�̍��ڂ�����ꍇ�ɔ��肪�o���Ȃ��������)�͉�����
	 * �R����{�^���������̏ڍג�`��������(selectstory.java���ŉ�����͂�ǂݍ���@strCSV�̏C������
	 * �S�{�^�����O���[�o���ɒ�`���AviewUpdate�œ��ꂳ�ꂽ(������₷��)���O�̔z��Ɋi�[�B-> ��
	 * �@viewUpdate���\�b�h���⑼���\�b�h���Ŕz��Ƃ��Ďg�p�ł���悤�ɂ���B���@�\�����͉�����
	 * �T�����d��\�̊i�[������Ă��遨������
	 * �U�{�^�������x�������Ɩ��������y����m�F�z��x�I�����ꂽ���ڂ�����������ɍX�ɍ��ڂ�I�����悤�Ƃ���Ɠ�x�������Ȃ��Ɗi�[����Ȃ��B���y������z�Č���������(or������)
	 * �V�d��\���ڃ{�^���̒l��""�ɂ��遨������
	 * �W�ŏI����̏�����intent��startactivity��SelectStory��ʂɖ߂�Ȃ��B�B�|>��
	 * �X���̖��Ƀ��X�i�[�����Ă��Ȃ���������
	 * 10�߂�{�^���������Ɂu���I���v�ɍs���̂ł͂Ȃ��A�O�̃y�[�W�ɍs����������
	 * 11������̕\���̓_�C�A���O�ł͂Ȃ��A���ʂ�Activity�y�[�W�ɂ���\��(���R�F�������ɑ΂��ă_�C�A���O��ʂ̑傫�����������A���A���s���ǂݎ��Ȃ����߁B->��
	 * 12(��)sharedPreferences�Ő��ѕ\��ۑ�(�ő����ԂƐ����������L���O)
	 * 13(���Ή�)�������͂����Ɏ��̖��ɍs�����Ƃ���ƒ��Ӄ_�C�A���O���\�����t�Ɏg�����肪�����̂ŁA�������̂ł���Ή𓚂���Ă���΃A���[�g���s����悤�ɂ���B
	 * 14(��)���̍ŏ��ɐ������͂�\�����A���̍ۂɃt���b�N�A�N�V�����ɂ��Ă̑�����@���\������->��ʉ����e�L�X�g�\��
	 * 15(���Ή�)�ۋ��^�v���O�����̍쐬->http://www.atmarkit.co.jp/fsmart/articles/android_billing02/01.html
	 * 16(��)���ь���xml�t�@�C���̕ۑ�->12�ɓ�����
	 * 17(��)�T���l�C���̍쐬
	 * 18(��)�C���[�W�{�^���쐬
	 * 19(��)�s�����ɂȂ��������͍ēx���Ȃ����y�ρz->�듚�ꗗ�̕\�������𕶏͂̕\�����듚��蒼���@�\�ǉ�
	 * �@�@�@�@�@->�s�����ɂȂ����񐔂̊i�[(xml�t�@�C���Ƃ��ĕۑ�)
	 * 		   ->�N���X��p�ӂ��Č듚�񐔃t�B�[���h���쐬
	 * 		�@�@->�s���������L���O�̏��ԂɎ��s����@�\
	 * 20(��)�I�����̕����������߂��ĕ����T�C�Y������������
	 * 21(���Ή�)��̖͂��ō��ڂ������͂��ĉ𓚃{�^���������Ƌ����I���B->�d��\�̕Б���null�̎���answerJudge�̃��W�b�N�m�F�[���Č����ēx�m�F
	 * 22(��)�ēx��蒼�����Ɏ��Ԃ����Z����Ă��܂��B
	 * 23(��)���э폜�����j���[��ʂɔz�u���A�듚�̂ݍēx���s�{�^������W�̎��ɕ\���B
	 * ->�듚�̂ݍēx���s�͂P�ŏI���Ŏ��փ{�^�����������ꂽ�Ƃ�(finalView)�A����i�[�z��(����)���g���Č��ԍ�����������񐔂����Z����shared...�Ɋi�[���遨�I�v�V�����{�^���ŕ\���A�폜����B
	 * ->�ʏ�𓚎��Ɂu�����v�u�듚�v�t���O(����)��\������(�D�揇�ʗ��)
	 * 24(�ρj���I����ʂ�finish�{�^�����G���[->Button��ImageButton���L���X�g�ł��Ȃ��B�I�u�W�F�N�g�͑������Ƒ�������e����A����
	 * 25(��)��蕶�͂����s����
	 * 26(���Ή�)�v�����Ԃ������x�傫���Ȃ�����v���I��(long passTime�̊i�[���e�ʎ���)
	 * 27(���Ή�)���I����ʂŃI�v�V�����ݒ�H�H
	 * 28(��)��蕶�̕������������ꍇ�͕����T�C�Y���k������
	 * 29(��)sectXXXtime��sectX��O�҂ɓ���
	 * 30(��)displayRecord�N���X�Ō듚����\��(�~�O���t)���\���������X�V����Ȃ����y�{���Ή��z
	 * 31(�H���Ή�)SharedPreferences���g�p����O�ɁA�K�����炩�̒l���i�[���邱�ƁI->nullPointerException�ƂȂ�
	 * 32(���Ή�)��蕶�́A�y�щ�����͂̑啶�����̃J���}�u�C�v���������J���}�u,�v�ɕϊ�
	 * 33(�ŗD�掖���I���Ή�)�ݕ��̐����̂ݔ�\���ɂ��ĉ𓚂���Ɨ�����B
	 * 34(��)�s�����Ď��s���[�h�ōŌ�܂ōs���Ă��I�����Ȃ�
	 * 35(��)�{�^���̕������؂�Ă���
	 * 36(��)�ŏI����next�{�^����A�����ĉ����Ȃ��l�ɂ���
	 */
	
	
	/*
	 * ���Ԍv���̓R���X�g���N�^�Ăяo�����ƍŏ������蒼���{�^���������̂݊J�n����(startTime������)
	 */
	private long startTime;
	private long endTime;
	private long passTime;
	
	//���I�����Ɍ듚�̂ݍĎ��s�{�^�����������ꂽ��true�ɂ���
	private boolean REPEAT_MODE;
	//�s������̂�true���i�[����->�S�␳���ɂȂ�����S��false�ɂȂ��Ė��I����ʂɖ߂�
	private boolean[] RepeatQuesNumberArray;
	
	private SharedPreferences pref;
	
	
	private boolean bt_status[] = new boolean[16];
	private boolean s_bt_status[] = new boolean[16];
	private int qNo;//�[������n�܂�
	private StoreData sData;
	private ResultData rData;
	private String strSelected;
	private int rowNum = 4;
	private Button st1_bCredit_num[];//�d��\�̐��l�{�^��(�ݕ�)
	private int status_st1_bCredit[];//�d��\�̐��l�{�^���̐��l�X�e�[�^�X(�ݕ�)
	private Button st1_bDebit_num[];//�d��\�̐��l�{�^��(�ؕ�)
	private int status_st1_bDebit[];//�d��\�̐��l�{�^���̐��l�X�e�[�^�X(�ؕ�)
	
	private boolean finalUpdateFlag;//finalUpdate���\�b�h�����s�ł�����
	
	private Button st1_bCredit_name[];//�d��\�̖��O�{�^��(�ؕ�)
	private Button st1_bDebit_name[];//�d��\�̖��O�{�^��(�ݕ�)
	
	private TextView st01_title;//���╶�� 
	
	private Context myContext;
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("START STORY MODEL1!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.story_model2);
		//setContentView(R.layout.story_model1);
		
		//���̃N���X�̃C���X�^���X���쐬(�듚��XML�t�@�C���ɋL��������ۂɊ��p��finalUpdate)
		myContext = this;
		
		
		//�ŏ��̉�ʕ`�掞�ɑ�����@���_�C�A���O�ŕ\��

		new AlertDialog.Builder(this)
			.setTitle("���߂�(������@)")
			.setMessage("�܂��ŏ��ɖ�蕶��ǂ�ł���A�K�؂Ȏd��\���쐬���ĉ������B\n" +
					"�d��\�ɍ��ڂ���͂���ɂ́A�ŏ��ɉ�ʒ����̂U�̑I�����̒��������I�сA" +
					"�����Ďd��\�̓K�؂Ȉʒu���^�b�v���ĉ������B" + 
					"���z�̓^�b�v�������͉E�t���b�N�ő����A���t���b�N�Ō������܂��B")
			.setPositiveButton("����",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* �������̏��� */
					//do nothing...
				}
			}).show();
		
		
		
		Intent intent = getIntent();
		//���f�[�^�Ɛ����f�[�^�̎󂯎��p�I�u�W�F�N�g
		sData = (StoreData)intent.getSerializableExtra("StoreData");
		//System.out.println("the number of question sentence = " + sData.getQuestion().length);
		strSelected = new String("");
		
		//sData���ɉ񓚕��͂��i�[����Ă���������ArData�ɂ����茋�ʂ��i�[����z���p�ӂ���
		rData = new ResultData(sData.getQuestionNo());
		

		//�d��\�̐��l�{�^���̏�����
		st1_bCredit_num = new Button[rowNum];
		st1_bDebit_num = new Button[rowNum];
		System.out.println("complete initating button");
		
		
		//���Ԍv���J�n
		startTime = System.currentTimeMillis();
		
		//��ʍX�V
		this.viewUpdate();
		
		//���ѕ\�i�[�p��xml�t�@�C���ۑ��p�C���X�^���X�̐ݒ�
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		System.out.println("complete executing constructor");
		
		this.RepeatQuesNumberArray = new boolean[sData.getQuestionNo()];
		
		//finalUpdate�t���O��true�ɂ��ă��\�b�h���󂯕t�������Ԃɂ���
		finalUpdateFlag = true;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick(View v){
    	/**
    	 * �ȉ��̏������͕ʃ��\�b�h�ɂ���(�����I��)
    	 */
    	//�I�����̏�����
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
    	//�d��\�̏��������ߋ��̃{�^���������Ƀ{�^���w�i�F�����F�ɂ���Ă��邽�߁A�ʂ̃{�^���������͂��ׂĊD�F�ɖ߂��B
    	if(
			    v.getId()==R.id.st1_b00 | v.getId()==R.id.st1_b01 | v.getId() == R.id.st1_b02 | v.getId() == R.id.st1_b03
    	   // | v.getId()==R.id.st1_b10 | v.getId()==R.id.st1_b11 | v.getId() == R.id.st1_b12 | v.getId() == R.id.st1_b13
    	      | v.getId()==R.id.st1_b20 | v.getId()==R.id.st1_b21 | v.getId() == R.id.st1_b22 | v.getId() == R.id.st1_b23
    	   // | v.getId()==R.id.st1_b30 | v.getId()==R.id.st1_b31 | v.getId() == R.id.st1_b32 | v.getId() == R.id.st1_b33
    	    ){
    		
    		//�����I�΂�Ă��Ȃ��|�A�x�����ĕ���
        	if(strSelected.equals("")){
    			new AlertDialog.Builder(this)
    				.setTitle("���݂܂���B")
    				.setMessage("��ɑI������I��ŉ������B\n�I������I��ł���Ή�����d��\��Ή��Â���Ɖ��F���Ȃ�܂��B")
    				.setPositiveButton("�I��",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int whichButton) {
    						/* �������̏��� */
    		    			System.out.println("�I�����������I������Ă��Ȃ���ԂŎd�󍀖ڂ�������܂���");
    	    			
    					}
    				}).show();
        		
        		//�����I��
        		return;
    		}
    		
    		
    		
    		//���ڑI����(�ؕ�)
    		this.findViewById(R.id.st1_b00).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b01).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b02).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b03).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	/*���l�I����(�ؕ�)
	    	this.findViewById(R.id.st1_b10).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b11).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b12).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b13).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	*/
	    	//���ڑI����(�ݕ�)
	    	this.findViewById(R.id.st1_b20).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b21).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b22).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	this.findViewById(R.id.st1_b23).setBackgroundColor(Color.parseColor("#bfbfbf"));
	    	/*���l�I����(�ݕ��j
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
			//����
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
	    		 * �I�����̃��A�N�V������`
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
					
					
					
					////////////////////��������������(�s�����̂݃T�C�h���s����h���{�^���ŁI
		    	case R.id.st1_previous:
		    		if(this.REPEAT_MODE){
	    				//�s�������������܂Ŗ߂�
		    			//�ŏ��܂Ŗ߂��Ă��s�����肪�Ȃ���Ζ��I����ʂɖ߂�
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
		    						break;//�����ꂪ�Ȃ��ƃG���[(IllegalStateException����)�ɂȂ�I�I
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
		    				//�ŏ��̖��Ŗ߂�{�^���������ꂽ����I����ʂɖ߂�(����Activity�����)
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
		    		//�I���m�F�̃_�C�A���O�\����A�I��

		    		//��肪�Ō�܂ŒB�����̂Ő��т̌��ʂ��_�C�A���O�ŕ\�����܂��B
		    		 AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

	    	        // �_�C�A���O�̐ݒ�
	    	        alertDialog.setTitle("���m�F");			//�^�C�g��
	    	        alertDialog.setMessage("�I���{�^����������܂����B\n�����I�����Ă��X�����ł����H");
	    	        //alertDialog.setIcon(R.drawable.icon);	//�A�C�R���ݒ�

	    	        alertDialog.setPositiveButton("�I��", new DialogInterface.OnClickListener() {

	    				public void onClick(DialogInterface dialog, int which) {
	    					// TODO �����������ꂽ���\�b�h�E�X�^�u
	    					System.out.println("�I���{�^���������ꂽ�̂ŏI�����܂��B");
	    					finish();
	    				}
	    			});
	    	        alertDialog.setNegativeButton("�L�����Z��", new DialogInterface.OnClickListener(){
	    	        	public void onClick(DialogInterface dialog, int which){
	    	        		System.out.println("�I���{�^��������A�L�����Z������܂���");
	    	        	}
	    	        });
	    	        // �_�C�A���O�̍쐬�ƕ\��
	    	        alertDialog.create();
	    	        alertDialog.show();
	    	        break;
		    	    

		    	case R.id.st1_answering:
		    		System.out.println("pressed answering!");
		    		//�܂��d��\�Ɍ��(�Е��̂ݓ��͂���Ă���)���Ȃ����`�F�b�N
		    		if(!journalCheck()){
		    			//�Е��������͂���Ă���̂ŏI��
		    			new AlertDialog.Builder(this)
			    			.setTitle("�d��\���������ĉ������B")
			    			.setMessage("���l�������͂������͍��ږ��������͂ł�!")
			    			.setPositiveButton("�I��",new DialogInterface.OnClickListener() {
			    				public void onClick(DialogInterface dialog, int whichButton) {
			    					/* �������̏��� */
			    	    			System.out.println("�V�X�e���`�F�b�N�d��\�G���[�Ȃ̂ŉ��������_�C�A���O��\��");
			    				}
			    			})
			    			.show();
		    			return;
		    		}else{//�����͍��ڂ��Ȃ����
			    		dialogEither(answerJudge());
			    		break;
		    		}
		    	case R.id.st1_next:
		    		System.out.println("pressed next!");
		    		if(this.REPEAT_MODE){//�s��������s���[�hON�Ȃ��
						//�O��s������܂�qNo++;���J��Ԃ�
						for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
							System.out.println("check" + qNo);
							if(this.RepeatQuesNumberArray[qNo]){
								System.out.println(qNo + "�͕s������Ȃ̂ŌJ��Ԃ��܂��B");
								break;//�s������Ȃ��for���𔲂���
							}else{
								//�s������ł͂Ȃ��̂ŁA�������Ȃ��Ŏ��̖���T������
							}
						}//for qNo
						//�J��Ԃ����s����T���������ʁA��蕶���Ō�܂ōs������I��
						if(!(qNo<sData.getQuestionNo())){
							//finalUpdate���\�b�h���Ăяo���āA���ѕ\�������ɂ����Ă��ǂ����A���̖��̔��肪�o���Ă��Ȃ��B
							System.out.println("�J��Ԃ����[�h�ōŏI�����I�����̂ŃV�X�e���I�����܂��B");
							finish();
							return;
						}
					}else{
						qNo++;
					}
		    		System.out.println(qNo + "��\�����܂��B");
	    			if(qNo < sData.getQuestionNo()){//���ԍ����i�[���ȉ��Ȃ�
	    				if(!(sData.getQuestion(qNo).equals("-"))){//���╶�Ƀn�C�t�������邱�Ƃ͂Ȃ����O�̂���
	    					//�ʏ�͈ȉ����������s����邾���ŁA��������͂���Ȃ��͂�(�n�C�t�������邱�Ƃ͖�������)
	    					System.out.println("call viewUpDate by next button!");
	    					//�\�Ȃ牽���I������Ă��Ȃ��ꍇ��alertDialog�𔭐�������->�d��\���쐬���Ă����̖��ɍs���Ă���߂�Ǝd��\�������͂̏�ԂɂȂ��Ă��܂��B
			    			this.viewUpdate();
	    				}else{//�n�C�t���ɂȂ邱�Ƃ͂Ȃ����A�O�̂��߃��X�N�w�b�W
			    			System.out.println("���炭�����͐���t���[�ɗ���Ă��Ȃ�");
			    			
			    		}
	    			}else{// if(qNo >= sData.getQuestion().length)
	    				/**
	    				 * ��肪�Ō�܂ŒB�����̂Ő��т̌��ʂ��_�C�A���O�ŕ\������
	    				 */
	    				//�s������Ď��s���[�h�ł͂����ɐ���t���[�͗���Ȃ�(��̃u���b�N�Œ�`��)
	    				if(finalUpdateFlag){
	    					finalUpdateFlag = false;//�A������finalUpdateflag���Ăяo���Ȃ��l�ɂ���
	    					this.finalUpdate();
	    				}
	    			}
	    			break;
		}
    }
    /**
     * �����̑S�Ă�boolean�z���false�ɂ���
     * �p�r�F��̃{�^���������ꂽ�Ƃ��A���̑S�Ẵ{�^�������(false)�ɂ���
     *  �����Y�{�^���̒l�͂��̐���t���[�̒���true�ɂ����B
     *      
     */
    private void ArrayAllFalse(boolean[] args){
    	for(int i = 0;i < args.length;i++){
    		args[i] = false;
    	}
    }
    
    /*
     * ��肪�Ō�܂ŕ\�����ꂽ���next�{�^�����������ꂽ�Ƃ��ɌĂяo�����
     */
    
    private void finalUpdate(){
    	//���댋�ʂ����ʗ����t�@�C��(XML)�ɏo�͂��邽�߂̃t�B�[���h�����C���X�^���X
    	//->�R���X�g���N�^���Ăяo�������Ŋi�[�����
    	AnswerContext ac = new AnswerContext(myContext);
    	ac.inputXML(rData, sData.getSectNo());
    	
    	/*
    	System.out.println(ac.outputXML(sData.getSectNo(), 0, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 1, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 2, true));
    	*/
    	
    	//�_�C�A���O�^�C�g��
    	String dialogTitle = new String("");
    	//�_�C�A���O�e�L�X�g
    	String dialogText = new String("");
    	
    	//���Ԃ̌v��
    	endTime = System.currentTimeMillis();
		passTime = endTime - startTime;
		
		//�u�S�␳���̏ꍇ�v�������́u���񎞁v�̂݁A
		//���񂩂��������Ԃ��O�����������Ώo�͂������o���B
		System.out.println("�S�⊮��");
		System.out.println("�񓚐�=" + qNo);
		System.out.println("�����A������=" + rData.getSumRightAnswer());
		
		
		
		DecimalFormat df000 = new DecimalFormat("000");
		/*
		 * �S�␳�����A���A���́y�P�z�܂��́y�Q�z�̏ꍇ�ɂ̂ݍ���̎��ԏ����i�[����
		 * �y�P�zSharedPreferences(XML�t�@�C��)��sectXXX�^�O�̃f�[�^���i�[����Ă��Ȃ�(���߂đ�XXX�͂��𓚂���)�ꍇ
		 * ��������
		 * �y�Q�z�O��𓚂��������𓚂����ꍇ
		 */
		int sectNo = sData.getSectNo();
		String sectNoXXX = df000.format(sectNo);
		if(rData.getSumRightAnswer() == sData.getQuestionNo() &&
				((pref.getString("sect" + sectNoXXX, "")).equals("") || 
				passTime < Long.parseLong(pref.getString("sect" + sectNoXXX, "")))){
			
			
			System.out.println("���т��i�[");
			SharedPreferences.Editor editor = pref.edit();
			System.out.println("����O�i�[�l" + pref.getString("sect" + sectNoXXX,""));
			
			
			dialogTitle = "�S�␳���I\n�^�C�����X�V���܂����I";
			dialogText = "���� : " + rData.getSumRightAnswer() + 
					"\n������ : " + 
					(new DecimalFormat("0.0%")).format(
							((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo())) + 
					"\n����^�C����" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "�b�ł�";
			
			if(pref.getString("sect" + sectNoXXX,"").equals("")){
				
				dialogText += "\n���O��̋L�^�͂���܂���B";
				
			}else{
				dialogText += "\n�O��^�C����" +
						(new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "�b�ł����B";
			}
			
			//���т̊i�[
			//editor.putString("sect" + (new DecimalFormat("000")).format(sData.getSectNo()) + "time", passTime + "");
			editor.putString("sect" + sectNoXXX ,passTime + "");
			editor.commit();
			System.out.println("���ъi�[����->" + pref.getString("sect" + sectNoXXX, ""));

		}else{
			System.out.println("���т��i�[���܂���B���̗��R��...");
			if(rData.getSumRightAnswer() != sData.getQuestionNo()){
				System.out.println("�S�␳���łȂ����߂ł�");
				dialogTitle="�y�c�O�I�z�ꕔ�𓚂Ɍ�肪����܂�������";
				dialogText = "���� : " + rData.getSumRightAnswer() + 
						"\n������ : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo()) 
						) + 
						"\n����^�C����" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "�b�ł�";
				
			}else if(passTime >= Long.parseLong(pref.getString("sect" + sectNoXXX,""))){
				System.out.println("�O��^�C��������Ȃ��������߂ł�");
				System.out.println("���Ȃ݂ɑO��^�C��" + pref.getString("sect" + sectNoXXX,"") + 
						"����^�C���F" + (new DecimalFormat("0.000")).format(passTime/1000));
				
				dialogTitle="�y�������I�z�S�␳���ł����A�^�C���X�V�Ȃ炸...";
//						Long.parseLong(pref.getString("sect" + sData.getSectNo(), ""))/1000 + "�b)������܂���ł�������";
				dialogText = "���� : " + rData.getSumRightAnswer() + 
						"\n������ : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestion().length) 
						) + 
						"\n����^�C����" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "�b�ł�" + 
						"\n�O��^�C����" + (new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "�b�ł����B";
			}
			
			System.out.println("�o�ߎ��� = " + passTime);
			System.out.println("���i�[����F" + (pref.getString("sect" + sectNoXXX, "")).equals(""));
			System.out.println("�ŏI�i�[�l = " + pref.getString("sect" + sectNoXXX,""));
			
			
		}
		
		//��肪�Ō�܂ŒB�����̂Ő��т̌��ʂ��_�C�A���O�ŕ\�����܂��B
		new AlertDialog.Builder(this)
			.setTitle(dialogTitle)
			.setMessage(dialogText)
			.setPositiveButton("�I��",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* �������̏��� */
					finalUpdateFlag = true;//����finalUpdateflag���Ăяo�����Ԃɂ���
	    			System.out.println("���ԍ����Ō�ɒB���A�I����I�������̂ŏ͑I����ʂɖ߂�܂�");
	    			finish();
				}
			})
			.setNegativeButton("������x\n�ŏ�����", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					/* �������̏��� */
					finalUpdateFlag = true;//����finalUpdateflag���Ăяo�����Ԃɂ���
					System.out.println("���ԍ����Ō�ɒB���A�ŏ�����I�������܂����B");
					REPEAT_MODE = false;//�s��������s���[�hOFF
					qNo = 0;
					//���Ԍv���͖��N����(�R���X�g���N�^�N����)�ƍĎ��s��(��)�I
					startTime = System.currentTimeMillis();
					viewUpdate();
				}
			})
			
			.setNeutralButton("�듚�̂ݍĎ��s", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					finalUpdateFlag = true;//����finalUpdateflag���Ăяo�����Ԃɂ���
					System.out.println("�듚�̂ݍēx���s");
					//////////////////
					//System.out.println(new AnswerContext(myContext, rData, sData.getSectNo()).getString());
					//�s������̊i�[
					for(int i = 0;i<RepeatQuesNumberArray.length;i++){
						if(rData.getJudgeArray(i)){
							//����A�����������ꍇ
							System.out.println("���" + i + "�͐���");
							RepeatQuesNumberArray[i] = false;
						}else{
							//����A�s�����������ꍇ
							System.out.println("���" + i + "�͕s�����Ȃ̂ŌJ��Ԃ����ɒǉ����܂��B");
							RepeatQuesNumberArray[i] = true;
						}
					}
					//�s��������s���[�hON
					REPEAT_MODE = true;
					for(qNo = 0;qNo<sData.getQuestionNo();qNo++){
						//�ŏ��ɊԈႦ�����Ń��[�v�𔲂���
						if(RepeatQuesNumberArray[qNo]){
							break;
						}
					}
					if(qNo < sData.getQuestionNo()){
						startTime=System.currentTimeMillis();
						viewUpdate();
					}else{
						System.out.println("�S�␳���Ȃ̂ŌJ��Ԃ������͎��s�����I�����܂��B");
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
		
		System.out.println("��蕶�� ; " + sData.getQuestion(qNo));
		
		System.out.println("sData.getQuestion().length = " + sData.getQuestion().length);
		System.out.println("sData.getQuestion(" + questionNo + ") = " + sData.getQuestion(qNo));
		*/
    	if(questionNo < sData.getQuestion().length &&
    			sData.getQuestion(questionNo) != null){
    		this.setContentView(R.layout.story_model2);
    		//this.setContentView(R.layout.story_model1);
    		//�͔ԍ��̐ݒ�
    		st01_title = (TextView)findViewById(R.id.st01_title);
    		st01_title.setText("�y��" + sData.getSectNo() + "�́z" + sData.getCategory());
    		
    		//���╶�e�L�X�g�r���[�̏�����
    		TextView question = (TextView)this.findViewById(R.id.st1_question);
    		//�e�L�X�g�r���[�ɉ��s���ꂽ�e�L�X�g��ݒ�
    		question.setText("(��" + (qNo + 1) + "��) " + question.getText() + 
    				insertParagraph(sData.getQuestion(questionNo)));
    		/*
    		 * ��ʍĒ����@�\(��蕶�̒����ɑI������z�u)�ɂ��]���̕����񒷂ɂ��T�C�Y�����͕s�v�ɂȂ���
    		if(sData.getQuestion(questionNo).length() > 100){
    			question.setTextSize(12.0f);
    		}else if(sData.getQuestion(questionNo).length() > 70){
    			question.setTextSize(13.0f);
    		}else{
    			question.setTextSize(15.0f);
    		}
    		*/
    		
    		//�I�����̒�`
    		System.out.println("�I�����̌��F" + sData.getSelectionCnt(questionNo));
    		Button button[] = new Button[sData.getSelectionCnt(questionNo)];//���[�J���Œ�`
    		button[0] = (Button)this.findViewById(R.id.st1_s_b00);
    		button[1] = (Button)this.findViewById(R.id.st1_s_b01);
    		button[2] = (Button)this.findViewById(R.id.st1_s_b10);
    		button[3] = (Button)this.findViewById(R.id.st1_s_b11);
    		button[4] = (Button)this.findViewById(R.id.st1_s_b20);
    		button[5] = (Button)this.findViewById(R.id.st1_s_b21);
    		int buttonLimitCnt = 6;//���̉�ʏ�ɂ͑I�����͍ő�U�܂ŁB(���}���u�I�I)
    		
    		/*
    		 * <�I������sData�Ɋi�[���ꂽ�I�������������>
    		 * �y���ӁF�쐬���I�I�zbutton�̐��͂P�Q��(selection�z��̌���SelectStory��sData�ɑ΂���selection���i�[���鎞��
    		 * ���̃p�^�[�����l�����čő�P�Q�܂ł̑I�������i�[�\�ȗl�ɔz����������Ă��邽��)
    		 * ���ǉ\�Ȃ���ǂ��邱�ƁI�I���������A���ǂ���Ȃ瑼��StoryModelXX�őI�����̌��������Ȃ�\�����l�����邱��)
    		 * ���ǌ��ꏊ�Fif(i < sData.getSelectionCnt(questionNo) && i<buttonLimitCnt){
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
    		
    		

    		//�{�^���ϐ��Ɖ�ʏ��XML�{�^���̑Ή��t��
    		//��ʏ�̐��l�{�^���͂Q���(st1_b1X)�ƂS���(st1_b3X)�̓��̂�
    		//�ؕ�
    		st1_bDebit_num[0] = (Button)findViewById(R.id.st1_b10);//����(�ؕ��Fdebit)
    		st1_bDebit_num[1] = (Button)findViewById(R.id.st1_b11);//����(�ؕ��Fdebit)
    		st1_bDebit_num[2] = (Button)findViewById(R.id.st1_b12);//����(�ؕ��Fdebit)
    		st1_bDebit_num[3] = (Button)findViewById(R.id.st1_b13);//����(�ؕ��Fdebit)
    		//�ݕ�
    		st1_bCredit_num[0] = (Button)findViewById(R.id.st1_b30);//�l���(�ݕ��Fcredit)
    		st1_bCredit_num[1] = (Button)findViewById(R.id.st1_b31);//�l���(�ݕ��Fcredit)
    		st1_bCredit_num[2] = (Button)findViewById(R.id.st1_b32);//�l���(�ݕ��Fcredit)
    		st1_bCredit_num[3] = (Button)findViewById(R.id.st1_b33);//�l���(�ݕ��Fcredit)
    		System.out.println("complete corresponding button to xml");
    		
    		/**
    		 * �ݕ��{�^���̏ڍאݒ�
    		 */
    		//�ݕ��{�^���Ƀt���b�N���X�i�[��t�^&&�i�[�������󔒂ɂ���
    		for(int i = 0;i<st1_bCredit_num.length;i++){
    			st1_bCredit_num[i].setText("");
    			//st1_bCredit_name[i].setText("");
    			st1_bCredit_num[i].setOnTouchListener(new SampleTouchListener());
    			
    		}
    		//System.out.println("complete setting Listener to credit button");
    		status_st1_bCredit = new int[st1_bCredit_num.length];
    		//�t���b�N�������ɕω�������l�̏�����(�ݕ�)
    		for(int i = 0;i<status_st1_bCredit.length;i++){
    			status_st1_bCredit[i] = 0;
    		}
    		
    		/**
    		 * �ؕ��{�^���̏ڍאݒ�
    		 */
    		//�ؕ��{�^���Ƀt���b�N���X�i�[��t�^&�i�[�������󔒂ɂ���
    		for(int i = 0;i<st1_bDebit_num.length;i++){
    			st1_bDebit_num[i].setText("");
    			st1_bDebit_num[i].setOnTouchListener(new SampleTouchListener());
    		}
    		//System.out.println("complete setting Listener to debit button");
    		status_st1_bDebit = new int[st1_bDebit_num.length];
    		for(int i = 0;i<status_st1_bDebit.length;i++){
    			status_st1_bDebit[i] = 0;
    		}
    		

    		//�d��\�̖��O�{�^���̏�����
    		st1_bDebit_name = new Button[rowNum];
    		st1_bCredit_name= new Button[rowNum];
    		System.out.println("complete initiating name button");
    		System.out.println(st1_bDebit_name.length);
    		System.out.println(st1_bCredit_name.length);
    		
    		//��ʏ�̖��O�{�^���͂P��ڂƂR��ڂ̂�
    		st1_bDebit_name[0] = (Button)findViewById(R.id.st1_b00);//����
    		st1_bDebit_name[1] = (Button)findViewById(R.id.st1_b01);//����
    		st1_bDebit_name[2] = (Button)findViewById(R.id.st1_b02);//����
    		st1_bDebit_name[3] = (Button)findViewById(R.id.st1_b03);//����
    		st1_bCredit_name[0] = (Button)findViewById(R.id.st1_b20);//�O���
    		st1_bCredit_name[1] = (Button)findViewById(R.id.st1_b21);//�O���
    		st1_bCredit_name[2] = (Button)findViewById(R.id.st1_b22);//�O���
    		st1_bCredit_name[3] = (Button)findViewById(R.id.st1_b23);//�O���
    		
    		/**
    		 * �d��\�̕�����̋󔒉�
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
    		 * ���ԍ���sData�̖��z��̒������傫���Ȃ邱�Ƃ��Ȃ����A
    		 * ��蕶�͂�null�l�ɂȂ邱�Ƃ��Ȃ��̂ŁA�����ɐ���t���[������邱�Ƃ͂Ȃ��B
    		 */
    		System.out.println("��O�����@���@updateView");
    		//this.setContentView(R.layout.story_model1);
    		//�Ō�̖����I�����ꍇ�A���I���̎|�̃_�C�A���O��\��������A���ѕ\���(���쐬)�Ɉڍs
    		//Intent intent=new Intent(this, SelectStory.class);
			////intent.putExtra("StoreData", rData);//sData��setQSA���Ŋi�[����Ă���
			////intent.setAction(Intent.ACTION_VIEW);
			//startActivity(intent);
			
    		finish();
    	}
    	
    }
    
    /*
     * 
	 * 20121218�V�X�e���`�F�b�N�ǉ�
	 * ���ږ��������͐��l�̕Е��������͂���Ă���ꍇ�A�G���[��\������B
	 * 
     */
    private boolean journalCheck(){
    	for(int i = 0;i<this.st1_bDebit_name.length;i++){
    		if(
    				((!this.st1_bDebit_name[i].getText().equals(""))&&   this.status_st1_bDebit[i]==0)  ||
    				(  this.st1_bDebit_name[i].getText().equals("") &&(!(this.status_st1_bDebit[i]==0)))
    		){
    			System.out.print("�{�^������" + this.st1_bDebit_name[i].getText() + "->");
    			System.out.println("���l" + this.status_st1_bDebit[i]);
    			return false;
    		}
    	}
    	
    	for (int i= 0;i<this.st1_bCredit_name.length;i++){
    		if(
    				((!this.st1_bCredit_name[i].getText().equals(""))&&   this.status_st1_bCredit[i]==0)  ||
    				(  this.st1_bCredit_name[i].getText().equals("") &&(!(this.status_st1_bCredit[i]==0)))
    		){
    			System.out.print("�{�^������" + this.st1_bCredit_name[i].getText() + "->");
    			System.out.println("���l" + this.status_st1_bCredit[i]);
    			return false;
    		}
    	}
    	return true;
    }
    
    
    private boolean answerJudge(){
    	/**
    	 * �񓚃{�^���������ꂽ���ɌĂяo����A
    	 * �_�C�A���O�{�b�N�X�Ŏd��\�̐����^�s�����𔻒�
    	 * 
    	 * (����)judgeCredit(Debit)�z��v�f�ɑ΂���
    	 * ��ʏ�̊e�{�^���s�̑g������T�����A
    	 * ���ږ��Ɛ��l�̑g������������������B
    	 * ��̓I�ɂ́A�T���s�̍��ږ���sData����JournalModel��credit,debit�t�B�[���h�ƑΉ������A
    	 * ���������ږ��̂ł���΁A���̐��l�������������肷��B
    	 * 
    	 * judgeCredit(Debit)�̏����l��false�ł��邽�߁A
    	 * ��ʏ�{�^���Ɋi�[����Ă��鍀�ڂ�JournalModel���t�B�[���h�Ɋi�[����Ă��鍀�ڂ��ߕs���Ȃ����������Ƃ𔻒肷��
    	 * �P�āA�����̌�����v���Ă�����̍��ږ��������ɂ��邩�ǂ����Ŕ��肷��A
    	 * �������͂Q�āA�����̍��ږ����݂��ɑ��݂��邩�A�𔻒肷��B
    	 * ���Ƃ肠�����A�P�č̗p�ō쐬��
    	 * 
    	 * 
    	 */
    	System.out.println("pressed Judgement!");
    	boolean judgeCredit[] = new boolean[rowNum];
    	boolean judgeDebit[] = new boolean[rowNum];
    	int judgeDebitButtonNum = 0;//��ʏ�d��{�^���Ɋi�[����Ă��鍀�ڐ�->�ݕ��A�ؕ����ꂼ��ɑ΂��ĕ��p���Ă���
    	int judgeDebitModelNum = 0;//�����d��\�Ɋi�[����Ă��鍀�ڐ�
    	int judgeCreditButtonNum = 0;
    	int judgeCreditModelNum = 0;
    	int sumDebitRightAnswer = 0;//��ʏ�̃{�^���Ɏؕ��̐��������ڂƐ��l���i�[����Ă����
    	int sumCreditRightAnswer = 0;//��ʏ�̃{�^���ɑݕ��̐��������ڂƐ��l���i�[����Ă����
    	boolean judgeCreditAll = false;
    	boolean judgeDebitAll = false;
    	if(sData!=null){
    		
    		//�ؕ�(debit)
    		
    		judgeDebitModelNum = 0;//����������Ă��邪�A�O�̂���
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
    					//indexOf���\�b�h�̓f�t�H���g������ubXX�v�����o����Ȃ����-1���o�͂����
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
    			System.out.println("�{�^���Ɛ������f���̊i�[�f�[�^���̈�v���m�F�B");
	    		for(int j = 0;j<this.sData.getJournalModel01(qNo).getDebitName().length;j++){
	    			judgeDebit[j] = false;//�f�t�H���g��false�����A�ꉞ�O�̂��ߖ����B
	    			
					System.out.println("j = " + j);
					if(this.sData.getJournalModel01(qNo).getDebitName(j) != null){
			    		for(int i = 0;i<this.st1_bDebit_name.length;i++){
			    			if(!this.st1_bDebit_name[i].equals("")){
			    				System.out.println("i = " + i);
								System.out.println(st1_bDebit_name[i].getText());
								/*
								 * �T���A�C�e��st1_Debit_name[i]�Ɠ��������̂������d��\�̎ؕ�debit�ɂȂ����m�F
								 * ���A���i�[�l""�ł��Ȃ����A������(���i�[�ň�v���Ă������Ƃ��Ȃ��悤��)
								 */
			    				if(
			    					(!this.sData.getJournalModel01(qNo).getDebitName(j).equals("")) &&
			    					(!this.st1_bDebit_name[i].getText().equals("")) &&
			    					(this.sData.getJournalModel01(qNo).getDebitName(j).equals(this.st1_bDebit_name[i].getText()))
			    						){
			    					System.out.println(i + ", " + j + " => string match at " + this.sData.getJournalModel01(qNo).getDebitName(j));
			    					
			    					//����i��j�̑g�����̎��̊i�[����Ă��鐔�l�����������ǂ���
			    					if(this.sData.getJournalModel01(qNo).getDebitAmount(j) == 
			    							Long.parseLong((String)(this.st1_bDebit_num[i].getText()))){
			    						System.out.println("-------------------�����I�I�I------------");
			    						System.out.println(i + ", " + j + " => number match at " + this.sData.getJournalModel01(qNo).getDebitAmount(j));
			    						judgeDebit[j] = true;
			    					}//���l����
			    				}//���ږ�����
							}
						}//for i
					}else{
						//�����d��\�̒T���Ώۍ��ڂ�null�̏ꍇ�͒T�������AjudgeDebit�̊Y���v�f��false��Ԃ�
						judgeDebit[j] = false;
					}
	    		}//for j
	    		sumDebitRightAnswer = 0;
	    		//Debit���ړ��̐����̌����J�E���g
	    		for(int i = 0;i<judgeDebit.length;i++){
	    			if(judgeDebit[i]){
	    				System.out.println("�ؕ�debit��" + i + "�Ԗڂ͐���");
	    				sumDebitRightAnswer++;
	    			}else{
	    				System.out.println("�ؕ�debit��" + i + "�Ԗڂ͕s����");
	    			}
	    		}
	    		//Debit�̍��ڂ̒��Ő����̐���judgeDebitModelNum(�{�^���Ɛ������f���ň�v���Ă���i�[�f�[�^��)�Ɠ�������ΐ����Ƃ���B
	    		if(sumDebitRightAnswer == judgeDebitModelNum){
	    			System.out.println("�ؕ�(Debit)�͉ߕs���Ȃ������I�I");
	    			judgeDebitAll = true;
	    		}else{
	    			System.out.println("the number of Debit right answer : " + sumDebitRightAnswer + " of total : " + judgeDebitModelNum);
	    			
	    		}
	    		
    		}//if(judgeDebitButtonNum == judgeDebitModelNum)
    		
    		
    		
    		/**
    		 * �ݕ�Credit�ɑ΂��Ă������l�ɔ��肷��(�ؕ�Debit�ƑS���������@)
    		 */
    		
    		judgeCreditModelNum = 0;//����������Ă��邪�A�O�̂���
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
    					//indexOf���\�b�h�̓f�t�H���g������ubXX�v�����o����Ȃ����-1���o�͂����
    					judgeCreditButtonNum ++;
    				}
    				System.out.println(this.st1_bCredit_name[i].getText() + ", " + judgeCreditButtonNum);
    			}
    		}
    		System.out.println(judgeCreditModelNum + ", " + judgeCreditButtonNum);
    		
    		
    		if(judgeCreditButtonNum == judgeCreditModelNum){
    			System.out.println("�{�^���Ɛ������f���̊i�[�f�[�^���̈�v���m�F�B");
	    		for(int j = 0;j<this.sData.getJournalModel01(qNo).getCreditName().length;j++){
	    			judgeCredit[j] = false;//�f�t�H���g��false�����A�ꉞ�O�̂��ߖ����B
	    			
					System.out.println("j = " + j);
					if(this.sData.getJournalModel01(qNo).getCreditName(j) != null){
			    		for(int i = 0;i<this.st1_bCredit_name.length;i++){
			    			if(!this.st1_bCredit_name[i].equals("")){
			    				System.out.println("i = " + i);
								System.out.println(st1_bCredit_name[i].getText());
								/*
								 * �T���A�C�e��st1_Credit_name[i]�Ɠ��������̂������d��\�̑ݕ�Credit�ɂȂ����m�F
								 * ���A���i�[�l""�ł��Ȃ����A������(���i�[�ň�v���Ă������Ƃ��Ȃ��悤��)
								 */
			    				if(
			    					(!this.sData.getJournalModel01(qNo).getCreditName(j).equals("")) &&
			    					(!this.st1_bCredit_name[i].getText().equals("")) &&
			    					(this.sData.getJournalModel01(qNo).getCreditName(j).equals(this.st1_bCredit_name[i].getText()))
			    						){
			    					System.out.println(i + ", " + j + " => string match at " + this.sData.getJournalModel01(qNo).getCreditName(j));
			    					
			    					//����i��j�̑g�����̎��̊i�[����Ă��鐔�l�����������ǂ���
			    					if(this.sData.getJournalModel01(qNo).getCreditAmount(j) == 
			    							Long.parseLong((String)(this.st1_bCredit_num[i].getText()))){
			    						System.out.println("-------------------�����I�I�I------------");
			    						System.out.println(i + ", " + j + " => number match at " + this.sData.getJournalModel01(qNo).getCreditAmount(j));
			    						judgeCredit[j] = true;
			    					}//���l����
			    				}//���ږ�����
							}
						}//for i
					}else{
						//�����d��\�̒T���Ώۍ��ڂ�null�̏ꍇ�͒T�������AjudgeCredit�̊Y���v�f��false��Ԃ�
						judgeCredit[j] = false;
					}
	    		}//for j
	    		sumCreditRightAnswer = 0;
	    		//Credit���ړ��̐����̌����J�E���g
	    		for(int i = 0;i<judgeCredit.length;i++){
	    			if(judgeCredit[i]){
	    				System.out.println("�ݕ�Credit��" + i + "�Ԗڂ͐���");
	    				sumCreditRightAnswer++;
	    			}else{
	    				System.out.println("�ݕ�Credit��" + i + "�Ԗڂ͕s����");
	    			}
	    		}
	    		//Credit�̍��ڂ̒��Ő����̐���judgeCreditModelNum(�{�^���Ɛ������f���ň�v���Ă���i�[�f�[�^��)�Ɠ�������ΐ����Ƃ���B
	    		if(sumCreditRightAnswer == judgeCreditModelNum){
	    			System.out.println("�ݕ�(Credit)�͉ߕs���Ȃ������I�I");
	    			judgeCreditAll = true;
	    		}else{
	    			System.out.println("the number of Credit right answer : " + sumCreditRightAnswer + " of total : " + judgeCreditModelNum);
	    			
	    		}
	    		
    		}//if(judgeCreditButtonNum == judgeCreditModelNum)
    	}else{
    		System.out.println("sData is null!");
    		//sData��null�̏ꍇ�͕s��v�t���O��Ԃ�
			return false;
		}
    	
    	

		System.out.println("����-> debit : " + sumDebitRightAnswer + ", credit:" + sumCreditRightAnswer);
		System.out.println("�ytotal judgement�z debit : " + judgeDebitAll + ", credit:" + judgeCreditAll);
		
    	
    	//�����̏ꍇ
    	if(judgeDebitAll && judgeCreditAll){
    		System.out.println("�ݕ��Ǝؕ������Ƃ�����");
	    	//�S�Ă̔�����N���A����
	    	return true;
    	}else if(judgeDebitAll){//�ؕ�(Debit)���������̂ŁA�ݕ����Ԉ���Ă���P�[�X
    		System.out.println("Credit is InCorrectAnswer");			
    		return false;
    	}else if(judgeCreditAll){//�ݕ�(Credit)���������̂ŁA�ؕ����Ԉ���Ă���P�[�X
    		System.out.println("Debit is InCorrectAnswer");
    		return false;
    	}else{
    		//�����ɐ��䂪�ڂ鎖�͂Ȃ�(���@��̐���)
    		return false;
    	}
    }
    
    //��ʏ�̉𓚃{�^�����������ꂽ�Ƃ���answerJudgement�֐��̕Ԃ�l(���딻�f)��\������
    private void dialogEither(boolean judgement){
    	
    	String strJudgement = null;
    	System.out.println("called dialog!!");
    	if(judgement){
    		strJudgement = "�����I";
    		rData.setJudgement(qNo, true);
    		System.out.println("�����I�F���܂ł̐���" + rData.getSumRightAnswer());
    	}else{
    		strJudgement = "�s����!";
    		rData.setJudgement(qNo, false);
    		System.out.println("�s�����I�F���܂ł̐���" + rData.getSumRightAnswer());
    	}
		new AlertDialog.Builder(StoryModel1.this)
			.setMessage(strJudgement)
			.setCancelable(false)
			.setPositiveButton("���̖���", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							//���̖���
							if(REPEAT_MODE){//�s��������s���[�hON�Ȃ��
								//�O��s������܂�qNo++;���J��Ԃ�
								for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
									System.out.println("Repeat = " + RepeatQuesNumberArray[qNo]);
									if(RepeatQuesNumberArray[qNo]){
										break;//�s������Ȃ��for���𔲂���
									}
								}
							}else{
								qNo++;
							}
							if(qNo < sData.getQuestion().length){//���ԍ����i�[���ȉ��Ȃ�
								//����ԍ����C���v�������g(�{�P)������ŉ�ʍX�V
			    				viewUpdate();
							}else{
								//����ȏ��萔���Ȃ��ꍇ
								finalUpdate();
							}
						}
			})
			.setNegativeButton("�����",
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
		.setMessage("�����I�I")
		.setCancelable(false)
		.setPositiveButton("�����\��", 
				new  DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						StoryModel1.this.finish();
					}
		})
		.setNegativeButton("���̖���",
				new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						dialog.cancel();
			}
		}).show();
    	
    }
    
    private void dialogWrong(){
		new AlertDialog.Builder(this)
		.setMessage("�c�O�I�I")
		.setCancelable(false)
		.setPositiveButton("������\��", 
				new  DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						StoryModel1.this.finish();
					}
		})
		.setNegativeButton("�����\��",
				new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						dialog.cancel();
			}
		}).show();
    	
    }*/
    
    

    /**
     * �t���b�N���X�i�[�����������C���i�[�N���X
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
    			pressedX = (int)e.getX();//�����ꂽ�ʒu�͎g��Ȃ�
    			
    			//�����ꂽ�{�^��ID�̎擾
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
    			//if(releasedX < 0){//���t���b�N�̏ꍇ
				if(releasedX < -10.0){//���t���b�N�̏ꍇ
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
	        
	        String line[] = new String[10];//�ő�10�s���x�Ƃ���
	        //#�g�[�N���ŕ�������
	        int cnt = 0;
	        while(st.hasMoreTokens()){
	        	line[cnt]=st.nextToken();
	        	cnt++;
	        }
	        //�������ꂽ�g�[�N���̐������̗v�f����������z����쐬���A�������ꂽ�g�[�N�����i�[
	        line2 = new String[cnt];
	        System.arraycopy(line, 0, line2, 0, cnt);
	        
        }catch(Exception e){
        	System.out.println("������͕\���y�[�W�Ńg�[�N�������G���[���������܂����B");
        	System.out.println(e);
        }
        
        System.out.println("complete reconstructing givenData");
        //�Ƃ肠�����͉�������ꊇ�\��
        
        String out = "";
        for(int i = 0;i<line2.length;i++){
        	out += "\n" + line2[i];
        }
    	
    	return out;
    }

}
