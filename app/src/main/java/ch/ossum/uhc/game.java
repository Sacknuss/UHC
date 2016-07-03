package ch.ossum.uhc;

import java.util.Date;

public class Game {

    Date datum;
    Integer heim_tore;
    Integer gast_tore;
    Integer id;
    String heim_team;
    String gast_team;


    public Game(Integer id, String heim_team, String gast_team, Date datum, Integer heim_tore, Integer gast_tore){
        this.id = id;
        this.heim_team = heim_team;
        this.gast_team = gast_team;
        this.datum = datum;
        this.heim_tore = heim_tore;
        this.gast_tore = gast_tore;
    }

}
