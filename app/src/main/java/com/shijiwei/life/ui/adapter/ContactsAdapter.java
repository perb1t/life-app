package com.shijiwei.life.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shijiwei.life.R;
import com.shijiwei.life.data.model.User;

import java.util.List;

/**
 * Created by shijiwei on 2020-02-28.
 *
 * @Desc:
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<User> users;
    private Context context;

    public ContactsAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contacts,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = users.get(i);
        viewHolder.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
