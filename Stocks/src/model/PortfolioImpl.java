package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import enums.stockTicker;


public class PortfolioImpl implements Portfolio {

  private void createXML(List<Stocks> stocks, String portfolioName) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    String lastDate = "2022-10-28";

    try {
      DocumentBuilder builder = dbf.newDocumentBuilder();
      Document doc = builder.newDocument(); //created in temporary memory

      //create root node
      Element root = doc.createElement("Portfolio");

      Element name = doc.createElement("Portfolio-Name");
      Text nameText = doc.createTextNode(portfolioName);
      name.appendChild(nameText);

      root.appendChild(name);

      for (Stocks stock : stocks) {
        stock.fillStockData(lastDate);

        //create stock
        Element stockElement = doc.createElement("Stock");

        fillStockEmel(doc, stockElement, "Date", stock.getDate());
        fillStockEmel(doc, stockElement, "Stock-ticker", stock.getTicker());
        fillStockEmel(doc, stockElement, "Shares-owned", String.valueOf(stock.getNumberOfShares()));
        root.appendChild(stockElement);

      }

      doc.appendChild(root);

      DOMSource source = new DOMSource(doc);

      String path = "src/allUserPortfolios/user1_portfolios/" + portfolioName + ".xml";
      File f = new File(path);
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

  private void fillStockEmel(Document doc, Element stock, String child, String text) {
    Element c = doc.createElement(child);
    Text textNode = doc.createTextNode(text);
    c.appendChild(textNode);
    stock.appendChild(c);
  }

  public void createPortfolio(List<Stocks> stocks, String portfolioName) {
    createXML(stocks, portfolioName);
  }

  public List<String[]> examinePortfolio(String portfolioName) {
    String path = "src/allUserPortfolios/user1_portfolios/"
            + portfolioName + ".xml";

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    List<String[]> stocks = new ArrayList<>();

    try {
      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(new File(path));

      doc.getDocumentElement().normalize();

      NodeList list = doc.getElementsByTagName("Stock");

      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;

          // get text
          String ticker = element.getElementsByTagName("Stock-ticker").item(0).getTextContent();
          stockTicker stockTickerVal = stockTicker.valueOf(ticker);
          String numberOfShares = element.getElementsByTagName("Shares-owned").item(0).getTextContent();
          String date = element.getElementsByTagName("Date").item(0).getTextContent();

          Stocks s = new Stocks(stockTickerVal, Integer.parseInt(numberOfShares));
          s.fillStockData(date);

          String[] stock = new String[5];
          stock[0] = String.valueOf(stockTickerVal);
          stock[1] = stockTickerVal.getStockName();
          stock[2] = numberOfShares;
          stock[3] = s.getValueOfShare();
          stock[4] = date;

          stocks.add(stock);
        }
      }

    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return stocks;
  }


  public Double getTotalValue(List<String[]> stocks, String date) {

    double totalValue = 0;

    for (String[] stock : stocks) {
      Stocks s = new Stocks(stockTicker.valueOf(stock[0]), Integer.parseInt(stock[2]));
      s.fillStockData(date);
      totalValue += Double.parseDouble(s.getValueOfShare()) * s.getNumberOfShares();
    }
    return totalValue;
  }

}
