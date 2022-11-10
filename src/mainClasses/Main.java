package mainClasses;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import utils.Complex;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException, XMLStreamException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        BlackBox box = new BlackBox();

        box.readFile(br, box);
        box.readXML("input.xml", box);
        box.readJSON("JSONjava.json", box);

        box.add(8);
        int[] masOfInt = new int[]{1, 2, 3, 4, 5, 6};
        box.add(masOfInt);
        Complex[] masOfComplex = new Complex[3];
        masOfComplex[0] = new Complex(12.3, 12.4);
        masOfComplex[1] = new Complex(1.0, 1.4);
        masOfComplex[2] = new Complex(12.4, 17.4);
        box.add(masOfComplex);
        
        box.writeBox(out);
        box.writeMinK(out);
        box.writeMinKJSON("JSONResult.json");
        box.writeMinKXML("output.xml");
        out.flush();
    }
}