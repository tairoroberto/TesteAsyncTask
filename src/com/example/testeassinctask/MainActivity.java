package com.example.testeassinctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements TarefaInferface{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void buscarImagem(View view) {
		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("Carregando...");
		progress.show();

		new Thread() {
			public void run() {
				Bitmap img = null;
				try {
					URL url = new URL(
							"http://info.abril.com.br/noticias/blogs/zonalivre/files/2011/10/AndroidEngine-Small.jpg");
					HttpURLConnection conexao = (HttpURLConnection) url
							.openConnection();
					InputStream input = conexao.getInputStream();
					img = BitmapFactory.decodeStream(input);

				} catch (IOException e) {
					// TODO: handle exception
				}
				final Bitmap imgAux = img;
				runOnUiThread(new Runnable() {
					public void run() {
						progress.setMessage("Imagem carregada!");
						ImageView imageView = (ImageView) findViewById(R.id.imageView);
						imageView.setImageBitmap(imgAux);
						progress.dismiss();
					}
				});
			}
		}.start();
	}

	public void buscarImagem2(View view) {
		Tarefa tarefa = new Tarefa(this, this);
		tarefa.execute("http://wallpaper.ultradownloads.com.br/285838_Papel-de-Parede-Android-Destruidor_1920x1080.jpg");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void depoisDownload(Bitmap bitmap) {
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageBitmap(bitmap);
		
	}
}
