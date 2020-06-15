package br.com.petersonmashni.appturmas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.com.petersonmashni.appturmas.DAO.TurmaDAO;
import br.com.petersonmashni.appturmas.Models.Turma;

public class InscricoesActivity extends AppCompatActivity {

    private TextView tvTurmaNome;
    private Turma turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricoes);

        tvTurmaNome = findViewById(R.id.incricoes_tvTurmaNome);

        int turma_id = getIntent().getIntExtra("turma_id", 0);
        turma = TurmaDAO.getTurmaById(this, turma_id);

        tvTurmaNome.setText(turma.getNome());

    }

    
}
