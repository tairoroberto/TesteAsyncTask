package com.example.testeassinctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class Tarefa extends AsyncTask<String, String, Bitmap> {
	Context context;
	TarefaInferface tarefaInferface;
	private ProgressDialog progress;

	public Tarefa(Context context,TarefaInferface tarefaInferface) {
		this.context = context;
		this.tarefaInferface = tarefaInferface;
	}

	@Override
	protected void onPreExecute() {
		progress = new ProgressDialog(context);
		progress.setMessage("Carregando...");
		progress.show();
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap img = null;
		try {
			publishProgress("Ainda carregando...!!!");
			URL url = new URL(params[0]);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();
			InputStream input = conexao.getInputStream();
			img = BitmapFactory.decodeStream(input);
			publishProgress("Carregado...!!!");

		} catch (IOException e) {
		}
		return img;
	}

	@Override
	protected void onProgressUpdate(String... params) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {	
		tarefaInferface.depoisDownload(bitmap);
		progress.dismiss();
	}

}
