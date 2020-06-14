package br.com.petersonmashni.appturmas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.petersonmashni.appturmas.DAO.TurmaDAO;
import br.com.petersonmashni.appturmas.Models.Turma;

public class TurmasActivity extends AppCompatActivity {

    private ListView lvTurmas;

    @Override
    protected void onResume() {
        super.onResume();
        carregarTurmas();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turmas);

        lvTurmas = findViewById(R.id.turmas_lvTurmas);
        FloatingActionButton fbAdicionar = findViewById(R.id.turmas_fbAdicionar);

        fbAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TurmasActivity.this, TurmaEdicaoActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvTurmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Turma turma = (Turma) lvTurmas.getItemAtPosition(position);
                Intent intent = new Intent(TurmasActivity.this, TurmaEdicaoActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("turma_id", turma.getTurma_id());
                startActivity(intent);
            }
        });

        lvTurmas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Turma turma = (Turma) lvTurmas.getItemAtPosition(position);

                AlertDialog.Builder alerta = new AlertDialog.Builder(TurmasActivity.this);
                alerta.setTitle(getString(R.string.dialogo_atencao));
                alerta.setIcon(android.R.drawable.ic_dialog_alert);
                alerta.setMessage(getString(R.string.turma_edicao_confirma_exclusao, turma.getNome()));
                alerta.setNeutralButton(getString(R.string.dialogo_atencao_cancelar), null);

                alerta.setPositiveButton(getString(R.string.dialogo_atencao_sim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TurmaDAO.excluir(TurmasActivity.this, turma.getTurma_id());
                        carregarTurmas();
                    }
                });
                alerta.show();

                return true;
            }
        });
    }

    private void carregarTurmas() {
        List<Turma> listaTurmas = TurmaDAO.listar(this, "");
        TurmaAdapter adapter = new TurmaAdapter(this, listaTurmas);
        lvTurmas.setAdapter(adapter);
    }
}
