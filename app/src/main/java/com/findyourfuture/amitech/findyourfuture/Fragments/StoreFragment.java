package com.findyourfuture.amitech.findyourfuture.Fragments;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.facebook.shimmer.ShimmerFrameLayout;
import com.findyourfuture.amitech.findyourfuture.Album1;
import com.findyourfuture.amitech.findyourfuture.AlbumsAdapter1;
import com.findyourfuture.amitech.findyourfuture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StoreFragment extends Fragment {
    public int size;
    View view;
    private AlbumsAdapter1 adapter,adapter1;
    private List<Album1> albumList,albumList1;
    RecyclerView horizontal,course_recycler;
    RequestQueue queue;
    private ShimmerFrameLayout mShimmerViewContainer;



    private static final String TAG = StoreFragment.class.getSimpleName();

    // url to fetch shopping items
    private static final String URL = "https://api.androidhive.info/json/movies_2017.json";


    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_store, container, false);
        horizontal = (RecyclerView)view.findViewById(R.id.recyclerview);
        course_recycler = (RecyclerView)view.findViewById(R.id.course_recycler);

        mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmerAnimation();




        load_cat();
//        load_course();







        final BottomNavigationView navigation = (BottomNavigationView)getActivity().findViewById(R.id.navigation);



        return view;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void load_cat() {

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter1(getActivity(), albumList);
        queue = Volley.newRequestQueue(getActivity());


        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setReverseLayout(false);

        horizontal.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(2), true));
        horizontal.setLayoutManager(linearLayoutManager);
        horizontal.setAdapter(adapter);

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://ka19news.com/has/load_cat.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

                        albumList.clear();


                        Album1 a;
                        try {
                            JSONArray data = new JSONArray(response);
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject c = data.getJSONObject(i);
                                String  id = c.getString("CategoryID").toString();
                                String  name = c.getString("CategoryName").toString();
                                String  image="http://www.ka19news.com/img/fossil.jpg";
                                Toast.makeText(getActivity(), id+"\n"+name+"\n"+image, Toast.LENGTH_SHORT).show();
//                              = c.getString("image").toString();

                                a = new Album1(id, name,image);
                                albumList.add(a);
                            }
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("cat_name", cat);
//                params.put("domain", "http://itsalif.info");

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10*1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

//    public void load_course() {
//
//        albumList1 = new ArrayList<>();
//        adapter1 = new AlbumsAdapter1(getActivity(), albumList);
//        queue = Volley.newRequestQueue(getActivity());
//
//
//        LinearLayoutManager linearLayoutManager;
//        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
//        linearLayoutManager.setReverseLayout(false);
//
//        course_recycler.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(2), true));
//        course_recycler.setLayoutManager(linearLayoutManager);
//        course_recycler.setAdapter(adapter1);
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://ka19news.com/has/load_cat.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//
//                        albumList1.clear();
//
//
//                        Album1 a;
//
//                        try {
//                            JSONArray data = new JSONArray(response);
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject c = data.getJSONObject(i);
//                                String  id = c.getString("CategoryID").toString();
//                                String  name = c.getString("CategoryName").toString();
//                                String  image="http://www.ka19news.com/img/fossil.jpg";
//                                Toast.makeText(getActivity(), id+"\n"+name+"\n"+image, Toast.LENGTH_SHORT).show();
////                              = c.getString("image").toString();
//
//                                a = new Album1(id, name,image);
//                                albumList1.add(a);
//                            }
//                            adapter1.notifyDataSetChanged();
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", error.toString());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
////                params.put("cat_name", cat);
////                params.put("domain", "http://itsalif.info");
//
//                return params;
//            }
//        };
//        queue.add(postRequest);
//    }








}
