package q182089;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;


public class AssignmentSeven extends Frame {
    private JFrame container;
    private JPanel mainPanel;
    private JLabel firstLabel;
    private JComboBox<String> selectBloodType = new JComboBox<>();

    public AssignmentSeven() {
        guione();
    }

    public void guione() {
        container = new JFrame("Blood compatibility  App");
        setLayout(new FlowLayout());

        container.setVisible(true);
        container.setSize(300, 170);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setResizable(true);
        container.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        firstLabel = new JLabel("Please select your blood type : ");
        firstLabel.setBounds(25, 60, 250, 20);
        String[] bloodTypeArrayAppearance = {"O-", "O+ ", "A- ", "A+ ", "B- ", "B + ", "AB - ", "AB + "};
        HashMap<String, String> hash = new HashMap<>();
        hash.put("O-", "O-");
        hash.put("O+", "O+, O-");
        hash.put("A-", "O-, A-");
        hash.put("A+", "O-, O+, A-, A+");
        hash.put("B-", "O-, B- ");
        hash.put("B+", "O-, O+, B-, B+");
        hash.put("AB-", "O-, A-, B-, AB-");
        hash.put("AB+", "O-, O+, A-, A+, B-, B+, AB-, AB+");
        String oMinus = hash.get("O-");
        String oPlus = hash.get("O+");
        String aMinus = hash.get("A-");
        String aPlus = hash.get("A+");
        String bMinus = hash.get("B-");
        String bPlus = hash.get("B+");
        String abMinus = hash.get("AB-");
        String abPlus = hash.get("AB+");
        final JPanel mainPanel = new JPanel();

        JComboBox selectBloodType = new JComboBox(bloodTypeArrayAppearance);
        selectBloodType.addItemListener(e -> {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            Object item = selectBloodType.getSelectedItem();
            if (bloodTypeArrayAppearance[0].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[0] + " is compatibal with " + oMinus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[1].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[1] + " is compatibal with " + oPlus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[2].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[2] + " is compatibal with " + aMinus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[3].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[3] + " is compatibal with " + aPlus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[4].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[4] + " is compatibal with " + bMinus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[5].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[5] + " is compatibal with " + bPlus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[6].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[6] + " is compatibal with " + abMinus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            } else if (bloodTypeArrayAppearance[7].equals(item)) {
                JOptionPane.showMessageDialog(null, bloodTypeArrayAppearance[7] + " is compatibal with " + abPlus,
                        "Blood type", JOptionPane.INFORMATION_MESSAGE);

            }

        });


        mainPanel.add(selectBloodType);
        mainPanel.add(firstLabel);
        mainPanel.add(selectBloodType);
        container.add(mainPanel);


    }

    public static void main(String[] args) {
        new AssignmentSeven();
    }
}
