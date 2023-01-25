package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import model.PortfolioFlexibleInterface;
import model.Stocks;

/**
 * Mock Portfolio class to test the flexible controller in isolation.
 */
public class MockFlexiblePortfolio implements PortfolioFlexibleInterface {
  private final StringBuilder log;
  private final int uniqueCode;

  /**
   * Mock Portfolio constructor to initialise the log and uniquecode.
   */
  public MockFlexiblePortfolio(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void createPortfolio(List<Stocks> stocks, String creationDate) {
    this.log.append(this.uniqueCode);
  }

  @Override
  public HashMap<String, Double> examinePortfolio() throws IOException {
    this.log.append(this.uniqueCode);
    HashMap<String, Double> hm = new HashMap<>();
    return hm;
  }

  @Override
  public boolean purchaseStocks(List<HashMap<String, String>> stocks) {
    this.log.append(this.uniqueCode);
    return true;
  }

  @Override
  public boolean sellStocks(String tick, int numOfShares, String date) {
    this.log.append(this.uniqueCode);
    return true;
  }

  @Override
  public double findCostBasis(String date) throws IOException {
    this.log.append(this.uniqueCode);
    return 0;
  }

  @Override
  public Double getTotalValue(String date) throws IOException {
    this.log.append(this.uniqueCode);
    return 0.0;
  }

  @Override
  public boolean changeCommission(float c) {
    this.log.append(c);
    this.log.append(this.uniqueCode);
    return true;
  }

  @Override
  public float getCommissionFromFile() {
    this.log.append(this.uniqueCode);
    return (float) 2.3;
  }

  @Override
  public StringBuilder getPerformanceChart(String startDate, String endDate) throws IOException {
    this.log.append(startDate).append(endDate).append(this.uniqueCode);
    return log;
  }

  @Override
  public void createStrategy(int total, HashMap<String, Integer> proportions,
                             String start, String end, int days, String name) {
    this.log.append(total);
    this.log.append(uniqueCode);
  }

  @Override
  public void updateStrategy() throws IOException {
    this.log.append(uniqueCode);
  }

  @Override
  public String getPortfolioName() {
    this.log.append(uniqueCode);
    return "";
  }

  @Override
  public void setPortfolioName(String s) {
    this.log.append(s);
    this.log.append(uniqueCode);
  }
}
