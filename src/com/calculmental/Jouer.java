package com.calculmental;

import java.text.DecimalFormat;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Jouer extends Activity implements View.OnClickListener
{
	//Texte pour traduction

	int resultat = 0;
	double resultatD = 0;
	int cpt = 0;
	int cptJuste = 0;
	int cptFaux = 0;
	String Langue;
	int Gmode;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jouer);
		Button valider = (Button)findViewById(R.id.valider);
		Button fermer = (Button)findViewById(R.id.retour);
		int difficult = 0;
		
		Bundle mode = getIntent().getExtras();
		if(mode != null)
		{
			Langue = mode.getString("langage");
			if(Langue.equals("Fra"))
			{
				fermer.setText(R.string.retour);
			}
			else if(Langue.equals("Eng"))
			{
				fermer.setText(R.string.returnEn);
			}
			
			difficult = mode.getInt("difficult");
			Gmode = difficult;
			System.out.println(Gmode);
			random(difficult);
		}	
		valider.setOnClickListener(this);
		fermer.setOnClickListener(this);
	}

	public void onClick(View v)
	{
		if (v.getId() == R.id.valider)
		{
			EditText reponse = (EditText)findViewById(R.id.editText1);
			String rep = reponse.getText().toString();
			int difficult = 0;
			Bundle mode1 = getIntent().getExtras();
			
			if(mode1 != null && rep.equals(""))
			{
				if(Langue.equals("Fra"))
					Toast.makeText(this, R.string.toastFr, Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(this, R.string.toastEn, Toast.LENGTH_SHORT).show();
			}
			else if (mode1 != null)
			{
				difficult = mode1.getInt("difficult");
				verifier(difficult);
			}
		}
		else if (v.getId() == R.id.retour)
		{
			fin();
		}
	}
	
	private void fin()
	{
		Intent i1 = new Intent();  
		i1.putExtra("Reussi", cptJuste);
		i1.putExtra("Faux", cptFaux);
		i1.putExtra("langage", Langue);
		i1.putExtra("difficult", Gmode);
		setResult(RESULT_OK, i1); 
		Jouer.this.finish();
	}

	//Fonction permettant de verifier si le resultat donné est le meme que le resultat du random
	public void verifier(final int mode)
	{
		// TODO Auto-generated method stub
		final EditText reponse = (EditText)findViewById(R.id.editText1);
		final TextView result = (TextView)findViewById(R.id.textView1);
		int res = 0;
		double resD = 0;
		//TextView test = (TextView)findViewById(R.id.textView2);
		//test.setText(Integer.toString(cptFaux));
		if (mode != 4)
		{
			
			res = Integer.parseInt(reponse.getText().toString());
			if(res == resultat)
			{	
				cptJuste++;
				if(Langue.equals("Fra"))
				{
					result.setText(R.string.bonFR);
				}
				else
				{
					result.setText(R.string.bon);
				}
				result.setVisibility(1);
				result.setTextColor(getResources().getColor(R.color.vert));
				result.postDelayed(new Runnable() {
					public void run() {
						result.setVisibility(-1);	
						random(mode);
					}			
				}, 1000);
				reponse.setText("");
			}
			else
			{
				cptFaux++;
				if(Langue.equals("Fra"))
				{
					result.setText(R.string.mauvaisFR);
				}
				else
				{
					result.setText(R.string.mauvais);
				}
				result.setVisibility(1);
				result.setTextColor(getResources().getColor(R.color.red));
				result.postDelayed(new Runnable() {
					public void run() {
						result.setVisibility(-1);
					}
					}, 1000);

				reponse.setText("");
			}
		}
		else if(mode == 4)
		{
			resD = Double.parseDouble(reponse.getText().toString());
			if(resD == resultatD)
			{	
				result.setText(R.string.bon);
				result.setVisibility(1);
				result.setTextColor(getResources().getColor(R.color.vert));
				reponse.setText("");
				result.postDelayed(new Runnable() {
					public void run() {
						result.setVisibility(-1);	
						random(mode);
					}
				}, 2000);
			}
			else
			{
				result.setText(R.string.mauvais);
				result.setVisibility(1);
				result.setTextColor(getResources().getColor(R.color.red));
				result.postDelayed(new Runnable() {
					public void run() {
						result.setVisibility(-1);
						reponse.setText("");
					}
					}, 2000);

				reponse.setText("");
			}
			
		}
		
	}
	
	//Fonction permettant d'afficher un calcule aleatoirement et inscrit le resultat dans un fichier
	public void random(int mode)
	{
		TextView calcul = (TextView)findViewById(R.id.calcul);
 		String cal = null;
		Random rand = new Random();
		double test = 0;
		double randfloat = rand.nextDouble();
		double randfloat2 = rand.nextDouble();
		int lower = 0;
		int higher = 0;
		int lowerdiv = 0;
		int higherdiv = 0;
		int number1 = 0;
		int number2 = 0;
		int signe = 0;
		double lowerD = 0;
		double higherD = 0;
		double nbr1 = 0;
		double nbr2 = 0;
		DecimalFormat df = null;
		String str = null;
		df = new DecimalFormat("########.00");
		
		cpt++;
		signe = rand.nextInt(4 - 0);
		//Difficulté facile - moyen - difficile
		if (mode == 1)
		{
			lower = -50;
			higher = 50;
			lowerdiv = -10;
			higherdiv = 10;	
		}
		else if(mode == 2)
		{
			lower = -100;
			higher = 100;
			lowerdiv = -50;
			higherdiv = 50;	
		}
		else if(mode == 3)
		{
			lower = -250;
			higher = 250;
			lowerdiv = -100;
			higherdiv = 100;	
		}
		
		if (mode != 4)
		{
			number1 = rand.nextInt(higher - lower + 1) + lower;
			number2 = rand.nextInt(higher - lower + 1) + lower;
			if (signe == 0)
			{
				cal = number1 + " + " + number2;
				resultat = number1 + number2;
			}
			else if (signe == 1)
			{
				cal = number1 + " - " + number2;
				resultat = number1 - number2;
			}
			else if (signe == 2)
			{
				cal = number1 + " * " + number2;
				resultat = number1 * number2;
			}	
			else if (signe == 3)
			{
				while(number2 == 0)
				{
					number2 = rand.nextInt(higherdiv - lowerdiv + 1) + lowerdiv;
				}
				while (number1%number2 != 0)
				{
					number2 = rand.nextInt(higherdiv - lowerdiv + 1) + lowerdiv;
					while(number2 == 0)
					{
						number2 = rand.nextInt(higherdiv - lowerdiv + 1) + lowerdiv;
						cal = number1 + " / " + number2;
						resultat = number1 / number2;
					}
					cal = number1 + " / " + number2;
					resultat = number1 / number2;
				}
				cal = number1 + " / " + number2;
				resultat = number1 / number2;
			}
		}
		else if(mode == 4)
		{
			lowerD = -100;
			higherD = 100;
			nbr1 = lowerD + (randfloat * (higherD - lowerD));
			nbr2 = lowerD + (randfloat2 * (higherD - lowerD));
			str = df.format(nbr1);
			nbr1 = Double.parseDouble(str.replace(',', '.'));	
			str = df.format(nbr2);
			nbr2 = Double.parseDouble(str.replace(',', '.'));
			
			if (signe == 0)
			{
				cal = nbr1 + " + " + nbr2;
				test = nbr1 + nbr2;
			}
			else if (signe == 1)
			{
				cal = nbr1 + " - " + nbr2;
				test = nbr1 - nbr2;
			}
			else if (signe == 2)
			{
				cal = nbr1 + " * " + nbr2;
				test = nbr1 * nbr2;
			}	
			else if (signe == 3)
			{
				while(number2 == 0)
				{
					nbr2 = lowerD + (randfloat * (higherD - lowerD));
					str = df.format(nbr1);
					nbr2 = Double.parseDouble(str.replace(',', '.'));
					cal = nbr1 + " / " + nbr2;
					test = nbr1 / nbr2;
				}
				cal = nbr1 + " / " + nbr2;
				test = nbr1 / nbr2;
			}
			str = df.format(test);
			test = Double.parseDouble(str.replace(',', '.'));
		}
		resultatD = test;
		calcul.setText(cal);
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
               
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            fin();
        }
		return true;
           
     }
}
