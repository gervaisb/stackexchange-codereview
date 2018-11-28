package q208480;

import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

class Cipher {
    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'x', 'z'};
    private static final int MOD = ALPHABET.length-1;

    private final int shift;

    public Cipher() {
        this(1);
    }
    public Cipher(int shift) {
        this.shift = shift;
    }

    public String encode(String input) {
        return map(input, i -> i-shift);
    }

    public String decode(String input) {
        return map(input, i -> i+shift);
    }

    private String map(String string, IntUnaryOperator mapping) {
        return string.codePoints()
                .mapToObj(i -> {
                    if ( Character.isWhitespace(i)) {
                        System.out.println("`"+(char)(i)+"`\t"+ i);
                        return " ";
                    } else {
                        int r = mapping.applyAsInt(i-'a');
                        r = r<0?MOD+r:r;
                        System.out.println("`"+(char)(i)+"`\t"+ i+"; "+(i-'a')+" -> "+r+"  %="+(r%MOD));
                        return ALPHABET[ r % MOD ]+"";
                    }
                })
                .collect(Collectors.joining());
    }

}
