package com.aparicio.conexaoweb;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    //public final static String EXTRA_MESSAGE = "com.aparicio.Conexaoweb.MESSAGE";
    String url;
    private TextView urlEditText;
    private TextView retornoTextView;
    private TextView retornoTextView2;
    private TextView retornoTextView3;
    private RadioButton a;
    private RadioButton b;
    private RadioButton c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = (TextView) findViewById(R.id.url);
        retornoTextView = (TextView) findViewById(R.id.TextView1);
        //retornoTextView2 = (TextView) findViewById(R.id.TextView2);
        retornoTextView3 = (TextView) findViewById(R.id.TextView3);

        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);


    }


    public void click(View view) {
        // String url = urlEditText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo redeInfo = connMgr.getActiveNetworkInfo();

        if (redeInfo != null && redeInfo.isConnected()) {
            new ComunicacaoAssincrona().execute(url);
        } else {
            Toast.makeText(this, R.string.msg2, Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarDados(View view) {
        Intent i = new Intent(this, DisplayMessageActivity.class);
        String mensagem = retornoTextView.getText().toString();
        i.putExtra("mensagem", mensagem);
        startActivity(i);
    }

    public void radioClick(View view) {
        //Usa o metodo isChecked() para saber se o radioClick está selecionado

        RadioButton controle = (RadioButton) view;
        boolean checado = controle.isChecked();

        // testa qual radioclick foi clicado

        switch (controle.getId()) {
            case R.id.a:
                if (checado) {
                    Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
                    String a = "a";
                    retornoTextView3.setText("a");
                    url = "http://professorangoti.comlu.com/?zxc=a";
                    urlEditText.setText(url);
                    break;
                }

            case R.id.b:
                if (checado) {
                    Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
                    String b = "b";
                    retornoTextView3.setText("b");
                    url = "http://professorangoti.comlu.com/?zxc=b";
                    urlEditText.setText(url);
                    break;
                }

            case R.id.c:
                if (checado) {
                    Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
                    String c = "c";
                    retornoTextView3.setText("c");
                    url = "http://professorangoti.comlu.com/?zxc=c";
                    urlEditText.setText(url);
                    break;
                }

        }

    }

    private class ComunicacaoAssincrona extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return getString(R.string.msg3);
            }
        }

        @Override
        protected void onPostExecute(String result) {

            retornoTextView.setText(result);
            retornoTextView3.setText("Conectado!");

        }

        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            int len = 9999;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(1000);
                conn.setConnectTimeout(1500);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // realiza requisicao HTTP
                conn.connect();
                is = conn.getInputStream();
                //Converte a resposta em String
                String contentAsString = readIt(is, len);
                return contentAsString;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        // Receber um InputStream e retornar String
        public String readIt(InputStream stream, int len) throws IOException {

            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }


    }

    // Para as almas bravas que chegaram tão longe: Vocês são as escolhidas, os valentes cavaleiros da programação que trabalham, sem descanso, consertando nossos mais terríveis códigos.
    // A vocês, verdadeiros salvadores, reis dos homens, eu digo isso: nunca desista de você, nunca fique triste, nunca fuja e abandone você. Nunca te faça chorar, nunca diga adeus.
    // Nunca conte uma mentira e nunca se machuque.
    // Paz.


}
