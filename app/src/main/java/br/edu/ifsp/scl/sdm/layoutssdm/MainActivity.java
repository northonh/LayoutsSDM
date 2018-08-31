package br.edu.ifsp.scl.sdm.layoutssdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private CheckBox notificacoesCheckBox;
    private RadioGroup notificacoesRadioGroup;
    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText telefoneEditText;

    // Constantes para salvar/restaurar estado dos botões
    private final String ESTADO_NOTIFICACAO_CHECKBOX = "ESTADO_NOTIFICACAO_CHECKBOX";
    private final String NOTIFICACAO_RADIOBUTTON_SELECIONADO = "NOTIFICACAO_RADIOBUTTON_SELECIONADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout_activity_main);

        // Buscando referências para componentes visuais
        notificacoesCheckBox = findViewById(R.id.notificacoesCheckBox);
        notificacoesRadioGroup = findViewById(R.id.notificacoesRadioGroup);
        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);

        // Tratamento de eventos de baseado na mudança de estado
        notificacoesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
                }
                else {
                    notificacoesRadioGroup.setVisibility(View.GONE);
                }
            }
        });

        // Tratamento de eventos baseado no clique
/*        notificacoesCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
                }
                else {
                    notificacoesRadioGroup.setVisibility(View.GONE);
                }
            }
        });*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Salvando estado dos botões
        outState.putBoolean(ESTADO_NOTIFICACAO_CHECKBOX, notificacoesCheckBox.isChecked());
        outState.putInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO, notificacoesRadioGroup.getCheckedRadioButtonId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        notificacoesCheckBox.setChecked(savedInstanceState.getBoolean(ESTADO_NOTIFICACAO_CHECKBOX,false));
        if (notificacoesCheckBox.isChecked()) {
            notificacoesRadioGroup.setVisibility(View.VISIBLE);
        }
        else {
            notificacoesRadioGroup.setVisibility(View.GONE);
        }

        int radioButtonId = savedInstanceState.getInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO, -1);
        if (radioButtonId != -1) {
            notificacoesRadioGroup.check(radioButtonId);
        }
    }

    public void limparFormulario(View view) {
        if (view.getId() == R.id.limparButton) {
            nomeEditText.setText("");
            emailEditText.setText("");
            telefoneEditText.setText("");
            notificacoesRadioGroup.clearCheck();
            notificacoesCheckBox.setChecked(false);
            nomeEditText.requestFocus();
        }
    }
}
