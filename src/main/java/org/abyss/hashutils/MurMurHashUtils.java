package org.abyss.hashutils;
public class MurMurHashUtils {
    public static int inverse(int code) {
        if (code == 0) {
            code = Integer.MIN_VALUE;
        }

        code = invertBitMix(code);
        code ^= 4;
        code = (code - 0xe6546b64)*-858993459; //code = code * 5 + 0xe6546b64;
        code = Integer.rotateRight(code, 13);
        code *= 1458385051; // 0x1b873593
        code = Integer.rotateRight(code, 15);
        code *= -555664463; //0xcc9e2d51

        return code;
    }

    private static int invertBitMix(int in) {
        in = invertRightShiftXor(in, 16); //inverse of in ^= in >>> 16;
        in *= 2127672349; // multiplicative inverse of 0xc2b2ae35
        in = invertRightShiftXor(in, 13); //inverse of in ^= in >>> 13;
        in *= -1513385405; // multiplicative inverse of 0x85ebca6b
        in = invertRightShiftXor(in, 16); //inverse of in ^= in >>> 16;
        return in;
    }

    private static int invertRightShiftXor(int value, int shift) {
        int output = 0;
        int i = 0;
        while (i * shift < 32)
        {
            int compartment = (0xffffffff << (32 - shift)) >>> (shift * i);
            int partOutput = value & compartment;
            value ^= partOutput >>> shift;
            output |= partOutput;
            i += 1;
        }
        return output;
    }

    private static int bitMix(int in) {
        in ^= in >>> 16;
        in *= 0x85ebca6b;
        in ^= in >>> 13;
        in *= 0xc2b2ae35;
        in ^= in >>> 16;
        return in;
    }

    public static int murmurHash(int code) {
        code *= 0xcc9e2d51;
        code = Integer.rotateLeft(code, 15);
        code *= 0x1b873593;

        code = Integer.rotateLeft(code, 13);
        code = code * 5 + 0xe6546b64;

        code ^= 4;
        code = bitMix(code);

        if (code >= 0) {
            return code;
        }
        else if (code != Integer.MIN_VALUE) {
            return -code;
        }
        else {
            return 0;
        }
    }
}
