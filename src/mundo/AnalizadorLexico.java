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

import java.util.ArrayList;

/**
 * Clase que modela un analizador léxico
 */

public class AnalizadorLexico {

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un código fuente dado
	 * 
	 * @param cod - código al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList extraerTokens(String cod) {
		Token token;
		ArrayList vectorTokens = new ArrayList();

		// El primer token se extrae a partir de posición cero
		int i = 0;
		// Ciclo para extraer todos los tokens
		while (i < cod.length()) {
			// Extrae el token de la posición i
			token = extraerSiguienteToken(cod, i);
			vectorTokens.add(token);
			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posición i, basándose en el
	 * Autómata
	 * 
	 * @param cod - código al cual se le va a extraer un token - codigo!=null
	 * @param i   - posición a partir de la cual se va a extraer el token - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken(String cod, int i) {
		Token token;

		// Intenta extraer un operador de asignación
		token = extraerOperadorAsignacion(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer un operador aritmetico
		token = extraerOperadorAritmetico(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer un operador lógico
		token = extraerOperadorLogico(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer un simbolo de abrir
		token = extraerSimboloAbrir(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer un simbolo de cerrar
		token = extraerSimboloCerrar(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer una palabra de ciclo
		token = extraerPalabraCiclo(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer una palabra de desición
		token = extraerPalabraDesicion(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer una palabra de clase
		token = extraerPalabraClase(cod, i);
		if (token != null) {
			return token;
		}

		// Intenta extraer un entero
		token = extraerEntero(cod, i);
		if (token != null) {
			return token;
		}

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;
	}

	public Token extraerOperadorAritmetico(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if ((cod.charAt(i) == 's' && cod.charAt(j) == 'm') || (cod.charAt(i) == 'r' && cod.charAt(j) == 't')
					|| (cod.charAt(i) == 'm' && cod.charAt(j) == 'p') || (cod.charAt(i) == 'd' && cod.charAt(j) == 'v')
					|| (cod.charAt(i) == 'm' && cod.charAt(j) == 'd')) {
				lex = cod.substring(i, j + 1);
				Token token = new Token(lex, Token.OPERADORARITMETICO, j + 1);
				return token;
			}
		}
		return null;
	}

	public Token extraerOperadorRelacional(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if ((cod.charAt(i) == 'm' && cod.charAt(j) == 'y') || (cod.charAt(i) == 'm' && cod.charAt(j) == 'n')
					|| (cod.charAt(i) == 'i' && cod.charAt(j) == 'g')) {
				j += 1;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		} else if ((j + 1 < cod.length())
				&& (cod.charAt(i) == 'x' && cod.charAt(j) == 'i' && cod.charAt(j + 1) == 'g')) {
			j += 2;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
			return token;
		}
		return null;
	}

	public Token extraerOperadorLogico(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == 'ÿ') || (cod.charAt(i) == 23) || (cod.charAt(i) == '×')) {
			lex = cod.substring(i);
			Token token = new Token(lex, Token.OPERADORLOGICO, i + 1);
			return token;
		}
		return null;
	}

	public Token extraerOperadorAsignacion(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j + 2 < cod.length()) {
			if (((cod.charAt(i) == 's' && cod.charAt(j) == 'm') || (cod.charAt(i) == 'r' && cod.charAt(j) == 't')
					|| (cod.charAt(i) == 'm' && cod.charAt(j) == 'p') || (cod.charAt(i) == 'd' && cod.charAt(j) == 'v')
					|| (cod.charAt(i) == 'm' && cod.charAt(j) == 'd'))
					&& (cod.charAt(j + 1) == 'i' && cod.charAt(j + 2) == 'g')) {
				j += 3;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORASIGNACION, j);
				return token;
			}
		}
		return null;
	}

	public Token extraerSimboloAbrir(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == '«') || (cod.charAt(i) == '^') || (cod.charAt(i) == '|')) {
			lex = cod.substring(i);
			Token token = new Token(lex, Token.SIMBOLOABRIR, i + 1);
			return token;
		}
		return null;
	}

	public Token extraerSimboloCerrar(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == '»') || (cod.charAt(i) == '/') || (cod.charAt(i) == '┤')) {
			lex = cod.substring(i);
			Token token = new Token(lex, Token.SIMBOLOCERRAR, i + 1);
			return token;
		}
		return null;
	}

	public Token extraerSeparadorSentencia(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == '┘') || (cod.charAt(i) == '¤') || (cod.charAt(i) == 'ð') || (cod.charAt(i) == '¦')) {
			lex = cod.substring(i);
			Token token = new Token(lex, Token.SIMBOLOCERRAR, i + 1);
			return token;
		}
		return null;
	}

	public Token extraerPalabraCiclo(String cod, int i) {
		int j = i + 1;
		String lex;
		if ((j < cod.length()) && cod.charAt(i) == 'r' && cod.charAt(j) == 'p') {
			lex = cod.substring(i, j + 1);
			Token token = new Token(lex, Token.CICLO, j + 1);
			return token;
		}
		return null;
	}

	public Token extraerPalabraDesicion(String cod, int i) {
		int j = i + 1;
		String lex;
		if ((j + 1 < cod.length()) && (cod.charAt(i) == 'k' && cod.charAt(j) == 'h' && cod.charAt(j + 1) == 'e')) {
			j += 2;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.DECISION, j);
			return token;
		} else if ((j < cod.length()) && (cod.charAt(i) == 's' && cod.charAt(j) == 'o')) {
			j += 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.DECISION, j);
			return token;
		}
		return null;
	}

	public Token extraerPalabraClase(String cod, int i) {
		int j = i + 1;
		String lex;
		if ((j + 1 < cod.length()) && (cod.charAt(i) == 'b' && cod.charAt(j) == 'o' && cod.charAt(j + 1) == 'x')) {
			j += 2;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.CICLO, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un entero de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod - código al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un entero
	 *            - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posición dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posición del
	 *         siguiente lexema.
	 */
	public Token extraerEntero(String cod, int i) {
		int j;
		String lex;
		if (cod.charAt(i) == '#') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.ENTERO, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador aditivo de la cadena cod a partir de la posición
	 * i, basándose en el Autómata
	 * 
	 * @param cod - código al cual se le va a intentar extraer el operador aditivo -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer el
	 *            operador aditivo - 0<=i<codigo.length()
	 * @return el token operador aditivo o NULL, si el token en la posición dada no
	 *         es un operador aditivo.El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerOperadorAditivo(String cod, int i) {
		if (cod.charAt(i) == '+' || cod.charAt(i) == '-') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORADITIVO, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un operador de asignación de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 * 
	 * @param cod - código al cual se le va a intentar extraer el operador de
	 *            asignación - codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer el
	 *            operador de asingación - 0<=i<codigo.length()
	 * @return el token operador asignación o NULL, si el token en la posición dada
	 *         no es un operador de asignación. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */

//	public Token extraerOperadorAsignacion(String cod, int i) {
//		if (cod.charAt(i) == '<') {
//			int j = i + 1;
//			if (j < cod.length() && (cod.charAt(j) == '<' || cod.charAt(j) == '-')) {
//				j++;
//				if (j < cod.length() && cod.charAt(j) == '<') {
//					j++;
//					String lex = cod.substring(i, j);
//					Token token = new Token(lex, Token.OPERADORASIGNACION, j);
//					return token;
//				}
//			}
//		}
//		return null;
//	}

	/**
	 * Intenta extraer un identificador de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod - código al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posición dada no es
	 *         un identificador. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */

	public Token extraerIdentificador(String cod, int i) {
		if (cod.charAt(i) == '_') {
			int j = i + 1;
			while (j < cod.length() && esLetra(cod.charAt(j)))
				j++;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.IDENTIFICADOR, j);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
	 * Antes de utilizar este método, debe haberse intentado todos los otros métodos
	 * para los otros tipos de token
	 * 
	 * @param cod - código al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la
	 *         posición del siguiente lexema.
	 * 
	 */
	public Token extraerNoReconocido(String cod, int i) {
		String lexema = cod.substring(i, i + 1);
		int j = i + 1;
		Token token = new Token(lexema, Token.NORECONOCIDO, j);
		return token;
	}

	/**
	 * Determina si un carácter es un dígito
	 * 
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un dígito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carácter es una letra
	 * 
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

}
