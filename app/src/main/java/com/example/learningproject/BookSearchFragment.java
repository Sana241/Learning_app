package com.example.learningproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookSearchFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<String> {
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";

    private EditText bookInput;
    private TextView titleTextView, authorTextView;
    private Button searchButton;

    public BookSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_search, container, false);
        bookInput = view.findViewById(R.id.bookInput);
        titleTextView = view.findViewById(R.id.titleText);
        authorTextView = view.findViewById(R.id.authorText);
        searchButton = view.findViewById(R.id.searchButton);

        if ((LoaderManager.getInstance(requireActivity())
                .getLoader(0)) != null) {
            LoaderManager.getInstance(requireActivity())
                    .initLoader(0, null, BookSearchFragment.this);
       }

        if(savedInstanceState!=null){
            titleTextView.setText(savedInstanceState.getString(TITLE));
            authorTextView.setText(savedInstanceState.getString(AUTHOR));

        }
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String queryString = bookInput.getText().toString();

                InputMethodManager input = (InputMethodManager) requireContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                if (input != null) {
                    input.hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                ConnectivityManager manager = (ConnectivityManager) getContext().
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = null;
                if (manager != null) {
                    networkInfo = manager.getActiveNetworkInfo();
                }
                if (networkInfo != null && networkInfo.isConnected() &&
                        (queryString.length()) != 0) {

                    Bundle queryBundle = new Bundle();
                    queryBundle.putString("query_string", queryString);
                    LoaderManager.getInstance(requireActivity()).
                            restartLoader(0, queryBundle,
                                    BookSearchFragment.this);

                    bookInput.setText("");
                    titleTextView.setText(R.string.loading_result);
                    authorTextView.setText(" ");
                } else {
                    if ((queryString.length()) == 0) {
                        titleTextView.setText(R.string.no_search_string);
                        authorTextView.setText(" ");
                    } else
                        titleTextView.setText(R.string.no_network_connection);
                    authorTextView.setText(" ");
                }
            }
        });

        return view;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("query_string");
        }
        return new BookAsyncLoader(getActivity(), queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        int i = 0;
        String title = null;
        String authors = null;

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray resultArray = jsonObject.getJSONArray("items");

            while (i < resultArray.length() &&
                    (authors == null && title == null)) {

                JSONObject book = resultArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
            if (title != null && authors != null) {
                titleTextView.setText("Title: " + title);
                authorTextView.setText("Author: " + authors);
            } else {
                titleTextView.setText(R.string.no_results);
                authorTextView.setText("");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            titleTextView.setText(R.string.no_results);
            authorTextView.setText("");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE,titleTextView.getText().toString());
        outState.putString(AUTHOR,authorTextView.getText().toString());
    }
}

