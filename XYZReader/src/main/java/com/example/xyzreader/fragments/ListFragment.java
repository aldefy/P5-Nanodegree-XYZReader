package com.example.xyzreader.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.adapter.ItemAdapter;
import com.example.xyzreader.interfaces.ItemClickListener;
import com.example.xyzreader.network.Item;
import com.example.xyzreader.network.model.DataModel;
import com.example.xyzreader.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by aditlal on 26/03/16.
 */
public class ListFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ItemAdapter adapter;
    private ArrayList<DataModel> resultList;
    private ItemClickListener itemClickListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        init(savedInstanceState);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;
        if (context instanceof Activity) {
            a = (Activity) context;
            try {
                itemClickListener = (ItemClickListener) a;
            } catch (ClassCastException e) {
                throw new ClassCastException(a.getClass().toString()
                        + " must implement ItemClickListener");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void init(Bundle savedInstanceState) {
        resultList = new ArrayList<>();
        GridLayoutManager staggeredGridLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new ItemAdapter(getActivity(), resultList, itemClickListener);
        mRecyclerView.setAdapter(adapter);
        if (savedInstanceState != null) {
            resultList.addAll((ArrayList<DataModel>) savedInstanceState.get(Utils.LIST_SAVE_INSTANCE));
        } else
            fetchLocalData();
    }

    public void fetchLocalData() {
        Item.createLocalResponses()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<DataModel>, List<DataModel>>() {
                    @Override
                    public List<DataModel> call(List<DataModel> list) {
                        return Item.sort(list);
                    }
                })
                .subscribe(new Subscriber<List<DataModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DataModel> results) {
                        if (results.size() != 0) {
                            resultList.clear();
                            resultList.addAll(results);
                            adapter.notifyDataSetChanged();
                        } else {
                            fetchNetworkData();
                        }
                    }
                });

    }


    public void fetchNetworkData() {
        Item.createNetworkResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<DataModel>, List<DataModel>>() {
                    @Override
                    public List<DataModel> call(List<DataModel> list) {
                        return Item.sort(list);
                    }
                })
                .subscribe(new Subscriber<List<DataModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DataModel> results) {
                        resultList.clear();
                        resultList.addAll(results);
                        for (DataModel model : results) {
                            model.save();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    // Fires when a configuration change occurs and fragment needs to save state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Utils.LIST_SAVE_INSTANCE,
                (ArrayList<? extends Parcelable>) resultList);
    }

}
