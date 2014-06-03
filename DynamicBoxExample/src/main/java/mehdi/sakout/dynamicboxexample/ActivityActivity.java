package mehdi.sakout.dynamicboxexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import mehdi.sakout.dynamicbox.DynamicBox;

/**
 * Created by Medyo.
 */
public class ActivityActivity extends ActionBarActivity {
    DynamicBox box ;
    boolean firstLaunch = false;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Drop this line ! it's a bomb
        //setContentView(R.layout.activity_activity);

        box = new DynamicBox(this,R.layout.activity_activity);

        // Setup my box
        box.setLoadingMessage("Loading content...");
        box.setOtherExceptionTitle("Error");
        box.setOtherExceptionMessage("An error has occurred while fetching data, please try again ...");
        box.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHeavyWork();
            }
        });

        // Call my fake HeavyWork Method
        myHeavyWork();


    }
    private void myHeavyWork(){

        box.showLoadingLayout();

        // Wait 2 seconds before showing result
        new Handler().postDelayed(new Runnable() {
            public void run() {

                // I made this trick just to get an error Exception first then the activity content after.
                firstLaunch = !firstLaunch;
                if(firstLaunch)
                    box.showExceptionLayout();
                else
                    box.hideAll();
            }
        }, 2000);

    }
}