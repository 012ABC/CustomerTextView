package com.example.customertextview.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customertextview.R;

import java.util.List;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/13 10:47
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private List<String> mList;

    private LayoutInflater inflater;

    public TestAdapter(List<String> list, Context context) {
        this.mList = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mTxt.setText(mList.get(i));
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxt = itemView.findViewById(R.id.txt);
        }
    }
}
