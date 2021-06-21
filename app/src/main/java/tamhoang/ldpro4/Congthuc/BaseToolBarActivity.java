package tamhoang.ldpro4.Congthuc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseToolBarActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    protected Toolbar toolbar;

    protected abstract int getLayoutId();

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(getLayoutId());
        setTitle(this.TAG);
        Toolbar toolbar = (Toolbar)findViewById(2131231325);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
    }
}
