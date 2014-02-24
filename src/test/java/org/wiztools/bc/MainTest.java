package org.wiztools.bc;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compute method, of class Main.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        String inputStr = "(10.3+ (4/2))*2";
        BigDecimal expResult = new BigDecimal("24.6");
        BigDecimal result = Main.compute(inputStr);
        assertEquals(expResult, result);
    }
    
}
