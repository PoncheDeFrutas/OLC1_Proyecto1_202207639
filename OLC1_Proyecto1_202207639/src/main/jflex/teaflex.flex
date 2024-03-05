
package analisis;

//Importaciones
import java.io.*;
import java.util.ArrayList;
import java_cup.runtime.*;
import Clases.*;


%%
// codigo de usuario
%{
    String cadena="";
%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

//Caracteristicas propias de jflex

%public // tipo de la clase
%class cupLex // nombre de la clase

%cup        //importación
%line       // conteo de lineas
%column     // conteo de columnas
%char       // conteo de caracteres
%full       // reconocimiento de caracteres
%ignorecase // insensitive case

%function next_token

%{
    public ArrayList<tokens> tokensitos = new ArrayList<tokens>();
    public ArrayList<errores> errorsitos = new ArrayList<errores>();

    StringBuffer buffer = new StringBuffer();

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }


%}

%eofval{
    return symbol(ParserSym.ENDOFFILE);     //Se le va pasando al cup
%eofval}

// Creación de estados si fuese necesario
%state CADENA

// SÍMBOLOS
LLAVEIZ = "{"
LLAVEDER = "}"
FINCADENA = ";"
MENOR = "<"
MAYOR = ">"
CORCHETEIZ = "["
CORCHETEDER = "]"
PARIZ = "("
PARDER = ")"
COMA = ","
DOSPUN = ":"
PUN = "."
ARROBA = "@"
EXCLAMACION = "!"
PREGUNTA = "?"
IGUAL = "="
COMI = "\"" | "&quot;"
IZCOMI = "\“" | "&ldquo;"
DERCOMI =  "\”"| "&rdquo;"


// PALABRAS RESERVADAS
//*FUNCIONES ARITMÉTICAS*//
FUNCIARIT = "sum" | "res" | "mul" | "div" | "mod"

//*FUNCIONES ESTADISTICAS*//
FUNCIESTA = "media" | "mediana" | "moda" | "varianza" | "max" | "min"

VARCHIKI = "var"
PROGRAMA = "program"
FINAL = "end"
CARACTER = "char[]"

//*IMPRESION DE EXPRESIONES*//
IMPRIMIR = "print"
CONSOLA = "console"
COLUMNA = "column"

//GRAFICACIÓN
//*TIPO*//
TIPOGRAFICA = "graphbar" | "graphpie" | "graphline" | "histogram"
//*TITULOS*//
TITULOS = "titulox" | "tituloy"
//*EJES*//
EJES = "ejex" | "ejey"
VALORES = "values"
ETIQUETA = "label"
EJECUTAR = "exec"


// EXPRESIONES REGULARES
IZFLECHA = "<-"
DERFLECHA = "->"
ARRAY = {ARROBA}{ID}
BLANCOS = [\ \r\t\f]+
SALTO = [\n]
ENTEROS = [0-9]+
DECIMALES = {ENTEROS} "." {ENTEROS}
NUM = (ENTEROS | DECIMALES)
ID = [a-z_][a-z_0-9]*
STRING = "\"" [^"\""] ~"\""
COMENTARIO_SIM = {EXCLAMACION} .* {SALTO}
COMENTARIO_MUL = {MENOR}{EXCLAMACION}( . | {SALTO})*{EXCLAMACION}{MAYOR}


%%
//Símbolos
<YYINITIAL>{LLAVEIZ} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.IZLLAV, yyline, yycolumn));
    return symbol(ParserSym.IZLLAV, yytext());
}
//}
<YYINITIAL>{LLAVEDER} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DERLLAV, yyline, yycolumn));
    return symbol(ParserSym.DERLLAV, yytext());
}
//;
<YYINITIAL>{FINCADENA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.PUNTOCOMA, yyline, yycolumn));
    return symbol(ParserSym.PUNTOCOMA, yytext());
}
//<
<YYINITIAL>{MENOR} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIGMENOR, yyline, yycolumn));
    return symbol(ParserSym.SIGMENOR, yytext());
}
//>
<YYINITIAL>{MAYOR} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIGMAYOR, yyline, yycolumn));
    return symbol(ParserSym.SIGMAYOR, yytext());
}
//[
<YYINITIAL>{CORCHETEIZ} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.IZCOR, yyline, yycolumn));
    return symbol(ParserSym.IZCOR, yytext());
}
//]
<YYINITIAL>{CORCHETEDER} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DERCOR, yyline, yycolumn));
    return symbol(ParserSym.DERCOR, yytext());
}
//(
<YYINITIAL>{PARIZ} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.IZPAREN, yyline, yycolumn));
    return symbol(ParserSym.IZPAREN, yytext());
}
//)
<YYINITIAL>{PARDER} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DERPAREN, yyline, yycolumn));
    return symbol(ParserSym.DERPAREN, yytext());
}
//,
<YYINITIAL>{COMA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIGCOMA, yyline, yycolumn));
    return symbol(ParserSym.SIGCOMA, yytext());
}
//:
<YYINITIAL>{DOSPUN} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DOSPUNTOS, yyline, yycolumn));
    return symbol(ParserSym.DOSPUNTOS, yytext());
}
//.
<YYINITIAL>{PUN} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.PUNTO, yyline, yycolumn));
    return symbol(ParserSym.PUNTO, yytext());
}
//@
<YYINITIAL>{ARROBA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.ARRO, yyline, yycolumn));
    return symbol(ParserSym.ARRO, yytext());
}
//!
<YYINITIAL>{EXCLAMACION} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.EXCLAMA, yyline, yycolumn));
    return symbol(ParserSym.EXCLAMA, yytext());
}
//?
<YYINITIAL>{PREGUNTA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIGPREGUNTA, yyline, yycolumn));
    return symbol(ParserSym.SIGPREGUNTA, yytext());
}
//=
<YYINITIAL>{IGUAL} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIGIGUAL, yyline, yycolumn));
    return symbol(ParserSym.SIGIGUAL, yytext());
}
//"
<YYINITIAL>{COMI} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIGCOMILLA, yyline, yycolumn));
    return symbol(ParserSym.SIGCOMILLA, yytext());
}
//“
<YYINITIAL>{IZCOMI} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.IZCOMILLA, yyline, yycolumn));
    return symbol(ParserSym.IZCOMILLA, yytext());
}
//”
<YYINITIAL>{DERCOMI} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DERCOMILLA, yyline, yycolumn));
    return symbol(ParserSym.DERCOMILLA, yytext());
}


//*PALABRAS RESERVADAS*
//Funciones Aritméticas
<YYINITIAL>{FUNCIARIT} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.FUNCARITMETICA, yyline, yycolumn));
    return symbol(ParserSym.FUNCARITMETICA, yytext());
}
//Funciones Estadísticas
<YYINITIAL>{FUNCIESTA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.FUNCESTADISTICA, yyline, yycolumn));
    return symbol(ParserSym.FUNCESTADISTICA, yytext());
}
//VARIABLE
<YYINITIAL>{VARCHIKI} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.VAR, yyline, yycolumn));
    return symbol(ParserSym.VAR, yytext());
}
//PROGRAMA
<YYINITIAL>{PROGRAMA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.PROGRAM, yyline, yycolumn));
    return symbol(ParserSym.PROGRAM, yytext());
}
//FINAL
<YYINITIAL>{FINAL} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.END, yyline, yycolumn));
    return symbol(ParserSym.END, yytext());
}
//CARACTER
<YYINITIAL>{CARACTER} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.TIPODATA, yyline, yycolumn));
    return symbol(ParserSym.TIPODATA, yytext());
}
//IMPRIMIR
<YYINITIAL>{IMPRIMIR} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CONSTIMPRIMIR, yyline, yycolumn));
    return symbol(ParserSym.CONSTIMPRIMIR, yytext());
}
//CONSOLA
<YYINITIAL>{CONSOLA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CONSTCONSOLA, yyline, yycolumn));
    return symbol(ParserSym.CONSTCONSOLA, yytext());
}
//COLUMNA
<YYINITIAL>{COLUMNA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CONSTCOLUMNA, yyline, yycolumn));
    return symbol(ParserSym.CONSTCOLUMNA, yytext());
}

//*GRAFICACIÓN*//
<YYINITIAL>{TIPOGRAFICA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CONSTIPOGRAFICA, yyline, yycolumn));
    return symbol(ParserSym.CONSTIPOGRAFICA, yytext());
}
//TITULOS
<YYINITIAL>{TITULOS} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.TITLE, yyline, yycolumn));
    return symbol(ParserSym.TITLE, yytext());
}
//EJES
<YYINITIAL>{EJES} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CONSTEJES, yyline, yycolumn));
    return symbol(ParserSym.CONSTEJES, yytext());
}
//VALORES
<YYINITIAL>{VALORES} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.VALUES, yyline, yycolumn));
    return symbol(ParserSym.VALUES, yytext());
}
//ETIQUETA
<YYINITIAL>{ETIQUETA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.LABEL, yyline, yycolumn));
    return symbol(ParserSym.LABEL, yytext());
}
//EJECUTAR
<YYINITIAL>{EJECUTAR} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.EXEC, yyline, yycolumn));
    return symbol(ParserSym.EXEC, yytext());
}



//Expresiones Regulares
//<-
<YYINITIAL>{IZFLECHA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.IZFLECHA, yyline, yycolumn));
    return symbol(ParserSym.IZFLECHA, yytext());
}
//->
<YYINITIAL>{DERFLECHA} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DERFLECHA, yyline, yycolumn));
    return symbol(ParserSym.DERFLECHA, yytext());
}
//ARRAY
<YYINITIAL>{ARRAY} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CONSARRAY, yyline, yycolumn));
    return symbol(ParserSym.CONSARRAY, yytext());
}

//BLANCOS
<YYINITIAL>{BLANCOS} {/*Ignore*/}
//SALTO
<YYINITIAL>{SALTO} {
    yyline++;
    yycolumn = 0;
}
//NUM
<YYINITIAL>{NUM} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.DOUBLE, yyline, yycolumn));
    return symbol(ParserSym.DOUBLE, yytext());
}
//ID
<YYINITIAL>{ID} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.IDENTIFICADOR, yyline, yycolumn));
    return symbol(ParserSym.IDENTIFICADOR, yytext());
}
//STRING
<YYINITIAL>{STRING} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.CADENA, yyline, yycolumn));
    return symbol(ParserSym.CADENA, yytext());
}
//COMENTARIO SIMPLE
<YYINITIAL>{COMENTARIO_SIM} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.SIMPLE_COMEN, yyline, yycolumn));
    return symbol(ParserSym.SIMPLE_COME, yytext());
}
//COMENTARIO MÚLTIPLE
<YYINITIAL>{COMENTARIO_MUL} {
    tokensitos.add(new tokens(tokensitos.size(), yytext(), constantToken.MULTI_COMEN, yyline, yycolumn));
    return symbol(ParserSym.MULTI_COME, yytext());
}

[.] {
    errorsitos.add(new errores(errorsitos.size(), yyline, yycolumn, yytext(), "Error léxico", "Lexema no reconocido"));
    return symbol(ParserSym.ERROR, yytext());
}