package com.example.xyzreader.network;

import com.example.xyzreader.network.model.DataModel;
import com.example.xyzreader.utils.Utils;

import org.joda.time.DateTime;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by aditlal on 26/03/16.
 */
public class Item {
    public static Observable<List<DataModel>> createNetworkResponse() {
        return Observable.create(new Observable.OnSubscribe<List<DataModel>>() {
            @Override
            public void call(Subscriber<? super List<DataModel>> subscriber) {
                try {
                    List<DataModel> networkResponse = ItemService.getApi().getItems();
                    subscriber.onNext(networkResponse);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static Observable<List<DataModel>> createLocalResponses() {
        return Observable.create(new Observable.OnSubscribe<List<DataModel>>() {
            @Override
            public void call(Subscriber<? super List<DataModel>> subscriber) {
                try {
                    List<DataModel> localResponse = ItemService.getLocalItems();
                    subscriber.onNext(localResponse);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static List<DataModel> sort(List<DataModel> list) {
        Collections.sort(list, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel lhs, DataModel rhs) {
                String lhsString = lhs.getPublished_date();
                String rhsString = rhs.getPublished_date();
                DateTime lhsDate = Utils.parseDateTimeFromString(lhsString);
                DateTime rhsDate = Utils.parseDateTimeFromString(rhsString);
                return rhsDate.compareTo(lhsDate);
            }
        });
        return list;
    }
}
