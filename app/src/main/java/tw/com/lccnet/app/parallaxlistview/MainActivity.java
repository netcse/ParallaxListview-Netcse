package tw.com.lccnet.app.parallaxlistview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private TextView stickyView;
    private ListView listView;
    private View heroImageView;
    private View stickyViewSpace;
    private int MAX_ROWS = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        heroImageView = findViewById(R.id.imageView);
        stickyView = (TextView) findViewById(R.id.textView);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listHeader = inflater.inflate(R.layout.list_header, null);
        stickyViewSpace = listHeader.findViewById(R.id.stickyView);

        listView.addHeaderView(listHeader);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                return;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (listView.getFirstVisiblePosition() == 0) {
                    View firstChid = listView.getChildAt(0);
                    float topY = 0f;
                    if (firstChid != null) {
                        topY = firstChid.getTop();
                    }

                    int heroTopY = stickyViewSpace.getTop();
                    stickyView.setY(Math.max(0f, (float)heroTopY + (float)topY));
                    heroImageView.setY(topY * 0.5f);
                }
            }
        });

        List<String> modelList = new ArrayList<>();
        for (int i = 0; i < MAX_ROWS; i++) {
            modelList.add("List Item" + i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_low, modelList);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
