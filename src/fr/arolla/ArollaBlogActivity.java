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

public class ArollaBlogActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // creation d'une action bar tab pour la navigation entre les feeds du site du blog et du formulaire de recherche de poste.

        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
        //actionBar.setHomeAction(new IntentAction(this, createIntent(this), R.drawable.ic_title_home_demo));
        actionBar.setTitle("Blog");


        final ActionBar.Action otherAction = new ActionBar.IntentAction(this, createIntent(this), R.drawable.actionbar_btn_normal);
        actionBar.addAction(otherAction);

        //url = new URL("http://thibault-koprowski.fr/feed");
        //url = new URL("http://www.arolla.fr/feed/");
        //url = new URL("http://www.arolla.fr/blog/feed/");

        ArrayList<Feed> feeds = ContainerData.getFeeds("http://www.arolla.fr/blog/feed/");
        for (Feed feed : feeds) {
            Log.e("feedPlayer", feed.toString());
        }
        ListFeedAdapter lfa = new ListFeedAdapter(this, feeds);
        ((ListView) findViewById(R.id.listFeed)).setAdapter(lfa);

    }

    public Intent createIntent(Context context) {
        Intent i = new Intent(context, ArollaMain.class);
        //this.startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }

}
