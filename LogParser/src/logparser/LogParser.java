/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

/**
 *
 * @author khajdari
 */
public class LogParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //All declaration is in main in order to control the flow
        try {
            System.out.println("Log Parser Application started");
            Parser r = new Parser();
            FileGeneration fg = new FileGeneration();

            try {
                r.chooseFile();
            } catch (Exception e) {
                System.out.println("Error of file chose!");
                System.out.println("Log Parser Application stoped");
                System.exit(0);
            }
            try {
                r.parseFile();
            } catch (Exception e) {
                System.out.println("Error of file read!");
                System.out.println("Log Parser Application stoped");
                System.exit(0);
            }

            try {
                fg.generateFile();
            } catch (Exception e) {
                System.out.println("Error on new file creation!");
                System.out.println("Log Parser Application stoped");
                System.exit(0);
            }

            try {
                fg.writeGeneratedFile(r);
            } catch (Exception e) {
                System.out.println("Error on new file writing!");
                System.out.println("Log Parser Application stoped");
                System.exit(0);
            }

            try {
                fg.closeFile();
            } catch (Exception e) {
                System.out.println("Error on new file save!");
                System.out.println("Log Parser Application stoped");
                System.exit(0);
            }

            System.out.println("Log Parser Application stoped");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            System.out.println("Log Parser Application stoped");
            System.exit(0);
        }
    }
    
}
