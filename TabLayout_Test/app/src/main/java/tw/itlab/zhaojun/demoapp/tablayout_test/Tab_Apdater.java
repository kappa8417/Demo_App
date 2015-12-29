package tw.itlab.zhaojun.demoapp.tablayout_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by zhaojun on 15/12/29.
 */
public class Tab_Apdater extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Tab_Apdater(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_1 tab1 = new Tab_1();
                return tab1;
            case 1:
                Tab_2 tab2 = new Tab_2();
                return tab2;
            case 2:
                Tab_3 tab3 = new Tab_3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

