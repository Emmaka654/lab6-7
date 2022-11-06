package mainClasses;

import org.json.simple.parser.ParseException;
import utils.Complex;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//Чтение из XML- файла, запись в XML файл

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        BlackBox box = new BlackBox();
        box.jsonRead("JSONjava.json", box);
//        box.readFile(br, box);
//        box.add(8);
//        int[] masOfInt = new int[]{1, 2, 3, 4, 5, 6};
//        box.add(masOfInt);
//        Complex[] masOfComplex = new Complex[3];
//        masOfComplex[0] = new Complex(12.3, 12.4);
//        masOfComplex[1] = new Complex(1.0, 1.4);
//        masOfComplex[2] = new Complex(12.4, 17.4);
//        box.add(masOfComplex);
//        box.writeBox(out);
//        box.writeTreeWithMinK(out);
        box.jsonWriterMinK("JSONResult.json", box);
        out.flush();
    }
}


//import java.io.IOException;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//public class Main {
//
//    public static void main(String[] args) {
//        try {
//            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document document = documentBuilder.parse("input.xml");
//
//            Node root = document.getDocumentElement();
//            NodeList nodeList = root.getChildNodes();
//
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if (node.getNodeType() != Node.TEXT_NODE) {
//                    Element element = (Element) node;
//                    int a = Integer.parseInt(element.getElementsByTagName("int").item(0).getTextContent());
//                }
//
//            }
//
//
//        } catch (ParserConfigurationException ex) {
//            ex.printStackTrace(System.out);
//        } catch (SAXException ex) {
//            ex.printStackTrace(System.out);
//        } catch (IOException ex) {
//            ex.printStackTrace(System.out);
//        }
//    }
//}
