/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
/**
 *
 * @author khajdari
 */
public class FileGeneration {
    private Formatter f;

    public void generateFile() {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy [] HH;mm;ss")
                .format(Calendar.getInstance().getTime());
        try {
            this.f = new Formatter("GeneratedLogs of "
                    + timeStamp + ".txt");
            System.out.println("File generated successfully!");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.exit(0);
        }
    }

    public void writeGeneratedFile(Parser p) {
        try {
            for (Log l : p.getLogList()) {
                if (l.getUserID() != null) {
                    f.format(l.getUserID() + " ; " + l.getHttpResult()
                            + " ; " + l.getActor() + " ; " + l.getPerson());
                    f.format("%n");
                }
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public void closeFile() {
        f.close();
    }
}
