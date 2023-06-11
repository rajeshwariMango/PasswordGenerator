package com.example.passwordgenerator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder> {
    private List<String> passwordList;

    public PasswordAdapter(List<String> passwordList) {
        this.passwordList = passwordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_password, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String password = passwordList.get(position);
        holder.passwordTextView.setText(password);
    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView passwordTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            passwordTextView = itemView.findViewById(R.id.passwordTextView);
        }
    }
}
