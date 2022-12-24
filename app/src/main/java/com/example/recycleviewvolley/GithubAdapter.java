package com.example.recycleviewvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder>
{
    private User[] data;
    private Context context;
    public GithubAdapter(Context context,User[] data)
    {
        this.context=context;
        this.data=data;
    }


    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_user_layout,parent,false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder holder, int position) {

        User user=data[position];
        holder.textUser.setText(user.getLogin());
        Glide.with(holder.imageUser.getContext()).load(user.getAvatarUrl()).into(holder.imageUser);
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,user.getLogin()+" was clicked",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageUser;
        TextView textUser;
        public GithubViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageUser=itemView.findViewById(R.id.imageUser);
            textUser=itemView.findViewById(R.id.txtUser);

        }
    }
}
