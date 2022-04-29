package com.example.myapplication3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class SdAdapter extends RecyclerView.Adapter<SdAdapter.ViewHolder> {
    protected List<Map<String, Object>> dataSet;

    public SdAdapter(List<Map<String, Object>> data) {
        dataSet = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.list_item_imageview1);
            mTextView = itemView.findViewById(R.id.list_item_textview1);
        }

        public ImageView getImageView() {
            return mImageView;
        }

        public TextView getTextView() {
            return mTextView;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View new_list_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(new_list_item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                String clickedName = (String) dataSet.get(position).get("title");

                Intent intent = new Intent(parent.getContext(), DisplayMessageActivity.class);
                Log.d("yahaha", String.valueOf(position));
                intent.putExtra("hahahaga", clickedName);
                parent.getContext().startActivity(intent);
            }

        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                String clickedName = (String) dataSet.get(position).get("title");
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("这是一个简单的Dialog").setMessage("你长按的是:" + clickedName)
                        .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                            }
                        })
                        .setNegativeButton("不好", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        }).show();
                Log.d("TAG", "onLongClick: ");
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImageView().setImageResource((int) dataSet.get(position).get("thumb"));
        holder.getTextView().setText((String) dataSet.get(position).get("title"));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
