package mainClasses;

import com.github.junrar.exception.RarException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.swing.*;
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
//        box.readFile("input.txt", box);
//        box.readJSON("JSONjava.json", box);
//        box.readXML("input.xml", box);
//        box.readZIP("arh.zip");
//        box.readJAR("arh.jar");
//        box.encryption("output.txt");

//        box.add(8);
//        int[] masOfInt = new int[]{1, 2, 3, 4, 5, 6};
//        box.add(masOfInt);
//        Complex[] masOfComplex = new Complex[3];
//        masOfComplex[0] = new Complex(12.3, 12.4);
//        masOfComplex[1] = new Complex(1.0, 1.4);
//        masOfComplex[2] = new Complex(12.4, 17.4);
//        box.add(masOfComplex);

        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Hello! Now you are going to enter som information)");

        String getMessage1 = JOptionPane.showInputDialog(frame, "How many Integer numbers you would like to add?");
        int amountOfInt = Integer.parseInt(getMessage1);
        for (int i = 0; i < amountOfInt; ++i) {
            String getMessage2 = JOptionPane.showInputDialog(frame, "Enter Integer number");
            box.add(Integer.parseInt(getMessage2));
            JOptionPane.showMessageDialog(frame, "You added to BlackBox number: " + getMessage2);
        }

        String getMessage3 = JOptionPane.showInputDialog(frame, "How many Double numbers you would like to add?");
        int amountOfDouble = Integer.parseInt(getMessage3);
        for (int i = 0; i < amountOfDouble; ++i) {
            String getMessage4 = JOptionPane.showInputDialog(frame, "Enter Double number");
            box.add(Double.parseDouble(getMessage4));
            JOptionPane.showMessageDialog(frame, "You added to BlackBox number: " + getMessage4);
        }

        String getMessage5 = JOptionPane.showInputDialog(frame, "How many Fraction numbers you would like to add?");
        int amountOfFraction = Integer.parseInt(getMessage5);
        for (int i = 0; i < amountOfFraction; ++i) {
            String getMessage6 = JOptionPane.showInputDialog(frame, "Enter Fraction numerator");
            String getMessage7 = JOptionPane.showInputDialog(frame, "Enter Fraction denominator");
            box.add(Integer.parseInt(getMessage6), Integer.parseInt(getMessage7));
            JOptionPane.showMessageDialog(frame, "You added to BlackBox number: " + getMessage6 + " " + getMessage7);
        }

        String getMessage8 = JOptionPane.showInputDialog(frame, "How many Complex numbers you would like to add?");
        int amountOfComplex = Integer.parseInt(getMessage8);
        for (int i = 0; i < amountOfComplex; ++i) {
            String getMessage9 = JOptionPane.showInputDialog(frame, "Enter Complex Re number");
            String getMessage10 = JOptionPane.showInputDialog(frame, "Enter Fraction Im number");
            box.add(Double.parseDouble(getMessage9), Double.parseDouble(getMessage10));
            JOptionPane.showMessageDialog(frame, "You added to BlackBox number: " + getMessage9 + " " + getMessage10);
        }

        box.writeBox("output.txt", box);
//        box.writeJAR("output.txt", "output.jar");
//        box.writeZIP("output.txt", "output.zip");
//        box.writeMinKJSON("JSONResult.json");
//        box.writeMinKXML("output.xml");
        System.exit(0);
    }
}