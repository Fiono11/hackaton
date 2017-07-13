package server;

import java.util.HashMap;

/**
 * Created by codecadet on 13/07/17.
 */
public interface Strategy {

    void action(HashMap<String,String> map);
}
