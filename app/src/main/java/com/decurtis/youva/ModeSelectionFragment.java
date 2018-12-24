package com.decurtis.youva;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Garima Chamaria on 21/12/18.
 */
public class ModeSelectionFragment extends Fragment {
    private View mView;
    private String name;
    private TextView mHeading;
    private ImageView mBusinessPerson, mIndividual;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.mode_selection_fragment, container, false);
        name = getArguments().getString("name");
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHeading = mView.findViewById(R.id.heading);
        mBusinessPerson = (ImageView) mView.findViewById(R.id.img_business_person);
        mIndividual = (ImageView) mView.findViewById(R.id.img_individual);

        String[] substring = name.split(" ");
        mHeading.setText("Hi " + substring[0] +"! "+ AppConstant.SELECT_MODE);

        mBusinessPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
