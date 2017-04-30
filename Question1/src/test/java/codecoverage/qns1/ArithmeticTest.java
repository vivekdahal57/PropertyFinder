/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecoverage.qns1;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Alex
 */
public class ArithmeticTest {
    private final Arithmetic arithmetic;
    
    public ArithmeticTest() {
        this.arithmetic=new Arithmetic();
    }
    
    @Test
    public void positiveNumber(){
        Assert.assertEquals(arithmetic.addPositiveNumbers(2, 5),7);
    }
    
    @Test
    public void negativeNumber(){
        Assert.assertEquals(arithmetic.addPositiveNumbers(-2, -5),0);
    }
    
    @Test
    public void bigNumbers(){
        Assert.assertEquals(arithmetic.addPositiveNumbers(2147483647, 2147483647)>2147483647,false);
        //this will fail because sum of integer cannot exceeds 2147483647
    }
}
