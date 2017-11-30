package com.example.shuangzhecheng.loweskevinapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuangzhecheng.loweskevinapp.MainActivity;
import com.example.shuangzhecheng.loweskevinapp.ProductAdapter;
import com.example.shuangzhecheng.loweskevinapp.R;
import com.example.shuangzhecheng.loweskevinapp.model.Products;
import com.example.shuangzhecheng.loweskevinapp.model.ReqObj;
import com.example.shuangzhecheng.loweskevinapp.model.SubCategories;
import com.example.shuangzhecheng.loweskevinapp.network.RetrofitClient;
import com.example.shuangzhecheng.loweskevinapp.network.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**

 */
public class ProductFragment extends Fragment {
    private List<Products.ProductBean> productBeanList;
    static private SubCategories.SubCategoryBean subCategoryBean;
    private RecyclerView recyclerView;
    private TextView tvTitle;
    static private ReqObj user;
    SharedPreferences sharedPreferences;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(SubCategories.SubCategoryBean subCategoryBean){
        ProductFragment fragment = new ProductFragment();
        ProductFragment.subCategoryBean = subCategoryBean;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvTitle = view.findViewById(R.id.title);
        tvTitle.setText(subCategoryBean.getSubCatagoryName());
        sharedPreferences = getActivity().getSharedPreferences("USER",MODE_PRIVATE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        productBeanList = new ArrayList<>();
        UserService apiService = RetrofitClient.getClient().create(UserService.class);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            user = bundle.getParcelable("User");
        }
        Call<Products> call = apiService.getProductResponse(subCategoryBean.getId(),sharedPreferences.getString("apikey","empty"),sharedPreferences.getString("id","empty"));
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                productBeanList = response.body().getProduct();
                ProductAdapter adapter = new ProductAdapter(getActivity(),productBeanList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });
    }
}
