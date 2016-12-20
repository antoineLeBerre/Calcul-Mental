package com.calculmental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPlay extends Activity implements View.OnClickListener
{
	String lang;
	int Gmode;
	int Reussi = 0;
	int faux = 0;
	int cpt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuplay);
		TextView mode = (TextView)findViewById(R.id.textView2);
		Button entrainement = (Button)findViewById(R.id.button1);
		Button test = (Button)findViewById(R.id.button2);
		Button retour = (Button)findViewById(R.id.button3);
		Bundle info = getIntent().getExtras();
		
		lang = info.getString("langage");
		Gmode = info.getInt("difficult");
		
		if(lang.equals("Fra"))
		{
			entrainement.setText(R.string.EntrainementFR);
			test.setText(R.string.TestFR);
			mode.setText(R.string.ModeFR);
			retour.setText(R.string.retour);
		}
		else
		{
			entrainement.setText(R.string.EntrainementEN);
			test.setText(R.string.TestEN);
			mode.setText(R.string.ModeEN);
			retour.setText(R.string.returnEn);
		}
		
		entrainement.setOnClickListener(this);
		test.setOnClickListener(this);
		retour.setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		if (v.getId() == R.id.button1)
		{
			Intent i2 = new Intent(this, Jouer.class);
			i2.putExtra("difficult", Gmode);
			i2.putExtra("langage", lang);
			startActivityForResult(i2, 2);
		}
		
		if (v.getId() == R.id.button3)
		{
			fin();
		}
	}
	
	private void fin()
	{
		Intent i1 = new Intent();  
		i1.putExtra("langage", lang);
		i1.putExtra("difficult", Gmode);
		if (cpt == 1)
		{
			i1.putExtra("Reussi", Reussi);
			i1.putExtra("Faux", faux);
		}
		else if(cpt == 0)
		{
			i1.putExtra("Reussi", 0);
			i1.putExtra("Faux", 0);
		}
		setResult(RESULT_OK, i1); 
		MenuPlay.this.finish();
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
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  
	{  
		super.onActivityResult(requestCode, resultCode, data);  
	    // check if the request code is same as what is passed  here it is 2  
	    if(requestCode==2)  
	    {  
	   		cpt = 1;
	   		Reussi = data.getIntExtra("Reussi", -1);
	   		faux = data.getIntExtra("Faux", -1);
	    	Gmode = data.getIntExtra("difficult", -1);
	    	lang = data.getStringExtra("langage");
	    }  
	}  
	 
	public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
               
        if (keyCode == KeyEvent.KEYCODE_BACK) 
        {
			fin();
        }
		return true;
           
     }
}
