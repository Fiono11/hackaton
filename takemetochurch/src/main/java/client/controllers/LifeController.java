package client.controllers;

import client.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    @FXML
    private CheckBox toDo_1_check;

    @FXML
    private CheckBox toDo_2_check;

    @FXML
    private CheckBox toDo_3_check;

    @FXML
    private CheckBox toDo_4_check;

    @FXML
    private CheckBox toDo_5_check;

    @FXML
    private CheckBox toDo_6_check;

    @FXML
    private CheckBox toDo_7_check;

    @FXML
    private CheckBox toDo_8_check;

    @FXML
    private CheckBox toDo_9_check;

    @FXML
    private CheckBox toDo_10_check;

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
        map.put(Values.TODO_1, Boolean.toString(toDo_1_check.isSelected()));
        map.put(Values.TODO_2, Boolean.toString(toDo_2_check.isSelected()));
        map.put(Values.TODO_3, Boolean.toString(toDo_3_check.isSelected()));
        map.put(Values.TODO_4, Boolean.toString(toDo_4_check.isSelected()));
        map.put(Values.TODO_5, Boolean.toString(toDo_5_check.isSelected()));
        map.put(Values.TODO_6, Boolean.toString(toDo_6_check.isSelected()));
        map.put(Values.TODO_7, Boolean.toString(toDo_7_check.isSelected()));
        map.put(Values.TODO_8, Boolean.toString(toDo_8_check.isSelected()));
        map.put(Values.TODO_9, Boolean.toString(toDo_9_check.isSelected()));
        map.put(Values.TODO_10, Boolean.toString(toDo_10_check.isSelected()));

        Message message = new Message(MessageType.LIFE_TEXT, (HashMap<String, String>) map);
        communication.write(message);

        Message message1 = new Message(MessageType.LIFE_BOOLEAN, (HashMap<String, String>) map1);
        communication.write(message1);
    }

    public void unnullifie() {
        toDo_1.setText("");
        toDo_2.setText("");
        toDo_3.setText("");
    }

    public void generateBucketList() {

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

    public void addToDos() {
        toDos.add("Eat Poop");
        toDos.add("Flip on a Trampoline");
        toDos.add("Get a Tattoo");
        toDos.add("Fight a Shark");
        toDos.add("Drink Pee");
        toDos.add("Chase a Tornado");
        toDos.add("Feed a Crocodile");
        toDos.add("Compete in a Frog Jumping Contest");
        toDos.add("Complete a Horse Jumping Obstacle");
        toDos.add("Milk a Cow");
        toDos.add("Do a Hackaton");
        toDos.add("Go to Covilhã by Foot");
        toDos.add("Whale Watching");
        toDos.add("Read Terms and Conditions");
        toDos.add("Play the Cookie Game");
        toDos.add("Be on a TV Show");
        toDos.add("Masturbate in Public");
        toDos.add("Punch Donald Trump");
        toDos.add("Overdose on Marijuana");
        toDos.add("Cure Cancer");
        toDos.add("Achieve World Peace");
        toDos.add("Have Sexual Intercourse in Space");
        toDos.add("Kick Kim Kardashian");
        toDos.add("Nail Cyrille's Cousin");
    }
}
