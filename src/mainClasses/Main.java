package mainClasses;

import mainClasses.BlackBox;
import utils.Complex;

import java.io.*;

//Чтение из XML- файла, запись в XML файл
//Чтение из JSON- файла, запись в JSON файл

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        BlackBox box = new BlackBox();

        box.readFile(br, box);
        box.add(8);
        int[] masOfInt = new int[]{1, 2, 3, 4, 5, 6};
        box.add(masOfInt);
        Complex[] masOfComplex = new Complex[3];
        masOfComplex[0] = new Complex(12.3, 12.4);
        masOfComplex[1] = new Complex(1.0, 1.4);
        masOfComplex[2] = new Complex(12.4, 17.4);
        box.add(masOfComplex);
        box.writeBox (out);
        box.writeTreeWithMinK(out);
        out.flush();
    }
}
