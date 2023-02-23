package Registri;

import Interfacce.Menu;
import Oggetti.Piatto.Piatto;

import java.util.HashMap;

public class MenuImpl implements Menu {
    HashMap<Piatto, Integer> listinoPrezzi;


    @Override
    public int ottieniPrezzoPiatto(Piatto piatto) {
        return 0;
    }
}
