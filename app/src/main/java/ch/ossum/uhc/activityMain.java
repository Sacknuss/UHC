package ch.ossum.uhc;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActivityMain extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Game> list = downloadSpiele();
        ArrayAdapterMain adapter = new ArrayAdapterMain(this, android.R.layout.simple_list_item_1, list);

        final ListView listview = (ListView) findViewById(R.id.list_spiele);
        listview.setAdapter(adapter);

    }

    private ArrayList<Game> downloadSpiele(){

        final ArrayList<Game> list = new ArrayList<>();
        Integer id;
        Integer heim_tore;
        Integer gast_tore;
        String heim_team;
        String gast_team;
        String datum_string;
        DateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        Date datum;

        try{

            URL url = new URL("http://api.swissunihockey.ch/rest/v1.0/teams/428041/games/?limit=50");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String readStream = readStream(con.getInputStream());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d1 = builder.parse(new InputSource(new StringReader(readStream)));

            Node node = d1.getDocumentElement();
            NodeList nodeList = node.getChildNodes();
            for(int i = 0; i < nodeList.getLength(); i++){

                Element currentNode = (Element) nodeList.item(i);
                id = Integer.valueOf(currentNode.getAttribute("id"));
                heim_team = currentNode.getAttribute("hometeamname");
                gast_team = currentNode.getAttribute("awayteamname");
                heim_tore = Integer.valueOf(currentNode.getAttribute("goalshome"));
                gast_tore = Integer.valueOf(currentNode.getAttribute("goalsaway"));
                datum_string = currentNode.getAttribute("time") + " " + currentNode.getAttribute("date");
                datum = format.parse(datum_string);

                list.add(new Game(id,heim_team,gast_team,datum,heim_tore,gast_tore));

            }


        }catch (Exception e){

            e.printStackTrace();

        }

        return list;

    }

    private static String readStream(InputStream in) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

}
