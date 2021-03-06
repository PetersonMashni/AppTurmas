package br.com.petersonmashni.appturmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.petersonmashni.appturmas.DAO.AlunoDAO;
import br.com.petersonmashni.appturmas.DAO.TurmaDAO;
import br.com.petersonmashni.appturmas.Models.Aluno;

public class MainActivity extends AppCompatActivity {
    TextView tvTurmasCadastradas;
    TextView tvAlunosCadastrados;

    @Override
    protected void onResume() {
        super.onResume();
        atualizaTotais();
    }

    private void atualizaTotais() {
        int turmasAtivas = TurmaDAO.getCount(this, "ativa=1");
        int turmas = TurmaDAO.getCount(this, "");
        int alunos = AlunoDAO.getCount(this, "");

        tvTurmasCadastradas.setText(
                getString(
                        R.string.main_activity_tvTurmasAtivas_text,
                        turmasAtivas,
                        turmas));
        tvAlunosCadastrados.setText(
                getString(
                        R.string.main_activity_tvAlunosCadastrados_text,
                        alunos));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTurmasCadastradas = findViewById(R.id.main_tvTurmasAtivas);
        tvTurmasCadastradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turmas();
            }
        });

        tvAlunosCadastrados = findViewById(R.id.main_tvAlunosCadastrados);
        tvAlunosCadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alunos();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_alunos:
                alunos();
            case R.id.menu_main_turmas:
                turmas();
        }
        return super.onOptionsItemSelected(item);
    }

    private void turmas() {
        Intent intent = new Intent(MainActivity.this, TurmasActivity.class);
        startActivity(intent);
    }

    private void alunos() {
        Intent intent = new Intent(MainActivity.this, AlunosActivity.class);
        startActivity(intent);
    }

}
