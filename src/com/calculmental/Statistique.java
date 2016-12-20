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

public class Statistique extends Activity implements View.OnClickListener
{

	int mode;
	String langue;
	int Reussi;
	int faux;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		String reussi;
		String wrong;
		setContentView(R.layout.statistique);
		TextView titre = (TextView)findViewById(R.id.title);
		TextView nbrTotal = (TextView)findViewById(R.id.nbrJouer);
		TextView nbrFaux = (TextView)findViewById(R.id.nbrFaux);
		TextView chiffreTotal = (TextView)findViewById(R.id.chiffreJouer);
		TextView chiffreFaux = (TextView)findViewById(R.id.chiffreFaux);
		Button retour = (Button)findViewById(R.id.retour);
		Bundle info = getIntent().getExtras();
		
		Reussi = info.getInt("Reussi");
		faux = info.getInt("Faux");
		langue = info.getString("langage");
		if(langue.equals("Fra"))
		{
			titre.setText(R.string.entrainement);
			nbrTotal.setText(R.string.nbrTotal);
			nbrFaux.setText(R.string.nbrFaux);
		}
		reussi = Integer.toString(Reussi);
		wrong = Integer.toString(faux);
		chiffreTotal.setText(reussi);
		chiffreFaux.setText(wrong);
		
		retour.setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		if (v.getId() == R.id.retour)
		{
			finish();
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
			Statistique.this.finish();
        }
		return true;
           
     }
}
