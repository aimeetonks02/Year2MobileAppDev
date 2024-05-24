package com.example.a21010398;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

public class bridgeAdapter extends RecyclerView.Adapter<bridgeAdapter.bridgeHolder> {
    private List<bridge> bridges = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public bridgeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bridge_item, parent, false);
        return new bridgeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull bridgeHolder holder, int position) {
        bridge currentBridge = bridges.get(position);
        holder.textViewTitle.setText(currentBridge.getName());
        holder.textViewPriority.setText(currentBridge.getStatus());
        holder.textViewDescription.setText(currentBridge.getDescription());
    }

    @Override
    public int getItemCount() {
        return bridges.size();
    }

    public void setBridges(List<bridge> bridges) {
        this.bridges = bridges;
        notifyDataSetChanged();
    }

    class bridgeHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewPriority;
        private TextView textViewDescription;

        public bridgeHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(bridges.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(bridge bridge);
    }

    public void SetItemOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
