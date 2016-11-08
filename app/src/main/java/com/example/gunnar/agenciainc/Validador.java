package com.example.gunnar.agenciainc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextWatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Max
 */

public abstract class Validador extends Context implements TextWatcher {

    /**
     * public @interface ConfirmEmail {
     *
     * @StringRes int messageResId()   default -1;
     * String message()                default "Emails don't match";
     * int sequence()                  default -1;
     * }
     */

    public void mailV(String email) {
        //boolean isValid = false;
        String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.find()) {
            System.out.println("mail correcto");
        } else {
            System.out.println("error");
            //JOptionPane.showMessageDialog(null, "¡error!");
            AlertDialog alerta = new AlertDialog.Builder(this).create(); //Aqui me marca el siguiente error The constructor AlertDialog.Builder(new View.OnClickListener(){}) is undefined

            alerta.setTitle("Alert");

            alerta.setMessage("correo invalido");

            alerta.setButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    return;

                }
            });

            alerta.show();
        }
    }


    public void nombreV(String nombre) {
        boolean respuesta = false;
        if ((nombre).matches("([a-z]|[A-Z]|\\s)+")) {
            respuesta = true;
        } else {

            System.out.println("error");
            //JOptionPane.showMessageDialog(null, "¡error!");
            final AlertDialog alerta = new AlertDialog.Builder(this).create();
            alerta.setTitle("Alert");

            alerta.setMessage("nombre invalido");

            alerta.setButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    alerta.dismiss();
                    return;
                }
            });

            alerta.show();
        }
    }

    public boolean placaV(String placa) {
        String expression = "(^[A-Za-z]{0,4})*([0-9]{3,})$";//cmo maximo 4 char letra y despues como minimo 3 char numero
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(placa);
        if (matcher.find()) {
            System.out.println("correcto");
            return true;
        } else {
            return false;
        }
    }

    public boolean precioV(String precio) {
        String expression = "([0-9]{10,})$";//precios de V solo numeros
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(precio);
        if (matcher.find()) {
            System.out.println("correcto");
            return true;
        } else {
            return false;
        }
    }

    public boolean anioV(String anio) {
        String exprecion = "^[0-9]";
        Pattern p = Pattern.compile(exprecion);
        Matcher m = p.matcher(anio);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }



        /*private final EditText editText;

        public Validador(EditText editText) {
            this.editText = editText;
        }

        public abstract void validate(EditText editText, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = editText.getText().toString();
            validate(editText, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */
}


/**
 * @Override final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care
 */



