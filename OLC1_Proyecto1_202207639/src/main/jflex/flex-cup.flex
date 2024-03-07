package com.Analyzer;

import java.io.*;
import java.util.ArrayList;
import java_cup.runtime.*;
import com.Classes.Token;
import com.Classes.TokenConstant;

%%
%public
%class flexcup

%cup
%line
%column




%{
    public ArrayList<Token> tokens = new ArrayList<Token>();
    StringBuffer buffer = new StringBuffer();

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    public void addToken(Token token){
            tokens.add(token);
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
SIMPLE_COMMENT = {EXCLAMATION} .* {JUMP}
MULTI_COMMENT = {SMALLER}{EXCLAMATION}( . | {JUMP})*{EXCLAMATION}{GREATHER}

%%
/**/
<YYINITIAL>"=" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"=",TokenConstant.EQUAL));
    return symbol (ParserSym.EQUAL, yytext());}

<YYINITIAL>"(" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"(",TokenConstant.LPAREN));
    return symbol (ParserSym.LPAREN, yytext());}

<YYINITIAL>")" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),")",TokenConstant.RPAREN));
    return symbol (ParserSym.RPAREN, yytext());}

<YYINITIAL>"[" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"[",TokenConstant.LBRACKET));
    return symbol (ParserSym.LBRACKET, yytext());}

<YYINITIAL>"]" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"]",TokenConstant.RBRACKET));
    return symbol (ParserSym.RBRACKET, yytext());}

<YYINITIAL>"," {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),",",TokenConstant.COMMA));
    return symbol (ParserSym.COMMA, yytext());}

<YYINITIAL>";" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),";",TokenConstant.SEMICOLON));
    return symbol (ParserSym.SEMICOLON, yytext());}

<YYINITIAL>":" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),":",TokenConstant.COLON));
    return symbol (ParserSym.COLON, yytext());}

<YYINITIAL>"::" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"::",TokenConstant.DOUBLECOLON));
    return symbol (ParserSym.DOUBLECOLON, yytext());}

<YYINITIAL>"<-" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"<-",TokenConstant.LARROW));
    return symbol (ParserSym.LARROW, yytext());}

<YYINITIAL>"->" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"->",TokenConstant.RARROW));
    return symbol (ParserSym.RARROW, yytext());}

/*PALABRAS RESERVADAS*/
<YYINITIAL>"PROGRAM" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"PROGRAM",TokenConstant.PROGRAM));
    return symbol (ParserSym.PROGRAM, yytext());}

<YYINITIAL>"END" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"END",TokenConstant.END));
    return symbol (ParserSym.END, yytext());}

<YYINITIAL>"VAR" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"VAR",TokenConstant.VAR));
    return symbol (ParserSym.VAR, yytext());}

<YYINITIAL>"ARR" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"ARRAY",TokenConstant.ARR));
    return symbol (ParserSym.ARR, yytext());}

<YYINITIAL>"CHAR[]" | "DOUBLE" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"---",TokenConstant.DATATYPE));
    return symbol (ParserSym.DATATYPE, yytext());}

/*OPERACIONES ARITMETICAS*/
<YYINITIAL>"SUM" | "RES" | "MUL" | "DIV" | "MOD" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.ARITFUNC));
    return symbol(ParserSym.ARITFUNC, yytext());}

/*OPERACIONES ESTADISTICAS*/
<YYINITIAL>"MEDIA" | "MEDIANA" | "MODA" | "VARIANZA" | "MAX" | "MIN" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.ESTFUNC));
    return symbol(ParserSym.ESTFUNC, yytext());}

/*IMPRESION DE EXPRESIONES*/
<YYINITIAL>"CONSOLE" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"CONSOLE",TokenConstant.CONSOLE));
    return symbol(ParserSym.CONSOLE, yytext());}

<YYINITIAL>"PRINT" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"PRINT",TokenConstant.PRINT));
    return symbol(ParserSym.PRINT, yytext());}

<YYINITIAL>"COLUMN" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"COLUMN",TokenConstant.COLUMN));
    return symbol(ParserSym.COLUMN, yytext());}

/*FUNCIONES DE GRAFICACION*/
<YYINITIAL>"GRAPHBAR" | "GRAPHLINE" | "GRAPHPIE" | "HISTOGRAM" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"GRAPHBAR",TokenConstant.GRAPHTYPE));
    return symbol(ParserSym.GRAPHTYPE, yytext());}

<YYINITIAL>"TITULO" | "TITULOX" | "TITULOY" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"TITULO",TokenConstant.TITLE));
    return symbol(ParserSym.TITLE, yytext());}

<YYINITIAL>"EJEX" | "EJEY" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"EJES",TokenConstant.EJES));
    return symbol(ParserSym.EJES, yytext());}

<YYINITIAL>"VALUES" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"VALUES",TokenConstant.VALUES));
    return symbol(ParserSym.VALUES, yytext());}

<YYINITIAL>"LABEL" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"LABEL",TokenConstant.LABEL));
    return symbol(ParserSym.LABEL, yytext());}

<YYINITIAL>"EXEC" {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),"EXEC",TokenConstant.EXEC));
    return symbol(ParserSym.EXEC, yytext());}

/*EXPRESIONES REGULARES*/
<YYINITIAL>{WHITESPACE} {/**/}
<YYINITIAL>{SIMPLE_COMMENT} {/**/}
<YYINITIAL>{MULTI_COMMENT} {/**/}

<YYINITIAL>{JUMP} {
    yyline++;
    yycolumn=0;}

<YYINITIAL>{IDENTIFIER} {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),yytext(),TokenConstant.ID));
    return symbol (ParserSym.ID, yytext());}

<YYINITIAL>{DOUBLE} {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),yytext(),TokenConstant.NUM));
    return symbol (ParserSym.NUM, yytext());}

<YYINITIAL>{STRING} {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext().replaceAll("[\"“”]", ""),"\".*\"",TokenConstant.STRING));
    return symbol (ParserSym.STRING, yytext());}


<YYINITIAL>{ARRAY} {
    addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),yytext(),TokenConstant.ARRAY));
    return symbol (ParserSym.ARRAY, yytext());}

[^] {addToken(new Token(tokens.size(),yyline, yycolumn, yytext(),yytext(),TokenConstant.ERROR));}