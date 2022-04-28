/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Dise�o original por: Leonardo A. Hern�ndez R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

/**
 * Clase que modela un token
 */

public class Token {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * Constantes para modelar los posibles tipos de token que se van a analizar
	 */
	final public static String NORECONOCIDO = "No reconocido";
	
	final public static String OPERADORARITMETICO = "Operador aritmetico";
	final public static String OPERADORRELACIONAL = "Operador relacional";
	final public static String OPERADORLOGICO = "Operador l�gico";
	final public static String OPERADORASIGNACION = "Operador de asignaci�n";
	final public static String SIMBOLOABRIR = "S�mbolo de abrir";
	final public static String SIMBOLOCERRAR = "S�mbolo de cerrar";
	final public static String SEPARADORSENTENCIA = "Separador de sentencia";
	final public static String TERMINALINICIAL = "Simbolo de iniciar o terminar";
	
	final public static String CICLO = "Palabra reservada para ciclo";
	final public static String DECISION = "Palabra reservada para decisi�n";
	final public static String CLASE = "Palabra reservada para clase";
	
	final public static String NOMBREVARIABLE = "Nombre de variable";
	final public static String NOMBREMETODO = "Nombre de m�todo";
	final public static String NOMBRECLASE = "Nombre de clase";
	
	final public static String NUMEROENTERO = "N�mero entero";
	final public static String NUMEROFLOAT = "N�mero float";
	final public static String NUMERODOUBLE = "N�mero double";
	final public static String NUMEROLONG = "N�mero long";
	
	final public static String CADENA = "Cadena de caracteres";
	final public static String SALTOLINEA = "Salto de linea";
	final public static String CARACTER = "Un caracter";
	
	final public static String PALABRAINT = "Palabra Int";
	final public static String PALABRAFLOAT = "Palabra float";
	final public static String PALABRALONG = "Palabra long";
	final public static String PALABRASTRING = "Palabra String";
	final public static String PALABRACHAR = "Palabra char";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Lexema
	 */
	private String lexema;

	/**
	 * tipo
	 */
	private String tipo;

	/**
	 * posici�n del siguiente lexema
	 */
	private int indiceSiguiente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * Constructor de un token
	 * 
	 * @param elLexema          - cadena - laCadena != null
	 * @param elTipo            - tipo del token - elTipo != null
	 * @param elIndiceSiguiente - posici�n del siguiente token - laPosicionSiguiente
	 *                          > 0
	 */
	public Token(String elLexema, String elTipo, int elIndiceSiguiente) {
		lexema = elLexema;
		tipo = elTipo;
		indiceSiguiente = elIndiceSiguiente;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Entrega la informaci�n del token
	 * 
	 * @return Descripci�n del token
	 */
	public String darDescripcion() {
		return "Token: " + lexema + "     Tipo: " + tipo + "     �ndice del siguiente: " + indiceSiguiente;
	}

	/**
	 * M�todo que retorna el lexema del token
	 * 
	 * @return el lexema del token
	 */
	public String darLexema() {
		return lexema;
	}

	/**
	 * M�todo que retorna la posici�n del siguiente lexema
	 * 
	 * @return posici�n del siguiente token
	 */
	public int darIndiceSiguiente() {
		return indiceSiguiente;
	}

	/**
	 * M�todo que retorna el tipo del token
	 * 
	 * @return el tipo del token
	 */
	public String darTipo() {
		return tipo;
	}

}
