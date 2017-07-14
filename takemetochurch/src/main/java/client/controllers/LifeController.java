package client.controllers;

import client.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

        Message message = new Message(MessageType.LIFE_C, (HashMap<String, String>) map);
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
        toDos.add("Flip on a trampoline");
        toDos.add("Get a tattoo");
        toDos.add("Fight a Shark");
        toDos.add("Find Fiona");
        toDos.add("Chase a tornado");
        toDos.add("Feed a crocodile");
        toDos.add("Compete in a frog jumping contest");
        toDos.add("Cenas");
        toDos.add("Milk a cow");
        toDos.add("Do a Hackaton");
        toDos.add("Go to Covilhã by foot");
        toDos.add("Whale watching");
        toDos.add("Read Terms and Conditions");
        toDos.add("Play the cookie game");
        toDos.add("Be on a TV show");
        toDos.add("Masturbate in public");
        toDos.add("Play the Cookie Game");
        toDos.add("Beat up Fazenda");
        toDos.add("Masturbate in Public");
        toDos.add("Punch Donald Trump");
        toDos.add("Overdose on marijuana");
        toDos.add("Cure cancer");
        toDos.add("Achieve world peace");
        toDos.add("Have sexual intercourse in space");
        toDos.add("Kick Kim Kardashian");
        toDos.add("Nail Cyrille's cousin");
    }

    public void addSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sounds/drumroll.mp3").toString()));

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
        new MediaPlayer(new Media(getClass().getResource("/sounds/life.mp3").toString())).play();
    }
}
