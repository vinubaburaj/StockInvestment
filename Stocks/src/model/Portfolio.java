package model;
import java.util.List;
import java.util.Map;

public interface Portfolio {

//  void createPortfolio(String userName, String uniqueID, String portfolioName, Map<String, Integer> portfolio);

  void createPortfolio(List<Stocks> stocks, String portfolioName);
  List<String[]> examinePortfolio(String portfolioName);

  Double getTotalValue(List<String[]> stocks, String date);
}
