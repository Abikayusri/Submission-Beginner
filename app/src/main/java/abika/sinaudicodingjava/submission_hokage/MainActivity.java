package abika.sinaudicodingjava.submission_hokage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import abika.sinaudicodingjava.submission_hokage.adapter.CardViewHokageAdapter;
import abika.sinaudicodingjava.submission_hokage.adapter.GridHokageAdapter;
import abika.sinaudicodingjava.submission_hokage.adapter.ListHokageAdapter;
import abika.sinaudicodingjava.submission_hokage.listener.ItemClickSupport;
import abika.sinaudicodingjava.submission_hokage.model.Hokage;
import abika.sinaudicodingjava.submission_hokage.model.HokageData;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Hokage> list;
    final String STATE_TITLE = "state_title";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();

        /*
        Gunakanlah savedinstancestate untuk menjaga data ketika terjadi config changes
         */
        if (savedInstanceState == null) {
            /*
            Pada saat pertama kali activity dijalankan,
            Ambil data dari method getListData, kemudian tampilkan recyclerviewlist
             */
            setActionBarTitle(title);
            list.addAll(HokageData.getListData());
            showRecyclerList();
            mode = R.id.action_list;

        } else {
            /*
            Jika terjadi config changes maka ambil data yang dikirimkan dari saveinstancestate
             */
            title = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Hokage> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);

            /*
            Set data untuk title, list, dan mode yang dipilih
             */
            setActionBarTitle(title);
            list.addAll(stateList);
            setMode(stateMode);
        }

    }

    private void showSelectedHokage(Hokage Hokagennya) {
        Intent moveWithObjectIntent = new Intent(MainActivity.this, DescriptionActivity.class);
        moveWithObjectIntent.putExtra(DescriptionActivity.EXTRA_HOKAGE, Hokagennya);
        startActivity(moveWithObjectIntent);

        Toast.makeText(this, "Kamu memilih " + Hokagennya.getName(), Toast.LENGTH_SHORT).show();
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListHokageAdapter listHokageAdapter = new ListHokageAdapter(this);
        listHokageAdapter.setListHokage(list);
        rvCategory.setAdapter(listHokageAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedHokage(list.get(position));
            }
        });
    }

    private void showRecyclerGrid() {
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridHokageAdapter gridHokageAdapter = new GridHokageAdapter(this);
        gridHokageAdapter.setListHokage(list);
        rvCategory.setAdapter(gridHokageAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedHokage(list.get(position));
            }
        });
    }

    private void showRecyclerCardView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewHokageAdapter cardViewHokageAdapter = new CardViewHokageAdapter(this);
        cardViewHokageAdapter.setListHokage(list);
        rvCategory.setAdapter(cardViewHokageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        setMode(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    /*
    Method ini digunakan untuk seleksi mode yang dipilih
     */
    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        /*
        Simpan jenis recyclerview yang sudah dipilih ke dalam variable mode
         */
        mode = selectedMode;
        setActionBarTitle(title);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, title);
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
}
