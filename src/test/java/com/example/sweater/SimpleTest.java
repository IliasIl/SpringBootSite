package com.example.sweater;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void test() {
        int x = 3;
        int y = 22;
        Assert.assertEquals(25, x + y);
    }

    @Test(expected = ArithmeticException.class)
    public void test2(){
        int i=0;
        int il=1/i;
    }

}
