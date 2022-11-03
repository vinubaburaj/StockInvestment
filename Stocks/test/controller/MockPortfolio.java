package controller;

import java.util.ArrayList;
import java.util.List;

import model.Portfolio;
import model.Stocks;

public class MockPortfolio implements Portfolio {

  private StringBuilder log;
  private final int uniqueCode;

  public MockPortfolio(StringBuilder log, int uniqueCode){
    this.log = log;
    this.uniqueCode = uniqueCode;
  }
  @Override
  public void createPortfolio(List<Stocks> stocks, String portfolioName) {
    log.append(portfolioName);
    log.append(this.uniqueCode);
  }

  @Override
  public List<String[]> examinePortfolio(String portfolioName) {
    log.append(portfolioName);
    log.append(uniqueCode);
    List<String[]> ls = new ArrayList<>();
    String[] s1 = new String[5];
    s1[0] = "a";
    s1[1] = "b";
    s1[2] = "c";
    s1[3] = "d";
    s1[4] = "e";

    String[] s2 = new String[5];
    s2[0] = "x";
    s2[1] = "y";
    s2[2] = "z";
    s2[3] = "p";
    s2[4] = "q";

    ls.add(s1);
    ls.add(s2);
    return ls;
  }

  @Override
  public Double getTotalValue(List<String[]> stocks, String date) {
    log.append(date);
    log.append(uniqueCode);
    return 456.023;
  }
}

