package com.example.communihelp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.Notify1ApiResponse;
import com.example.communihelp.server.Notify1Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notify1Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Notify1Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        return view;
    }

    private void loadData() {
        String userId = SharedPrefManager.getInstance(requireContext()).getUserId();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getNotify1(userId).enqueue(new Callback<Notify1ApiResponse>() {
            @Override
            public void onResponse(Call<Notify1ApiResponse> call, Response<Notify1ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Notify1ApiResponse.Notify1Data> list = response.body().getData();

                    if (list != null) {
                        adapter = new Notify1Adapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Notify1ApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
