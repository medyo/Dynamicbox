package mehdi.sakout.dynamicboxexample;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity implements OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
        getListView().setOnItemClickListener(this);
    }


    String[] listItems = {"Simple View", "Custom View",};

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch(position){
            case 0 :
                Intent intentActivity = new Intent(MainActivity.this,ActivityActivity.class);
                startActivity(intentActivity);

                break;
            case 1 :
                Intent intentList = new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intentList);
                break;
            default: throw new IllegalArgumentException("Hold up, hold my phone :)");
        }
    }
}
