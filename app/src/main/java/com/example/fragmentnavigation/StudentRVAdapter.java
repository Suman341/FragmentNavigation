package com.example.fragmentnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentnavigation.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.MyHolder>{

    List<Student> stud_list = new ArrayList<>();
    Context contexts;

    public StudentRVAdapter(List<Student> stud_list, Context contexts) {
        this.stud_list = stud_list;
        this.contexts = contexts;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlayout,parent,false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        final  Student stud = stud_list.get(position);
        holder.fullname.setText(stud.getFullname());
        holder.age.setText(stud.getAge());
        holder.address.setText(stud.getAddress());
        holder.gender.setText(stud.getGender());
        holder.imageview.setImageDrawable(contexts.getResources().getDrawable(R.drawable.ic_delete));

        String gen= stud.getGender();

        if(gen=="Male"){
            holder.imageview.setImageResource(R.drawable.boy);


        }
        else if(gen=="Female"){
            holder.imageview.setImageDrawable(contexts.getResources().getDrawable(R.drawable.girl));

        }
        else{
            holder.imageview.setImageDrawable(contexts.getResources().getDrawable(R.drawable.girl));


        }



        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contexts,"Hello: "+stud.getFullname(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student = stud_list.get(position);

                stud_list.remove(position);

                notifyItemRemoved(position);

                notifyItemRangeChanged(position,stud_list.size());


                Toast.makeText(contexts,"Removed Succestully: " + student.getFullname(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return stud_list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageview,delete;
        TextView fullname,age,address,gender;
        public MyHolder(@NonNull View itemView) {

            super(itemView);
            imageview= itemView.findViewById(R.id.stimage);
            fullname = itemView.findViewById(R.id.stfullname);
            age=itemView.findViewById(R.id.st_age);
            address=itemView.findViewById(R.id.staddress);
            gender =itemView.findViewById(R.id.stgender);
            delete=itemView.findViewById(R.id.stdelete);

        }
    }
}
