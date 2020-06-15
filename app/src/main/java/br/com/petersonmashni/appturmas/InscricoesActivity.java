package br.com.petersonmashni.appturmas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InscricoesActivity extends AppCompatActivity {

    private TextView tvCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricoes);

        tvCurso = findViewById(R.id.incricoes_tvCurso);

    }
}
