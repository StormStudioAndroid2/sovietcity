package com.sovietcity.View;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sovietcity.Model.BuildModel;
import com.sovietcity.Presenter.World;
import com.sovietcity.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ConstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstructorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SeekBar blockSeek;
    private SeekBar stageSeek;
    private TextView blockView;
    private TextView stageView;
    private int blocks;
    private int stages;
    private BuildModel buildModel;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConstructorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConstructorFragment newInstance(String param1, String param2) {
        ConstructorFragment fragment = new ConstructorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ConstructorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_constructor, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        blocks = 2;
        stages = 2;
        this.blockSeek = (SeekBar) view.findViewById(R.id.seek_block);
        this.stageSeek = (SeekBar) view.findViewById(R.id.seek_stage);
        this.blockView = (TextView) view.findViewById(R.id.text_block);
        this.stageView = (TextView) view.findViewById(R.id.text_stage);
        this.blockView.setText("Кол-во блоков: " + blocks);
        this.stageView.setText("Кол-во этажей: " + stages);
        blockSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    blocks = progress + 2;
                    blockView.setText("Кол-во блоков: " + blocks);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        stageSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    stages = progress + 2;
                    stageView.setText("Кол-во этажей: " + stages);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                World world = mainActivity.getWorld();

                mainActivity.getWorld().getBuildManager().startBuild(buildModel, world.getCashManager(), blocks, stages);

                boolean b = buildModel.getBuildComponent().payForBuilding(world.getCashManager());
                if (b) {
                    mainActivity.surfaceTransaction();
                } else
                    Toast.makeText(getActivity().getApplicationContext(), "Не хватает денег!", Toast.LENGTH_LONG).show();


            }

        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setBuildModel(BuildModel buildModel) {
        this.buildModel = buildModel;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
