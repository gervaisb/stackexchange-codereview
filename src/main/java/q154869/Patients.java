package q154869;

import static java.lang.String.format;

/**
 * Represent a group of patients that suffer of the same disease.
 */
class Patients {

    private final Disease disease;
    private int size = 0;

    public Patients(Disease disease, int initialSize) {
        this.disease = disease;
        this.size = initialSize;
    }

    public int getSize() {
        return size;
    }

    private void increments(int quantity) {
        this.size += quantity;
    }

    private void clear() {
        this.size = 0;
    }

    public void becomes(Patients other) {
        other.increments(size);
        this.clear();
    }

    public Disease getDisease() {
        return disease;
    }

    @Override
    public String toString() {
        return format("%1$d patient(s) with %2$s", getSize(), getDisease());
    }
}
