package fr.arolla;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import fr.arolla.core.ContainerData;
import fr.arolla.core.Feed;
import fr.arolla.core.ListFeedAdapter;

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

        //  ActionBar actionBar;
        //  actionBar = (ActionBar) findViewById(R.id.actionbar);

        //   actionBar.setHomeAction(new fr.arolla.core.gui.ActionBar.IntentAction(this, ArollaMain.createIntent(this), R.drawable.ic_title_home_default));
        //   actionBar.addAction(new fr.arolla.core.gui.ActionBar.IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default));
        //url = new URL("http://thibault-koprowski.fr/feed");
        //url = new URL("http://www.arolla.fr/feed/");
        //url = new URL("http://www.arolla.fr/blog/feed/");

        ArrayList<Feed> feeds = ContainerData.getFeeds("http://www.arolla.fr/feed/");
        for (Feed feed : feeds) {
            Log.e("feedPlayer", feed.toString());
        }
        ListFeedAdapter lfa = new ListFeedAdapter(this, feeds);
        ((ListView) findViewById(R.id.listFeed)).setAdapter(lfa);

    }

}
