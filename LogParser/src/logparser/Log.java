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
public class Log {

    private final String userID;
    private final String httpResult;
    private final String actor;
    private final String person;

    public Log(String userID, String httpResult, String actor, String person) {
        this.userID = userID;
        this.httpResult = httpResult;
        this.actor = actor;
        this.person = person;
    }

    public String getUserID() {
        return userID;
    }

    public String getHttpResult() {
        return httpResult;
    }

    public String getActor() {
        return actor;
    }

    public String getPerson() {
        return person;
    }
}
