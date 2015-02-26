package alonedroid.com.mymodule;

import io.realm.Realm;
import io.realm.RealmMigration;

public class MyRealmMigration implements RealmMigration {
    @Override
    public long execute(Realm realm, long version) {

        // Migrate from version 0 to version 1
        if (version == 0) {
            // 自分で、Tableの再構築を行う必要がある
            // 省略
            version++;
        }

        return version;
    }
}