package jalandhar.sakshiaggarwal.myfirebasephone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    ArrayList<DataModel> dataSet;

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_views, viewGroup, false);
        //view.setOnClickListener(Home.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        TextView name=myViewHolder.name;
        name.setText(dataSet.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size(); }

    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView name;
         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView5);
        }}}
