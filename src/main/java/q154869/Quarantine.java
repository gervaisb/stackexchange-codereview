package q154869;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static q154869.Medicine.Antibiotic;
import static q154869.Medicine.Aspirin;
import static q154869.Medicine.Insulin;
import static q154869.Medicine.None;
import static q154869.Medicine.Paracetamol;

/**
 * The responsibility of the Quarantine object is to simulate diseases on a
 * group of patients.
 * It is initialized with a list of patients' health status, separated by a
 * comma. Each health status is described by one or more characters
 *
 * The characters mean:
 * H : Healthy
 * F : Fever
 * D : Diabetes
 * T : Tuberculosis
 */
public class Quarantine {
    private Treatment treatment = new Treatment(None);
    private List<Patients> patients = new ArrayList<>(5);

    public Quarantine(String patients) {
        Classifier classifier = new Classifier(patients.toUpperCase());
        this.patients = Arrays.asList(
            new Patients(Disease.Tuberculosis, classifier.getNumberOf("T")),
            new Patients(Disease.Diabetes, classifier.getNumberOf("D")),
            new Patients(Disease.Healthy, classifier.getNumberOf("H")),
            new Patients(Disease.Fever, classifier.getNumberOf("F")),
            new Patients(Disease.Death, 0)
        );
    }

    protected Quarantine(Patients... patients) {
        this.patients = Arrays.asList(patients);
    }

    public String report() {
        return String.format("F:%1$d H:%2$d D:%3$d T:%4$d X:%5$d",
            feverish().getSize(), healthy().getSize(), diabetics().getSize(), tuberculous().getSize(), death().getSize());
    }

    public void wait40Days() {
        treatment.on(this);
    }

    public void aspirin() {
        distribute(Aspirin);
    }

    public void antibiotic() {
        distribute(Antibiotic);
    }

    public void insulin() {
        distribute(Insulin);
    }

    public void paracetamol() {
        distribute(Paracetamol);
    }

    public Treatment getTreatment() {
        return treatment;
    }

    private void distribute(Medicine medicine) {
        treatment = medicine.combine(treatment);
    }

    Patients healthy() {
        return get(Disease.Healthy);
    }
    Patients feverish() {
        return get(Disease.Fever);
    }
    Patients tuberculous() {
        return get(Disease.Tuberculosis);
    }
    Patients death() {
        return get(Disease.Death);
    }
    Patients diabetics() {
        return get(Disease.Diabetes);
    }

    private Patients get(Disease disease) {
        for (Patients patient : patients) {
            if ( disease.equals(patient.getDisease()) ) {
                return patient;
            }
        }
        throw new NoSuchElementException("No group of patients with "+disease);
    }
}
