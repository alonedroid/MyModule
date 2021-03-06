package alonedroid.com.mymodule.scene.reactive;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class ObservableProperty<T> {

    private BehaviorSubject<T> subject = BehaviorSubject.create();

    public ObservableProperty(T initialValue) {
        set(initialValue);
    }

    public T get() {
        return this.subject.toBlocking().first();
    }

    public void set(T value) {
        this.subject.onNext(value);
    }

    public Observable<T> asObservable() {
        return this.subject.asObservable();
    }

    public Observable<T> asObservableDistinct() {
        return this.subject.asObservable().distinctUntilChanged();
    }
}