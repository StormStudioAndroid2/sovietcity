package com.sovietcity.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sovietcity.Adapters.ResourceAdapter;
import com.sovietcity.Model.Resource;
import com.sovietcity.Model.StockFunctionComponent;
import com.sovietcity.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StockResourceShooserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockResourceShooserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockResourceShooserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MainActivity mainActivity;
    private StockFunctionComponent stockFunctionComponent;
    private ArrayList<Resource> resources;
    private Resource resource;
    private View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StockResourceShooserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StockResourceShooserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockResourceShooserFragment newInstance(String param1, String param2) {
        StockResourceShooserFragment fragment = new StockResourceShooserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//    выбор ресурса, который будет экспортировать склад
        final View view = inflater.inflate(R.layout.fragment_stock_resource_shooser, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view_stock);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(mainActivity));
        ResourceAdapter adapter = new ResourceAdapter(resources, this);
        rv.setAdapter(adapter);
        this.view = view;
        chooseResource(resources.get(0));
        Button chooseButton = (Button) view.findViewById(R.id.choose_res);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockFunctionComponent.setMaterial(resource);
                RadioButton radioButton = (RadioButton)view.findViewById(R.id.export_res);
                if (radioButton.isChecked()) {
                    stockFunctionComponent.setImport(false);
                } else {
                    stockFunctionComponent.setImport(true);
                }
                mainActivity.surfaceTransaction();

            }
        });
        Button cancelButton = (Button) view.findViewById(R.id.cancel_choose);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void chooseResource(Resource resource) {
        ImageView imageView = (ImageView) view.findViewById(R.id.mat_choose);
        imageView.setImageBitmap(resource.getImage());
        TextView textView = (TextView) view.findViewById(R.id.mat_choose_name);
        textView.setText("Выбран ресурс: "+resource.getName());
        textView = (TextView) view.findViewById(R.id.mat_choose_price);
        textView.setText("Цена ресурса: "+Integer.toString(resource.getPrice()));
        this.resource = resource;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setStockFunctionComponent(StockFunctionComponent stockFunctionComponent) {
        this.stockFunctionComponent = stockFunctionComponent;
    }
}
