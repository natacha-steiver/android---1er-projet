package android.eni.fr;

//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;

//import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


//ici c'était android.app.fragment donc erreur convertir fragment...
import android.support.v4.app.Fragment;

import android.widget.TableLayout;


//!!! déclarer attributs (btn1) en amont !!!

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private  TableLayout rt;
    private DrawerLayout drawer;
    private   ActionBarDrawerToggle toggle;
    private   Toolbar toolbar;
    //FOR FRAGMENTS
    // 1 - Declare fragment handled by Navigation Drawer
    private Fragment fragmentNews;
    private Fragment fragmentProfile;
    private Fragment fragmentParams;

    //FOR DATAS
    // 2 - Identify each fragment with a number
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_PARAMS = 2;

   private  NavigationView navigationView;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ici il ne peut exister qu'un setContentView (plusieurs vues=fragments)
        setContentView(R.layout.activity_main2);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.activity_main_drawer_open, R.string.activity_main_drawer_close);
        //drawer.setDrawerListener(toggle);


        drawer.addDrawerListener(toggle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (actionBar != null) {

            actionBar.setTitle("Bien être au travail");
        }

        toolbar.setNavigationIcon(R.drawable.logo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si drawer est ouvert alors je ferme sinon j'ouvre condition a faire
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
                drawer.openDrawer(Gravity.START);
            }
        });

        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                // 6 - Show fragment after user clicked on a menu item
                switch (id) {
                    case R.id.activity_main_drawer_rentabilite:
                        showFragment(FRAGMENT_NEWS);
                        break;
                    case R.id.activity_main_drawer_burnout:
                        showFragment(FRAGMENT_PROFILE);
                        break;
                    case R.id.activity_main_drawer_conquete:
                        showFragment(FRAGMENT_PARAMS);
                        break;
                    default:
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }


        });




        //--------------------------------------------------------------


        // ---------------------
        // FRAGMENTS
        // ---------------------

        // 5 - Show fragment according an Identifier
        showFirstFragment();

    }
    // FRAGMENTS
    // ---------------------

    // 5 - Show fragment according an Identifier

    private void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case FRAGMENT_NEWS :
                this.showNewsFragment();
                break;
            case FRAGMENT_PROFILE:
                this.showProfileFragment();
                break;
            case FRAGMENT_PARAMS:
                this.showParamsFragment();
                break;
            default:
                break;
        }
    }

    // ---

    // 4 - Create each fragment page and show it

    private void showNewsFragment(){
        if (this.fragmentNews == null) this.fragmentNews = NewsFragment.newInstance();
        this.startTransactionFragment(this.fragmentNews);
    }

    private void showParamsFragment(){
        if (this.fragmentParams == null) this.fragmentParams = ParamsFragment.newInstance();
        this.startTransactionFragment(this.fragmentParams);
    }

    private void showProfileFragment(){
        if (this.fragmentProfile == null) this.fragmentProfile = ProfileFragment.newInstance();
        this.startTransactionFragment(this.fragmentProfile);
    }

    // ---

    // 3 - Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }


    // 1 - Show first fragment when activity is created
    private void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null){
            // 1.1 - Show News Fragment
            this.showFragment(FRAGMENT_NEWS);
            // 1.2 - Mark as selected the menu item corresponding to NewsFragment
            NavigationView navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);

            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }











}