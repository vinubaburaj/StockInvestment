package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ControllerImplTest {

  @Test
  public void examinePortfolioTest() throws IOException {
      StringBuffer out = new StringBuffer();
      Reader in = new StringReader("2 Trial18 4");
      Controller cnt = new ControllerImpl(in, out);
      cnt.start();
      String[] output  = out.toString().split("\n");
      assertEquals("Stock Name: Microsoft", output[8]);
      assertEquals("Number of shares purchased: 5", output[9]);
      assertEquals("Value purchased at: 235.8700", output[10]);
      assertEquals("Date purchased on: 2022-10-28", output[11]);
  }

  @Test
  public void examineMultipleStocksPortfolio() throws IOException{
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 Trial15 4");
    Controller cnt = new ControllerImpl(in, out);
    cnt.start();
    String[] output  = out.toString().split("\n");
//    assertEquals("Stock Name: Morgan Stanley", out.toString());
    assertEquals("Stock Name: Morgan Stanley", output[11]);
    assertEquals("Number of shares purchased: 5", output[12]);
    assertEquals("Value purchased at: 82.2200", output[13]);
    assertEquals("Date purchased on: 2022-10-28", output[14]);

    assertEquals("Stock Name: Apple", output[16]);
    assertEquals("Number of shares purchased: 4", output[17]);
    assertEquals("Value purchased at: 155.7400", output[18]);
    assertEquals("Date purchased on: 2022-10-28", output[19]);

    assertEquals("Stock Name: Google", output[21]);
    assertEquals("Number of shares purchased: 2", output[22]);
    assertEquals("Value purchased at: 96.2900", output[23]);
    assertEquals("Date purchased on: 2022-10-28", output[24]);
  }

  @Test
  public void createSinglePortfolio() throws IOException{
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 singlePortf GOOGL 22 MSFT 5 " +
            "IBM 10 MS 23 Finish 4");
    Controller cnt = new ControllerImpl(in, out);
    cnt.start();
    String[] output  = out.toString().split("\n");
    assertEquals("Successfully created portfolio.",output[61]);
  }

  @Test
  public void createMultiplePortfolios() throws IOException{
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1 multPortf1 AAPL 5 IBM 2 Finish " +
            "1 multPortf2 GOOGL 7 MS 2 Finish " +
            "1 multPortf3 IBM 10 MSFT 23 Finish " +
            "2 multPortf2 " +
            "2 multPortf3 4");
    Controller cnt = new ControllerImpl(in, out);
    cnt.start();
    String[] output = out.toString().split("\n");
    assertEquals("Successfully created portfolio.", output[39]);
    assertEquals("Successfully created portfolio.", output[79]);
    assertEquals("Successfully created portfolio.", output[119]);

    assertEquals("Stock Name: Google", output[131]);
    assertEquals("Number of shares purchased: 7", output[132]);
    assertEquals("Value purchased at: 96.2900", output[133]);
    assertEquals("Date purchased on: 2022-10-28", output[134]);

    assertEquals("Stock Name: Morgan Stanley", output[136]);
    assertEquals("Number of shares purchased: 2", output[137]);
    assertEquals("Value purchased at: 82.2200", output[138]);
    assertEquals("Date purchased on: 2022-10-28", output[139]);
  }

  // Needs to be worked on.
  @Test
  public void getValueTest() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("3 multPortf1 2022-10-27");
    Controller cnt = new ControllerImpl(in, out);
    cnt.start();
    assertEquals("abc",out.toString());
    String[] output = out.toString().split("\n");
    // Total value of portfolio multportf1 on 2022-10-27 is: 993.54
  }
}