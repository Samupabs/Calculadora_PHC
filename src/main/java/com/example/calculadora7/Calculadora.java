//Clase calculadora. Se definen los atributos y métodos que necesita la calculadora para operar

package com.example.calculadora7;

public class Calculadora {
    private String primerNumero;
    private String segundoNumero;
    private String tipoDeCalculo;

    public Calculadora(String primerNumero, String segundoNumero, String tipoDeCalculo) {
        this.primerNumero = primerNumero;
        this.segundoNumero = segundoNumero;
        this.tipoDeCalculo = tipoDeCalculo;
    }

    @Override
    public String toString() {
        return "Calculadora{" +
                "primerNumero='" + primerNumero + '\'' +
                ", segundoNumero='" + segundoNumero + '\'' +
                ", tipoDeCalculo='" + tipoDeCalculo + '\'' +
                '}';
    }


    // Métodos ---------------------------------------------------------------------------------------------------------

    /**
     * Este método recibe el texto de la calculadora actual, comprueba sus dos números y operando solicitados y en base a ellos
     * hace una de las 4 operaciones disponibles delimitadas por el operando.
     *
     * Además le da un nuevo valor a primer número como el resultado para seguir operando con él
     * @param textoActual
     * @return "5+8=13"
     */

    public String calcular(String textoActual) {
        obtenerPrimerNumero(textoActual);
        obtenerOperandos(textoActual);
        obtenerSegundoNumero(textoActual);

        double numero = Double.parseDouble(primerNumero);
        double numero2 = Double.parseDouble(segundoNumero);

        try {

            String operador = this.tipoDeCalculo;
            double resultado;

            switch (operador) {
                case "+":
                    resultado = numero + numero2;
                    break;
                case "-":
                    resultado = numero - numero2;
                    break;
                case "*":
                    resultado = numero * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero / numero2;
                    } else {
                        return "0";
                    }
                    break;
                default:
                    return "0";
            }
            // Pasamos el resultado nuevamente a String para mostrarlo por pantalla
            String resultadoEscrito = Double.toString(resultado);
            setPrimerNumero(resultadoEscrito); // Esto es necesario para seguir haciendo operaciones sobre el resultado
            setTipoDeCalculo("");
            setSegundoNumero("");
            return resultadoEscrito;
        } catch (NumberFormatException e) {
            return "0";
        }
    }
    /**
     * Este método recibe el texto de la calculadora actual y devuelve el primer número de la misma.
     * @param textoActual
     * @return "9"
     */
    public String obtenerPrimerNumero(String textoActual) {
        textoActual = textoActual.trim();
        String primerNumero = "";  // Inicializamos la variable en blanco

        for (int i = 0; i < textoActual.length(); i++) {
            char caracter = textoActual.charAt(i);
            if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
                // Cuando encuentra un operador, detén el bucle
                break;
            }
            primerNumero += caracter;  // Agrega el carácter al final de la cadena
        }
        this.primerNumero = primerNumero;

        return primerNumero;
    }
    /**
     * Este método recibe el texto de la calculadora actual y devuelve el operando de la misma.
     * @param textoActual
     * @return "3+"
     */
    protected String obtenerOperandos(String textoActual) {

        char ultimoOperador = ' ';
        String respuesta;

        // Recorre el texto para encontrar el último operador
        for (int i = textoActual.length() - 1; i >= 0; i--) {
            char caracter = textoActual.charAt(i);
            if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
                ultimoOperador = caracter;
                break;
            }
        }

        respuesta = String.valueOf(ultimoOperador);
        this.tipoDeCalculo = respuesta;

        return respuesta;
    }

    /**
     * Este método recibe el texto de la calculadora actual y devuelve el segundo número de la misma.
     * @param textoActual
     * @return "5"
     */
    public String obtenerSegundoNumero(String textoActual) {

        String operando = obtenerOperandos(textoActual);
        String segundoNumeroModificado = "";  // Inicializamos la variable en blanco

        if (operando == null || operando.isEmpty() || operando.isBlank() ) {
            //Si no ponemos esto y solo hay un número lo va a coger tanto de primer numero como de segundo
        } else {
            textoActual = textoActual.trim();

            for (int i = textoActual.length() - 1; i >= 0; i--) {
                char caracter = textoActual.charAt(i);
                if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
                    // Cuando encuentra un operador, detien el bucle y recopila el segundo número
                    break;
                }
                segundoNumeroModificado = caracter + segundoNumeroModificado;  // Agrega el carácter al inicio de la cadena
            }
            this.segundoNumero = segundoNumeroModificado;
        }

        return segundoNumeroModificado;
    }
    /**
     * Este método recibe el texto de la calculadora y el operador que se desea utilizar y lo muestra por pantalla.
     * @param operador
     * @param textoActual
     * @return "10 + 4"
     */
    protected String confirmarOperador(String operador, String textoActual) {
        String respuesta;
        String primerNumero = obtenerPrimerNumero(textoActual);
        String existeOperador = obtenerOperandos(textoActual);
        String segundoNumero = obtenerSegundoNumero(textoActual);

        primerNumero = comprobarComilla(primerNumero);

        if (existeOperador == null && existeOperador.isEmpty() || existeOperador.isBlank()){ //Solo cambia el operador si no hay uno
            /*if (textoActual.isBlank() && operador.equals("-")) {
                respuesta = operador;
            } else*/ //Esto debía comprobar si querías introducir un número negativo, pero no me da tiempo a implementarlo porque
            // habría que cambiar mucho código en obtenerPrimerNumero(), obtenerOperandos() y obtenerSegundoNumero()
            if (textoActual.isEmpty()) { // Evita escribir los operandos sin numeros previos
                respuesta = "";
            }  else if (segundoNumero != null || !segundoNumero.isBlank() || !segundoNumero.isEmpty()) {
                respuesta = primerNumero + operador + segundoNumero;
            } else {
                respuesta = primerNumero + operador;
            }
        } else {
            respuesta = textoActual;
        }

        //System.out.println("1ro: "+primerNumero+" Op: "+operador+" 2do: "+segundoNumero);

        return respuesta;
    }

    /**
     * Este método recibe una cadena de texto para escribir decimales en los String y mostrarlos por pantalla.
     * @param textoActual
     * @return "1.2"
     */
    public String aniadirComilla(String textoActual) {

        String primerNumero = obtenerPrimerNumero(textoActual);
        String tipoDeCalculo = obtenerOperandos(textoActual);
        String segundoNumero = obtenerSegundoNumero(textoActual);
        String respuesta;

        if (primerNumero == null || primerNumero.isEmpty() || primerNumero.isBlank()) { // caso sin numero _ _ _
            //No hace nada
        } else if (tipoDeCalculo == null || tipoDeCalculo.isEmpty() || tipoDeCalculo.isBlank()) { // caso sin operando: 1 _ _

            if (primerNumero.contains(".")) {
                // Asumimos que tiene decimal así que no hacemos nada
            } else {
                primerNumero = primerNumero + "."; // Si no cambiamos el valor yy le añadimos el punto. El método comprobarComilla() se encarga de corregir los '.' sueltos al poner los operandos
                //primerNumero = comprobarComilla(primerNumero);
                setPrimerNumero(primerNumero);
            }

        } else if (segundoNumero == null || segundoNumero.isEmpty() || primerNumero.isBlank()) { // caso numero y operando, pero no segundo: 1 + _
            // No hacemos naada
        } else { // caso numero-op-numero2 1 + 2

            if (segundoNumero.contains(".")) {
                // Asumimos que tiene decimal así que no hacemos nada
            } else {
                segundoNumero = segundoNumero + "."; // Si no cambiamos el valor yy le añadimos el punto. El método comprobarComilla() se encarga de corregir los '.' sueltos al poner los operandos
                //segundoNumero = comprobarComilla(segundoNumero);
                setSegundoNumero(segundoNumero);

            }

        }
        respuesta = primerNumero + tipoDeCalculo + segundoNumero;
        return respuesta.trim();
    }
    /**
     * Este método recibe un número y comprueba si acaba en '.' para añadirle un 0.
     * @param numero
     * @return "54.0"
     */
    public String comprobarComilla (String numero){

        if (numero.endsWith(".")){
            numero += "0";
        }
        return numero;
    }

    public String borrarUnChar (String textoActual) {
        String nuevoTexto="";

        if (textoActual.length() > 0) {
            char[] caracteres = textoActual.toCharArray();
            char[] nuevosCaracteres = new char[caracteres.length - 1];

            for (int i = 0, j = 0; i < caracteres.length - 1; i++) {
                if (caracteres[i] != ' ') {
                    nuevosCaracteres[j] = caracteres[i];
                    j++;
                }
            }

            nuevoTexto = new String(nuevosCaracteres);
        }
        return nuevoTexto;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    // Los gettes he pensado en borrarlos ya que no se usan.

    public String getPrimerNumero() {
        return primerNumero;
    }

    public void setPrimerNumero(String primerNumero) {
        this.primerNumero = primerNumero;
    }

    public String getSegundoNumero() {
        return segundoNumero;
    }

    public void setSegundoNumero(String segundoNumero) {
        this.segundoNumero = segundoNumero;
    }

    public String getTipoDeCalculo() {
        return tipoDeCalculo;
    }

    public void setTipoDeCalculo(String tipoDeCalculo) {
        this.tipoDeCalculo = tipoDeCalculo;
    }

}
