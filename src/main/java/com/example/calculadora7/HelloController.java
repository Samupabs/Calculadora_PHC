package com.example.calculadora7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    protected Calculadora calculadora = new Calculadora("","","");

    @FXML
    private Label panelCalculadora;

    // Botones ---------------------------------------------------------------------------------------------------------

    /**
     * Este método es el encargado de seleccionar el botón numérico que clica el usuario y añadir el número solicitado
     * a la operación
     * @param evento
     */
    @FXML
    protected void procesarNumeros(ActionEvent evento){
        Button boton = (Button) evento.getSource();
        String numeroDelBoton = boton.getText();
        panelCalculadora.setText(panelCalculadora.getText() + numeroDelBoton);
    }

    /**
     * Este método es el encargado de seleccionar el operador que clica el usuario y añadirlo a la operación
     * @param evento
     */
    @FXML
    protected void procesarOperandos(ActionEvent evento){
        Button boton = (Button) evento.getSource();
        String operadorDelBoton = boton.getText();

        panelCalculadora.setText(calculadora.confirmarOperador(operadorDelBoton,panelCalculadora.getText()).trim());
    }
    /**
     * Este método es el encargado de añadir una comilla a nuestros números si queremos que tenga decimales
     */
    @FXML
    protected void botonComilla() {
        String textoActual = panelCalculadora.getText();
        panelCalculadora.setText(calculadora.aniadirComilla(textoActual));
    }
    /**
     * Este método es el botón que borra tod0 lo escrito por pantalla y 'reinicia' la calculadora
     */
    @FXML
    protected void botonBorrarTODO() {
        calculadora.setPrimerNumero("");
        calculadora.setTipoDeCalculo("");
        calculadora.setSegundoNumero("");
        panelCalculadora.setText("");
    }

    /**
     * Este método es el botón igual que termina por mostrar en pantalla el resultado de las variables solicitadas por el
     * usuario
     */
    @FXML
    protected void botonIgual() {

        try {
            String textoActual = panelCalculadora.getText().trim();
            String resultado = calculadora.calcular(textoActual); // Calculamos en base a la calculadora
            panelCalculadora.setText(resultado);
        } catch (Exception e){
            //Este tryCatch es necesario para que no pete si le das a '=' antes de meter comandos. Simplemente no hace nada y no se cierra el programa
        }
    }

    /**
     * Este botón coge el texto actual, lo mete en un array de char y borra el último char actual
     */
    @FXML
    protected void botonBorrar() {
        String textoActual = panelCalculadora.getText();
            panelCalculadora.setText(calculadora.borrarUnChar(textoActual));

    }
}