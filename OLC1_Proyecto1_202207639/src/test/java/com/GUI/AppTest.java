package com.GUI;



import com.Analyzer.LexemeAnalyzer;
import com.Analyzer.Parser;
import com.Analyzer.flexcup;
import com.Classes.Simbols;
import com.Classes.Token;
import com.Classes.Tree;
import com.Classes.Interpreter;
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
public class






AppTest
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
                var:double:: numero <- 2.5 end;
                arr:double::@darray <- [1, 2, 3, 4, 5] end;
                var:char[]:: titulo1 <- “Enteros” end;
                console::column = “Enteros” -> @darray end;
                console::column = titulo1 -> [1, numero, 3, 4, 5] end;
            END PROGRAM
            """;
        Reader stringReader = new StringReader(testString.toUpperCase());
        flexcup flexcup = new flexcup(stringReader);
        Parser parser = new Parser(flexcup);
        Tree tree = (Tree) parser.parse().value;
        tree.saveTree(tree);
        tree.printInstruccions();
        System.out.println("--------------------------------*-*-*-*-*-*--------------------------");
        Interpreter interpreter = new Interpreter(tree);
        interpreter.run();
        interpreter.printHash();

        String textomamalon = interpreter.getConsole_text();
        System.out.println(textomamalon);
        System.out.println("Sintactic Analyzer test passed");
    }

}
