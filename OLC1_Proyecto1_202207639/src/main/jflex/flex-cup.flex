package com.Analyzer;

import java.util.ArrayList;
import com.Classes.Error;
import com.Classes.Token;
import com.Classes.TokenConstant;

%%
%public
%class flexcup
%{
    public ArrayList<Token> tokens = new ArrayList<Token>();
                    public ArrayList<Error> errors = new ArrayList<Error>();
                    public void addToken(Token token){
                        tokens.add(token);
                    }
%}
%cup
%line
%column

%function next_token

%{
    StringBuffer buffer = new StringBuffer();

    private Symbol symbol(int type){
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

%eofval{
    addToken(new Token(tokens.size(),-1, -1, yytext(),"",TokenConstant.EOF));
    return symbol(ParserSym.EOF);
%eofval}

JUMP = [\n]
DIGIT = [0-9]
LETTER = [a-zA-Z]
WHITESPACE = [ \t\r]

AT = "@"
DOT = "."
SMALLER = "<"
GREATHER = ">"
EXCLAMATION = "!"
QUOT = "\"" | "&quot;"
LQUOT = "\“" | "&ldquo;"
RQUOT =  "\”"| "&rdquo;"


/*EXPRESIONES REGULARES*/
ARRAY = {AT}{IDENTIFIER}
STRING = ({QUOT}|{LQUOT}) ~({QUOT}|{RQUOT})
DOUBLE = ( 0 |[1-9]+ 0*)({DOT}{DIGIT}+)?
IDENTIFIER = {LETTER}({LETTER}|{DIGIT})*
SIMPLE_COMMENT = {EXCLAMATION}~{JUMP}
MULTI_COMMENT = {SMALLER}{EXCLAMATION}~{EXCLAMATION}{GREATHER}

%%
/**/
<YYINITIAL>"=" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"=",TokenConstant.EQUAL));
    return symbol (ParserSym.EQUAL, yytext());}

<YYINITIAL>"(" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"(",TokenConstant.LPAREN));
    return symbol (ParserSym.LPAREN, yytext());}

<YYINITIAL>")" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),")",TokenConstant.RPAREN));
    return symbol (ParserSym.RPAREN, yytext());}

<YYINITIAL>"[" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"[",TokenConstant.LBRACKET));
    return symbol (ParserSym.LBRACKET, yytext());}

<YYINITIAL>"]" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"]",TokenConstant.RBRACKET));
    return symbol (ParserSym.RBRACKET, yytext());}

<YYINITIAL>"," {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),",",TokenConstant.COMMA));
    return symbol (ParserSym.COMMA, yytext());}

<YYINITIAL>";" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),";",TokenConstant.SEMICOLON));
    return symbol (ParserSym.SEMICOLON, yytext());}

<YYINITIAL>":" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),":",TokenConstant.COLON));
    return symbol (ParserSym.COLON, yytext());}

<YYINITIAL>"::" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"::",TokenConstant.DOUBLECOLON));
    return symbol (ParserSym.DOUBLECOLON, yytext());}

<YYINITIAL>"<-" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"<-",TokenConstant.LARROW));
    return symbol (ParserSym.LARROW, yytext());}

<YYINITIAL>"->" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"->",TokenConstant.RARROW));
    return symbol (ParserSym.RARROW, yytext());}

/*PALABRAS RESERVADAS*/
<YYINITIAL>"PROGRAM" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"PROGRAM",TokenConstant.PROGRAM));
    return symbol (ParserSym.PROGRAM, yytext());}

<YYINITIAL>"END" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"END",TokenConstant.END));
    return symbol (ParserSym.END, yytext());}

<YYINITIAL>"VAR" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"VAR",TokenConstant.VAR));
    return symbol (ParserSym.VAR, yytext());}

<YYINITIAL>"ARR" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"ARRAY",TokenConstant.ARR));
    return symbol (ParserSym.ARR, yytext());}

<YYINITIAL>"CHAR[]" | "DOUBLE" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"---",TokenConstant.DATATYPE));
    return symbol (ParserSym.DATATYPE, yytext());}

/*OPERACIONES ARITMETICAS*/
<YYINITIAL>"SUM" | "RES" | "MUL" | "DIV" | "MOD" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"",TokenConstant.ARITFUNC));
    return symbol(ParserSym.ARITFUNC, yytext());}

/*OPERACIONES ESTADISTICAS*/
<YYINITIAL>"MEDIA" | "MEDIANA" | "MODA" | "VARIANZA" | "MAX" | "MIN" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"",TokenConstant.ESTFUNC));
    return symbol(ParserSym.ESTFUNC, yytext());}

/*IMPRESION DE EXPRESIONES*/
<YYINITIAL>"CONSOLE" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"CONSOLE",TokenConstant.CONSOLE));
    return symbol(ParserSym.CONSOLE, yytext());}

<YYINITIAL>"PRINT" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"PRINT",TokenConstant.PRINT));
    return symbol(ParserSym.PRINT, yytext());}

<YYINITIAL>"COLUMN" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"COLUMN",TokenConstant.COLUMN));
    return symbol(ParserSym.COLUMN, yytext());}

/*FUNCIONES DE GRAFICACION*/
<YYINITIAL>"GRAPHBAR" | "GRAPHLINE" | "GRAPHPIE" | "HISTOGRAM" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"GRAPHBAR",TokenConstant.GRAPHTYPE));
    return symbol(ParserSym.GRAPHTYPE, yytext());}

<YYINITIAL>"TITULO" | "TITULOX" | "TITULOY" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"TITULO",TokenConstant.TITLE));
    return symbol(ParserSym.TITLE, yytext());}

<YYINITIAL>"EJEX" | "EJEY" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"EJES",TokenConstant.EJES));
    return symbol(ParserSym.EJES, yytext());}

<YYINITIAL>"VALUES" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"VALUES",TokenConstant.VALUES));
    return symbol(ParserSym.VALUES, yytext());}

<YYINITIAL>"LABEL" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"LABEL",TokenConstant.LABEL));
    return symbol(ParserSym.LABEL, yytext());}

<YYINITIAL>"EXEC" {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),"EXEC",TokenConstant.EXEC));
    return symbol(ParserSym.EXEC, yytext());}

/*EXPRESIONES REGULARES*/
<YYINITIAL>{WHITESPACE} {/**/}
<YYINITIAL>{SIMPLE_COMMENT} {/**/}
<YYINITIAL>{MULTI_COMMENT} {/**/}

<YYINITIAL>{JUMP} {/**/}

<YYINITIAL>{IDENTIFIER} {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),yytext(),TokenConstant.ID));
    return symbol (ParserSym.ID, yytext());}

<YYINITIAL>{DOUBLE} {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),yytext(),TokenConstant.NUM));
    return symbol (ParserSym.NUM, yytext());}

<YYINITIAL>{STRING} {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext().replaceAll("[\"“”]", ""),"\".*\"",TokenConstant.STRING));
    return symbol (ParserSym.STRING, yytext());}


<YYINITIAL>{ARRAY} {
    addToken(new Token(tokens.size(),yyline+1, yycolumn+1, yytext(),yytext(),TokenConstant.ARRAY));
    return symbol (ParserSym.ARRAY, yytext());}

[^] {errors.add(new Error(errors.size(),yyline+1, yycolumn+1, yytext(),"ERROR LEXICO","Caracter desconocido"));}