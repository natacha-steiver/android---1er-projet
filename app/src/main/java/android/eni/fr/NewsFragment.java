package android.eni.fr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFragment extends Fragment {

    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    public static NewsFragment newInstance() {
        return (new NewsFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}