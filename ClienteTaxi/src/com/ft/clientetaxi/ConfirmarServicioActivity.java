package com.ft.clientetaxi;

import com.ft.clientetaxi.model.TaxiMessenger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarServicioActivity extends Activity implements OnClickListener {
	
	private String numPasajero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmar_servicio);
		
		findViewById(R.id.btnConf5).setOnClickListener(this);
		findViewById(R.id.btnConf10).setOnClickListener(this);
		findViewById(R.id.btnConf15).setOnClickListener(this);
		
		numPasajero = getIntent().getStringExtra(TaxiMessenger.EXTRA_NUMERO);
		String ubicacion = getIntent().getStringExtra(TaxiMessenger.EXTRA_UBICACION);
		TextView txtCon = (TextView)findViewById(R.id.txtMessageConf);
		txtCon.setText("Te han solicitado un servicio en la siguiente direccion: "+ubicacion);
	}

	@Override
	public void onClick(View v) {
	
		if(v.equals(findViewById(R.id.btnConf5)))
		{
			sendConfirmation(5);
			
		}
		else if (v.equals(findViewById(R.id.btnConf10)))
		{
			sendConfirmation(10);
		}
		else if (v.equals(findViewById(R.id.btnConf15)))
		{
			sendConfirmation(15);
		}
		showToast();
	}
	
	private void sendConfirmation(int time)
	{
		TaxiMessenger tm = TaxiMessenger.getInstance();
		tm.enviarConfirmacion(time, numPasajero);
	}
	
	private void showToast(){
		Toast.makeText(getApplicationContext(), "El servicio ha sido confirmado", Toast.LENGTH_LONG).show();
	}

}
