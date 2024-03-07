package com.GUI;



import com.Analyzer.LexemeAnalyzer;
import com.Analyzer.Parser;
import com.Analyzer.flexcup;
import com.Classes.Token;
import com.Classes.Tree;
import com.Classes.Interpreter;
import com.Classes.TokenConstant;
import org.jfree.chart.ChartPanel;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

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
                <!MULTICOMENTARIO!>
                    ! Ejemplos Variables
                    var:double:: numero <- 2.5 ;
                    var:double:: numero <- 2.5 eND;
                END PROGRAM
            """;
        Reader stringReader = new StringReader(testString.toUpperCase());
        flexcup flexcup = new flexcup(stringReader);

        System.out.println("--------------------------------*-*-*-*-*-*--------------------------");

        Parser parser = new Parser(flexcup);


        Tree tree = null;
        try {
            tree = (Tree) parser.parse().value;
        } catch (Exception e){
            System.out.println("NO POS VALIO QUESO MANITU");
        }
        System.out.println(parser.error_sym());
        System.out.println(parser.TablaES);
        for(Token tempTK : flexcup.tokens){
            if (tempTK.getTokenType() != TokenConstant.ERROR) {
                System.out.println(tempTK);
            } else{
                System.out.println("\033[0;31m" + tempTK + "\033[0m");
            }
        }

        if (tree != null) {
            tree.saveTree(tree);
            tree.printInstruccions();
            System.out.println("--------------------------------*-*-*-*-*-*--------------------------");
            Interpreter interpreter = new Interpreter(tree);
            interpreter.run();
            interpreter.printHash();
            System.out.println("--------------------------------*-*-*-*-*-*--------------------------");
            String textomamalon = interpreter.getConsole_text();
            System.out.println(textomamalon);
            System.out.println("Sintactic Analyzer test passed");
        }



    }

    @Test
    public void testGraphs() throws Exception {
        ArrayList<String> ejeX = new ArrayList<>(Arrays.asList("1 Parcial", "2 parcial", "Final"));
        ArrayList<Float> ejeY = new ArrayList<>(Arrays.asList(50.0f, 30.0f, 70.0f));
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Uno", "Dos", "Tres"));
        ArrayList<Float> values = new ArrayList<>(Arrays.asList(50.0f, 30.0f, 20.0f));

        CombinedGraphs combinedGraphs = new CombinedGraphs();

        JFrame frame = new JFrame("Test Combined Graphs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Crear y mostrar la gráfica de barras
        ChartPanel barGraph = combinedGraphs.createBarGraph("Estudiantes", ejeX, ejeY, "Actividades", "Notas");
        //combinedGraphs.replacePanel(frame, barGraph);

        // Esperar 5 segundos
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Crear y mostrar la gráfica de pastel
        ChartPanel pieGraph = combinedGraphs.createPieGraph("Ejemplo Gráfica de Pie", labels, values);
        //combinedGraphs.replacePanel(frame, pieGraph);

        // Esperar 5 segundos
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Crear y mostrar la gráfica lineal
        ChartPanel lineGraph = combinedGraphs.createLineGraph("Gráfica de Línea", ejeX, ejeY, "Actividades", "Notas");
        //combinedGraphs.replacePanel(frame, lineGraph);

        // Esperar 5 segundos
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
