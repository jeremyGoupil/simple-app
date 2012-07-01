package fr.arolla;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import fr.arolla.core.ContainerData;
import fr.arolla.core.Feed;
import fr.arolla.core.ListFeedAdapter;
import fr.arolla.core.gui.ActionBar;

import java.util.ArrayList;

public class ArollaMain extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // creation d'une action bar tab pour la navigation entre les feeds du site du blog et du formulaire de recherche de poste.

        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
        actionBar.setTitle("Site");


        final ActionBar.Action otherAction = new ActionBar.IntentAction(this, createIntent(this), R.drawable.actionbar_btn_normal);
        actionBar.addAction(otherAction);

        final ActionBar.Action mainAction = new ActionBar.IntentAction(this, createIntent(this), R.drawable.actionbar_btn_normal);
        actionBar.addAction(mainAction);


        ArrayList<Feed> feeds = ContainerData.getFeeds("http://www.arolla.fr/feed/");
        for (Feed feed : feeds) {
            Log.e("feedPlayer", feed.toString());
        }
        ListFeedAdapter lfa = new ListFeedAdapter(this, feeds);
        ((ListView) findViewById(R.id.listFeed)).setAdapter(lfa);

    }

    public Intent createIntent(Context context) {
        Intent i = new Intent(context, ArollaBlogActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }

}
