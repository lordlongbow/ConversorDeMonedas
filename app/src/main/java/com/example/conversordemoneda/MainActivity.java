package com.example.conversordemoneda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //recupero los componentes con los que voy a trabajar
    private MainActivityViewModel mv;
    private EditText etDollar, etEuro, etConvertido;
    private Button btConvertir;
    private RadioButton rbEuroDollar, rbDollarEuro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo el viewModel
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        //Seteo las variables de los componentes con sus componentes ID
        //variables y componentes EditText
        this.etDollar = findViewById(R.id.etDolares);
        this.etEuro = findViewById(R.id.etEuros);
        this.etConvertido = findViewById(R.id.etCambio);
        //Variables y Componentes Radio Buttons
        this.rbDollarEuro = findViewById(R.id.rBDolarEuro);
        this.rbEuroDollar = findViewById(R.id.rBEuroDolar);
        //Variable y Componentes Boton
        this.btConvertir = findViewById(R.id.btnConvertir);

        //realizo un Observer sobre el valor del convertido en el viewModel
        mv.getConvertido().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                etConvertido.setText(aDouble + "");
            }
        });

        rbDollarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mv.habilitar(rbDollarEuro,rbEuroDollar,etDollar,etEuro);
            }
        });
        rbEuroDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.habilitar(rbDollarEuro,rbEuroDollar,etDollar,etEuro);
            }
        });


        //Escuchar al boton convertir
        btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.convertirMoneda(etEuro.getText().toString(),etDollar.getText().toString(),rbDollarEuro, rbEuroDollar );
            }
        });

    }
}