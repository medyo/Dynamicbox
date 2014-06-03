package mehdi.sakout.dynamicboxexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import mehdi.sakout.dynamicbox.DynamicBox;

/**
 * Created by Medyo.
 */
public class ListViewActivity extends ActionBarActivity {

    DynamicBox box;
    Button btn_loadingView;
    Button btn_customException;
    Button btn_noInternet;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView lv = (ListView)findViewById(R.id.listView);

        // Setup by Box
        box = new DynamicBox(this,lv); // or new DynamicBox(this,R.id.listView)
        box.setLoadingMessage("Loading your music ...");
        View emptyCollectionView = getLayoutInflater().inflate(R.layout.music_not_found, null, false);
        box.addCustomView(emptyCollectionView,"music_not_found");
        box.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Retry button clicked :)", Toast.LENGTH_SHORT).show();
            }
        });

        box.showLoadingLayout();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                box.showCustomView("music_not_found");
            }
        }, 2000);


    }


}