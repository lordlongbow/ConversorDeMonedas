package com.example.conversordemoneda;

import android.app.Application;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private Context contexto;
    private MutableLiveData<Double> Convertido =  null;

    private String mensaje = "";

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<Double> getConvertido(){
        if(Convertido == null){
            this.Convertido = new MutableLiveData<>();
        }
        return Convertido;
    }


    public void convertirDollarEuro(String dollares){
        double valorEuro = 0.93;
        if(dollares.isEmpty() || dollares=="" || dollares==" "){
            mensaje= "Debes escribir un número";
            Toast.makeText(contexto, mensaje,Toast.LENGTH_SHORT).show();
        }else{
            double valorDollar = Double.parseDouble(dollares);

            Convertido.setValue(valorDollar*valorEuro);
        }

    }
    public void convertirEuroDollar(String euros){
        double valorDollar= 1.08;
        if(euros.isEmpty() || euros=="" || euros ==" "){
            mensaje= "Debes escribir un número";
            Toast.makeText(contexto, mensaje,Toast.LENGTH_SHORT).show();
        }else{
            double valorEuro = Double.parseDouble(euros);
            Convertido.setValue(valorEuro*valorDollar);
        }

    }

    public void convertirMoneda(String euros, String dolares, RadioButton rbDolarEuro, RadioButton rbEuroDolar ){
            if(rbDolarEuro.isChecked()){
                 convertirDollarEuro(dolares);
            } else{
                 convertirEuroDollar(euros);
            }
    }

    public void habilitar(RadioButton rbDolarEuro, RadioButton rbEuroDolar, EditText etdolar, EditText etEuro){
        if(rbDolarEuro.isChecked()){
           etEuro.setEnabled(false);
           etdolar.setEnabled(true);
        } else if(rbEuroDolar.isChecked()){
            etEuro.setEnabled(true);
            etdolar.setEnabled(false);
        }else{
            Toast.makeText(contexto,mensaje,Toast.LENGTH_SHORT).show();
        }
    }
}
