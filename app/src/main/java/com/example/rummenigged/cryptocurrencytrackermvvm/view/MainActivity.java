package com.example.rummenigged.cryptocurrencytrackermvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.rummenigged.cryptocurrencytrackermvvm.R;
import com.example.rummenigged.cryptocurrencytrackermvvm.data.repository.ApiCryptocurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase.CryptocurrencyUseCase;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar tbMain;
    private RecyclerView rvCryptocurrency;
    private ProgressBar pbCryptocurrency;
    private FloatingActionButton fabRefreshCryptocurrencyList;
    private TextView tvAmountTotal;
    private Context context = this;
    private CryptocurrencyAdapter adapter;
    private CryptocurrencyViewModel cryptocurrencyViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadUI();
        initRecyclerView();

        initViewModel();

        Observer<List<Cryptocurrency>> listCryprocurrencyChangeObserver = list ->{
            adapter.swapData(list);
            pbCryptocurrency.setVisibility(View.GONE);
        };

        Observer<String> onErrorChangeObserver = error ->{
            showDialogError("Error", error)
                    .setPositiveButton(R.string.positive_button
                        , (dialog, i) -> cryptocurrencyViewModel.getListCryptocurrency())
                    .setNegativeButton(R.string.negative_button
                        , (dialogInterface, i) -> {})
                    .show();
        };

        Observer<String> onAmontTotalChangeObserver = response ->
                tvAmountTotal.setText(response);

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

        cryptocurrencyViewModel.getListCryptocurrency();
    }

    private void loadUI(){
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
                                        new ApiCryptocurrencyRepository()
                                )))
                .get(CryptocurrencyViewModel.class);
    }
    
    private AlertDialog.Builder showDialogError(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog
                .Builder(
                        context
                , android.R.style.Theme_Material_Dialog);
        return builder
                .setTitle(title)
                .setMessage(msg)
                .setIcon(R.drawable.ic_error);
    }

    public void refreshCryptocurrencyList(View view) {
        cryptocurrencyViewModel.getListCryptocurrency();
    }
}
