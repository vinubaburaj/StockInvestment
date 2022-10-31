package model;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PortfolioImplTest {
  @Test
  public void test(){
    Portfolio obj = new PortfolioImpl();

    HashMap<String, Integer> stocksList = new HashMap<>();

    stocksList.put("IBM",3);
    stocksList.put("GPV.TRV",3);

    obj.createPortfolio("Sris", "123", "p1", stocksList);

    obj.examinePortfolio("Sris", "123", "p1");
  }

  @Test
  public void test2(){
    Portfolio obj = new PortfolioImpl();

    HashMap<String, Integer> stocksList = new HashMap<>();

    stocksList.put("IBM",5);
    stocksList.put("GPV.TRV",7);

    obj.createPortfolio("Vinu", "5673", "Second", stocksList);

    obj.examinePortfolio("Vinu", "5673", "Second");
  }

}