package com.example.shuangzhecheng.loweskevinapp.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangzhecheng.loweskevinapp.R;
import com.example.shuangzhecheng.loweskevinapp.SubCategoryAdapter;
import com.example.shuangzhecheng.loweskevinapp.model.Categories;
import com.example.shuangzhecheng.loweskevinapp.model.ReqObj;
import com.example.shuangzhecheng.loweskevinapp.model.SubCategories;
import com.example.shuangzhecheng.loweskevinapp.network.RetrofitClient;
import com.example.shuangzhecheng.loweskevinapp.network.UserService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**

 */
public class SubFragment extends Fragment {
    private List<SubCategories.SubCategoryBean> subCategories;
    static private Categories.CategoryBean category;

    static private ReqObj user;

    private RecyclerView recyclerView;
    private TextView tvTitle;
    public SubFragment() {
        // Required empty public constructor
    }
    public static SubFragment newInstance(Categories.CategoryBean category, ReqObj user){
        SubFragment fragment = new SubFragment();
        SubFragment.category = category;
        SubFragment.user = user;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvTitle = view.findViewById(R.id.title);
        tvTitle.setText(category.getCatagoryName());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        subCategories = new ArrayList<>();
        UserService apiService = RetrofitClient.getClient().create(UserService.class);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            user = bundle.getParcelable("User");
        }
        Call<SubCategories> call = apiService.getSubCategoryResponse(category.getId(), user.getAppApiKey(), user.getUserID());
        call.enqueue(new Callback<SubCategories>() {
            @Override
            public void onResponse(Call<SubCategories> call, Response<SubCategories> response) {
                Toast.makeText(getActivity(), "Bundle get" + user.getUserName(), Toast.LENGTH_SHORT).show();
                subCategories = response.body().getSubCategory();
                SubCategoryAdapter adapter = new SubCategoryAdapter(getActivity(), subCategories);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<SubCategories> call, Throwable t) {

            }
        });
    }

}
