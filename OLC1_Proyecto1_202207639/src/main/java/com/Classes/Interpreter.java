package com.Classes;

import org.jfree.data.Value;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Interpreter {
    private final HashMap<String, Simbols> hash = new HashMap<>();
    private final ArrayList<String> Instruccions;

    private String console_text;

    private final List<String> ARITFUNC = Arrays.asList("SUM", "RES", "MUL", "DIV", "MOD");
    private final List<String> ESTAFUNC = Arrays.asList("MEDIA", "MEDIANA", "MODA", "VARIANZA", "MAX", "MIN");

    public Interpreter(Tree tree){
        this.Instruccions = tree.getInstruccions(tree);
        this.console_text = "";
    }

    public void run(){
        while(!this.Instruccions.isEmpty()){
            String instruccion = this.Instruccions.remove(0);
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
            }
        }

    }

    public void declareArray() {
        String type = this.Instruccions.remove(0); // DOUBLE, CHAR[]
        this.Instruccions.remove(0); //::
        String name = this.Instruccions.remove(0);
        this.Instruccions.remove(0); // <-
        this.Instruccions.remove(0); // [
        switch (type) {
            case ("DOUBLE"):
                ArrayList<Float> array = new ArrayList<Float>();
                while (true){
                    array.add(this.getFloatValue());
                    if(this.Instruccions.remove(0) == "]"){ //,
                        break;
                    }
                }
                this.hash.put(name, new Simbols.Builder(name, type, 0, 0).setAFvalue(array).build());
                break;
            case ("CHAR[]"):
                ArrayList<String> arrayS = new ArrayList<String>();
                while (true){
                    String value = this.Instruccions.remove(0); //STRING, ID
                    if(this.hash.containsKey(value)){
                        value = this.hash.get(value).getSvalue();
                    }
                    arrayS.add(value);
                    if(this.Instruccions.remove(0) == "]"){ //,
                        break;
                    }
                }
                this.hash.put(name, new Simbols.Builder(name, type, 0, 0).setASvalue(arrayS).build());
                break;
        }
        this.Instruccions.remove(0); //END
        this.Instruccions.remove(0); //;
    }

    public void declareVariable() {
        String type = this.Instruccions.remove(0); // DOUBLE, CHAR[]
        this.Instruccions.remove(0); //::
        String name = this.Instruccions.remove(0);
        this.Instruccions.remove(0); // <-

        switch (type) {
            case ("DOUBLE"):
                float num = this.getFloatValue();
                this.hash.put(name, new Simbols.Builder(name, type, 0, 0).setFvalue(num).build());
                break;
            case ("CHAR[]"):
                String value = this.Instruccions.remove(0); //STRING, ID
                if(this.hash.containsKey(value)){
                    value = this.hash.get(value).getSvalue();
                }
                this.hash.put(name, new Simbols.Builder(name, type, 0, 0).setSvalue(value).build());
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
        String value = this.Instruccions.remove(0); //NUM, ARITFUNC, ID, ESTAFUNC
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
        String value = this.Instruccions.remove(0); //NUM, ID, [
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
                if(this.Instruccions.remove(0) == "]"){ //,
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
        String value = this.Instruccions.remove(0); // COLUMN, PRINT
        this.Instruccions.remove(0); // =
        if (value.equals("PRINT")){
            console_text += "\n!SALIDA : ";
            while (true) {
                String print = this.Instruccions.remove(0); //STRING, ID, NUM
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
            String column = this.Instruccions.remove(0); //STRING, ID
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
            column = this.Instruccions.remove(0); // @ID, [

            if (column.equals("[")){
                while (true){
                    column = this.Instruccions.remove(0); //STRING, ID
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
                    } else {
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

        } else{
            console_text += "\n DATO NO CONOIDO " + value;
        }
        this.Instruccions.remove(0); // END
        this.Instruccions.remove(0); // ;
    }

    public void printHash(){
        for (String key : this.hash.keySet()){
            System.out.println(this.hash.get(key).toString());
        }
    }

    public String getConsole_text(){
        return this.console_text;
    }
}
