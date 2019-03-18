package jalandhar.sakshiaggarwal.myfirebasephone;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Home extends Fragment {
    RecyclerView recyc;
    static RecyclerView.Adapter adapter;
    public Home() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        final ArrayList<DataModel> data = new ArrayList<DataModel>();
        recyc = view.findViewById(R.id.myrecyclerview);

       /* ListAdapter listAdap=new ListAdapter();
        recyc.setAdapter(listAdap);*/


        RecyclerView.LayoutManager layoutMang = new LinearLayoutManager(getActivity());
        recyc.setLayoutManager(layoutMang);
        recyc.setItemAnimator(new DefaultItemAnimator());

        for (int x = 0; x < RecyclerViewData.title.length; x++) {
            data.add(new DataModel(RecyclerViewData.title[x], RecyclerViewData.id_[x]));
        }
        adapter = new CustomAdapter(data);
        recyc.setAdapter(adapter);
        return view; }
}




