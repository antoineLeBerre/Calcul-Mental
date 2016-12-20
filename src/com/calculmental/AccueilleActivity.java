package com.calculmental;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccueilleActivity extends Activity implements View.OnClickListener
{
	String langue;
	int mode = 1;
	int cpt;
	int Reussi;
	int faux;
	TextView titre; 
	Button jouer;
	Button stats;
	Button quitter;
	Button option;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueille);
		titre = (TextView)findViewById(R.id.textView1);
		jouer = (Button)findViewById(R.id.Jouer);
		stats = (Button)findViewById(R.id.Stats);
		quitter = (Button)findViewById(R.id.Quitter);
		option = (Button)findViewById(R.id.Option);
		
		if (langue == null)
		{
			System.out.println("entrer");
			titre.setText(R.string.titre);
			jouer.setText(R.string.jouer);
			stats.setText(R.string.stats);
			option.setText(R.string.options);
			quitter.setText(R.string.quitter);		
		}

		option.setOnClickListener(this);
		jouer.setOnClickListener(this);
		stats.setOnClickListener(this);
		quitter.setOnClickListener(this);
	}

	public void onClick(View v)
	{	
		if(v.getId() == R.id.Jouer)
		{
			if(cpt == 1)
			{
				Intent i2 = new Intent(this, MenuPlay.class);
				i2.putExtra("difficult", mode);
				i2.putExtra("langage", langue);
				startActivityForResult(i2, 2);
			}
			else
			{
				Intent i2 = new Intent(this, MenuPlay.class);
				i2.putExtra("difficult", 1);
				i2.putExtra("langage", "Fra");
				startActivityForResult(i2, 2);
			}
		}
		else if (v.getId() == R.id.Stats)
		{
			if(cpt == 1)
			{
				Intent i2 = new Intent(this, Statistique.class);
				i2.putExtra("langage", langue);
				i2.putExtra("Reussi", Reussi);
				i2.putExtra("Faux", faux);
				startActivity(i2);
			}
			else
			{
				Intent i2 = new Intent(this, Statistique.class);
				i2.putExtra("langage", "Fra");
				i2.putExtra("Reussi", 0);
				i2.putExtra("Faux", 0);
				startActivity(i2);
			}
		}
		else if(v.getId() == R.id.Option)
		{		
			if(cpt == 1)
			{
				Intent i2 = new Intent(this, Option.class);
				i2.putExtra("difficult", mode);
				i2.putExtra("langage", langue);
				startActivityForResult(i2, 2);
			}
			else
			{
				Intent i2 = new Intent(this, Option.class);
				i2.putExtra("difficult", 1);
				i2.putExtra("langage", "Fra");
				startActivityForResult(i2, 2);
			}
		}
		else if(v.getId() == R.id.Quitter)
		{
			AccueilleActivity.this.finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  
    {  
		super.onActivityResult(requestCode, resultCode, data);  
		titre = (TextView)findViewById(R.id.textView1);
		jouer = (Button)findViewById(R.id.Jouer);
		stats = (Button)findViewById(R.id.Stats);
		quitter = (Button)findViewById(R.id.Quitter);
		option = (Button)findViewById(R.id.Option);
        // check if the request code is same as what is passed  here it is 2  
        if(requestCode==2)  
        {  
        	cpt = 1;
        	
        	Reussi = data.getIntExtra("Reussi", -1);
        	faux = data.getIntExtra("Faux", -1);
        	mode = data.getIntExtra("difficult", -1);
        	langue = data.getStringExtra("langage");
        	
        	if (langue.equals("Fra"))
    		{
    			titre.setText(R.string.titre);
    			jouer.setText(R.string.jouer);
    			stats.setText(R.string.stats);
    			option.setText(R.string.options);
    			quitter.setText(R.string.quitter);		
    		}
    		else if (langue.equals("Eng"))
    		{
    			System.out.println("entrer");
    			titre.setText(R.string.title);
    			jouer.setText(R.string.play);
    			stats.setText(R.string.stat);
    			option.setText(R.string.options);
    			quitter.setText(R.string.quit);
    		}
        }  
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
}
