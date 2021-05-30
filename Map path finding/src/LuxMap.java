import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class LuxMap {
    public NodeList arcList;
    public NodeList nodeList;
    public Vector<Nod1> convertedNodeList;
    public Vector<Vector<Arcs>> listaAdiacenta;

    public void readNodes(){
        System.out.println(java.time.LocalTime.now());
        convertedNodeList = new Vector<>();
        try {
            File file = new File("map2.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            nodeList = doc.getElementsByTagName("node");
            int numberOfNodes=nodeList.getLength();
            listaAdiacenta = new Vector<>(numberOfNodes);

            for (int index = 0; index < numberOfNodes; index++) {
                Node node = nodeList.item(index);
                Element nodeE = (Element) node;
                int point1X, point1Y, id, aux;

                point1X = MapScaling.convertLong((double) Integer.parseInt(nodeE.getAttribute("longitude")) / 100000);
                point1Y = MapScaling.convertLat((double) Integer.parseInt(nodeE.getAttribute("latitude")) / 100000);
                id = Integer.parseInt(nodeE.getAttribute("id"));
                aux = id;
                Nod1 auxNod = new Nod1(point1X, point1Y, id);
                convertedNodeList.add(auxNod);

                Vector<Arcs> auxVec = new Vector<>();
                Arcs arc = new Arcs(aux , 0);
                auxVec.add(arc);
                listaAdiacenta.add(auxVec);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
        }
        System.out.println(java.time.LocalTime.now());
    }

    public void readList(){
        try {
            File file = new File("map2.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            arcList = doc.getElementsByTagName("arc");

            for (int index = 0; index < arcList.getLength(); index++) {
                Node arc = arcList.item(index);
                Element arcE = (Element) arc;
                Integer aux = Integer.parseInt(arcE.getAttribute("to"));
                Integer aux1 = Integer.parseInt(arcE.getAttribute("from"));
                Integer len = Integer.parseInt(arcE.getAttribute("length"));
                Arcs auxL = new Arcs(aux, len);
                listaAdiacenta.elementAt(aux1).add(auxL);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
        }
        System.out.println(java.time.LocalTime.now());
    }
    public LuxMap() {

    }
}  