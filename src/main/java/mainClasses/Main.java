package mainClasses;

import com.github.junrar.exception.RarException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import utils.Complex;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException, XMLStreamException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException, RarException {
        BlackBox box = new BlackBox();
        box.readFile("input.txt", box);
        box.readJSON("JSONjava.json", box);
        box.readXML("input.xml", box);
        box.readZIP("arh.zip");
        box.readJAR("arh.jar");
        box.encryption("output.txt");

        box.add(8);
        int[] masOfInt = new int[]{1, 2, 3, 4, 5, 6};
        box.add(masOfInt);
        Complex[] masOfComplex = new Complex[3];
        masOfComplex[0] = new Complex(12.3, 12.4);
        masOfComplex[1] = new Complex(1.0, 1.4);
        masOfComplex[2] = new Complex(12.4, 17.4);
        box.add(masOfComplex);

        box.writeBox("output.txt", box);
        box.writeJAR("output.txt", "output.jar");
        box.writeZIP("output.txt", "output.zip");
        box.writeMinKJSON("JSONResult.json");
        box.writeMinKXML("output.xml");
    }
}