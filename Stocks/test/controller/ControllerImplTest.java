package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ControllerImplTest {

  @Test
  public void IOTest() throws IOException {
      StringBuffer out = new StringBuffer();
      Reader in = new StringReader("1 Trial18 MSFT 5 Quit 4");
      Controller cnt = new ControllerImpl(in, out);
      cnt.start();
      assertEquals("Successfully created portfolio.", out.toString());
  }

}