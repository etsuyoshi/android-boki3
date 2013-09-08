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
	private JournalModel01[] journalModel01;//�����d��\�I�u�W�F�N�g->answer
	private String strCSV[][];//csv�t�@�C���̓��e��S���񎟌��̕�����s��ɕϊ�
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
		System.out.println("selectStory���i�[����");
		
	}

	public void dispAlert(){
		//�܂��J�����Ȃ̂ŁA�_�C�A���O��\�����ďI������
		new AlertDialog.Builder(this)
			.setMessage("�\���󂲂����܂���B�����łł͂��g�p�ɂȂ�܂���B")
			.setCancelable(true)
			.setPositiveButton("����", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							return;
						}
			})
		.show();
	}
	public void onClick(View v){
		/*
		 * ���ӁF���̉�ʂ�S��ImageButton�݂̂ɂ���K�v
		 * 
		 * �y���R�zview����xButton�ɃL���X�g���Ă��܂���ImageButton�ɒ����Ȃ��̂�
		 * �@�@�@�@����ImageButton�̔���Ɏg���Ȃ��I�I
		 * 
		 */
		
		System.out.println("original view = " + v.toString());
		
		/*
		View viewImageButton = v;
		View viewButton = v;
		//view��Button�I�u�W�F�N�g�ł���ꍇ�A�ȉ��̊i�[������ۂɃG���[������
		viewImageButton = (ImageButton)viewImageButton;
		viewButton = (Button)viewButton;
		System.out.println("�Poriginal view = " + v.toString());
		System.out.println("�Pimage button = " + viewImageButton.toString());
		System.out.println("�Pbutton = " + viewButton.toString());
	 	*/
		
		
		//�ȉ�����(try-error�ő����Ă��ǂ��j
		//�Q�l�Fsubstring(start, end)�͕�������O����J�E���g����[start]�Ԗڂ���[end-1]�Ԗڂ܂ł𒊏o
		System.out.println(((v.toString())).substring(0,21));
		if(((Button)v).getId() == R.id.s_s_bt_return){
			System.out.println("pressed finish button!");
			finish();
			return;
		}else if (((Button)v).getId()==R.id.s_st_bt_Q1){
			System.out.println("pressed story0");
			InputStream is = res01.openRawResource(R.raw.sect001);
			if (setQSA2(is)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, StoryModel1.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
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
		 * �ȉ��̎O���ڂ�StoreData(�O���[�o��)�C���X�^���X�Ɋi�[
		 * Question
		 * Selection
		 * Answer
		 */
		//sData�ɓn�����߂̎���z��
		String[] outputQuestion = null;
		//InputStream is = res01.openRawResource(R.raw.sect001);
		br = new BufferedReader(new InputStreamReader(is));
		
		//strCSV�z��(�񎟌�)�Ɋi�[
		strCSV = new String[50][21];//40x20�����}�g���N�X
		
		try{
			for(int row = 0;row<strCSV.length;row++){
				//��s�ǂݍ���Ńg�[�N���ɕ���
				StringTokenizer st = new StringTokenizer(br.readLine(), ",");
				for(int col =0;col < strCSV[row].length;col++){
					//strCsv�̊e�v�f�Ɋi�[
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
			System.out.println("CSV�ǂݍ��ݎ��ɃG���[�����I�I");
			System.out.println("�G���[��" + e);
		}
		
		int rowCSV = 1;//�Ǎ��p�J�E���^�[(0�s�ڂ̓��x��)
		int noArray = 0;//�i�[�p�J�E���^�[
		category = strCSV[rowCSV][2];//�J�e�S���[
		
		do{//�J�E���^�[rowCSV�Ń��[�v
			System.out.println("rowCSV =  " + rowCSV);
			if(strCSV[rowCSV][1] == null){
				break;
			}else{
				//�f�o�b�O�̕K�v
				System.out.println("strCSV = " + strCSV[rowCSV][1]);
			}
			//���݂̍s�̖�蕶���n�C�t���ł���Ȃ�Ύ��̍s��ǂݍ���
			if((!strCSV[rowCSV][3].equals("-"))){
				/*
				 * �͔ԍ��̊i�[
				 */
				sectNo[noArray] = strCSV[rowCSV][0];
				/*
				 *���ԍ��̊i�[ 
				 */
				qNo[noArray] = strCSV[rowCSV][1];
				//category = strCSV[rowCSV][2];�����[�v���Ŋi�[����K�v���Ȃ��̂ŊO�o����
				/*
				 * ���╶�̊i�[
				 */
				question[noArray] = strCSV[rowCSV][3];
				System.out.println("noArray = " + noArray);
				/*
				 * �����d��\
				 */
				journalModel01[noArray] = new JournalModel01();
				//�d��\�̍쐬
				//��s�ڂ����ŏ��ɓǂݎ��A���̍s�̎��╶(�R���)���m�F���ăn�C�t���Ȃ玟�̍s���ǂݍ���
				journalModel01[noArray].setDebitName(0,strCSV[rowCSV][4]);//�ݕ����ږ���
				System.out.println(strCSV[rowCSV][4]);
				journalModel01[noArray].setDebitAmount(0,Long.valueOf(strCSV[rowCSV][5]));//�ݕ����z
				System.out.println(strCSV[rowCSV][5]);
				journalModel01[noArray].setCreditName(0,strCSV[rowCSV][6]);//�ؕ����ږ���
				System.out.println(strCSV[rowCSV][6]);
				journalModel01[noArray].setCreditAmount(0,Long.valueOf(strCSV[rowCSV][7]));//�ؕ����z
				System.out.println(strCSV[rowCSV][7]);
				
				
				//���̉��Ɏ��̍s�̎��╶���n�C�t��(��������qNo�̉E�ꕶ���ڂ�0�łȂ���false�ő�p��������)�Ȃ�,
				//���̍s��setXXXXName,Amount����B
				
				for(int i = 1;i+rowCSV<strCSV.length;i++){//rowCSV�ȉ��̍s���������ĂR��ڂ��n�C�t���Ȃ炻�̍s�͓������Ŏd��\�̑ݕ��������͎ؕ��̍��ڂ��������݂��Ă���
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
							//���̍s�̎��╶�񂪃n�C�t���łȂ���ΏI��
							break;
						}
					}else{
						//���̍s�̂O��ڂ�End of File�Ȃ�I��
						
						System.out.println("��蕶�̊i�[����");
						
						/*
						 * �d�v�I�I�I
						 * �z��̗v�f����̊i�[���Ɠ����ɂ���
						 * �z��question�z��̒�����ς���(�i�[����Ă���question�v�f��0�Ԗڂ���noArray�Ԗڂ܂�)
						 */
						outputQuestion = new String[noArray+1];
						System.arraycopy(question, 0, outputQuestion, 0, outputQuestion.length);
						
						for(int no = 0;no<outputQuestion.length;no++){
							System.out.println(outputQuestion[no]);
						}
						//System.arraycopy(inArray, x1 , outArray, x2,count)->inArray��x1����AoutArray[x2:x2+count-1]��copy����
						break;
					}
				}//for i
				System.out.println("complete input & initiate JournalModel01");
				/*
				 * �I�����̍쐬
				 */
				for(int colCSV = 8;colCSV<20;colCSV++){//csv�t�@�C���̂W��ڂ���Q�O��ڂ܂ł��I����(strCSV[rowCSV].length�͂Q�P���݂��A�Q�P�Ԗڂ͉����)
					System.out.println("colCSV = " + colCSV);
					
					selection[noArray][colCSV-8] = strCSV[rowCSV][colCSV];
					System.out.println("selection = " + selection[noArray][colCSV - 8]);
				}
				
				System.out.println("selection complete initiating!");
				
				
				/*
				 * ������̎�荞��
				 */
				explain[noArray] = strCSV[rowCSV][20];
				System.out.println("explain complete initiating");
				
				noArray++;
			}else if(strCSV[rowCSV][0].equals("[EOF]")){
				//��Ɏ��̍s�̈��ڂ�[EOF]�����邩�m�F���Ă��邽�ߕs�K�v�ȏ����H
				System.out.println("�����ɐ���t���[������邱�Ƃ͂Ȃ�");
				
				break;
			}
			rowCSV ++;
		}while(true);
		//�I����selection�͂X��ڂ���P�S���(col=8-13)��ǂݍ���Ŋi�[
		
		System.out.println("complete initiating Array!!");
		
		sData = new StoreData();
		System.out.println("complete initiating StoreData!!");
		//���͔̏ԍ���SECT�����������l�����̂ݓn��
		System.out.println(sectNo[0].replaceAll("SECT", ""));
		sData.setSectNo(Integer.valueOf(sectNo[0].replaceAll("SECT","")).intValue());
		System.out.println("complete setSectNo");
		sData.setCategory(category);
		System.out.println("complete setCategory");
		sData.setQuestion(outputQuestion);
		System.out.println("complete setQuestion");
		sData.setJournalModel01(journalModel01);//�d��\�̐����p�^�[���̊i�[
		System.out.println("complete setJournal");
		sData.setSelection(selection);
		System.out.println("complete setSelection");
		sData.setExplain(explain);
		System.out.println("complete setExplain");
	
		return true;
	}
}
