package q156363;

//this is the calculator
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame {
    public static void main(String[]args){
        Calculator ob = new Calculator();
        ob.setVisible(true);
        ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ob.setSize(250,200);
    }

    private JButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, add, sub,  mul, div, slo, cls;
    private JPanel panel, panel1;
    private double temp;
    private double solTemp;
    private JTextField srn;
    Boolean addb = false ;
    Boolean subb = false ;
    Boolean divb = false ;
    Boolean mulb = false ;
    String display = "";

    public Calculator(){
        super("Clac"); //The title
        setLayout(new FlowLayout());
        srn = new JTextField(20);
        add(srn);
        panel = new JPanel();  //numbers buttons
        panel1 = new JPanel (); 
        panel.setLayout(new GridLayout(4, 3));
        panel1.setLayout(new GridLayout(3, 2));
        //the buttons
        n1 = new JButton("1");
        n1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "1");
                    }
                }
        );
        panel.add(n1);
        n2 = new JButton("2");
        n2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "2");
                    }
                }
        );
        panel.add(n2);
        n3 = new JButton("3");
        n3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "3");
                    }
                }
        );
        panel.add(n3);
        n4 = new JButton("4");
        n4.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "4");
                    }
                }
        );
        panel.add(n4);
        n5 = new JButton("5");
        n5.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "5");
                    }
                }
        );
        panel.add(n5);
        n6 = new JButton("6");
        n6.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "6");
                    }
                }
        );
        panel.add(n6);
        n7 = new JButton("7");
        n7.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "7");
                    }
                }
        );
        panel.add(n7);
        n8 = new JButton("8");
        n8.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "8");
                    }
                }
        );
        panel.add(n8);
        n9 = new JButton("9");
        n9.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "9");
                    }
                }
        );
        panel.add(n9);
        n0 = new JButton("0");
        n0.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         display = srn.getText();
                            srn.setText(display + "0");
                    }
                }
        );
        panel.add(n0);
        cls = new JButton("C");
        cls.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        srn.setText("");
                        addb = false ;
                        subb = false ;
                        mulb = false ;
                        divb = false ;

                        temp = 0;
                        solTemp =0 ;
                    }
                }
        );
        panel1.add(cls);
        add = new JButton("+");
        add.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                         temp = Double.parseDouble(srn.getText());
                            srn.setText("");
                            addb = true ;
                    }
                }
        );
        panel1.add(add);
        sub = new JButton("-");
        sub.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                         temp = Double.parseDouble(srn.getText());
                            srn.setText("");
                            subb = true ;
                    }
                }
        );
        panel1.add(sub);
        mul = new JButton("*");
        mul.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                         temp = Double.parseDouble(srn.getText());
                            srn.setText("");
                            mulb = true ;
                    }
                }
        );
        panel1.add(mul);
        div = new JButton("/");
        div.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                         temp = Double.parseDouble(srn.getText());
                            srn.setText("");
                            divb = true ;
                    }
                }
        );
        panel1.add(div);    
        slo = new JButton("=");
        slo.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        solTemp = Double.parseDouble(  srn.getText() );
                        if ( addb == true  )
                            solTemp = solTemp + temp;

                        else if ( subb == true  )
                            solTemp = solTemp - temp;
                        else if ( mulb == true  )
                            solTemp = solTemp * temp;
                        else if ( divb == true  )
                            solTemp = temp / solTemp;
                        srn.setText(  Double.toString( solTemp ) );
                        addb = false;
                        subb = false;
                        mulb = false;
                        divb= false;
                    }
                }
        );
        panel1.add(slo);
        add(panel);
        add(panel1);
    }   
}


// ------------------------------------ Main Class

