package land.basso.android.popularmovies;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity   extends     ActionBarActivity
                            implements  MainActivityFragmentDetail.OnFragmentInteractionListener
{
    public ArrayList<Movie>     mMovies;
    public ArrayList<Integer>   mFavorites;
    public String               mSort;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if(mFavorites == null )
        {
            TinyDB tinyDB = new TinyDB(this);
            mFavorites = tinyDB.getListInt(getString(R.string.favorites_list_key));

            //Had a bug at one point that was adding a bunch of '-1's, so this will clean them out
            for(int i = 0; i < mFavorites.size(); i++)
            {   if(mFavorites.get(i) == -1) {   mFavorites.remove(i);   i = 0;   }
            }
            tinyDB.putListInt(getString(R.string.favorites_list_key), mFavorites);
        }

        if(findViewById(R.id.fragment_detail) != null)
        {   mTwoPane = true;    }
        else
        {   mTwoPane = false;   }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MainActivityFragment fragment = new MainActivityFragment();
        ft.add(R.id.fragment, fragment);
        ft.commit();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDetailsForMovie(int position)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MainActivityFragmentDetail fragment = new MainActivityFragmentDetail(position);

        if(mTwoPane)
        {   ft.replace(R.id.fragment_detail, fragment); }
        else
        {   ft.replace(R.id.fragment, fragment);    }

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("Detail");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
        {   getFragmentManager().popBackStack();    }
        else
        {   super.onBackPressed();  }
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {    }
}
