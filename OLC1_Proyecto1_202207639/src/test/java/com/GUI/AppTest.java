package com.GUI;



import com.Analyzer.LexemeAnalyzer;
import com.Analyzer.Parser;
import com.Analyzer.flexcup;
import com.Classes.Token;
import com.Classes.Tree;
import com.Classes.Interpreter;
import com.Classes.TokenConstant;
import com.Functions.Reports;
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
                                                      	! Estadísticas de Ingeniería en Guatemala
                                                      	CONSOLE::PRINT = "-----------------ESTADÍSTICAS DE INGENIERÍA EN GUATEMALA-----------------" END;
                                                      
                                                      	<! Datos estadísticos !>
                                                      	VAR:DOUBLE::ingenierosElectronicos <- 150.0 END;
                                                      	VAR:DOUBLE::ingenierosMecanicos <- 120.0 END;
                                                      	VAR:DOUBLE::ingenierosInformaticos <- 200.0 END;
                                                      
                                                      	CONSOLE::PRINT = "Ingenieros Electrónicos:", ingenierosElectronicos END;
                                                      	CONSOLE::PRINT = "Ingenieros Mecánicos:", ingenierosMecanicos END;
                                                      	CONSOLE::PRINT = "Ingenieros Informáticos:", ingenierosInformaticos END;
                                                      
                                                      	VAR:DOUBLE::totalIngenieros <- SUM(SUM(ingenierosElectronicos, ingenierosMecanicos), ingenierosInformaticos) END;
                                                      	CONSOLE::PRINT = "Total de Ingenieros:", totalIngenieros END;
                                                      
                                                      	<!
                                                      		-----------------ESTADÍSTICAS DE INGENIERÍA EN GUATEMALA-----------------
                                                      		Ingenieros Electrónicos: 150.0
                                                      		Ingenieros Mecánicos: 120.0
                                                      		Ingenieros Informáticos: 200.0
                                                      		Total de Ingenieros: 470.0
                                                      	!>
                                                      
                                                      	arr:double[]::@arreglo <- [ingenierosElectronicos, ingenierosMecanicos, ingenierosInformaticos] end;
                                                      	CONSOLE::COLUMN = "Número de Ingenieros por Especialidad en Guatemala" -> @arreglo END;
                                                      	<!
                                                      		--------------------------------------------
                                                      		Número de Ingenieros por Especialidad en Guatemala
                                                      		--------------------------------------------
                                                      		150.0
                                                      		120.0
                                                      		200.0
                                                      	!>
                                                      
                                                      	!!!! Errores !!!!!\s
                                                      
                                                      	VAR:DOUBLE = ingenierosElectronicos <- 150.0 END;
                                                      	VAR:DOUBLE = ingenierosElectronicos <- 150.0;
                                                      
                                                      	CONSOLE::PRINT :: "Ingenieros Electrónicos:" ingenierosElectronicos END;
                                                      
                                                      	GRAPH_BAR(
                                                      		ejeX::char[] = ["Electrónicos", "Mecánicos", "Informáticos"] END;
                                                      		ejeY::DOUBLE[] = [ingenierosElectronicos, ingenierosMecanicos, ingenierosInformaticos] END;
                                                      		titulo::char[] = "Número de Ingenieros por Especialidad en Guatemala" END;
                                                      		tituloX::char[] = "Especialidad" end;
                                                      		tituloY::char[] = "Cantidad" end;
                                                      		ejeX::char[] = ["Electrónicos", "Mecánicos", "Informáticos"] END;
                                                      		EXEC GRAPH_BAR end;
                                                      	) END;
                                                      
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

        Reports reports = new Reports();
        reports.tokensReport(flexcup.tokens);
        reports.errorsReport(parser.TablaES);

        if (tree != null && parser.TablaES != null) {
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

            reports.simbolTable(interpreter.getHash());
        } else if(parser.TablaES.size() != 0){
            System.out.println(parser.error_sym());
            System.out.println(parser.TablaES);
            System.out.println("Error en la tabla de simbolos");
        } else {
            System.out.println("Sintactic Analyzer test failed");
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
