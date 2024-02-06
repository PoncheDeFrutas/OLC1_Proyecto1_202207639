package com.Analyzer;

import java.io.*;
import java.util.ArrayList;
import com.Classes.Token;
import com.Classes.TokenConstant;

%%
%public
%class LexemeAnalyzer

%line
%column

%{
    public ArrayList<Token> tokens = new ArrayList<Token>();
%}

%function next_token
%{
    StringBuffer buffer = new StringBuffer();
%}

%type Token
%eofval{
    return new Token(tokens.size(),yyline, yycolumn, null,null,TokenConstant.EOF);
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

ARRAY = {AT}{IDENTIFIER}
STRING = ({QUOT}|{LQUOT}) ~({QUOT}|{RQUOT})
DOUBLE = {DIGIT}+({DOT}{DIGIT}+)?
IDENTIFIER = {LETTER}({LETTER}|{DIGIT})*
SIMPLE_COMMENT = {EXCLAMATION} .* {JUMP}
MULTI_COMMENT = {SMALLER}{EXCLAMATION}( . | {JUMP})*{EXCLAMATION}{GREATHER}

%%

/*SIMBOLOS*/

"=" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"=",TokenConstant.EQUAL));
    return tokens.get(tokens.size()-1);}

"(" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"(",TokenConstant.LPAREN));
    return tokens.get(tokens.size()-1);}

")" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),")",TokenConstant.RPAREN));
    return tokens.get(tokens.size()-1);}

"[" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"[",TokenConstant.LBRACKET));
    return tokens.get(tokens.size()-1);}

"]" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"]",TokenConstant.RBRACKET));
    return tokens.get(tokens.size()-1);}

"," {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),",",TokenConstant.COMMA));
    return tokens.get(tokens.size()-1);}

";" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),";",TokenConstant.SEMICOLON));
    return tokens.get(tokens.size()-1);}

":" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),":",TokenConstant.COLON));
    return tokens.get(tokens.size()-1);}

"::" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"::",TokenConstant.DOUBLECOLON));
    return tokens.get(tokens.size()-1);}

"<-" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"<-",TokenConstant.LARROW));
    return tokens.get(tokens.size()-1);}

"->" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"->",TokenConstant.RARROW));
    return tokens.get(tokens.size()-1);}


/*PALABRAS RESERVADAS*/
"PROGRAM" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"PROGRAM",TokenConstant.PROGRAM));
    return tokens.get(tokens.size()-1);}

"END PROGRAM" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"END",TokenConstant.ENDPROGRAM));
    return tokens.get(tokens.size()-1);}

"END" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"END",TokenConstant.END));
    return tokens.get(tokens.size()-1);}

"CHAR[]" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"CHAR[]",TokenConstant.CHAR));
    return tokens.get(tokens.size()-1);}

"DOUBLE" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"DOUBLE",TokenConstant.DOUBLE));
    return tokens.get(tokens.size()-1);}

"VAR" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"VAR",TokenConstant.VAR));
    return tokens.get(tokens.size()-1);}

"ARR" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"ARR",TokenConstant.ARR));
    return tokens.get(tokens.size()-1);}

/*OPERACIONES ARITMETICAS*/
"SUM" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"SUM",TokenConstant.SUM));
    return tokens.get(tokens.size()-1);}

"RES" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"RES",TokenConstant.RES));
    return tokens.get(tokens.size()-1);}

"MUL" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MUL",TokenConstant.MUL));
    return tokens.get(tokens.size()-1);}

"DIV" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"DIV",TokenConstant.DIV));
    return tokens.get(tokens.size()-1);}

"MOD" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MOD",TokenConstant.MOD));
    return tokens.get(tokens.size()-1);}

/*OPERACIONES ESTADISTICAS*/
"MEDIA" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MEDIA",TokenConstant.MEDIA));
    return tokens.get(tokens.size()-1);}

"MEDIANA" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MEDIANA",TokenConstant.MEDIANA));
    return tokens.get(tokens.size()-1);}

"MODA" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MODA",TokenConstant.MODA));
    return tokens.get(tokens.size()-1);}

"VARIANZA" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"VARIANZA",TokenConstant.VARIANZA));
    return tokens.get(tokens.size()-1);}

"MAX" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MAX",TokenConstant.MAX));
    return tokens.get(tokens.size()-1);}

"MIN" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"MIN",TokenConstant.MIN));
    return tokens.get(tokens.size()-1);}

/*IMPRESION DE EXPRESIONES*/
"CONSOLE" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"CONSOLE",TokenConstant.CONSOLE));
    return tokens.get(tokens.size()-1);}

"PRINT" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"PRINT",TokenConstant.PRINT));
    return tokens.get(tokens.size()-1);}

"COLUMN" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"COLUMN",TokenConstant.COLUMN));
    return tokens.get(tokens.size()-1);}

/*FUNCIONES DE GRAFICACION*/
"GRAPHBAR" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"GRAPHBAR",TokenConstant.GRAPHBAR));
    return tokens.get(tokens.size()-1);}

"GRAPHPIE" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"GRAPHPIE",TokenConstant.GRAPHPIE));
    return tokens.get(tokens.size()-1);}

"GRAPHLINE" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"GRAPHLINE",TokenConstant.GRAPHLINE));
    return tokens.get(tokens.size()-1);}

"HISTOGRAM" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"HISTOGRAM",TokenConstant.HISTOGRAM));
    return tokens.get(tokens.size()-1);}

"TITULO" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"TITULO",TokenConstant.TITULO));
    return tokens.get(tokens.size()-1);}

"EJEX" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"EJEX",TokenConstant.EJEX));
    return tokens.get(tokens.size()-1);}

"EJEY" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"EJEY",TokenConstant.EJEY));
    return tokens.get(tokens.size()-1);}

"TITULOX" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"TITULOX",TokenConstant.TITULOX));
    return tokens.get(tokens.size()-1);}

"TITULOY" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"TITULOY",TokenConstant.TITULOY));
    return tokens.get(tokens.size()-1);}

"VALUES" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"VALUES",TokenConstant.VALUES));
    return tokens.get(tokens.size()-1);}

"LABEL" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"LABEL",TokenConstant.LABEL));
    return tokens.get(tokens.size()-1);}

"EXEC" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"EXEC",TokenConstant.EXEC));
    return tokens.get(tokens.size()-1);}



<YYINITIAL>{WHITESPACE} {/*Ignore*/}
<YYINITIAL>{SIMPLE_COMMENT} {/*Ignore*/}
<YYINITIAL>{MULTI_COMMENT} {/*Ignore*/}

<YYINITIAL>{JUMP} {
    yyline++;
    yycolumn = 0;}

<YYINITIAL>{IDENTIFIER} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"L(L|D)*",TokenConstant.ID));
    return tokens.get(tokens.size()-1);}

<YYINITIAL>{DOUBLE} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"D(P|D+)?",TokenConstant.NUM));
    return tokens.get(tokens.size()-1);}

<YYINITIAL>{STRING} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"\".*\"",TokenConstant.STRING));
    return tokens.get(tokens.size()-1);}

<YYINITIAL>{ARRAY} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"@L(L|D)*",TokenConstant.ARRAY));
    return tokens.get(tokens.size()-1);}

[^] {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.ERROR));
    return tokens.get(tokens.size()-1);}
