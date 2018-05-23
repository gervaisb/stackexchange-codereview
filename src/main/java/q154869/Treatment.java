package q154869;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A combination of medicine
 */
public class Treatment implements Medicine {

    private final Set<Medicine> content;

    public Treatment(Medicine content) {
        this.content = new HashSet<>(5);
        this.content.add(content);
    }

    private Treatment(Set<Medicine> content) {
        this.content = new HashSet<>(content);
    }

    Treatment remove(Medicine other) {
        if ( !content.contains(other) ) {
            throw new NoSuchElementException("No "+other+" in this treatment");
        }
        Treatment copy = new Treatment(content);
        copy.content.remove(other);
        return copy;
    }

    Treatment plus(Medicine other) {
        Treatment copy = new Treatment(content);
        copy.content.add(other);
        return copy;
    }

    boolean contains(Medicine medicine) {
        return content.contains(medicine);
    }

    @Override
    public void on(Quarantine quarantine) {
        content.forEach(m -> m.on(quarantine));
    }

    @Override
    public Treatment combine(Treatment treatment) {
        Treatment copy = new Treatment(content);
        copy.content.addAll(treatment.content);;
        return copy;
    }
}
