package com.noklin;
 
import com.google.gwt.junit.tools.GWTTestSuite;
import com.noklin.client.util.JsonTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PotatoTestSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for JsonTest");
    suite.addTestSuite(JsonTest.class);
    return suite;
  }
}
