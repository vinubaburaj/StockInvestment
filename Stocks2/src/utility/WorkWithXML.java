package utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.File;
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
      root.setAttribute("creation-date", stockData.get(0).get("Date"));


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
      File f = new File(this.path);
      saveXMLContent(doc, path);

    } catch (ParserConfigurationException ex) {
      Logger.getLogger(PortfolioImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String getFileCreationDate(){
    Document doc = getDocument(path);
    Element portfolio = doc.getDocumentElement();
    return portfolio.getAttribute("creation-date");
  }


  public List<HashMap<String, String>> read(){
    List<HashMap<String, String>> stocks = new ArrayList<>();

    try {
      Document doc = getDocument(path);
      doc.getDocumentElement().normalize();
      NodeList list = doc.getElementsByTagName("Stock");

      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          String ticker = element.getElementsByTagName("Stock-ticker").item(0).getTextContent();
          String numberOfShares = element.getElementsByTagName("Number-of-shares").item(0).getTextContent();
          String date = element.getElementsByTagName("Date").item(0).getTextContent();
          NodeList commission = element.getElementsByTagName("Commission");

          HashMap<String, String> stock = new HashMap<>();
          stock.put("Stock ticker", ticker);
          stock.put("Number of shares", numberOfShares);
          stock.put("Date of transaction", date);
          if(commission.getLength() > 0){
            stock.put("Commission",element.getElementsByTagName("Commission").item(0).getTextContent());
          }
          stocks.add(stock);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stocks;
  }

  public boolean update(List<HashMap<String, String>> stocks){
    try {
      Document d = getDocument(path);
      Element portfolio = d.getDocumentElement();

      for(HashMap<String, String> i: stocks){
        Element stock = d.createElement("Stock");

        for (Map.Entry<String, String> j : i.entrySet()) {
          Element c = d.createElement(j.getKey());
          Text textNode = d.createTextNode(j.getValue());
          c.appendChild(textNode);
          stock.appendChild(c);
        }
        portfolio.appendChild(stock);
        saveXMLContent(d, path);
      }
    } catch (Exception ex) {
      return false;
    }
    return true;
  }

  private static Document getDocument(String path_to_file) {
    Document d = null;
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      d = db.parse(path_to_file);
    } catch (Exception ex) {
      d = null;
    }
    return d;
  }

  private static void saveXMLContent(Document d, String path_to_file) {
    try {
      TransformerFactory tff = TransformerFactory.newInstance();
      Transformer tf = tff.newTransformer();
      tf.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource ds = new DOMSource(d);
      StreamResult sr = new StreamResult(path_to_file);
      tf.transform(ds, sr);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
