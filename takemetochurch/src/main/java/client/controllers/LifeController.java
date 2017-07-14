package client.controllers;

import client.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import shared.Communication;
import shared.Message;
import shared.MessageType;
import shared.Values;

import java.net.URL;
import java.util.*;

/**
 * Created by codecadet on 13/07/17.
 */
public class LifeController implements Initializable {

    private LinkedList<TextField> textFields;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_generate_bucketlist;

    @FXML
    private Button btn_submit;

    @FXML
    private ListView<?> bucketList;

    @FXML
    private TextField toDo_1;

    @FXML
    private TextField toDo_2;

    @FXML
    private TextField toDo_3;

    @FXML
    private TextField toDo_4;

    @FXML
    private TextField toDo_5;

    @FXML
    private TextField toDo_6;

    @FXML
    private TextField toDo_7;

    @FXML
    private TextField toDo_8;

    @FXML
    private TextField toDo_9;

    @FXML
    private TextField toDo_10;

    private Communication communication;

    private LinkedList<String> toDos;

    private int i;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communication = Navigation.getInstance().getCommunication();
        toDos = new LinkedList<>();

        //unnullifie();

        textFields = new LinkedList<>();
        textFields.add(toDo_1);
        textFields.add(toDo_2);
        textFields.add(toDo_3);
        textFields.add(toDo_4);
        textFields.add(toDo_5);
        textFields.add(toDo_6);
        textFields.add(toDo_7);
        textFields.add(toDo_8);
        textFields.add(toDo_9);
        textFields.add(toDo_10);

        addToDos();
        addLifeSound();
    }

    @FXML
    public void back() {
        Navigation.getInstance().back();
    }

    @FXML
    public void submit() {

        Map<String, String> map = new HashMap<>();
        map.put(Values.TODO_1, toDo_1.getText());
        map.put(Values.TODO_2, toDo_2.getText());
        map.put(Values.TODO_3, toDo_3.getText());
        map.put(Values.TODO_4, toDo_4.getText());
        map.put(Values.TODO_5, toDo_5.getText());
        map.put(Values.TODO_6, toDo_6.getText());
        map.put(Values.TODO_7, toDo_7.getText());
        map.put(Values.TODO_8, toDo_8.getText());
        map.put(Values.TODO_9, toDo_9.getText());
        map.put(Values.TODO_10, toDo_10.getText());

        Map<String, String> map1 = new HashMap<>();

        Message message = new Message(MessageType.LIFE, (HashMap<String, String>) map);
        communication.write(message);
    }

    public void unnullifie() {
        toDo_1.setText("");
        toDo_2.setText("");
        toDo_3.setText("");
    }

    public void generateBucketList() {

        addSound();
    }

    public void addToDos() {
        toDos.add("Hack Elton Jorge");
        toDos.add("Flip on a Trampoline");
        toDos.add("Get a Tattoo");
        toDos.add("Fight a Shark");
        toDos.add("Drink Pee");
        toDos.add("Chase a Tornado");
        toDos.add("Feed a Crocodile");
        toDos.add("Compete in a Frog Jumping Contest");
        toDos.add("Cenas");
        toDos.add("Milk a Cow");
        toDos.add("Do a Hackaton");
        toDos.add("Go to Covilhã by Foot");
        toDos.add("Whale Watching");
        toDos.add("Read Terms and Conditions");
        toDos.add("Play the Cookie Game");
        toDos.add("Beat up Fazenda");
        toDos.add("Masturbate in Public");
        toDos.add("Punch Donald Trump");
        toDos.add("Overdose on Marijuana");
        toDos.add("Cure Cancer");
        toDos.add("Achieve World Peace");
        toDos.add("Have Sexual Intercourse in Space");
        toDos.add("Kick Kim Kardashian");
        toDos.add("Nail Cyrille's Cousin");
    }

    public void addSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/drumroll.mp3").toString()));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                if (i > 0) {
                    addToDos();
                }

                for (TextField textField: textFields) {
                    int number = (int) (Math.random() * toDos.size());
                    textField.setText(toDos.get(number));
                    toDos.remove(number);
                }
                i++;
            }
        });
    }

    public void addLifeSound() {
        new MediaPlayer(new Media(getClass().getResource("/life.mp3").toString())).play();
    }
}
