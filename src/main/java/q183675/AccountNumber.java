package q183675;

class AccountNumber {

    private final String value;
    public AccountNumber(String value) {
        // Fake implementation
        if ( value.length()!=8 ) {
            throw new IllegalArgumentException("Wrong account number ["+value+"`. Length must be 8");
        }
        this.value = value;
    }

    public boolean isSameBank(AccountNumber other) {
        // Fake implementation
        String thisBank = this.value.substring(0, 4);
        String otherBank = other.value.substring(0, 4);

        return thisBank.equals(otherBank);
    }
}
