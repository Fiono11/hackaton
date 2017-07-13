package server.model;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Death {

    private long id;
    private String ceremony;
    private BodyTreatment bodyTreatment;
    private boolean music;
    private int numberOfGuests;
    private boolean religion;

    public Death(){

    }

    public Death(String ceremony, BodyTreatment bodyTreatment, boolean music, int numberOfGuests, boolean religion) {
        this.ceremony = ceremony;
        this.bodyTreatment = bodyTreatment;
        this.music = music;
        this.numberOfGuests = numberOfGuests;
        this.religion = religion;
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

    public BodyTreatment getBodyTreatment() {
        return bodyTreatment;
    }

    public void setBodyTreatment(BodyTreatment bodyTreatment) {
        this.bodyTreatment = bodyTreatment;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isReligion() {
        return religion;
    }

    public void setReligion(boolean religion) {
        this.religion = religion;
    }
}
