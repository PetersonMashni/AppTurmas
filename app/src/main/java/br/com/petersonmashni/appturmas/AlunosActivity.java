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

import br.com.petersonmashni.appturmas.Adapters.AlunoAdapter;
import br.com.petersonmashni.appturmas.DAO.AlunoDAO;
import br.com.petersonmashni.appturmas.Models.Aluno;

public class AlunosActivity extends AppCompatActivity {

    private ListView lvAlunos;

    @Override
    protected void onResume() {
        super.onResume();
        carregarAlunos();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        lvAlunos = findViewById(R.id.turmas_lvAlunos);
        FloatingActionButton fbAdicionar = findViewById(R.id.alunos_fbAdicionar);

        fbAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlunosActivity.this, AlunoEdicaoActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(position);
                Intent intent = new Intent(AlunosActivity.this, AlunoEdicaoActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("aluno_id", aluno.getAluno_id());
                startActivity(intent);
            }
        });


        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(position);

                final AlertDialog.Builder alerta = new AlertDialog.Builder(AlunosActivity.this);
                alerta.setTitle(getString(R.string.dialogo_atencao));
                alerta.setIcon(android.R.drawable.ic_dialog_alert);
                alerta.setMessage(getString(R.string.aluno_edicao_confirma_exclusao, aluno.getNome()));
                alerta.setNeutralButton(getString(R.string.dialogo_atencao_cancelar), null);

                alerta.setPositiveButton(getString(R.string.dialogo_atencao_sim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlunoDAO.excluir(AlunosActivity.this, aluno.getAluno_id());
                        carregarAlunos();
                    }
                });
                alerta.show();

                return true;
            }
        });
    }

    private void carregarAlunos() {
        List<Aluno> listaAlunos = AlunoDAO.listar(this, "");
        AlunoAdapter adapter = new AlunoAdapter(this, listaAlunos);
        lvAlunos.setAdapter(adapter);
    }
}
