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
 * {@link RoadConstructorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RoadConstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoadConstructorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BuildModel buildModel;
    private Button button;
    private SeekBar blockSeek;
    private int blocks;
    private TextView blockView;
    private MainActivity mainActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoadConstructorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoadConstructorFragment newInstance(String param1, String param2) {
        RoadConstructorFragment fragment = new RoadConstructorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RoadConstructorFragment() {
        // Required empty public constructor
    }

    public void setBuildModel(BuildModel buildModel) {
        this.buildModel = buildModel;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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
        View v = inflater.inflate(R.layout.fragment_road_constructor, container, false);
        this.button = (Button) v.findViewById(R.id.road_button);
        blocks = 2;
        blockSeek = (SeekBar) v.findViewById(R.id.seek_block_road);
        blockView = (TextView) v.findViewById(R.id.text_block_road);
        blockView.setText("Кол-во клеток: " + blocks);
        blockSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blocks = progress + 2;
                blockView.setText("Кол-во клеток: " + blocks);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //    в данном фрагменте указывается инфа о клетках, сколько занимают дороги и проверяется корректность
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                World world = mainActivity.getWorld();
                mainActivity.getWorld().getBuildManager().startBuild(buildModel, world.getCashManager(), blocks);
                boolean b = buildModel.getBuildComponent().payForBuilding(world.getCashManager());
                if (b) {
                    mainActivity.surfaceTransaction();
                } else
                    Toast.makeText(getActivity().getApplicationContext(), "Не хватает денег!", Toast.LENGTH_LONG).show();

            }
        });
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
