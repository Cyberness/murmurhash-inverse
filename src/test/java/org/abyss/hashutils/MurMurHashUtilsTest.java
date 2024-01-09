package org.abyss.hashutils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MurMurHashUtilsTest {

    @Test
    public void inverse() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            assertTrue(i == MurMurHashUtils.murmurHash(MurMurHashUtils.inverse(i)));
        }
    }
}