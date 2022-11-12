package utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.PortfolioImpl;

public class WorkWithXML implements WorkWithFileTypes{

  private final String path;

  private final String name;

  public WorkWithXML(String path, String name) {
    this.path = path;
    this.name = name;
  }

  public void create(ArrayList<HashMap<String, String>> stockData){
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {
      DocumentBuilder builder = dbf.newDocumentBuilder();
      Document doc = builder.newDocument(); //created in temporary memory

      //create root node
      Element root = doc.createElement("Portfolio");

      Element name = doc.createElement("Portfolio-Name");
      Text nameText = doc.createTextNode(this.name);
      name.appendChild(nameText);

      root.appendChild(name);

      for(HashMap<String, String> i: stockData){
        Element stockElement = doc.createElement("Stock");

        for (Map.Entry<String, String> j : i.entrySet()) {
          Element c = doc.createElement(j.getKey());
          Text textNode = doc.createTextNode(j.getValue());
          c.appendChild(textNode);
          stockElement.appendChild(c);
        }
        root.appendChild(stockElement);
      }
      doc.appendChild(root);
      DOMSource source = new DOMSource(doc);
      File f = new File(this.path);
      Result result = new StreamResult(f);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, result);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(PortfolioImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException e) {
      throw new RuntimeException(e);
    }
  }


  public List<String[]> read(){
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    List<String[]> stocks = new ArrayList<>();

    try {
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(this.path));
      doc.getDocumentElement().normalize();
      NodeList list = doc.getElementsByTagName("Stock");

      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          String ticker = element.getElementsByTagName("Stock-ticker").item(0).getTextContent();
          String numberOfShares = element.getElementsByTagName("Shares-owned").item(0).getTextContent();
          String date = element.getElementsByTagName("Date").item(0).getTextContent();

          String[] stock = new String[3];
          stock[0] = ticker;
          stock[1] = numberOfShares;
          stock[2] = date;
          stocks.add(stock);
        }
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return stocks;
  }
}
