package mainClasses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Complex;
import utils.Fraction;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class BlackBox {

    public static TreeMap<Integer, Integer> integerTreeMap;
    public static TreeMap<Integer, Double> doubleTreeMap;
    public static TreeMap<Integer, Fraction> fractionTreeMap;
    public static TreeMap<Integer, Complex> complexTreeMap;
    public static int kInteger;
    public static int kDouble;
    public static int kFraction;
    public static int kComplex;

    public static TreeMap<Integer, Integer> getIntegerTreeMap() {
        return integerTreeMap;
    }

    public static TreeMap<Integer, Double> getDoubleTreeMap() {
        return doubleTreeMap;
    }

    public static TreeMap<Integer, Fraction> getFractionTreeMap() {
        return fractionTreeMap;
    }

    public static TreeMap<Integer, Complex> getComplexTreeMap() {
        return complexTreeMap;
    }

    public BlackBox() {
        integerTreeMap = new TreeMap<>();
        kInteger = 0;
        doubleTreeMap = new TreeMap<>();
        kDouble = 0;
        fractionTreeMap = new TreeMap<>();
        kFraction = 0;
        complexTreeMap = new TreeMap<>();
        kComplex = 0;
    }

    public static void add(int value) {
        integerTreeMap.put(++kInteger, value);
    }

    public void add(double value) {
        doubleTreeMap.put(++kDouble, value);
    }

    public void add(int numerator, int denominator) {
        fractionTreeMap.put(++kFraction, new Fraction(numerator, denominator));
    }

    public void add(double re, double im) {
        complexTreeMap.put(++kComplex, new Complex(re, im));
    }

    public void add(int[] masOfInt) {
        for (int i = 0; i < masOfInt.length; i++) {
            integerTreeMap.put(++kInteger, masOfInt[i]);
        }
    }

    public void add(double[] masOfDouble) {
        for (int i = 0; i < masOfDouble.length; i++) {
            doubleTreeMap.put(++kDouble, masOfDouble[i]);
        }
    }

    public void add(Complex[] masOfComplex) {
        for (int i = 0; i < masOfComplex.length; i++) {
            complexTreeMap.put(++kComplex, new Complex(masOfComplex[i].getRe(), masOfComplex[i].getIm()));
        }
    }

    public void add(Fraction[] masOfFraction) {
        for (int i = 0; i < masOfFraction.length; i++) {
            fractionTreeMap.put(++kFraction, new Fraction(masOfFraction[i].getNumerator(), masOfFraction[i].getDenominator()));
        }
    }

    public static void readFile(BufferedReader br, BlackBox box) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.split(" ")[0].equals("int")) {
                box.add(Integer.parseInt(line.split(" ")[1]));
            } else if (line.split(" ")[0].equals("double")) {
                box.add(Double.parseDouble(line.split(" ")[1]));
            } else if (line.split(" ")[0].equals("fraction")) {
                box.add(Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]));
            } else {
                box.add(Double.parseDouble(line.split(" ")[1]), Double.parseDouble(line.split(" ")[2]));
            }
        }
    }

    public static void writeBox(PrintWriter out) {
        writeIntegerTree(out);
        writeDoubleTree(out);
        writeFractionTree(out);
        writeComplexTree(out);
    }

    private static void writeComplexTree(PrintWriter out) {
        out.println("ComlexTreeMap:");
        out.println("K = " + kComplex);
        Set complexKeys = complexTreeMap.keySet();
        for (Iterator i = complexKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Complex value = complexTreeMap.get(key);
            double value1 = value.getRe();
            double value2 = value.getIm();
            out.println(key + " = re " + value1 + ", im " + value2);
        }
        out.println();
    }

    private static void writeFractionTree(PrintWriter out) {
        out.println("FractionTreeMap:");
        out.println("K = " + kFraction);
        Set fractionKeys = fractionTreeMap.keySet();
        for (Iterator i = fractionKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Fraction value = fractionTreeMap.get(key);
            int value1 = value.getNumerator();
            int value2 = value.getDenominator();
            out.println(key + " = numerator " + value1 + ", denominator " + value2);
        }
        out.println();
    }

    private static void writeDoubleTree(PrintWriter out) {
        out.println("DoubleTreeMap:");
        out.println("K = " + kDouble);
        Set doubleKeys = doubleTreeMap.keySet();
        for (Iterator i = doubleKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Double value = doubleTreeMap.get(key);
            out.println(key + " = " + value);
        }
        out.println();
    }

    private static void writeIntegerTree(PrintWriter out) {
        out.println("IntegerTreeMap:");
        out.println("K = " + kInteger);
        Set intKeys = integerTreeMap.keySet();
        for (Iterator i = intKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Integer value = integerTreeMap.get(key);
            out.println(key + " = " + value);
        }
        out.println();
    }

    public static void writeTreeWithMinK(PrintWriter out) {
        out.println("Tree with min K:");
        out.println();
        int minK = findMin(kInteger, kDouble, kFraction, kComplex);
        if (kInteger == minK) {
            writeIntegerTree(out);
        }
        if (kDouble == minK) {
            writeDoubleTree(out);
        }
        if (kFraction == minK) {
            writeFractionTree(out);
        }
        if (kComplex == minK) {
            writeComplexTree(out);
        }
    }

    private static int findMin(int a, int b, int c, int d) {
        return Math.min(a, Math.min(b, Math.min(c, d)));
    }

    public static void jsonRead(String path, BlackBox blackBox) throws IOException, org.json.simple.parser.ParseException {
        Object obj = new JSONParser().parse(new FileReader(path));
        JSONObject jo = (JSONObject) obj;

        blackBox.add(Integer.parseInt(String.valueOf(jo.get("int1"))));
        blackBox.add(Integer.parseInt(String.valueOf(jo.get("int2"))));
        blackBox.add(Integer.parseInt(String.valueOf(jo.get("int3"))));
        blackBox.add(Integer.parseInt(String.valueOf(jo.get("int4"))));

        blackBox.add(Double.parseDouble(String.valueOf(jo.get("double1"))));
        blackBox.add(Double.parseDouble(String.valueOf(jo.get("double2"))));
        blackBox.add(Double.parseDouble(String.valueOf(jo.get("double3"))));

        blackBox.add(Integer.parseInt(String.valueOf(jo.get("fractionN1")), Integer.parseInt(String.valueOf(jo.get("fractionD1")))));
        blackBox.add(Integer.parseInt(String.valueOf(jo.get("fractionN2")), Integer.parseInt(String.valueOf(jo.get("fractionD2")))));
        blackBox.add(Integer.parseInt(String.valueOf(jo.get("fractionN3")), Integer.parseInt(String.valueOf(jo.get("fractionD3")))));

        blackBox.add(Double.parseDouble(String.valueOf(jo.get("complexR1"))), Double.parseDouble(String.valueOf(jo.get("complexI1"))));
        blackBox.add(Double.parseDouble(String.valueOf(jo.get("complexR2"))), Double.parseDouble(String.valueOf(jo.get("complexI2"))));
    }

    public static void jsonWriterMinK(String path, BlackBox blackBox) throws IOException s{
        JSONObject json = new JSONObject();
        FileWriter out = new FileWriter(path);
        int minK = findMin(kInteger, kDouble, kFraction, kComplex);
        if (kInteger == minK) {
            writeIntegerJSON(json);
        }
        if (kDouble == minK) {
            writeDoubleJSON(json);
        }
        if (kFraction == minK) {
            writeFractionJSON(json);
        }
        if (kComplex == minK) {
            writeComplexJSON(json);
        }
        out.write(json.toJSONString());
        out.flush();
    }

    private static void writeComplexJSON(JSONObject json) {
        json.put("Tree with min K", "Complex Tree");
        json.put("K = ", kComplex);
        Set complexKeys = complexTreeMap.keySet();
        for (Iterator i = complexKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Complex value = complexTreeMap.get(key);
            double value1 = value.getRe();
            double value2 = value.getIm();
            json.put("re", value1);
            json.put("im", value2);
        }
    }

    private static void writeFractionJSON(JSONObject json) {
        json.put("Tree with min K", "Fraction Tree");
        json.put("K = ", kFraction);
        Set fractionKeys = fractionTreeMap.keySet();
        for (Iterator i = fractionKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Fraction value = fractionTreeMap.get(key);
            int value1 = value.getNumerator();
            int value2 = value.getDenominator();
            json.put("numerator", value1);
            json.put("denominator", value2);
        }
    }

    private static void writeDoubleJSON(JSONObject json) {
        json.put("Tree with min K", "Double Tree");
        json.put("K = ", kDouble);
        Set doubleKeys = doubleTreeMap.keySet();
        for (Iterator i = doubleKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Double value = doubleTreeMap.get(key);
            json.put(key, value);
        }
    }

    private static void writeIntegerJSON(JSONObject json) {
        json.put("Tree with min K", "Integer Tree");
        json.put("K = ", kInteger);
        Set intKeys = integerTreeMap.keySet();
        for (Iterator i = intKeys.iterator(); i.hasNext(); ) {
            Integer key = (Integer) i.next();
            Integer value = integerTreeMap.get(key);
            json.put(key, value);
        }
    }
}
