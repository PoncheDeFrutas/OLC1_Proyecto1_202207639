package com.GUI;



import com.Analyzer.LexemeAnalyzer;
import com.Analyzer.Parser;
import com.Analyzer.flexcup;
import com.Classes.Simbols;
import com.Classes.Token;
import com.Classes.Tree;
import com.Classes.TokenConstant;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testLexemeAnalyzer() throws IOException{
        String testString = """
                00000003
                30
                PROGRAM
                END PROGRAM
                ! Esto es un comentario de una sola línea
                <! Esto es un comentario
                Multilínea !>
                var:double:: numero <- 2.5 end;
                var:char[]::cadena <- “cadena” end;
                arr:double::@darray <- [1, 2, 3, 4, 5] end;
                arr:char[]::@carray <- [“12”, “2”, “3”] end;
                
                SUM(5, 2)
                RES(3, 2)
                MUL(4, numero)
                DIV(1, variable)
                MOD(5, 4)
                
                
                Media(ARREGLO_DOUBLE)
                Mediana(ARREGLO_DOUBLE)
                Moda(ARREGLO_DOUBLE)
                Varianza(ARREGLO_DOUBLE)
                Max(ARREGLO_DOUBLE)
                Min(ARREGLO_DOUBLE)
                
                console::print = “hola”, numero, 15, “adios” end;
                console::column = “Enteros” -> @darray end;
                
                graphBar(
                titulo::char[] = “Estudiantes” end;
                ejeX::char[] = [“1 Parcial”, “2 parcial”, “Final”] end;
                ejeY::double = [50, 30, 70] end;
                tituloX::char[] = “Actividades” end;
                tituloY::char[] = “Notas” end;
                EXEC grapBar end;
                ) end;
                
                graphPie(
                label::char[] = ARREGLOCADENA end;
                values::double = ARREGLODOUBLE end;
                titulo::char[] = Cadena end;
                EXEC grapPie end;
                ) end;
                
                graphLine(
                titulo::char[] = “Gráfica de Línea” end;
                ejeX::char[] = [“1 Parcial”, “2 parcial”, “Final”] end;
                ejeY::double = [50, 30, 70] end;
                tituloX::char[] = “Actividades” end;
                tituloY::char[] = “Notas” end;
                EXEC grapLine end;
                ) end;
                
                Histogram(
                titulo::char[] = “Analisis de Arreglo” end;
                values::char[] = [2,2,2,5,5,7,8] end;
                EXEC Histogram end;
                ) end;
                titulo
                """;
        Reader stringReader = new StringReader(testString.toUpperCase());
        LexemeAnalyzer lexemeAnalyzer = new LexemeAnalyzer(stringReader);
        Token token = lexemeAnalyzer.next_token();

        while (token.getLexeme() != null) {
            if (token.getTokenType() != TokenConstant.ERROR) {
                System.out.println(token);
            } else{
                System.out.println("\033[0;31m" + token + "\033[0m");
            }

            token = lexemeAnalyzer.next_token();
        }
    }

    @Test
    public void testSintacticAnalyzer() throws Exception {
        String testString = """
                PROGRAM
                
                ! Variables Simples
                var:double:: numero <- 2.5 end;
                var:char[]::cadena <- “cadena” end;
                var:double:: copia <- numero end; ! copia tiene el valor 2.5
                
                ! Arreglos
                arr:double::@darray <- [1, 2, 3, 4, 5] end; ! Arreglo de tipo double
                arr:char[]::@carray <- [“12”, “2”, “3”] end; ! Arreglo de tipo string
                arr:double::@carray <- [numero, copia, 7] end; ! Puede usar variables
                
                ! Operaciones
                var:double:: suma <- SUM(5, 2) end;
                var:double:: resta <- RES(3, 2) end;
                var:double:: multi <- MUL(4, numero) end; ! Funciona con variables
                var:double:: division <- DIV(1, variable) end; ! Funciona con variables
                
                ! Operaciones anidadas)
                var:double:: suma <- MUL( SUM(7,3) , RES(7, DIV(25,5)) ) end;
                arr:double::@darray <- [ SUM(7,3), DIV(25,5)] end; ! Arreglo con funciones
                
                ! se pueden ingresar el arreglo directamente o por variable
                var:double:: med1 <- Media([1, 2, SUM(3, b), 4, a]) end;
                var:double:: med2 <- Mediana( @arreglo ) end;
                arr:double::@arreglo <- [Media(@data), Mediana(@data)] end;
                
                ! Tambien se pueden utilizar en operaciones aritmeticas
                var:double:: mitad <- DIV( SUM(Max(@data), Min(@data) ), 2) end;
                
                console::print = “hola”, numero, 15, “adios” end;
                console::print = 1, 2, SUM(3,5), Media(@arreglo) end;
                
                arr:double::@darray <- [1, 2, 3, 4, 5] end;
                
                var:char[]:: cadena <- “cadena” end;
                var:char[]:: titlo1 <- “enteros” end;
                
                console::column = “Enteros” -> @darray end;
                console::column = titulo1 -> [1, 2, 3, 4, 5] end;
                
                graphPie(
                titulo::char[] = “Titulo inicial” end;
                label::char[] = [“dato incorrecto”, “dato2” ] end;
                values::double = [20, 70] end;
                titulo::char[] = “Titulo que se debe mostrar” end;
                label::char[] = [“dato correcto”, “dato2” ] end;
                EXEC graphPie end;
                ) end;
                
                graphBar(
                titulo::char[] = “Estudiantes” end;
                ejeX::char[] = [“1 Parcial”, “2 parcial”, “Final”] end;
                ejeY::double = [50, 30, 70] end;
                tituloX::char[] = “Actividades” end;
                tituloY::char[] = “Notas” end;
                EXEC graphBar end;
                ) end;
                
                graphLine(
                titulo::char[] = “Gráfica de Línea” end;
                ejeX::char[] = [“1 Parcial”, “2 parcial”, “Final”] end;
                ejeY::double = [50, 30, 70] end;
                tituloX::char[] = “Actividades” end;
                tituloY::char[] = “Notas” end;
                EXEC graphLine end;
                ) end;
                
                Histogram(
                titulo::char[] = “Analisis de Arreglo” end;
                values::char[] = [2,2,2,5,5,7,8] end;
                EXEC Histogram end;
                ) end;
                
                END PROGRAM
                """;
        Reader stringReader = new StringReader(testString.toUpperCase());
        flexcup flexcup = new flexcup(stringReader);
        Parser parser = new Parser(flexcup);
        Tree tree = (Tree) parser.parse().value;
        tree.printTree(tree);
        //parser.parse();
        System.out.println("Sintactic Analyzer test passed");
    }

    @Test
    public void testHashMap() throws  Exception{
        HashMap<String, Simbols> hash = new HashMap<>();

        Simbols simbolo = new Simbols("numero", "double", "2.5", 1, 1);
        Simbols simbolo1 = new Simbols("cadena", "char[]", "cadena", 2, 2);
        Simbols simbolo2 = new Simbols("copia", "double", "numero", 3, 3);


        hash.put(simbolo.getName(), simbolo1);
        hash.put(simbolo1.getName(), simbolo2);
        hash.put(simbolo2.getName(), simbolo);


        System.out.println(hash);
        System.out.println(hash.get("numero"));

        for(String key : hash.keySet()){
            System.out.println(key + " " + hash.get(key));
        }
    }
}
