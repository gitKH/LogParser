/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
/**
 *
 * @author khajdari
 */
public class Parser {
    private static File selectedFile;

    private StringBuilder sb;

    private ArrayList<Log> logList;

    public void chooseFile() {
        sb = new StringBuilder();
        logList = new ArrayList<>();

        JFileChooser jFileChooser = new JFileChooser();
        try {
            int result = jFileChooser.showOpenDialog(new JFrame());

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = jFileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public ArrayList<Log> getLogList() {
        return logList;
    }

    public void parseFile() {
        String[] fieldSearch = new String[3];
        fieldSearch[0] = "user.identifier";
        fieldSearch[1] = "user.typeOfPerson";
        fieldSearch[2] = "user.typeOfActor";

        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line = new String();
            firstPointOfBroke:
            while ((line = br.readLine()) != null) {

                if (line.equals("")) {
                    continue firstPointOfBroke;
                }

                if (line.equals("") == false) {
                    String id = null;
                    String status = null;
                    String person = null;
                    String actor = null;

                    for (String strSearch : fieldSearch) {
                        int startPoint = line.indexOf(strSearch); //user.identifier
                        startPoint += strSearch.concat("[").length() + 1; //user.identifier[
                        int endPoint = 0;
                        endPoint = startPoint;

                        //The scope of this loop is to determine the endpoint
                        secondPointOfBroke:
                        while (line.indexOf(strSearch.concat("=")) != -1) { //"user.identifier="
                            if (line.charAt(endPoint) == ';' || line.charAt(endPoint) == ',') {
                                break secondPointOfBroke;
                            } else {
                                endPoint++;
                            }
                        }

                        if (strSearch.equals("user.identifier")
                                && line.indexOf(strSearch) != -1) {
                            id = line.substring(startPoint, endPoint - 1);
                        }

                        if (strSearch.equals("user.typeOfPerson")
                                && line.indexOf(strSearch) != -1) {
                            person = line.substring(startPoint, endPoint - 1);
                            if (person.contains("]")) {
                                person = line.substring(startPoint, endPoint - 2);
                            }
                        }

                        if (strSearch.equals("user.typeOfActor")
                                && line.indexOf(strSearch) != -1) {
                            actor = line.substring(startPoint, endPoint - 1);
                        }
                    }

                    if (line.contains("SUCCESS")) {
                        status = "SUCCESS";
                    }

                    if (line.contains("FAILED")) {
                        status = "FAILED";
                    }

                    logList.add(new Log(id, id != null ? status : null,
                            actor, person));
                }
            }
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public void printLogs() {
        for (Log l : logList) {
            System.out.println(l.getUserID() + " ; " + l.getHttpResult());
        }
    }
}
