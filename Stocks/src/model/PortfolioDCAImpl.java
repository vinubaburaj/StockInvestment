package model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import enums.stockTicker;
import utility.ReadFromAlphaVantage;
import utility.UtilityClass;
import utility.WorkWithFileTypes;
import utility.WorkWithXML;

/**
 * This class implements the functionalities regarding portfolio strategies.
 */
public class PortfolioDCAImpl extends PortfolioFlexible {

  private String strategyName;

  /**
   * This function sets the private member strategy name.
   */
  public void setStrategyName(String name) {
    this.strategyName = name;
  }


  /**
   * This function creates a DCA strategy for the current portfolio.
   *
   * @param total       the total amount that the user wants to invest.
   * @param proportions the proportions for each stock.
   * @param start       the start date
   * @param end         the end date for the strategy.
   * @param days        the frequency in days.
   */
  public void createDCAStrategy(int total, HashMap<String, Integer> proportions, String start,
                                String end, int days) {
    WorkWithFileTypes w = new WorkWithXML(super.finalPath + super.portfolioName + "_DCA_"
            + strategyName + ".xml",
            super.portfolioName + "_DCA_" + strategyName + ".xml");

    w.createDCAFile(total, proportions, start, end, days);
  }


  /**
   * This function updates the strategy to the portfolio.
   */
  public void updateDCAStrategyToPortfolio() throws IOException {
    File dir = new File(super.finalPath);
    List<String> fileNames = new ArrayList<>();

    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date = dateObj.format(formatter);

    for (File file : Objects.requireNonNull(dir.listFiles())) {
      if (file.getName().startsWith(super.portfolioName + "_DCA_")) {
        fileNames.add(file.getName());
      }
    }
    if (fileNames.size() > 0) {
      for (String fileName : fileNames) {
        WorkWithFileTypes w = new WorkWithXML(super.finalPath + fileName,
                fileName);
        HashMap<String, String> h = w.readDCA();
        String startDate = w.readDCAStart();
        String endDate = w.readDCAEnd();
        String lastUpdated = w.readLastUpdated();
        String freq = w.readDCAFreq();
        String finished = w.readFinished();

        if (finished.equals("false")) {
          LocalDate start = lastUpdated.equals("NA") ? LocalDate.parse(startDate) :
                  LocalDate.parse(lastUpdated).plusDays(Integer.parseInt(freq));
          LocalDate end = LocalDate.parse(date).isBefore(LocalDate.parse(endDate)) ?
                  LocalDate.parse(date) : LocalDate.parse(endDate);

          List<LocalDate> dates = new ArrayList<>();
          if (start.isEqual(end)) {
            dates = getDateList(start, end, 1);
          } else {
            dates = getDateList(start, end, Integer.parseInt(freq));
          }
          List<HashMap<String, String>> allPurchases = new ArrayList<>();
          for (LocalDate d : dates) {
            for (Map.Entry<String, String> i : h.entrySet()) {
              HashMap<String, String> stockData = new HashMap<>();
              stockData.put("Date", String.valueOf(d));
              stockData.put("Stock-ticker", i.getKey());
              Stocks stock = new Stocks(stockTicker.valueOf(i.getKey()), 0);
              stock.fillStockData(String.valueOf(d), true);
              double num = Double.parseDouble(i.getValue()) /
                      Double.parseDouble(stock.getValueOfShare());
              stockData.put("Number-of-shares",
                      String.valueOf(UtilityClass.round(num, 2)));
              allPurchases.add(stockData);
            }
          }
          super.purchaseStocks(allPurchases);
          if (dates.size() > 0) {
            w.setDCALastUpdated(String.valueOf(dates.get(dates.size() - 1)
                    .plusDays(1)));
          }
          if (LocalDate.parse(date).isAfter(LocalDate.parse(endDate))) {
            w.setFinished(true);
          }
        }
      }
    }
  }

  private List<LocalDate> getDateList(LocalDate start, LocalDate end, int freq)
          throws IOException {
    LocalDate temp = start;
    List<LocalDate> list = new ArrayList<>();
    while (temp.isBefore(end) || temp.isEqual(end)) {
      LocalDate d = ReadFromAlphaVantage.checkifDatePresent(temp);
      if (d.equals(LocalDate.parse("2300-01-01"))) {
        return list;
      } else {
        list.add(d);
      }
      temp = d.plusDays(freq);
    }
    return list;
  }
}
