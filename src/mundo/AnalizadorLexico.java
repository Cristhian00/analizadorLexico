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

		// 1. Intenta extraer una cadena
		token = extraerCadena(cod, i);
		if (token != null) {
			return token;
		}

		// 2. Intenta extraer un operador de asignación
		token = extraerOperadorAsignacion(cod, i);
		if (token != null) {
			return token;
		}

		// 3. Intenta extraer un operador aritmetico
		token = extraerOperadorAritmetico(cod, i);
		if (token != null) {
			return token;
		}

		// 4. Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if (token != null) {
			return token;
		}

		// 5. Intenta extraer un operador lógico
		token = extraerOperadorLogico(cod, i);
		if (token != null) {
			return token;
		}

		// 6. Intenta extraer un simbolo de abrir
		token = extraerSimboloAbrir(cod, i);
		if (token != null) {
			return token;
		}

		// 7. Intenta extraer un simbolo de cerrar
		token = extraerSimboloCerrar(cod, i);
		if (token != null) {
			return token;
		}

		// 8. Intenta extraer un simbolo de separador de sentencia
		token = extraerSeparadorSentencia(cod, i);
		if (token != null) {
			return token;
		}

		// 9. Intenta extraer un simbolo de iniciar o terminar
		token = extraerSimboloTerminalInicial(cod, i);
		if (token != null) {
			return token;
		}

		// 10. Intenta extraer una palabra de ciclo
		token = extraerPalabraCiclo(cod, i);
		if (token != null) {
			return token;
		}

		// 11. Intenta extraer una palabra de desición
		token = extraerPalabraDesicion(cod, i);
		if (token != null) {
			return token;
		}

		// 12. Intenta extraer una palabra de clase
		token = extraerPalabraClase(cod, i);
		if (token != null) {
			return token;
		}

		// 13. Intenta extraer un nombre de variable
		token = extraerNombreVariable(cod, i);
		if (token != null) {
			return token;
		}

		// 14. Intenta extraer un nombre de método
		token = extraerNombreMetodo(cod, i);
		if (token != null) {
			return token;
		}

		// 15. Intenta extraer un nombre de clase
		token = extraerNombreClase(cod, i);
		if (token != null) {
			return token;
		}

		// 16. Intenta extraer un número entero
		token = extraerNumeroEntero(cod, i);
		if (token != null) {
			return token;
		}

		// 17. Intenta extraer un número float
		token = extraerNumeroFloat(cod, i);
		if (token != null) {
			return token;
		}

		// 18. Intenta extraer un número double
		token = extraerNumeroDouble(cod, i);
		if (token != null) {
			return token;
		}

		// 19. Intenta extraer un caracter
		token = extraerCaracter(cod, i);
		if (token != null) {
			return token;
		}

		// 20. Intenta extraer palabra int
		token = extraerPalabraInt(cod, i);
		if (token != null) {
			return token;
		}

		// 21. Intenta extraer palabra float
		token = extraerPalabraFloat(cod, i);
		if (token != null) {
			return token;
		}

		// 22. Intenta extraer palabra long
		token = extraerPalabraLong(cod, i);
		if (token != null) {
			return token;
		}

		// 23. Intenta extraer palabra String
		token = extraerPalabraString(cod, i);
		if (token != null) {
			return token;
		}

		// 24. Intenta extraer palabra char
		token = extraerPalabraChar(cod, i);
		if (token != null) {
			return token;
		}

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;
	}

		/**
	 * Extraer un caracter de tipo operador aritmetic de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - operador aritmético sea mas, menos, por o dividido
	 * Sea sm para suma, rt para resta, mp para multiplicacion, md para modulo y dv para division
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el operador lógico identificado
	 * 
	 */
	public Token extraerOperadorAritmetico(String cod, int i) {
		int j = i + 1;
		String lex;
		boolean ban = false;
		if (j < cod.length()) {
			if (cod.charAt(i) == 's') {
				if (cod.charAt(j) == 'm') {
					ban = true;
				}
			} else if (cod.charAt(i) == 'r') {
				if (cod.charAt(j) == 't') {
					ban = true;
				}
			} else if (cod.charAt(i) == 'd') {
				if (cod.charAt(j) == 'v') {
					ban = true;
				}
			} else if (cod.charAt(i) == 'm') {
				if ((cod.charAt(j) == 'p') || (cod.charAt(j) == 'd')) {
					ban = true;
				}
			}
			if (ban) {
				j += 1;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORARITMETICO, j);
				return token;
			}
		}
		return null;
	}
	
	/**
	 * Extraer un caracter de tipo operador relacional de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - operador relacional sea mayor, menor, igual o diferente
	 *Sea my para >, mn para <, sea ig para = y ×ig para ¡=
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el operador relacional identificado
	 * 
	 */
	public Token extraerOperadorRelacional(String cod, int i) {
		int j = i + 1;
		String lex;
		boolean ban = false;

		if (j + 1 < cod.length()) {
			if (cod.charAt(i) == '×') {
				if (cod.charAt(j) == 'i') {
					if (cod.charAt(j + 1) == 'g') {
						j += 2;
						ban = true;
					}
				}
			}
		}
		if ((ban == false) && (j < cod.length())) {
			if (cod.charAt(i) == 'i') {
				if (cod.charAt(j) == 'g') {
					ban = true;
					j += 1;
				}
			} else if (cod.charAt(i) == 'm') {
				if ((cod.charAt(j) == 'y') || (cod.charAt(j) == 'n')) {
					ban = true;
					j += 1;
				}
			}
		}
		if (ban) {
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un caracter de tipo operador lógico de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - operador logico sea y, o no
	 *Sea ÿ para el &&, ? para el || y × para el no
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el operador lógico identificado
	 * 
	 */
	public Token extraerOperadorLogico(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == 'ÿ') || (cod.charAt(i) == '?') || (cod.charAt(i) == '×')) {
			lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.OPERADORLOGICO, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un caracter de tipo operador de asignación la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - operador de asignacion sea mas igual, menos igual
	 * por igual, dividir igual, modulo igual
	 *Sea smig para mas igual, rtig para menos igual, mpig para por igual, 
	 *mdig para modulo igual y dvig para dividir igual
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el operador de asignación identificado
	 * 
	 */

	public Token extraerOperadorAsignacion(String cod, int i) {
		int j = i + 1;
		String lex;
		boolean ban = false;
		if (j + 2 < cod.length()) {
			if (cod.charAt(i) == 's') {
				if (cod.charAt(j) == 'm') {
					ban = true;
				}
			} else if (cod.charAt(i) == 'r') {
				if (cod.charAt(j) == 't') {
					ban = true;
				}
			} else if (cod.charAt(i) == 'd') {
				if (cod.charAt(j) == 'v') {
					ban = true;
				}
			} else if (cod.charAt(i) == 'm') {
				if ((cod.charAt(j) == 'p') || (cod.charAt(j) == 'd')) {
					ban = true;
				}
			}
			if (ban) {
				j += 1;
				if (cod.charAt(j) == 'i') {
					if (cod.charAt(j + 1) == 'g') {
						j += 2;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADORASIGNACION, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de tipo simbolo de abrir de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - simbolo de abrir sea {, ( y [
	 *Sea « para {, \\ para ( y | para [
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el simbolo de abrir identificado
	 */

	public Token extraerSimboloAbrir(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == '«') || (cod.charAt(i) == '\\') || (cod.charAt(i) == '|')) {
			lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.SIMBOLOABRIR, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un caracter de tipo simbolo de cerrar de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - simbolo de abrir sea }, ) y ]
	 *Sea » para {, / para ) y ┤ para ]
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el simbolo de abrir identificado
	 */
	public Token extraerSimboloCerrar(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == '»') || (cod.charAt(i) == '/') || (cod.charAt(i) == '┤')) {
			lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.SIMBOLOCERRAR, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un caracter de tipo Separador de sentencia de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - simbolo de abrir sea ; . : , 
	 *Sea ┘ para ;, ¤ para . , ð para , y ¦ para :
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el separador de sentencia
	 */
	public Token extraerSeparadorSentencia(String cod, int i) {
		String lex;
		if ((cod.charAt(i) == '┘') || (cod.charAt(i) == '¤') || (cod.charAt(i) == 'ð') || (cod.charAt(i) == '¦')) {
			lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.SEPARADORSENTENCIA, i + 1);
			return token;
		}
		return null;
	}
	/**
	 * Extraer un caracter de tipo Simbolo de terminal o inicial de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - simbolo de inicial o terminal
	 * Como lo es ~
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el separador de sentencia
	 */
	public Token extraerSimboloTerminalInicial(String cod, int i) {
		String lex;
		if (cod.charAt(i) == '~') {
			lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.TERMINALINICIAL, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra utilizada para el ciclo la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra de ciclo
	 *Sea rp la palabra utilizada para realizar un ciclo
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra utilizada para realizar un ciclo 
	 */
	public Token extraerPalabraCiclo(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if (cod.charAt(i) == 'r') {
				if (cod.charAt(j) == 'p') {
					lex = cod.substring(i, j + 1);
					Token token = new Token(lex, Token.CICLO, j + 1);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra utilizada para la desicion la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra para realizar una desicion
	 *Sea khe la palabra utilizada para realizar un ciclo reemplazando el if en java 
	 * y en caso que no se cumpla so la palabra utilizada para reemplazar el else 
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra utilizada para realizar una desicion
	 */
	public Token extraerPalabraDesicion(String cod, int i) {
		int j = i + 1;
		String lex;
		boolean ban = false;
		if (j + 1 < cod.length()) {
			if (cod.charAt(i) == 'k') {
				if (cod.charAt(j) == 'h') {
					if (cod.charAt(j + 1) == 'e') {
						j += 2;
						ban = true;
					}
				}
			}
		}
		if ((ban == false) && (j < cod.length())) {
			if (cod.charAt(i) == 's') {
				if (cod.charAt(j) == 'o') {
					j += 1;
					ban = true;
				}
			}
		}
		if (ban) {
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.DECISION, j);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra que nombra las clases de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra usada para nombrar las clases
	 *Sea box la palabra utilizada para nombrar una clase
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra utilizada para nombrar una clase
	 */
	public Token extraerPalabraClase(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j + 1 < cod.length()) {
			if (cod.charAt(i) == 'b') {
				if (cod.charAt(j) == 'o') {
					if (cod.charAt(j + 1) == 'x') {
						j += 2;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.CLASE, j);
						return token;
					}
				}
			}
		}
		return null;
	}
	/**
	 * Extraer un caracter del nombre de las variables de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - nombre de la variable
	 * Para nombrar una variable debe de empezar con la primera letra en mayuscula
	 * y termine en guion bajo
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la variable nombrada
	 */
	public Token extraerNombreVariable(String cod, int i) {
		if ((cod.length() >= 2) && (esMayuscula(cod.charAt(i)))) {
			int j = i + 1;
			while ((j < cod.length()) && ((esLetra(cod.charAt(j))) || (esDigito(cod.charAt(j))))) {
				j++;
			}
			if ((j < cod.length()) && (cod.charAt(j) == '_')) {
				j += 1;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.NOMBREVARIABLE, j);
				return token;
			}
		}
		return null;
	}
	/**
	 * Extraer un caracter del nombre de los metodos de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - nombre del metodo
	 * Para nombrar un metodo debe de estar todo en mayuscula
	 * y minimo debe de tener 3 letras
	 * y termine en guion bajo
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el metodo nombrado
	 */
	public Token extraerNombreMetodo(String cod, int i) {
		int cont = 0;
		if ((cod.length() >= 3) && (esMayuscula(cod.charAt(i)))) {
			cont++;
			int j = i + 1;
			while (j < cod.length() && esMayuscula(cod.charAt(j))) {
				cont++;
				j++;
			}
			if (cont >= 3) {
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.NOMBREMETODO, j);
				return token;
			}
		}
		return null;
	}
 
	/**
	 * Extraer un caracter del nombre de las clases de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - nombre de la clase
	 * Para nombrar una clase debe de comenzar en # y todo debe de estar
	 * en minúscula y minimo debe de tener 3 letras
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la clase nombrada
	 */
	public Token extraerNombreClase(String cod, int i) {
		int cont = 0;
		if ((cod.length() >= 3) && (cod.charAt(i) == '#')) {
			int j = i + 1;
			while (j < cod.length() && esMinuscula(cod.charAt(j))) {
				cont++;
				j++;
			}
			if (cont >= 3) {
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.NOMBRECLASE, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un número entero de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - numero entero
	 * Para que un número entero pueda ser interpretado debe de empezar por @ 
	 * tener al menos un dígito seguido de cero o más digitos y finaliza con una e
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el numero entero asignado
	 */
	public Token extraerNumeroEntero(String cod, int i) {
		int j;
		String lex;
		if (cod.charAt(i) == '@') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));
				if (j < cod.length() && cod.charAt(j) == 'e') {
					j += 1;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.NUMEROENTERO, j);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un número flotante de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - numero flotante
	 * Para que un número flotante pueda ser interpretado debe de empezar por ©
	 * tener al menos un dígito seguido de cero o más digitos, luego un punto seguido
	 * de al menos un dígito y luego cero o más dígitos
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el numero flotante asignado
	 */
	public Token extraerNumeroFloat(String cod, int i) {
		int j;
		String lex;
		if (cod.charAt(i) == '©') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));
				if ((j < cod.length()) && (cod.charAt(j) == '.')) {
					j++;
					if (j < cod.length() && esDigito(cod.charAt(j))) {
						do {
							j++;
						} while (j < cod.length() && esDigito(cod.charAt(j)));
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.NUMEROFLOAT, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un número doble de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - numero doble
	 * Para que un número doble pueda ser interpretado debe de empezar por ┌
	 * tener al menos un dígito seguido de cero o más digitos, luego un punto seguido
	 * de al menos un dígito y luego cero o más dígitos
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el numero doble asignado
	 */
	public Token extraerNumeroDouble(String cod, int i) {
		int j;
		String lex;
		if (cod.charAt(i) == '┌') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));
				if ((j < cod.length()) && (cod.charAt(j) == '.')) {
					j++;
					if (j < cod.length() && esDigito(cod.charAt(j))) {
						do {
							j++;
						} while (j < cod.length() && esDigito(cod.charAt(j)));
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.NUMERODOUBLE, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un número largo de la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - numero largo
	 * Para que un número largo pueda ser interpretado debe de empezar por └
	 * tener al menos un dígito seguido de cero o más digitos, luego un punto seguido
	 * de al menos un dígito y luego cero o más dígitos
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el numero largo asignado
	 */
	public Token extraerNumeroLong(String cod, int i) {
		int j;
		String lex;
		if (cod.charAt(i) == '└') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));
				if ((j < cod.length()) && (cod.charAt(j) == '.')) {
					j++;
					if (j < cod.length() && esDigito(cod.charAt(j))) {
						do {
							j++;
						} while (j < cod.length() && esDigito(cod.charAt(j)));
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.NUMEROLONG, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un nombramiento de una cadena de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - cadena de caracteres
	 * Para que una cadena de caracteres pueda ser interpretado debe de empezar 
	 * y terminar con un guión por ejemplo -hola-
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la cadena asignada
	 */
	public Token extraerCadena(String cod, int i) {
		int j;
		String lex;
		boolean ban = false;
		if (cod.charAt(i) == '-') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '-') {
				j++;
				ban = true;
			} else if (j < cod.length()) {
				do {
					j++;
				} while (j < cod.length() && cod.charAt(j) != '-');
				if (j < cod.length() && cod.charAt(j) == '-') {
					ban = true;
					j++;
				}
			}
			if (ban) {
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.CADENA, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un un salto de linea de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - un salto de linea
	 * Para que un salto de linea pueda ser interpretado se debe expresar así: -§-
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el caracter asignado
	 */
	public Token extraerSaltoLinea(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j + 1 < cod.length()) {
			if (cod.charAt(i) == '-') {
				if (cod.charAt(j) == '§') {
					if (cod.charAt(j + 1) == '-') {
						j += 2;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.SALTOLINEA, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de un nombramiento de un caracter de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - un caracter
	 * Para que una caracter pueda ser interpretado debe de empezar 
	 * y terminar con ¡ por ejemplo ¡hola¡
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return el caracter asignado
	 */
	public Token extraerCaracter(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j + 1 < cod.length()) {
			if (cod.charAt(i) == '¡') {
				if (esLetra(cod.charAt(j)) || esDigito(cod.charAt(j))) {
					if (cod.charAt(j + 1) == '¡') {
						j += 2;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.CARACTER, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra int de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra int
	 * La palabra int es reemplazada por en
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra int reemplazada
	 */
	public Token extraerPalabraInt(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if (cod.charAt(i) == 'e') {
				if (cod.charAt(j) == 'n') {
					lex = cod.substring(i, j + 1);
					Token token = new Token(lex, Token.PALABRAINT, j + 1);
					return token;
				}
			}
		}
		return null;
	}
 
	/**
	 * Extraer un caracter de la palabra Float de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra Float
	 * La palabra float es reemplazada por fl
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra float reemplazada
	 */
	public Token extraerPalabraFloat(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if (cod.charAt(i) == 'f') {
				if (cod.charAt(j) == 'l') {
					lex = cod.substring(i, j + 1);
					Token token = new Token(lex, Token.PALABRAFLOAT, j + 1);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra Long de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * @param cod - palabra long
	 * La palabra long es reemplazada por lg
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra long reemplazada
	 */
	public Token extraerPalabraLong(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if (cod.charAt(i) == 'l') {
				if (cod.charAt(j) == 'g') {
					lex = cod.substring(i, j + 1);
					Token token = new Token(lex, Token.PALABRALONG, j + 1);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra String de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra String
	 * La palabra String es reemplazada por cad
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra String reemplazada
	 */
	public Token extraerPalabraString(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j + 1 < cod.length()) {
			if (cod.charAt(i) == 'c') {
				if (cod.charAt(j) == 'a') {
					if (cod.charAt(j + 1) == 'd') {
						lex = cod.substring(i, j + 2);
						Token token = new Token(lex, Token.PALABRASTRING, j + 2);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Extraer un caracter de la palabra char de  la cadena cod a partir de la posición i.
	 * Para cambiarlo por los simbolos determinados en ella
	 * 
	 * @param cod - palabra char
	 * La palabra char es reemplazada por xr
	 * @param i   - posición a partir de la cual se va a extraer el token 
	 * @return la palabra char reemplazada
	 */
	public Token extraerPalabraChar(String cod, int i) {
		int j = i + 1;
		String lex;
		if (j < cod.length()) {
			if (cod.charAt(i) == 'x') {
				if (cod.charAt(j) == 'r') {
					lex = cod.substring(i, j + 1);
					Token token = new Token(lex, Token.PALABRACHAR, j + 1);
					return token;
				}
			}
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

	public boolean esMayuscula(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z');
	}

	public boolean esMinuscula(char caracter) {
		return (caracter >= 'a' && caracter <= 'z');
	}

}
