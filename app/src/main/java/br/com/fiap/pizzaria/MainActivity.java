package br.com.fiap.pizzaria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.fiap.pizzaria.model.Pedido;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.usuario)
    TextView usuario;

    //Pizza
    @BindView(R.id.cbBacon)
    CheckBox cbBacon;

    @BindView(R.id.cbAtum)
    CheckBox cbAtum;

    @BindView(R.id.cbPalmito)
    CheckBox cbPalmito;

    @BindView(R.id.cbPortuguesa)
    CheckBox cbPortuguesa;

    @BindView(R.id.rgTamanho)
    RadioGroup rgTamanho;

    @BindView(R.id.spPagamentos)
    Spinner spPagamentos;

    Pedido pedido = new Pedido();

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        username = getIntent().getStringExtra("USUARIO");
        usuario.setText(username);
        setListenerCheckBox(cbAtum);
        setListenerCheckBox(cbPalmito);
        setListenerCheckBox(cbPortuguesa);
        setListenerCheckBox(cbBacon);
    }

    private void setListenerCheckBox(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pedido.addSabor(checkBox.getText().toString());
                } else {
                    pedido.removerSabor(checkBox.getText().toString());
                }
            }
        });
    }

    @OnClick(R.id.btFecharPedido)
    public void btFecharPedido() {

        pedido.setCliente(username);
        pedido.setTamanho(getTamanhoSelecionado());
        pedido.setFormaPagamento(spPagamentos.getSelectedItem().toString());

        Intent i = new Intent(This, ConfirmarPedidoActivity.class);
        i.putExtra("PEDIDO", pedido);
        startActivity(i);
    }

    public String getTamanhoSelecionado() {
        return ((RadioButton) findViewById(rgTamanho.getCheckedRadioButtonId())).getText().toString();
    }
}

