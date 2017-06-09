package zinnur.iot.rockylabs.asphalt.domain.repository;

import com.annimon.stream.function.Supplier;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.realm.Realm;
import io.realm.exceptions.RealmException;
import rx.functions.Func1;

/**
 * Created by Zinnur on 04.06.17.
 */

public class RealmController {


    private final Realm realmProvider;

    public RealmController(Realm realmProvider) {
        this.realmProvider = realmProvider;
    }

    public <T> Observable<T> executeTransaction(final Func1<Realm, T> f) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull  ObservableEmitter<T> emitter) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                T object;
                realm.beginTransaction();
                try{
                    object = f.call(realm);
                    realm.commitTransaction();
                }catch (RuntimeException e) {
                    realm.cancelTransaction();
                    emitter.onError(new RealmException("Error during transaction.", e));
                    return;
                } catch (Error e) {
                    realm.cancelTransaction();
                    emitter.onError(e);
                    return;
                }
                emitter.onNext(object);
                emitter.onComplete();
                realm.close();
            }
        });
    }



    public void clearRealmInstance() {
        realmProvider.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }
}