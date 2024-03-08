package com.Classes;

import com.GUI.CombinedGraphs;
import org.jfree.data.Value;

import java.io.Console;
import java.util.*;

public class Interpreter {
    private final HashMap<String, Simbols> hash = new HashMap<>();
    private final ArrayList<Intruccions> Instruccions;
    private CombinedGraphs combinedGraphs = new CombinedGraphs();
    private String console_text;

    private final List<String> ARITFUNC = Arrays.asList("SUM", "RES", "MUL", "DIV", "MOD");
    private final List<String> ESTAFUNC = Arrays.asList("MEDIA", "MEDIANA", "MODA", "VARIANZA", "MAX", "MIN");

    public Interpreter(Tree tree){
        this.Instruccions = tree.getInstruccions(tree);
        this.console_text = "";
    }

    public void run(){
        while(!this.Instruccions.isEmpty()){
            String instruccion = this.Instruccions.remove(0).getLexeme();
            switch (instruccion){
                case("VAR"):
                    this.Instruccions.remove(0); //:
                    this.declareVariable();
                    break;
                case("ARR"):
                    this.Instruccions.remove(0); //:
                    this.declareArray();
                    break;
                case("CONSOLE"):
                    this.Instruccions.remove(0); //::
                    this.printConsole();
                    break;
                case("GRAPHBAR"):
                    this.Instruccions.remove(0); // (
                    this.graphGraphBL("GRAPHBAR");
                    break;
                case("GRAPHLINE"):
                    this.Instruccions.remove(0); // (
                    this.graphGraphBL("GRAPHLINE");
                    break;
                case("GRAPHPIE"):
                    this.Instruccions.remove(0); // (
                    this.graphPH("GRAPHPIE");
                    break;
                case("HISTOGRAM"):
                    this.graphPH("HISTOGRAM");
                    this.Instruccions.remove(0); // (
                    break;
            }
        }

    }

    public void graphPH(String graph){
        String Title = "No Hay titulo";
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<Float> values = new ArrayList<>();
        while(true){
            String data = this.Instruccions.remove(0).getLexeme(); //TITULO, LABEL, VALUES
            if(data.equals("TITULO")){
                this.Instruccions.remove(0); //::
                this.Instruccions.remove(0); // char[]
                this.Instruccions.remove(0); // =
                if(this.hash.containsKey(this.Instruccions.get(0))){
                    if(this.hash.get(this.Instruccions.get(0)).getSvalue() != null){
                        Title = this.hash.get(this.Instruccions.remove(0)).getSvalue();
                    } else{
                        this.Instruccions.remove(0);
                    }
                } else{
                    Title = this.Instruccions.remove(0).getLexeme(); //STRING
                }
                this.Instruccions.remove(0); // END
                this.Instruccions.remove(0); // ;
            } else if (data.equals("LABEL")) {
                this.Instruccions.remove(0); //::
                this.Instruccions.remove(0); // char[]
                this.Instruccions.remove(0); // =
                data = this.Instruccions.remove(0).getLexeme(); // [, @ID
                if(data == "["){
                    while (true){
                        data = this.Instruccions.remove(0).getLexeme(); //STRING, ID
                        if(this.hash.containsKey(data)){
                            if(this.hash.get(data).getSvalue() != null){
                                labels.add(this.hash.get(data).getSvalue());
                            } else{
                                labels.add("NO ENCONTRADO: " + data);
                            }
                        } else if (data.endsWith("”") || data.endsWith("\"")){
                            labels.add(data);
                        } else{
                            labels.add("NO ENCONTRADO: " + data);
                        }
                        if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                            break;
                        }
                    }
                } else{
                    if(this.hash.containsKey(data)){
                        if(this.hash.get(data).getSvalue() != null){
                            labels.add(this.hash.get(data).getSvalue());
                        } else{
                            labels.add("NO ENCONTRADO: " + data);
                        }
                    } else{
                        labels.add(data);
                    }
                }
                this.Instruccions.remove(0); // END
                this.Instruccions.remove(0); // ;
            } else if (data.equals("VALUES")) {
                this.Instruccions.remove(0); //::
                this.Instruccions.remove(0); // double
                this.Instruccions.remove(0); // =
                data = this.Instruccions.remove(0).getLexeme(); // [, @ID
                if(data == "["){
                    while (true){
                        data = this.Instruccions.remove(0).getLexeme(); //NUM, ID
                        if(this.hash.containsKey(data)){
                            values.add(this.hash.get(data).getFvalue());
                        } else if (this.isParsableToFloat(data)){
                            values.add(Float.parseFloat(data));
                        }
                        if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                            break;
                        }
                    }
                } else{
                    if(this.hash.containsKey(data)){
                        if(this.hash.get(data).getAFvalue() != null){
                            values = this.hash.get(data).getAFvalue();
                        } else{
                            values = null;
                        }
                    } else if (this.isParsableToFloat(data)){
                        values.add(Float.parseFloat(data));
                    }
                }
                this.Instruccions.remove(0); // END
                this.Instruccions.remove(0); // ;
            } else if (data.equals("EXEC")) {
                break;
            }
        }
        if(graph.equals("GRAPHPIE")){
            combinedGraphs.addChartPanel(combinedGraphs.createPieGraph(Title, labels, values));
        } else{
            combinedGraphs.addChartPanel(combinedGraphs.createFrequencyBarGraph(Title, values));
            console_text += createFrequencyTable(values);
        }
        this.Instruccions.remove(0); // END
        this.Instruccions.remove(0); // ;
        this.Instruccions.remove(0); // )
        this.Instruccions.remove(0); // END
        this.Instruccions.remove(0); // ;
    }

    public String createFrequencyTable(ArrayList<Float> numbers) {
        // Calcular la frecuencia de cada número
        Map<Float, Integer> frequencyMap = new HashMap<>();
        for (float number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        // Crear el StringBuilder para la tabla
        StringBuilder table = new StringBuilder();

        // Agregar el título de la tabla
        table.append("\nAnálisis de Arreglo\n");

        // Agregar los nombres de las columnas
        table.append(String.format("%-10s %-10s %-20s %-20s\n", "Valor", "Frecuencia", "Frecuencia Absoluta", "Frecuencia Relativa"));

        // Calcular los totales
        int totalFrequency = 0;
        int totalAbsoluteFrequency = 0;
        float totalRelativeFrequency = 0;

        // Recorrer el HashMap y agregar cada fila a la tabla
        for (Map.Entry<Float, Integer> entry : frequencyMap.entrySet()) {
            float value = entry.getKey();
            int frequency = entry.getValue();
            int absoluteFrequency = frequency;
            float relativeFrequency = (float) frequency / numbers.size();

            table.append(String.format("%-10.2f %-10d %-20d %-20.2f\n", value, frequency, absoluteFrequency, relativeFrequency));

            totalFrequency += frequency;
            totalAbsoluteFrequency += absoluteFrequency;
            totalRelativeFrequency += relativeFrequency;
        }

        // Agregar los totales a la tabla
        table.append(String.format("%-10s %-10d %-20d %-20.2f\n", "Total", totalFrequency, totalAbsoluteFrequency, totalRelativeFrequency));

        return table.toString();
    }

    public void graphGraphBL(String graph){
        String Title = "No Hay titulo";
        String tituloX = "No Hay titulo";
        String tituloY = "No Hay titulo";
        ArrayList<String> ejeX = new ArrayList<>();
        ArrayList<Float> ejeY = new ArrayList<>();
        while(true){
            String data = this.Instruccions.remove(0).getLexeme(); //TITULO, EJEX, EJEY, TITULOX, TITULOY
            if(data.equals("TITULO") || data.equals("TITULOX")  || data.equals("TITULOY") ){
                String tempT = "";
                this.Instruccions.remove(0); //::
                this.Instruccions.remove(0); // char[]
                this.Instruccions.remove(0); // =
                if(this.hash.containsKey(this.Instruccions.get(0))){
                    if(this.hash.get(this.Instruccions.get(0)).getSvalue() != null){
                        tempT = this.hash.get(this.Instruccions.remove(0)).getSvalue();
                    } else{
                        this.Instruccions.remove(0);
                    }
                } else{
                    tempT = this.Instruccions.remove(0).getLexeme(); //STRING
                }
                this.Instruccions.remove(0); // END
                this.Instruccions.remove(0); // ;
                switch (data){
                    case("TITULO"):
                        Title = tempT;
                        break;
                    case("TITULOX"):
                        tituloX = tempT;
                        break;
                    case("TITULOY"):
                        tituloY = tempT;
                        break;
                }
            } else if (data.equals("EJEX")) {
                ejeX = new ArrayList<>();
                this.Instruccions.remove(0); //::
                this.Instruccions.remove(0); // char[]
                this.Instruccions.remove(0); // =
                data = this.Instruccions.remove(0).getLexeme(); // [ / @ID
                if(data == "["){
                    while (true){
                        data = this.Instruccions.remove(0).getLexeme(); //STRING, ID
                        if(this.hash.containsKey(data)){
                            if(this.hash.get(data).getSvalue() != null){
                                ejeX.add(this.hash.get(data).getSvalue());
                            } else{
                                ejeX.add("NO ENCONTRADO: " + data);
                            }
                        } else if (data.endsWith("”") || data.endsWith("\"")){
                            ejeX.add(data);
                        } else{
                            ejeX.add("NO ENCONTRADO: " + data);
                        }
                        if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                            break;
                        }
                    }
                } else{
                    if(this.hash.containsKey(data)){
                        if(this.hash.get(data).getSvalue() != null){
                            ejeX.add(this.hash.get(data).getSvalue());
                        } else{
                            ejeX.add("NO ENCONTRADO: " + data);
                        }
                    } else{
                        ejeX.add(data);
                    }
                }
                this.Instruccions.remove(0); // END
                this.Instruccions.remove(0); // ;

            } else if (data.equals("EJEY")) {
                ejeY = new ArrayList<>();
                this.Instruccions.remove(0); //::
                this.Instruccions.remove(0); // double
                this.Instruccions.remove(0); // =
                data = this.Instruccions.remove(0).getLexeme(); // [ / @ID
                if(data == "["){
                    while (true){
                        data = this.Instruccions.remove(0).getLexeme(); //NUM, ID
                        if(this.hash.containsKey(data)){
                            ejeY.add(this.hash.get(data).getFvalue());
                        } else if (this.isParsableToFloat(data)){
                            ejeY.add(Float.parseFloat(data));
                        }
                        if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                            break;
                        }
                    }
                } else{
                    if(this.hash.containsKey(data)){
                        if(this.hash.get(data).getAFvalue() != null){
                            ejeY = this.hash.get(data).getAFvalue();
                        } else{
                            ejeY = null;
                        }
                    } else if (this.isParsableToFloat(data)){
                        ejeY.add(Float.parseFloat(data));
                    }
                }
                this.Instruccions.remove(0); // END
                this.Instruccions.remove(0); // ;
            } else if(data.equals("EXEC")){break;}
        }

        if (graph.equals("GRAPHBAR")) {
            this.combinedGraphs.addChartPanel(combinedGraphs.createBarGraph(Title, ejeX, ejeY, tituloX, tituloY));
        } else{
            this.combinedGraphs.addChartPanel(combinedGraphs.createLineGraph(Title, ejeX, ejeY, tituloX, tituloY));
        }
        this.Instruccions.remove(0); // END
        this.Instruccions.remove(0); // ;
        this.Instruccions.remove(0); // )
        this.Instruccions.remove(0); // END
        this.Instruccions.remove(0); // ;
    }

    public void declareArray() {
        String type = this.Instruccions.remove(0).getLexeme(); // DOUBLE, CHAR[]
        this.Instruccions.remove(0); //::
        Intruccions name = this.Instruccions.remove(0);
        this.Instruccions.remove(0); // <-
        this.Instruccions.remove(0); // [
        switch (type) {
            case ("DOUBLE"):
                ArrayList<Float> array = new ArrayList<Float>();
                while (true){
                    array.add(this.getFloatValue());
                    if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                        break;
                    }
                }
                this.hash.put(name.getLexeme(), new Simbols.Builder(name.getLexeme(), type, name.getLine(), name.getColumn()).setAFvalue(array).build());
                break;
            case ("CHAR[]"):
                ArrayList<String> arrayS = new ArrayList<String>();
                while (true){
                    String value = this.Instruccions.remove(0).getLexeme(); //STRING, ID
                    if(this.hash.containsKey(value)){
                        value = this.hash.get(value).getSvalue();
                    }
                    arrayS.add(value);
                    if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                        break;
                    }
                }
                this.hash.put(name.getLexeme(), new Simbols.Builder(name.getLexeme(), type, name.getLine(), name.getColumn()).setASvalue(arrayS).build());
                break;
        }
        this.Instruccions.remove(0); //END
        this.Instruccions.remove(0); //;
    }

    public void declareVariable() {
        String type = this.Instruccions.remove(0).getLexeme(); // DOUBLE, CHAR[]
        this.Instruccions.remove(0); //::
        Intruccions name = this.Instruccions.remove(0);
        this.Instruccions.remove(0); // <-

        switch (type) {
            case ("DOUBLE"):
                float num = this.getFloatValue();
                this.hash.put(name.getLexeme(), new Simbols.Builder(name.getLexeme(), type, name.getLine(), name.getColumn()).setFvalue(num).build());
                break;
            case ("CHAR[]"):
                String value = this.Instruccions.remove(0).getLexeme(); //STRING, ID
                if(this.hash.containsKey(value)){
                    value = this.hash.get(value).getSvalue();
                }
                this.hash.put(name.getLexeme(), new Simbols.Builder(name.getLexeme(), type, name.getLine(), name.getColumn()).setSvalue(value).build());
                break;
        }
        this.Instruccions.remove(0); //END
        this.Instruccions.remove(0); //;
    }

    public boolean isParsableToFloat(String input){
        try{
            Float.parseFloat(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private float getFloatValue() {
        String value = this.Instruccions.remove(0).getLexeme(); //NUM, ARITFUNC, ID, ESTAFUNC
        float num = 0;
        if (ARITFUNC.contains(value)){
            num = this.aritFunctions(value);
        } else if (ESTAFUNC.contains(value)) {
            num = this.estaFunctions(value);
        } else if (this.isParsableToFloat(value)) {
            num = Float.parseFloat(value);
        } else if (this.hash.containsKey(value)) {

            num = this.hash.get(value).getFvalue();
        }
        return num;
    }

    public float media(ArrayList<Float> array){
        float sum = 0;
        for (float num : array){
            sum += num;
        }
        return sum / array.size();
    }

    public float mediana(ArrayList<Float> array){
        int n = array.size();
        if (n % 2 == 0){
            return (array.get(n / 2) + array.get(n / 2 - 1)) / 2;
        } else{
            return array.get(n / 2);
        }
    }

    public float moda(ArrayList<Float> array){
        float moda = 0;
        int maxCount = 0;
        for (int i = 0; i < array.size(); i++){
            int count = 0;
            for (int j = 0; j < array.size(); j++){
                if (array.get(j) == array.get(i)){
                    count++;
                }
            }
            if (count > maxCount){
                moda = array.get(i);
                maxCount = count;
            }
        }
        return moda;
    }

    public float varianza(ArrayList<Float> array){
        float media = this.media(array);
        float sum = 0;
        for (float num : array){
            sum += Math.pow(num - media, 2);
        }
        return sum / array.size();
    }

    public float max(ArrayList<Float> array){
        float max = array.get(0);
        for (float num : array){
            if (num > max){
                max = num;
            }
        }
        return max;
    }

    public float min(ArrayList<Float> array){
        float min = array.get(0);
        for (float num : array){
            if (num < min){
                min = num;
            }
        }
        return min;
    }

    public float estaFunctions(String operation){
        float num = 0;
        boolean select = false;
        ArrayList<Float> AFvalue = new ArrayList<>();
        this.Instruccions.remove(0); // (
        String value = this.Instruccions.remove(0).getLexeme(); //NUM, ID, [
        if (value != "["){
            select = true;
        }

        if(select){
            if(this.hash.containsKey(value)){
                AFvalue = this.hash.get(value).getAFvalue();
            } else{
                AFvalue = null;
            }
        } else{
            while (true){
                AFvalue.add(this.getFloatValue());
                if(this.Instruccions.remove(0).getLexeme() == "]"){ //,
                    break;
                }
            }
        }

        this.Instruccions.remove(0); // )

        if (AFvalue == null){
            return -1;
        }

        switch (operation) {
            case ("MEDIA"):
                num = this.media(AFvalue);
                break;
            case ("MEDIANA"):
                num = this.mediana(AFvalue);
                break;
            case ("MODA"):
                num = this.moda(AFvalue);
                break;
            case ("VARIANZA"):
                num = this.varianza(AFvalue);
                break;
            case ("MAX"):
                num = this.max(AFvalue);
                break;
            case ("MIN"):
                num = this.min(AFvalue);
                break;
        }
        return num;
    }

    public float aritFunctions(String operation){
        float num_1 = 0, num_2 = 0;
        this.Instruccions.remove(0); // (
        num_1 = this.getFloatValue();
        this.Instruccions.remove(0); // ,
        num_2 = this.getFloatValue();
        this.Instruccions.remove(0); // )

        return switch (operation) {
            case ("SUM") -> num_1 + num_2;
            case ("RES") -> num_1 - num_2;
            case ("MUL") -> num_1 * num_2;
            case ("DIV") -> num_1 / num_2;
            case ("MOD") -> num_1 % num_2;
            default -> -1;
        };
    }

    public void printConsole(){
        String value = this.Instruccions.remove(0).getLexeme(); // COLUMN, PRINT
        this.Instruccions.remove(0); // =
        if (value.equals("PRINT")){
            console_text += "\n!SALIDA : ";
            while (true) {
                String print = this.Instruccions.remove(0).getLexeme(); //STRING, ID, NUM
                if (this.hash.containsKey(print)) {
                    if (this.hash.get(print).getType().equals("DOUBLE")){
                        console_text += this.hash.get(print).getFvalue();
                    } else if (this.hash.get(print).getType().equals("CHAR[]")) {
                        console_text += this.hash.get(print).getSvalue();
                    } else{
                        console_text += "\n SIN TIPO DE DATO";
                    }
                } else if (this.isParsableToFloat(print)) {
                    console_text += print;
                } else if (ARITFUNC.contains(print)) {
                    console_text += this.aritFunctions(print);
                } else if (ESTAFUNC.contains(print)) {
                    console_text += this.estaFunctions(print);
                } else if (print.endsWith("”") || print.endsWith("\"")) {
                    console_text += print;
                } else if (print.equals(",")) {
                    console_text += ", ";
                } else if (print.equals("END")) {
                    this.Instruccions.remove(0); // ;
                    break;
                } else{
                    console_text += "DATO NO CONOCIDO " + "\"" + print + "\"";
                }
            }
        } else if (value.equals("COLUMN")) {
            console_text += "\n----------";
            String column = this.Instruccions.remove(0).getLexeme(); //STRING, ID
            this.Instruccions.remove(0); // ->
            if (this.hash.containsKey(column)){
                if(this.hash.get(column).getSvalue() != null){
                    console_text += "\n" + this.hash.get(column).getSvalue();
                } else {
                    console_text += "\nSIN TITULO ";
                }
            } else if (column.endsWith("”") || column.endsWith("\"")) {
                console_text += "\n" + column;
            } else{
                console_text += "\n TITULO NO CONOCIDO " + column;
            }
            console_text += "\n----------";
            column = this.Instruccions.remove(0).getLexeme(); // @ID, [

            if (column.equals("[")){
                while (true){
                    column = this.Instruccions.remove(0).getLexeme(); //STRING, ID
                    if (this.hash.containsKey(column)){
                        if (this.hash.get(column).getSvalue() != null) {
                            console_text += "\n" + this.hash.get(column).getSvalue();
                        } else if (this.hash.get(column).getFvalue() != Float.NaN){
                            console_text += "\n" + this.hash.get(column).getFvalue();
                        } else {
                            console_text += "SIN DATOS";
                        }
                    } else if (isParsableToFloat(column)) {
                        console_text += "\n" + Float.parseFloat(column);
                    } else if (column.equals(",")) {
                        continue;
                    } else if (column.equals("]")) {
                        break;
                    }  else {
                        console_text += "\nDATOS NO CONOIDOS " + column;
                    }
                }
            } else {
                if(this.hash.containsKey(column)){
                    if(this.hash.get(column).getAFvalue() != null){
                        for (float num : this.hash.get(column).getAFvalue()){
                            console_text += "\n" + num;
                        }
                    } else {
                        console_text += "SIN DATOS";
                    }
                } else {
                    console_text += "\nDATOS NO CONOIDOS " + column;
                }
            }

            this.Instruccions.remove(0); //END
            this.Instruccions.remove(0); //;
        } else{
            console_text += "\n DATO NO CONOIDO " + value;
        }
    }

    public void printHash(){
        for (String key : this.hash.keySet()){
            System.out.println(this.hash.get(key).toString());
        }
    }

    public String getConsole_text(){
        return this.console_text;
    }

    public CombinedGraphs getCombinedGraphs(){return  this.combinedGraphs;}

    public HashMap<String, Simbols> getHash() {
        return hash;
    }
}
