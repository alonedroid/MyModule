package alonedroid.com.mymodule;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;

import alonedroid.com.mymodule.scene.custom_view.CustomViewActivity;
import alonedroid.com.mymodule.scene.custom_view.CustomViewActivity_;
import alonedroid.com.mymodule.scene.reactive.ObservableProperty;
import alonedroid.com.mymodule.scene.realm.User;
import alonedroid.com.mymodule.scene.volley_jackson.VolleyActivity;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.observables.AndroidObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {
    final private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private ObservableProperty<String> s = new ObservableProperty<>(new String());

    @AfterInject
    protected void onAfterInject() {
        Observable<String> str = AndroidObservable.bindActivity(this, s.asObservable());
        this.compositeSubscription.add(str
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        return s;
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Toast.makeText(getApplicationContext(), o.toString(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        this.compositeSubscription.clear();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void test(View view) {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(this, "wasabeef.realm");

        realm.beginTransaction();

        // Create a new object
        User user = realm.createObject(User.class);
        user.setName("Wasabeef");
        user.setAge(27);

        realm.commitTransaction();

        s.set(System.currentTimeMillis() + "");
    }

    public void test2(View view) {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(this, "wasabeef.realm");

// Build the query looking at all users:
        RealmQuery<User> query = realm.where(User.class);

// Add query conditions:
        query.equalTo("name", "Wasabeef");
        query.or().equalTo("name", "Chip");
// Execute the query:
        RealmResults<User> resultAll = query.findAll();

// Or alternatively do the same all at once (the "Fluent interface"):
        RealmResults<User> result =
                realm.where(User.class)
                        .equalTo("name", "Wasabeef")
                        .or()
                        .equalTo("name", "Chip")
                        .findAll();

        // Asc
        RealmResults<User> sortedAscending = result.sort("age");

// Desc
        RealmResults<User> sortedDescending =
                result.sort("age", RealmResults.SORT_ORDER_DECENDING);

        Toast.makeText(this, result.get(0).getAge() + "", Toast.LENGTH_SHORT).show();
    }

    public void delete() {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(this, "wasabeef.realm");

        // All changes to data must happen in a transaction
        realm.beginTransaction();

// remove single match
//        result.remove(0);
//        result.removeLast();

// Delete all matches
//        result.clear();

        realm.commitTransaction();
    }

    public void test3(View view) {
        startActivity(new Intent(this, VolleyActivity.class));
        view.setOnClickListener(this::nextActivity);
    }

    public void test4(View view) {
        startActivity(new Intent(this, CustomViewActivity_.class));
    }

    public void nextActivity(View view) {
        try {
            String tag = view.getTag().toString();
            Intent intent = new Intent(this, Class.forName(getPackageName() + tag));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
