package ch.ossum.uhc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ArrayAdapterMain extends ArrayAdapter<Game> {

    DateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm");

    public ArrayAdapterMain(Context context, int resource, List<Game> games){
        super(context,resource,games);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.arrayadapter_main, null);

        }

        Game g = getItem(position);

        if(g != null){

            v.setOnClickListener(listener);

            TextView t1 = (TextView) v.findViewById(R.id.heim_team);
            if(t1 != null){
                t1.setText(g.heim_team);
            }

            TextView t2 = (TextView) v.findViewById(R.id.gast_team);
            if(t2 != null){
                t2.setText(g.gast_team);
            }

            TextView t3 = (TextView) v.findViewById(R.id.datum);
            if(t3 != null){
                t3.setText(format.format(g.datum));
            }

            TextView t4 = (TextView) v.findViewById(R.id.heim_tore);
            if(t4 != null){
                t4.setText(g.heim_tore.toString());
            }

            TextView t5 = (TextView) v.findViewById(R.id.gast_tore);
            if(t5 != null){
                t5.setText(g.gast_tore.toString());
            }

        }

        return v;

    }

    View.OnClickListener listener = new View.OnClickListener(){

        public void onClick(View v) {



        }

    };

}
