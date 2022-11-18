package mainClasses;

import com.adarshr.raroscope.RAREntry;
import com.adarshr.raroscope.RARFile;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.Complex;
import utils.Fraction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.security.*;
import java.security.spec.*;

import static utils.GSig.saveToFile;
import static utils.VSig.readFromFile;

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

    public static void readFile(String path, BlackBox box) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
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

    public static void writeBox(String path, BlackBox box) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(path));
        writeInt(out);
        writeDouble(out);
        writeFraction(out);
        writeComplex(out);
        box.writeMinK(out, path);
        out.flush();
    }

    private static void writeComplex(PrintWriter out) {
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

    private static void writeFraction(PrintWriter out) {
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

    private static void writeDouble(PrintWriter out) {
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

    private static void writeInt(PrintWriter out) {
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

    private static void writeMinK(PrintWriter out, String path) throws IOException {
        out.println("Tree with min K:");
        out.println();
        int minK = findMin(kInteger, kDouble, kFraction, kComplex);
        if (kInteger == minK) {
            writeInt(out);
        }
        if (kDouble == minK) {
            writeDouble(out);
        }
        if (kFraction == minK) {
            writeFraction(out);
        }
        if (kComplex == minK) {
            writeComplex(out);
        }
    }

    private static int findMin(int a, int b, int c, int d) {
        return Math.min(a, Math.min(b, Math.min(c, d)));
    }

    public static void readJSON(String path, BlackBox blackBox) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(path));
        JSONObject jo = (JSONObject) obj;
        readIntJSON(blackBox, jo);
        readDoubleJSON(blackBox, jo);
        readFractionJSON(blackBox, jo);
        readComplexJSON(blackBox, jo);
    }

    private static void readComplexJSON(BlackBox blackBox, JSONObject jo) {
        String strComplex = (String) jo.get("complex");
        String[] masComplex = strComplex.split(" ");
        for (int i = 0; i < masComplex.length; i += 2) {
            blackBox.add(Double.parseDouble(masComplex[i]), Double.parseDouble(masComplex[i + 1]));
        }
    }

    private static void readFractionJSON(BlackBox blackBox, JSONObject jo) {
        String strFraction = (String) jo.get("fraction");
        String[] masFraction = strFraction.split(" ");
        for (int i = 0; i < masFraction.length; i += 2) {
            blackBox.add(Integer.parseInt(masFraction[i]), Integer.parseInt(masFraction[i + 1]));
        }
    }

    private static void readDoubleJSON(BlackBox blackBox, JSONObject jo) {
        String strDouble = (String) jo.get("double");
        String[] masDouble = strDouble.split(" ");
        for (int i = 0; i < masDouble.length; i++) {
            blackBox.add(Double.parseDouble(masDouble[i]));
        }
    }

    private static void readIntJSON(BlackBox blackBox, JSONObject jo) {
        String strInt = (String) jo.get("int");
        String[] masInt = strInt.split(" ");
        for (int i = 0; i < masInt.length; i++) {
            blackBox.add(Integer.parseInt(masInt[i]));
        }
    }

    public static void writeMinKJSON(String path) throws IOException {
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
    }

    private static void writeFractionJSON(JSONObject json) {
        json.put("Tree with min K", "Fraction Tree");
        json.put("K = ", kFraction);
    }

    private static void writeDoubleJSON(JSONObject json) {
        json.put("Tree with min K", "Double Tree");
        json.put("K = ", kDouble);
    }

    private static void writeIntegerJSON(JSONObject json) {
        json.put("Tree with min K", "Integer Tree");
        json.put("K = ", kInteger);
    }

    public static void readXML(String path, BlackBox box) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(path);
        Node root = document.getDocumentElement();
        int val1 = 0;
        int val2 = 0;
        double val3 = 0.0;
        double val4 = 0.0;
        NodeList segments = root.getChildNodes();
        for (int i = 0; i < segments.getLength(); i++) {
            Node segment = segments.item(i);
            if (segment.getNodeType() != Node.TEXT_NODE) {
                NodeList segmentPoints = segment.getChildNodes();
                for (int j = 0; j < segmentPoints.getLength(); j++) {
                    Node segmentPoint = segmentPoints.item(j);
                    if (segmentPoint.getNodeType() != Node.TEXT_NODE) {
                        String name = segmentPoint.getNodeName();
                        if (name == "int") {
                            box.add(Integer.parseInt(segmentPoint.getChildNodes().item(0).getTextContent()));
                        } else if (name == "double") {
                            box.add(Double.parseDouble(segmentPoint.getChildNodes().item(0).getTextContent()));
                        } else if (name == "fraction") {
                            if (val1 != 0) {
                                val2 = Integer.parseInt(segmentPoint.getChildNodes().item(0).getTextContent());
                                box.add(val1, val2);
                                val1 = 0;
                            } else {
                                val1 = Integer.parseInt(segmentPoint.getChildNodes().item(0).getTextContent());
                            }
                        } else {
                            if (val3 != 0) {
                                val4 = Double.parseDouble(segmentPoint.getChildNodes().item(0).getTextContent());
                                box.add(val3, val4);
                                val3 = 0;
                            } else {
                                val3 = Double.parseDouble(segmentPoint.getChildNodes().item(0).getTextContent());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void writeMinKXML(String path) throws IOException, XMLStreamException {
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(path));
        writer.writeStartDocument("UTF-8", "1.0");
        int minK = findMin(kInteger, kDouble, kFraction, kComplex);
        if (minK == kInteger) {
            writer.writeStartElement("TreeWithMinK");
            writer.writeCharacters("Integer Tree");
            writer.writeStartElement("K");
            writer.writeCharacters(String.valueOf(kInteger));
        }
        if (minK == kDouble) {
            writer.writeStartElement("TreeWithMinK");
            writer.writeCharacters("Double Tree");
            writer.writeStartElement("K");
            writer.writeCharacters(String.valueOf(kDouble));
        }
        if (minK == kFraction) {
            writer.writeStartElement("TreeWithMinK");
            writer.writeCharacters("Fraction Tree");
            writer.writeStartElement("K");
            writer.writeCharacters(String.valueOf(kFraction));
        }
        if (minK == kComplex) {
            writer.writeStartElement("TreeWithMinK");
            writer.writeCharacters("Complex Tree");
            writer.writeStartElement("K");
            writer.writeCharacters(String.valueOf(kComplex));
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
    }

    public static void readZIP(String path) throws IOException {
        ZipInputStream zin = new ZipInputStream(new FileInputStream(path));
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            String name = entry.getName();
            FileOutputStream fout = new FileOutputStream("new" + name);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();
        }
    }

    public static void writeZIP(String fileToArh, String arh) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(arh));
        FileInputStream fis = new FileInputStream(fileToArh);
        ZipEntry zipEntry = new ZipEntry("out.txt");
        zipOutputStream.putNextEntry(zipEntry);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        zipOutputStream.write(buffer);
        fis.close();
        zipOutputStream.close();
    }

    public static void readJAR(String path) throws IOException {
        JarInputStream zin = new JarInputStream(new FileInputStream(path));
        JarEntry entry;
        while ((entry = zin.getNextJarEntry()) != null) {
            String name = entry.getName();
            FileOutputStream fout = new FileOutputStream("new" + name);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();
        }
    }

    public static void writeJAR(String fileToArh, String arh) throws IOException {
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(arh));
        FileInputStream fis = new FileInputStream(fileToArh);
        JarEntry jarEntry = new JarEntry("out.txt");
        jarOutputStream.putNextEntry(jarEntry);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        jarOutputStream.write(buffer);
        fis.close();
        jarOutputStream.close();
    }

    public static void encryption(String file) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, IOException, SignatureException, InvalidKeySpecException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(priv);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
            len = bufin.read(buffer);
            dsa.update(buffer, 0, len);
        }
        bufin.close();
        byte[] realSig = dsa.sign();
        saveToFile(realSig, "signature.txt");
        byte[] key = pub.getEncoded();
        saveToFile(key, "pubkey.txt");


        byte[] encKey = readFromFile("pubkey.txt");
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
        byte[] sigToVerify = readFromFile("signature.txt");
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pubKey);
        FileInputStream datafis = new FileInputStream(file);
        BufferedInputStream bufin1 = new BufferedInputStream(datafis);
        byte[] buffer1 = new byte[1024];
        int len1;
        while (bufin1.available() != 0) {
            len1 = bufin1.read(buffer1);
            sig.update(buffer1, 0, len1);
        }
        bufin1.close();
        boolean verifies = sig.verify(sigToVerify);
        System.out.println("Signature verifies: " + verifies);
    }

//    public static void readRAR(String path) throws IOException, RarException {
//        Archive archive = new Archive(new File(path));
//        FileHeader fh = archive.nextFileHeader();
//        while (fh != null) {
//            File fileEntry = new File(fh.getFileNameString().trim());
//            System.out.println(fileEntry.getAbsolutePath());
//            FileOutputStream os = new FileOutputStream(fileEntry);
//            archive.extractFile(fh, os);
//            os.close();
//            fh = archive.nextFileHeader();
//        }
//    }


//    public static void readRAR(String path) throws IOException {
//        RARFile file = new RARFile(path);
//        Enumeration<RAREntry> entries = file.entries();
//        while (entries.hasMoreElements()) {
//            RAREntry entry = entries.nextElement();
//            System.out.println(entry.getName());
//        }
//    }

}