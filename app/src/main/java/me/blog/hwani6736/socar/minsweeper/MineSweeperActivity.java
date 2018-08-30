package me.blog.hwani6736.socar.minsweeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.blog.hwani6736.socar.R;
import me.blog.hwani6736.socar.minsweeper.component.plane.PlaneLayout;

/**
 * Created by NarZa on 2018. 8. 30..
 */
public class MineSweeperActivity extends AppCompatActivity {
    public static final String EXTRA_HORIZONTAL = "horizontal";
    public static final String EXTRA_VERTICAL = "vertical";
    public static final String EXTRA_LANDMINE = "landmine";
    public static final int DEFAULT_NUMBER = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mine_sweeper);

        int numberHor, numberVer, numberMine;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            numberHor = bundle.getInt(EXTRA_HORIZONTAL, DEFAULT_NUMBER);
            numberVer = bundle.getInt(EXTRA_VERTICAL, DEFAULT_NUMBER);
            numberMine = bundle.getInt(EXTRA_LANDMINE, DEFAULT_NUMBER);
        } else {
            numberHor = numberVer = numberMine = DEFAULT_NUMBER;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(getString(R.string.landmine_description, numberHor, numberVer, numberMine));

        Observable.just(new GameManager(numberHor, numberVer, numberMine))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GameManager>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GameManager gameManager) {
                        ((PlaneLayout)findViewById(R.id.plane_layout)).bind(gameManager.run());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
