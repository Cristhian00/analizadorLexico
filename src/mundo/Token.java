/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseño original por: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
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
	final public static String OPERADORLOGICO = "Operador lógico";
	final public static String OPERADORASIGNACION = "Operador de asignación";
	final public static String SIMBOLOABRIR = "Símbolo de abrir";
	final public static String SIMBOLOCERRAR = "Símbolo de cerrar";
	final public static String SEPARADORSENTENCIA = "Separador de sentencia";
	final public static String TERMINALINICIAL = "Simbolo de iniciar o terminar";
	
	final public static String CICLO = "Palabra reservada para ciclo";
	final public static String DECISION = "Palabra reservada para decisión";
	final public static String CLASE = "Palabra reservada para clase";
	
	final public static String NOMBREVARIABLE = "Nombre de variable";
	final public static String NOMBREMETODO = "Nombre de método";
	final public static String NOMBRECLASE = "Nombre de clase";
	
	final public static String NUMEROENTERO = "Número entero";
	final public static String NUMEROFLOAT = "Número float";
	final public static String NUMERODOUBLE = "Número double";
	final public static String NUMEROLONG = "Número long";
	
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
	 * posición del siguiente lexema
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
	 * @param elIndiceSiguiente - posición del siguiente token - laPosicionSiguiente
	 *                          > 0
	 */
	public Token(String elLexema, String elTipo, int elIndiceSiguiente) {
		lexema = elLexema;
		tipo = elTipo;
		indiceSiguiente = elIndiceSiguiente;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Entrega la información del token
	 * 
	 * @return Descripción del token
	 */
	public String darDescripcion() {
		return "Token: " + lexema + "     Tipo: " + tipo + "     Índice del siguiente: " + indiceSiguiente;
	}

	/**
	 * Método que retorna el lexema del token
	 * 
	 * @return el lexema del token
	 */
	public String darLexema() {
		return lexema;
	}

	/**
	 * Método que retorna la posición del siguiente lexema
	 * 
	 * @return posición del siguiente token
	 */
	public int darIndiceSiguiente() {
		return indiceSiguiente;
	}

	/**
	 * Método que retorna el tipo del token
	 * 
	 * @return el tipo del token
	 */
	public String darTipo() {
		return tipo;
	}

}
