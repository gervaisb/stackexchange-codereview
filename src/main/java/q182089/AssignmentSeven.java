package q182089;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class AssignmentSeven {
    private final JFrame container = new JFrame("Blood compatibility  App");
    private final JLabel usage = new JLabel("Please select your blood type : ");

    public AssignmentSeven() {

        container.setSize(300, 170);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setResizable(true);
        container.setLocationRelativeTo(null);

        usage.setBounds(25, 60, 250, 20);

        HashMap<String, String> hash = new LinkedHashMap<>();
        hash.put("O-", "O-");
        hash.put("O+", "O+, O-");
        hash.put("A-", "O-, A-");
        hash.put("A+", "O-, O+, A-, A+");
        hash.put("B-", "O-, B- ");
        hash.put("B+", "O-, O+, B-, B+");
        hash.put("AB-", "O-, A-, B-, AB-");
        hash.put("AB+", "O-, O+, A-, A+, B-, B+, AB-, AB+");

        final JPanel mainPanel = new JPanel();

        JComboBox<String> selectBloodType = new JComboBox(hash.keySet().toArray(new String[hash.size()]));
        selectBloodType.addItemListener(e -> {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            String item = (String) e.getItem();
            String compatibilities = hash.get(item);
            JOptionPane.showMessageDialog(null,
                    item+ " is compatible with " + compatibilities,
                    "Blood type", JOptionPane.INFORMATION_MESSAGE);

        });
        mainPanel.add(usage);
        mainPanel.add(selectBloodType);
        container.add(mainPanel);
        container.setVisible(true);
    }

    public static void main(String[] args) {
        new AssignmentSeven();
    }
}
