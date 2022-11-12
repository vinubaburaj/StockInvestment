package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
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

/**
 * This class contains the implementation of a portfolio.
 * Performs create, view and getValue operations of a portfolio.
 */
public class PortfolioImpl extends AbstractPortfolio{

//  @Override
//  public void createPortfolio(List<Stocks> stocks, String portfolioName) {
//
//  }


//  @Override
//  public Double getTotalValue(List<String[]> stocks, String date) {
//
//  }

//  private void fillStockEmel(Document doc, Element stock, String child, String text) {
//    Element c = doc.createElement(child);
//    Text textNode = doc.createTextNode(text);
//    c.appendChild(textNode);
//    stock.appendChild(c);
//  }

}
