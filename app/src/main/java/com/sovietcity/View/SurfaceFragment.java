package com.sovietcity.View;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sovietcity.Presenter.SovietView;
import com.sovietcity.Presenter.World;
import com.sovietcity.R;


public class SurfaceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private World world;
    private SovietView surfaceView;
    private MainActivity mainActivity;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SurfaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SurfaceFragment newInstance(String param1, String param2) {
        SurfaceFragment fragment = new SurfaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public SurfaceFragment() {
        // Required empty public constructor
    }
    public MainActivity getMainActivity() {
        return this.mainActivity;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surface, container, false);
        final SovietView sovietView = (SovietView) view.findViewById(R.id.surface);
        this.surfaceView = sovietView;
        //    инициализация surfaceView
        this.surfaceView.setSurfaceFragment(this);
        sovietView.setWorld(world);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    public SovietView getSurfaceView() {
        return this.surfaceView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setWorld(World world) {
        this.world = world;
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

    public class InformationThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
}