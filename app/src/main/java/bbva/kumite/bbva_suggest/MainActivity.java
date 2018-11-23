package bbva.kumite.bbva_suggest;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements CategoriesFragment.OnFragmentInteractionListener,
        RecommendationsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener
        {

    private TextView mTextMessage;
    List<Recommendation> recommendations = new ArrayList<>();
    Fragment recommendationsFragment = RecommendationsFragment.newInstance(recommendations);
    Fragment categoriesFragment = CategoriesFragment.newInstance(recommendations);
    Fragment settingsFragment = SettingsFragment.newInstance(recommendations);
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(recommendationsFragment);
                    return true;
                case R.id.navigation_categories:
                    setFragment(categoriesFragment);
                    return true;
                case R.id.navigation_config:
                    setFragment(settingsFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(categoriesFragment);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
