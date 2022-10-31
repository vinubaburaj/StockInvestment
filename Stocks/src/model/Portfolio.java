package model;
import java.util.List;
import java.util.Map;

public interface Portfolio {

//  void createPortfolio(String userName, String uniqueID, String portfolioName, Map<String, Integer> portfolio);

  void createPortfolio(List<Stocks> stocks, String portfolioName);
  void examinePortfolio(String userName, String uniqueID, String portfolioName);

  void getTotalValue(String userName, String uniqueID, String portfolioName, String date);
}
