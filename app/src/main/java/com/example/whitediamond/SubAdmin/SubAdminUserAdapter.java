package com.example.whitediamond.SubAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.UserPojo;

import java.util.List;

public class SubAdminUserAdapter extends RecyclerView.Adapter<SubAdminUserAdapter.SubUserViewHolder> {

    private Context context;
    private List<UserPojo> userPojoList;

    public SubAdminUserAdapter(Context context, List<UserPojo> userPojoList) {
        this.context = context;
        this.userPojoList = userPojoList;
    }

    @NonNull
    @Override
    public SubUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subadmin_user, parent, false);
        return new SubUserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubUserViewHolder holder, int position) {

        UserPojo data = userPojoList.get(position);

        String name = data.getUser_name();
        String email = data.getEmail();
        int point = data.getDimondPoint();

        holder.mSubuserEmail.setText(email);
        holder.mSubuserName.setText(name);
        holder.mSubUserPoint.setText(point+"");
    }

    @Override
    public int getItemCount() {
        return userPojoList.size();
    }

    public class SubUserViewHolder extends RecyclerView.ViewHolder {
        private TextView mSubuserName;
        private TextView mSubuserEmail;
        private TextView mSubUserPoint;
        public SubUserViewHolder(@NonNull View itemView) {
            super(itemView);
            mSubuserName = itemView.findViewById(R.id.subuserName);
            mSubuserEmail = itemView.findViewById(R.id.subuserEmail);
            mSubUserPoint = itemView.findViewById(R.id.subUserPoint);

        }
    }
}
