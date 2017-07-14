package server.model;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Death implements Data {

    private long id;
    private String ceremony;
    private String bodyTreatment;
    private String music;
    private String religion;
    private String specialRequest;
    private int numberOfGuests;
    private int budget;

    public Death() {
    }

    public Death(String ceremony, String bodyTreatment, String music, int numberOfGuests, String religion, int budget, String specialRequest) {
        this.ceremony = ceremony;
        this.bodyTreatment = bodyTreatment;
        this.music = music;
        this.numberOfGuests = numberOfGuests;
        this.religion = religion;
        this.budget = budget;
        this.specialRequest = specialRequest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCeremony() {
        return ceremony;
    }

    public void setCeremony(String ceremony) {
        this.ceremony = ceremony;
    }

    public String getBodyTreatment() {
        return bodyTreatment;
    }

    public void setBodyTreatment(String bodyTreatment) {
        this.bodyTreatment = bodyTreatment;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }
}
