package com.example.fragmentnavigation.ui.StudentsAdd;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fragmentnavigation.MainActivity;
import com.example.fragmentnavigation.R;
import com.example.fragmentnavigation.model.Student;

public class StudentsAddFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    private StudentsAddViewModel mViewModel;
    private String fullName,address,gender,age;
    private EditText editTextFullName,editTextAddress,editTextAge;
    private RadioGroup radioG;
    private Button btnsubmit;





    public static StudentsAddFragment newInstance() {
        return new StudentsAddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(StudentsAddViewModel.class);
        View root = inflater.inflate(R.layout.students_add_fragment, container, false);
        editTextFullName = root.findViewById(R.id.fullname);
        editTextAddress = root.findViewById(R.id.address);
        editTextAge = root.findViewById(R.id.age);
        radioG=root.findViewById(R.id.gender);
        btnsubmit=root.findViewById(R.id.btn_save);

        radioG.setOnCheckedChangeListener(this);
        btnsubmit.setOnClickListener(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StudentsAddViewModel.class);

    }


    private boolean validate(){
        if(TextUtils.isEmpty(fullName))
        {
            editTextFullName.setError("Please enter a name");
            editTextFullName.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(age))
        {
            editTextAge.setError("Please enter age");
            editTextAge.requestFocus();
            return false;
        }

        if(!TextUtils.isDigitsOnly(age))
        {
            editTextAge.setError("Please enter age");
            editTextAge.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(address))
        {
            editTextAddress.setError("Please enter an address");
            editTextAddress.requestFocus();
            return false;
        }



        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
               return  true;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.male_radio_btn)
        {
            gender="Male";

        }
        if(i == R.id.female_radio_btn)
        { gender = "Female";

        }
        if(i== R.id.other_radio_btn)
        {
            gender ="Other";

        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_save)
        {

            fullName = editTextFullName.getText().toString();
            age=editTextAge.getText().toString();
            address=editTextAddress.getText().toString();
            if(validate())
            {
                MainActivity.st.add(new Student(fullName,age,gender,address));
                Toast.makeText(getContext(),"Student added", Toast.LENGTH_SHORT).show();



            }



        }

    }
}
