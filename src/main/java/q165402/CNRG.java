package q165402;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CNRG {

    static String fileName;
    static File inputFile;
    static PrintStream write;
    static int[] subnetMask = new int[4];

    public static void main(String[] args) throws FileNotFoundException {
        int userChoice;
        String ip;
        init();

        pickRoutingProtocol();

    }

    private static void pickRoutingProtocol() throws FileNotFoundException {
        Scanner Input = new Scanner(System.in);

        int userChoice;

        System.out.println("Pick your routing protocol" + "\n1. OSPF v2" + "\n2. EIGRP v4");
        userChoice = Input.nextInt();

        switch (userChoice) {
        case 1:
            write.println("enable");
            write.println("conf t");
            write.println("router ospf 1");
            break;

        case 2:
            write.println("enable");
            write.println("conf t");
            write.println("router eigrp 1");

            break;
        }

        generateCIDR(userChoice);
    }

    private static void generateCIDR(int userChoice) throws FileNotFoundException {
        Scanner Read = new Scanner(new File(fileName + ".txt"));

        boolean done = false;
        String line, ipcidr, ip, port;
        int cidr;
        ip = " ";

        subnetMask[0] = 0;
        subnetMask[1] = 0;
        subnetMask[2] = 0;
        subnetMask[3] = 0;

        while (!done) {
            if (Read.hasNextLine()) {
                line = Read.nextLine();

                Scanner Scan = new Scanner(line);
                Scanner Scan2 = new Scanner(line).useDelimiter(",");

                Scan.next();//no
                ipcidr = Scan.next();//ip and cidr
                Scan2.next();// no
                port = Scan2.next();//port

                //Splits the ipcidr line and stores it in an array
                String[] s = ipcidr.split("/");
                ip = s[0];
                cidr = Integer.parseInt(s[1]);

                //re initialize array

                subnetMask[0] = 0;
                subnetMask[1] = 0;
                subnetMask[2] = 0;
                subnetMask[3] = 0;

                while (cidr <= 32 && cidr >= 25) {
                    subnetMask[3] += 1;
                    cidr--;
                }
                while (cidr <= 24 && cidr >= 17) {
                    subnetMask[2] += 1;
                    cidr--;
                }
                while (cidr <= 16 && cidr >= 9) {
                    subnetMask[1] += 1;
                    cidr--;
                }
                while (cidr <= 8 && cidr >= 1) {
                    subnetMask[0] += 1;
                    cidr--;
                }

                generateSubnetMask();
                generateWildCardMask(userChoice, ip);

            } else{
                done = true;
            }
        }
        write.println("end");

    }

    private static void generateSubnetMask() {
        for (int i = 0;i < 4;i++) {
            switch (subnetMask[i]) {
            case 8:
                subnetMask[i] = 255;
                break;
            case 7:
                subnetMask[i] = 254;
                break;
            case 6:
                subnetMask[i] = 252;
                break;
            case 5:
                subnetMask[i] = 248;
                break;
            case 4:
                subnetMask[i] = 240;
                break;
            case 3:
                subnetMask[i] = 224;
                break;
            case 2:
                subnetMask[i] = 192;
                break;
            case 1:
                subnetMask[i] = 128;
                break;
            default:
                subnetMask[i] = 0;
                break;
            }
        }
    }

    private static void generateWildCardMask(int userChoice, String ip) {
        if (userChoice == 1)//ospf
        {
            write.println("network " + ip + " " + (255 - subnetMask[0]) + "." + (255 - subnetMask[1]) + "." +
                    (255 - subnetMask[2]) + "." + (255 - subnetMask[3]) + " area 51");
        } else//eigrp
        {
            write.println("network " + ip + " " + (255 - subnetMask[0]) + "." + (255 - subnetMask[1]) + "." +
                    (255 - subnetMask[2]) + "." + (255 - subnetMask[3]));
        }
    }

    public static void init() {
        fileName = "R1";
        inputFile = new File(fileName + ".txt");
        try {
            write = new PrintStream(fileName + "_script.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
