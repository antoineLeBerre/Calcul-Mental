package com.calculmental;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Option extends Activity implements View.OnClickListener
{

	int mode;
	String langue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		
		TextView diff = (TextView)findViewById(R.id.textView1);
		RadioButton easy = (RadioButton)findViewById(R.id.radio0);
		RadioButton moyen = (RadioButton)findViewById(R.id.radio1);
		RadioButton diffi = (RadioButton)findViewById(R.id.radio2);
		RadioButton tDiffi = (RadioButton)findViewById(R.id.radioButton1);
		TextView fr = (TextView)findViewById(R.id.textView2);
		TextView en = (TextView)findViewById(R.id.textView3);
		Button fra = (Button)findViewById(R.id.button1);
		Button eng = (Button)findViewById(R.id.button2);
		Button close = (Button)findViewById(R.id.button3);
		fra.setOnClickListener(this);
		eng.setOnClickListener(this);
		close.setOnClickListener(this);
		
		int difficult = 0;
		String lang = null;
		Bundle mode1 = getIntent().getExtras();
		
		if(mode1 != null)
		{
			difficult = mode1.getInt("difficult");
			lang = mode1.getString("langage");
			if (lang.equals("Fra"))
			{
				diff.setText(R.string.difficulte);
				easy.setText(R.string.facile);
				moyen.setText(R.string.moyen);
				diffi.setText(R.string.difficile);
				tDiffi.setText(R.string.hardcore);
				fr.setText(R.string.francais);
				en.setText(R.string.anglais);
				close.setText(R.string.retour);
				langue = "Fra";
			}
			else if (lang.equals("Eng"))
			{
				diff.setText(R.string.difficulty);
				easy.setText(R.string.easy);
				moyen.setText(R.string.medium);
				diffi.setText(R.string.hard);
				tDiffi.setText(R.string.vHard);
				fr.setText(R.string.french);
				en.setText(R.string.english);
				close.setText(R.string.close);
				langue = "Eng";
			}
			if(difficult == 1)
			{
				easy.setChecked(true);
				mode = 1;
			}
			else if(difficult == 2)
			{
				moyen.setChecked(true);
				mode = 2;
			}
			else if(difficult == 3)
			{
				diffi.setChecked(true);
				mode = 3;
			}
			else if(difficult == 4)
			{
				tDiffi.setChecked(true);
				mode = 4;
			}
		}
	}

	public void onRadioButtonClicked(View view)
	{
		Context context = this;
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) 
	    {
	        case R.id.radio0:
	            if (checked)
	            {
	            	mode = 1;
	            }
	            break;
	        case R.id.radio1:
	            if (checked)
	            {
	            	mode = 2;
	            }
	            break;
	        case R.id.radio2:
	        	if (checked)
	        	{
	        		mode = 3;
	        	}
	        	break;
	        case R.id.radioButton1:
	        	if (checked)
	        	{
	        		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
	        		
	        		// set title
	    			alertDialog.setTitle(R.string.choix);
	     
	    			// set dialog message
	    			alertDialog
	    				.setMessage(R.string.question)
	    				.setCancelable(false)
	    				.setNegativeButton(R.string.valider ,new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, close
	    						// current activity
	    						mode = 4;
	    					}
	    				})
	    				.setPositiveButton(R.string.cancel , new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, just close
	    						// the dialog box and do nothing
	    						dialog.cancel();
	    						RadioButton r1 = (RadioButton)findViewById(R.id.radio0);
	    						r1.setChecked(true);
	    					}
	    				  });

	     
	    				// create alert dialog
	    				AlertDialog alertDialog1 = alertDialog.create();
	     
	    				// show it
	    				alertDialog1.show();
	        	}
	    }
	}
	
	public void onClick(View v)
	{
		TextView diff = (TextView)findViewById(R.id.textView1);
		RadioButton easy = (RadioButton)findViewById(R.id.radio0);
		RadioButton moyen = (RadioButton)findViewById(R.id.radio1);
		RadioButton diffi = (RadioButton)findViewById(R.id.radio2);
		RadioButton tDiffi = (RadioButton)findViewById(R.id.radioButton1);
		TextView fr = (TextView)findViewById(R.id.textView2);
		TextView en = (TextView)findViewById(R.id.textView3);
		Button close = (Button)findViewById(R.id.button3);
		if (v.getId() == R.id.button2)
		{
			diff.setText(R.string.difficulte);
			easy.setText(R.string.facile);
			moyen.setText(R.string.moyen);
			diffi.setText(R.string.difficile);
			tDiffi.setText(R.string.hardcore);
			fr.setText(R.string.francais);
			en.setText(R.string.anglais);
			close.setText(R.string.retour);
			langue = "Fra";
		}
		
		else if (v.getId() == R.id.button1)
		{
			diff.setText(R.string.difficulty);
			easy.setText(R.string.easy);
			moyen.setText(R.string.medium);
			diffi.setText(R.string.hard);
			tDiffi.setText(R.string.vHard);
			fr.setText(R.string.french);
			en.setText(R.string.english);
			close.setText(R.string.close);
			langue = "Eng";
		}
		
		else if(v.getId() == R.id.button3)
		{ 
            Intent i1 = new Intent();  
			i1.putExtra("difficult", mode);
			i1.putExtra("langage", langue);
			setResult(RESULT_OK, i1); 
			Option.this.finish();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueille, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
               
        if (keyCode == KeyEvent.KEYCODE_BACK) 
        {
        	Intent i1 = new Intent();  
			i1.putExtra("difficult", mode);
			i1.putExtra("langage", langue);
			setResult(RESULT_OK, i1); 
			Option.this.finish();
        }
		return true;
           
     }
}
