package fr.arolla.core;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.arolla.R;

import java.util.ArrayList;

public class ListFeedAdapter extends BaseAdapter {

    // les données à afficher
    private ArrayList<Feed> feeds;

    // * Le LayoutInflater permet de parser un layout XML et de
    // * te transcoder en IHM Android. Pour respecter l'interface
    // * BaseAdapter

    private LayoutInflater inflater;

    public ListFeedAdapter(Context context, ArrayList<Feed> feeds) {
        inflater = LayoutInflater.from(context);
        this.feeds = feeds;

    }

    // * il nous faut spécifier la méthode "count()".
    // * Cette méthode permet de connaître le nombre d'items présent
    // * dans la liste.

    @Override
    public int getCount() {
        return feeds.size();
    }


    // Permet de retourner un objet contenu dans la liste

    @Override
    public Object getItem(int index) {
        return feeds.get(index);
    }

    @Override
    public long getItemId(int index) {
        return this.feeds.get(index).getId();

    }


    // * Voici la partie la plus complexe de la classe

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // * Le paramètre "convertView" permet de recycler les élements
        // * de notre liste. En effet, l'opération pour convertir un layout
        // * XML en IHM standard est très couteuse pour la plateforme Android.
        // * On nous propose ici de réutiliser des occurences créées qui ne sont
        // * plus affichées. Donc si ce paramètre est "null" alors, il faut "inflater"
        // * notre layout XML, sinon on le réutilise
        FeedView fv;

        if (convertView == null) {
            fv = new FeedView();
            convertView = inflater.inflate(R.layout.feed_view, null);

            fv.creator = (TextView) convertView.findViewById(R.id.creator);
            fv.title = (TextView) convertView.findViewById(R.id.title);
            fv.pubDate = (TextView) convertView.findViewById(R.id.pub_date);
            fv.textContent = (TextView) convertView.findViewById(R.id.text_content);
            fv.link = (TextView) convertView.findViewById(R.id.link);
            convertView.setTag(fv);

        } else {
            fv = (FeedView) convertView.getTag();
        }
        fv.creator.setText(feeds.get(position).getCreator());
        fv.pubDate.setText(feeds.get(position).getPubDate());
        fv.title.setText(feeds.get(position).getTitle());
        fv.textContent.setText(feeds.get(position).getDescription());
        fv.link.setText(feeds.get(position).getLink());

        return convertView;
    }

}

