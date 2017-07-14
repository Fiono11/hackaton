package server.model;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Death implements Data {

    private long id;
    private String bodyTreatment;
    private String religion;
    private String food;
    private String music;
    private String numberOfGuests;
    private String specialRequest;
    private String budget;

    public Death() {
    }

    public Death(String bodyTreatment, String religion, String food, String music, String numberOfGuests, String specialRequest , String budget) {
        this.food = food;
        this.bodyTreatment = bodyTreatment;
        this.music = music;
        this.numberOfGuests = numberOfGuests;
        this.religion = religion;
        this.budget = budget;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
