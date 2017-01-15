package com.example.android.spellingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.spellingapp.R;

/**
 * Created by hernandez on 1/8/2017.
 */
public class PictureFragment extends Fragment {

    private WebView webView;

    String url, randomWord;

    public PictureFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        url = "";

        randomWord = "";

        View view = inflater.inflate(R.layout.fragment_picture, container, false);

        webView = (WebView) view.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());

        randomWord = SpellingBeeActivity.spellingWord;

        url = "http://www.google.com/images?q=" + randomWord;

        webView.loadUrl(url);

        return view;
    }


}
