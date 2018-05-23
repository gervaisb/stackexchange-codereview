package q182089;

import javax.swing.*;
import java.awt.event.*;


class BloodCompatibilityUi {

    private final JFrame container = new JFrame("Blood compatibility  App");
    private final JLabel usage = new JLabel("Please select your blood type : ");

    private final BloodCompatibilityService service;

    public BloodCompatibilityUi(BloodCompatibilityService service) {
        this.service = service;

        container.setSize(300, 170);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setResizable(true);
        container.setLocationRelativeTo(null);

        usage.setBounds(25, 60, 250, 20);

        JPanel mainPanel = new JPanel();
        JComboBox<String> selectBloodType = new JComboBox(service.getAllTypes().toArray());
        selectBloodType.addItemListener(e -> {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            String selected = (String) e.getItem();
            String compatibilities = service.getCompatibility(selected);
            JOptionPane.showMessageDialog(null,
                    selected+ " is compatible with " + compatibilities,
                    "Blood type", JOptionPane.INFORMATION_MESSAGE);

        });
        mainPanel.add(usage);
        mainPanel.add(selectBloodType);
        container.add(mainPanel);
        container.setVisible(true);
    }


}
