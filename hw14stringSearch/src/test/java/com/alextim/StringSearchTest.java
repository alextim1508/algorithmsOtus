package com.alextim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class StringSearchTest {

    @Test
    public void prefixSuffixSearTest() {
        String str =        "OLOKOKWLOKOLOKOLO";
        String pattern =    "KOLOKOL";

        Assertions.assertEquals(9, PrefixSuffixSearch.search0(str, pattern));
        Assertions.assertEquals(9,  PrefixSuffixSearch.search1(str, pattern));
        Assertions.assertEquals(9,  PrefixSuffixSearch.search2(str, pattern));
    }


    @Test
    public void StateMachineTest() {
        Assertions.assertEquals(10, StateMachine.search("ABBABBABABABABCAB", "ABABC"));
    }

    @Test
    public void KMPTest() {
        Assertions.assertEquals(10, KMP.search("ABBABBABABABABCAB", "ABABC"));
    }

}
