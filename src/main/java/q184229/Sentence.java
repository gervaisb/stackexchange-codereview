package q184229;


public class Sentence {

    private final StringBuilder value;

    public Sentence(CharSequence value) {
        this.value = new StringBuilder(value);
    }

    public Sentence reverse() {
        if ( value.indexOf(" ")<0 )
            return this;

        final StringBuilder target = new StringBuilder(value.length());
        for (int i=value.length()-1, at=0; i>-1; i--) {
            char character = value.charAt(i);
            if ( Character.isWhitespace(character) ) {
                target.append(character);
                at = target.length();
            } else {
                target.insert(at, character);
            }
        }
        return new Sentence(target);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
