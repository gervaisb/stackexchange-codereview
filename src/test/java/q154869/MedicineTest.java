package q154869;

import org.junit.Test;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static q154869.Disease.Death;
import static q154869.Disease.Diabetes;
import static q154869.Disease.Fever;
import static q154869.Disease.Healthy;
import static q154869.Disease.Tuberculosis;
import static q154869.Medicine.Antibiotic;
import static q154869.Medicine.Aspirin;
import static q154869.Medicine.Insulin;
import static q154869.Medicine.None;
import static q154869.Medicine.Paracetamol;

public class MedicineTest {
    Patients tuberculous = spy(new Patients(Tuberculosis, 1));
    Patients diabetics = spy(new Patients(Diabetes, 1));
    Patients healthy = spy(new Patients(Healthy, 1));
    Patients feverish = spy(new Patients(Fever, 1));
    Patients death = spy(new Patients(Death, 1));

    Quarantine quarantine = new Quarantine(tuberculous, diabetics, healthy, feverish, death);


    @Test
    public void none_kill_diabetics() {
        None.on(quarantine);
        verify(diabetics).becomes(death);
    }

    @Test
    public void aspirin_cure_feverish() {
        Aspirin.on(quarantine);
        verify(feverish).becomes(healthy);
    }

    @Test
    public void antibiotic_cure_tuberculosis() {
        Antibiotic.on(quarantine);
        verify(tuberculous).becomes(healthy);
    }

    @Test
    public void insuline_prevent_diabetic_to_die() {
        Insulin.on(quarantine);
        verify(diabetics, never()).becomes(death);
    }

    @Test
    public void paracetamol_cure_feverish() {
        Paracetamol.on(quarantine);
        verify(feverish).becomes(healthy);
    }

}
