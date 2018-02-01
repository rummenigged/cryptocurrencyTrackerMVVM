package com.example.rummenigged.cryptocurrencytrackermvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.rummenigged.cryptocurrencytrackermvvm.App;
import com.example.rummenigged.cryptocurrencytrackermvvm.R;
import com.example.rummenigged.cryptocurrencytrackermvvm.data.repository.MockCryptocurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase.CryptocurrencyUseCase;
import com.example.rummenigged.cryptocurrencytrackermvvm.util.DialogsFactory;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private Toolbar tbMain;
    private RecyclerView rvCryptocurrency;
    private ProgressBar pbCryptocurrency;
    private FloatingActionButton fabRefreshCryptocurrencyList;
    private TextView tvAmountTotal;
    private Context context = this;
    private CryptocurrencyAdapter adapter;
    private CryptocurrencyViewModel cryptocurrencyViewModel;

//    Observers
    Observer<List<Cryptocurrency>> listCryprocurrencyChangeObserver;
    Observer<String> onErrorChangeObserver;
    Observer<String> snackBarTextChangeObserver;
    Observer<String> onAmontTotalChangeObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadUI();
        initRecyclerView();

        initViewModel();

        listCryprocurrencyChangeObserver = list ->{
            adapter.swapData(list);
            pbCryptocurrency.setVisibility(View.GONE);
        };

        onErrorChangeObserver = error ->
            DialogsFactory.showDialogWithPositiveAndNegativeButton
                    (context,"Error", error
                            , android.R.style.Theme_Material_Light_Dialog, R.drawable.ic_error)
                        .setPositiveButton(R.string.positive_button
                            , (dialog, i) -> cryptocurrencyViewModel.getListCryptocurrency())
                        .setNegativeButton(R.string.negative_button
                            , (dialogInterface, i) -> {})
                        .show();

        snackBarTextChangeObserver = text ->
            Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();

        onAmontTotalChangeObserver = response ->
                tvAmountTotal.setText(response);

        subscribeToCryptocurrenciesChange();

        cryptocurrencyViewModel.getListCryptocurrency();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort:
                cryptocurrencyViewModel.sortList();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadUI(){
        layout = findViewById(R.id.main_layout);
        tbMain = findViewById(R.id.tb_main);
        setSupportActionBar(tbMain);
        rvCryptocurrency = findViewById(R.id.rv_cryptocurrency);
        pbCryptocurrency = findViewById(R.id.pb_cryptocurrency);
        fabRefreshCryptocurrencyList = findViewById(R.id.fab_refresh_cryptocurrency_list);
        tvAmountTotal = findViewById(R.id.tv_amount_total);
    }

    private void initRecyclerView(){
        adapter = new CryptocurrencyAdapter(context);
        rvCryptocurrency.setLayoutManager(new LinearLayoutManager(context));
        rvCryptocurrency.setAdapter(adapter);

    }

    private void initViewModel(){
        cryptocurrencyViewModel = ViewModelProviders
                .of((FragmentActivity) context
                        , new CryptocurrencyViewModel
                                .CryptocurrencyViewModelFactory(
                                new CryptocurrencyUseCase(
                                        new MockCryptocurrencyRepository()
                                )))
                .get(CryptocurrencyViewModel.class);
    }

    private void subscribeToCryptocurrenciesChange(){
        cryptocurrencyViewModel
                .getLdAmountTotal()
                .observe(this
                        , onAmontTotalChangeObserver);

        cryptocurrencyViewModel
                .getLdError()
                .observe(this
                        , onErrorChangeObserver);

        cryptocurrencyViewModel
                .getLdCryptocurrencyList()
                .observe(this
                        , listCryprocurrencyChangeObserver);

        cryptocurrencyViewModel
                .getSleSnackBarText()
                .observe(this, snackBarTextChangeObserver );
    }

    public void refreshCryptocurrencyList(View view) {
        cryptocurrencyViewModel.refreshCryptocurrencyList();
        pbCryptocurrency.setVisibility(View.VISIBLE);
    }
}
