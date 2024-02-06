package com.Ponche;



import com.Analyzer.LexemeAnalyzer;
import com.Classes.Token;
import com.Classes.TokenConstant;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

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
                &
                PROGRAM
                END PROGRAM
                ! This is a comment
                <!-- 
                This is a comment 
                --!>
                var:double:: numero <- 2.5 end;
                var:char[]::cadena <- “cadena” end;
                
                arr:double::@darray <- [1, 2, 3, 4, 5] end; ! Arreglo de tipo double
                arr:char[]::@carray <- [“12”, “2”, “3”] end;
                
                var:double:: suma <- SUM(5, 2) end;
                var:double:: resta <- RES(3, 2) end;
                var:double:: multi <- MUL(4, numero) end; ! Funciona con variables
                var:double:: division <- DIV(1, variable) end;
                var:double:: modulo <- MOD(5, 4) end;
                
                var:double:: med1 <- Media([1, 2, SUM(3, b), 4, a]) end;
                var:double:: med2 <- Mediana( @arreglo ) end;
                arr:double::@arreglo <- [Media(@data), Mediana(@data)] end;
                
                onsole::print = “hola”, numero, 15, “adios” end;
                ! Salida: hola, 15, adios
                
                console::column = “Enteros” -> @darray end;
                console::column = titulo -> [1, 2, 3, 4, 5] end;
                
                GraphPie(
                titulo::char[] = “Titulo inicial” end;
                label::char[] = [“dato incorrecto”, “dato2” ] end;
                values::double = [20, 70] end;
                titulo::char[] = “Titulo que se debe mostrar” end;
                label::char[] = [“dato correcto”, “dato2” ] end;
                EXEC grapPie end;
                ) end;
                
                graphBar(
                titulo::char[] = “Estudiantes” end;
                ejeX::char[] = [“1 Parcial”, “2 parcial”, “Final”] end;
                ejeY::double = [50, 30, 70] end;
                tituloX::char[] = “Actividades” end;
                tituloY::char[] = “Notas” end;
                EXEC grapBar end;
                ) end;
                
                graphPie(
                label::char[] = [“Uno”, “Dos”, “Tres”] end;
                values::double = [50, 30, 20] end;
                titulo::char[] = “Ejemplo Gráfica de Pie” end;
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
}
