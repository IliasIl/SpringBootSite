package com.example.sweater;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


public class SimplePasswordEncoderTest {
    @Test
    public void encode() {
        SimplePasswordEncoder simp = new SimplePasswordEncoder();
        Assert.assertEquals("Secret: 'mypwd'", simp.encode("mypwd"));
        Assert.assertThat(simp.encode("mypwd"), Matchers.containsString("mypwd"));
    }
}