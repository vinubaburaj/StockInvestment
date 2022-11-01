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

import javax.xml.XMLConstants;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.stockTicker;


public class PortfolioImpl implements Portfolio{

//
//  private String[] getStockData(String ticker, String date) {
//    String apiKey = "W0M1JOKC82EZEQA8";
//    URL url = null;
//
//    try {
//      url = new URL("https://www.alphavantage"
//              + ".co/query?function=TIME_SERIES_DAILY"
//              + "&outputsize=compact"
//              + "&symbol"
//              + "=" + ticker + "&apikey=" + apiKey + "&datatype=csv");
//    } catch (MalformedURLException e) {
//      throw new RuntimeException("the alphavantage API has either changed or "
//              + "no longer works");
//    }
//
//    InputStream in = null;
//    try {
//      in = url.openStream();
//      Reader reader = new InputStreamReader(in);
//      BufferedReader br = new BufferedReader(reader);
//      br.readLine();
//      String line = br.readLine();
//      String[] lines = new String[6];
//
//      while (line != null) {
//        lines = line.split(",");
//        break;
//      }
//      return lines;
//    } catch (IOException e) {
//      throw new IllegalArgumentException("No price data found for " + ticker);
//    }
//  }

//  private void createXML2(Stocks[] stocks){
//
//    for(Stocks stock : stocks){
//      String stockTicker = stock.getTicker();
//      int numberOfStocks = stock.getNumberOfStocks();
//    }
//  }

  private void createXML(List<Stocks> stocks, String portfolioName) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    String date = dateObj.format(formatter);
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

      for(Stocks stock : stocks){
        fillStockData(stock, lastDate);

          //create stock
          Element stockElement = doc.createElement("Stock");

          Element dateElem = doc.createElement("Date");
          Text dateText = doc.createTextNode(stock.getDate());
          dateElem.appendChild(dateText);

          Element stockTicker = doc.createElement("Stock-ticker");
          Text stockTickerText = doc.createTextNode(stock.getTicker());
          stockTicker.appendChild(stockTickerText);

          Element sharesOwned = doc.createElement("Shares-owned");
          Text sharesOwnedText = doc.createTextNode(String.valueOf(stock.getNumberOfShares()));
          sharesOwned.appendChild(sharesOwnedText);

          Element price = doc.createElement("Price");
          Text priceText = doc.createTextNode(stock.getValueOfShare());
          price.appendChild(priceText);

        stockElement.appendChild(dateElem);
        stockElement.appendChild(stockTicker);
        stockElement.appendChild(sharesOwned);
        stockElement.appendChild(price);

          root.appendChild(stockElement);

      }

      doc.appendChild(root);

      DOMSource source = new DOMSource(doc);

      String path = "src/allUserPortfolios/user1_portfolios/" + portfolioName;
      File f = new File(path);
      Result result = new StreamResult(f);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(source, result);
      System.out.println("Written data!" + path);
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(PortfolioImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException e) {
      throw new RuntimeException(e);
    }
  }

  public void fillStockData(Stocks stock, String date){
    stock.fillStockData(date);
  }

//  private void createXML(String userName, String uniqueID, String portfolioName, Map<String, Integer> portfolio) {
//    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//
//    LocalDate dateObj = LocalDate.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    String date = dateObj.format(formatter);
//
//    try {
//      DocumentBuilder builder = dbf.newDocumentBuilder();
//      Document doc = builder.newDocument(); //created in temporary memory
//
//      //create root node
//      Element root = doc.createElement("Portfolio");
//
//      Element name = doc.createElement("Portfolio-Name");
//      Text nameText = doc.createTextNode(portfolioName);
//      name.appendChild(nameText);
//
//      root.appendChild(name);
//
//      for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
//        String ticker = entry.getKey();
//        int num = entry.getValue();
//
//        String[] stockData = getStockData(ticker, null);
//
//        if(stockData.length > 0){
//          //create stock
//          Element stock = doc.createElement("Stock");
//
//          Element dateElem = doc.createElement("Date");
//          Text dateText = doc.createTextNode(date);
//          dateElem.appendChild(dateText);
//
//          Element stockTicker = doc.createElement("Stock-ticker");
//          Text stockTickerText = doc.createTextNode(ticker);
//          stockTicker.appendChild(stockTickerText);
//
//          Element sharesOwned = doc.createElement("Shares-owned");
//          Text sharesOwnedText = doc.createTextNode(String.valueOf(num));
//          sharesOwned.appendChild(sharesOwnedText);
//
//          Element price = doc.createElement("Price");
//          Text priceText = doc.createTextNode(stockData[4]);
//          price.appendChild(priceText);
//
//          stock.appendChild(dateElem);
//          stock.appendChild(stockTicker);
//          stock.appendChild(sharesOwned);
//          stock.appendChild(price);
//
//          root.appendChild(stock);
//        }
//      }
//
//      doc.appendChild(root);
//
//      DOMSource source = new DOMSource(doc);
//
//      String path = "src\\" + userName + "_" + uniqueID + "_" + portfolioName;
//      File f = new File(path);
//      Result result = new StreamResult(f);
//      TransformerFactory transformerFactory = TransformerFactory.newInstance();
//      Transformer transformer = transformerFactory.newTransformer();
//      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//      transformer.transform(source, result);
//      System.out.println("Written data!" + path);
//    } catch (ParserConfigurationException ex) {
//      Logger.getLogger(PortfolioImpl.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (TransformerException e) {
//      throw new RuntimeException(e);
//    }
//  }

  public void createPortfolio(List<Stocks> stocks, String portfolioName) {
    createXML(stocks, portfolioName);
  }


//  public void createPortfolio(String userName, String uniqueID, String portfolioName, Map<String, Integer> portfolio) {
//    createXML(userName, uniqueID, portfolioName, portfolio);
//  }



  public List<String[]> examinePortfolio(String portfolioName){
    String path = "src/allUserPortfolios/user1_portfolios/" + portfolioName;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    List<String[]> stocks = new ArrayList<>();

    try {

      // optional, but recommended
      // process XML securely, avoid attacks like XML External Entities (XXE)
//      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(new File(path));

      doc.getDocumentElement().normalize();

//      System.out.println("Displaying stocks from " + portfolioName);

      NodeList list = doc.getElementsByTagName("Stock");


      for (int temp = 0; temp < list.getLength(); temp++) {

        Node node = list.item(temp);

        if (node.getNodeType() == Node.ELEMENT_NODE) {

          Element element = (Element) node;

          // get text
          String ticker = element.getElementsByTagName("Stock-ticker").item(0).getTextContent();
          stockTicker st = stockTicker.valueOf(ticker);
          String numberOfShares = element.getElementsByTagName("Shares-owned").item(0).getTextContent();
          String valueOfShare = element.getElementsByTagName("Price").item(0).getTextContent();
          String date = element.getElementsByTagName("Date").item(0).getTextContent();

          String[] stock = new String[5];

          stock[0] = String.valueOf(st);
          stock[1] = st.getStockName();
          stock[2] = numberOfShares;
          stock[3] = valueOfShare;
          stock[4] = date;

          stocks.add(stock);

//          System.out.println("STOCK NAME : " + st.getStockName());
//          System.out.println("Date : " + date);
//          System.out.println("Number of shares owned : " + number);
//          System.out.println("Price on " + date + " : " + price);

        }
      }

    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return stocks;
  }


  public void getTotalValue(String userName, String uniqueID, String portfolioName, String date){
    String path = "src\\" + userName + "_" + uniqueID + "_" + portfolioName;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {

      // optional, but recommended
      // process XML securely, avoid attacks like XML External Entities (XXE)
      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

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
          String number = element.getElementsByTagName("Shares-owned").item(0).getTextContent();
          String price = element.getElementsByTagName("Price").item(0).getTextContent();

//          String[] stockData =  getStockData(ticker, date);
        }
      }

    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

}
