package q154869;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class PatientsTest {

    @Test
    public void getSize_returns_the_group_size() throws Exception {
        Patients patients = new Patients(Disease.Healthy, 5);
        assertEquals(5, patients.getSize());
    }

    @Test
    public void becomes_move_everyone_to_other() throws Exception {
        Patients from = new Patients(Disease.Healthy, 5);
        Patients to = new Patients(Disease.Healthy, 0);

        from.becomes(to);
        assertEquals(0, from.getSize());
        assertEquals(5, to.getSize());
    }

}
