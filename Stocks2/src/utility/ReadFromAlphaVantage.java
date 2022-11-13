package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadFromAlphaVantage extends AbsGetDataFromSrc {

  public final String apiKey = "W0M1JOKC82EZEQA8";
  public String stockSymbol;

  public URL url;

  public ReadFromAlphaVantage(String stockSymbol, String timeSeries){
    this.stockSymbol = stockSymbol;

    switch (timeSeries) {
      case "TIME_SERIES_DAILY": {
        try {
          url = new URL("https://www.alphavantage"
                  + ".co/query?function=TIME_SERIES_DAILY"
                  + "&outputsize=full"
                  + "&symbol"
                  + "=" + stockSymbol + "&apikey="+apiKey+"&datatype=csv");
        }
        catch (MalformedURLException e) {
          throw new RuntimeException("the alphavantage API has either changed or "
                  + "no longer works");
        }
        break;
      }
      default: {

      }
    }
  }

  public String[] getDataByDate(String date) throws IOException {
      BufferedReader in = new BufferedReader(
              new InputStreamReader(url.openStream()));
    return absGetDataByDate(in, date);
  }
}
