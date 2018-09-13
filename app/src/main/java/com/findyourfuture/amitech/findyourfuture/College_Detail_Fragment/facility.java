package com.findyourfuture.amitech.findyourfuture.College_Detail_Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.findyourfuture.amitech.findyourfuture.R;
import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class facility extends BottomSheetDialogFragment {

    ViewGroup dynamicLayoutContainer;
    public facility() {

    }
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.facility, container, false);
        dynamicLayoutContainer=(ViewGroup)view.findViewById(R.id.dynamicLayoutContainer);
        final ExpansionLayout ex1 = addDynamicLayout();
        final ExpansionLayout ex2 = addDynamicLayout();

        //example of how to add a listener
        ex1.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {

            }
        });

        final ExpansionLayoutCollection expansionLayoutCollection = new ExpansionLayoutCollection();
        expansionLayoutCollection.add(ex1).add(ex2);
        expansionLayoutCollection.openOnlyOne(true);

        return view;
    }
    public ExpansionLayout addDynamicLayout() {

        final ExpansionHeader expansionHeader = createExpansionHeader();
        dynamicLayoutContainer.addView(expansionHeader, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final ExpansionLayout expansionLayout = createExpansionLayout();
        dynamicLayoutContainer.addView(expansionLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        expansionHeader.setExpansionLayout(expansionLayout);

        return expansionLayout;

    }
    private ExpansionLayout createExpansionLayout() {
        final ExpansionLayout expansionLayout = new ExpansionLayout(getActivity());

        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(getActivity(), 48)); //equivalent to addView(linearLayout)

        final TextView text = new TextView(getActivity());
        text.setText("Content");
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.parseColor("#3E3E3E"));
        text.setBackgroundColor(Color.parseColor("#EEEEEE"));
        layout.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(getActivity(), 200));

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View child = new View(getActivity());
                child.setBackgroundColor(Color.BLACK);
                layout.addView(child, ViewGroup.LayoutParams.MATCH_PARENT, 100);
            }
        });

        layout.addView(LayoutInflater.from(getActivity()).inflate(R.layout.expansion_panel_sample_panel, layout, false));

        return expansionLayout;
    }

    private ExpansionHeader createExpansionHeader() {
        final ExpansionHeader expansionHeader = new ExpansionHeader(getActivity());
        expansionHeader.setBackgroundColor(Color.WHITE);

        expansionHeader.setPadding(dpToPx(getActivity(), 16), dpToPx(getActivity(), 8), dpToPx(getActivity(), 16), dpToPx(getActivity(), 8));

        final RelativeLayout layout = new RelativeLayout(getActivity());
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)

        //image
        final ImageView expansionIndicator = new AppCompatImageView(getActivity());
        expansionIndicator.setImageResource(R.drawable.ic_expansion_header_indicator_grey_24dp);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionIndicator, imageLayoutParams);

        //label
        final TextView text = new TextView(getActivity());
        text.setText("Trip name");
        text.setTextColor(Color.parseColor("#3E3E3E"));

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        expansionHeader.setExpansionHeaderIndicator(expansionIndicator);
        return expansionHeader;
    }
    public static int dpToPx(Context context, float dp) {
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static float pxToDp(Context context, float px) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
