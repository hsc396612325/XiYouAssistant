package debug;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.EducationFragment;
import com.xiyoumoblie.module.education.R;


/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/7/1 13:13
 * @version V1.2.0
 * @name MainActivity
 */
@Route(path = "/main/main")
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.search_edit_frame, new EducationFragment()).commit();
    }



}
