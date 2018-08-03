package debug;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.xiyoumobile.module.library.ui.fragment.LyMainFragment;
import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.search_edit_frame, new LyMainFragment()).commit();
    }

}
