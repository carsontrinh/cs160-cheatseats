package com.cc.mad.cheatseats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SpaceCardAdapter extends RecyclerView.Adapter<SpaceCardAdapter.SpaceCardViewHolder> {

    private ArrayList<SpaceCardItem> cardList;

    public SpaceCardAdapter(ArrayList<SpaceCardItem> list) {
        cardList = list;
    }

    @NonNull
    @Override
    public SpaceCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.space_card, viewGroup, false);
        return new SpaceCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceCardViewHolder holder, int i) {
        SpaceCardItem currentItem = cardList.get(i);

        holder.textView_parentName.setText(currentItem.getSpaceName());
        holder.textView_parentType.setText(currentItem.getSpaceType());

        int noOfChildTextViews = holder.linearLayout_spaceItems.getChildCount();
        int noOfChild = currentItem.getFloors().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {

                LinearLayout currentLinearLayout = (LinearLayout) holder.linearLayout_spaceItems.getChildAt(index);
                TextView currentFloorTextView = (TextView) currentLinearLayout.getChildAt(0);
                currentFloorTextView.setVisibility(View.GONE);
            }
        }
        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            LinearLayout currentLinearLayout = (LinearLayout) holder.linearLayout_spaceItems.getChildAt(textViewIndex);
            TextView currentFloorTextView = (TextView) currentLinearLayout.getChildAt(0);
            currentFloorTextView.setText(currentItem.getFloors().get(textViewIndex).getName());  // TODO

        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class SpaceCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView textView_parentName;
        private TextView textView_parentType;
        private LinearLayout linearLayout_spaceItems;

        SpaceCardViewHolder (@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.space_name);
            textView_parentType = itemView.findViewById(R.id.space_type);

            linearLayout_spaceItems = itemView.findViewById(R.id.ll_space_items);
            linearLayout_spaceItems.setVisibility(View.GONE);

            int intMaxNoOfChild = 0;
            for (int index = 0; index < cardList.size(); index++) {
                int intMaxSizeTemp = cardList.get(index).getFloors().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }
            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {

                LinearLayout linearLayout_floorItems;

//                linearLayout_floorItems = itemView.findViewById(R.id.ll_floor_items);
                linearLayout_floorItems = new LinearLayout(context);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout_floorItems.setLayoutParams(params);
                linearLayout_floorItems.setOrientation(LinearLayout.HORIZONTAL);

                linearLayout_floorItems.setId(indexView);
                linearLayout_floorItems.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                linearLayout_floorItems.setOnClickListener(this);


                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setPadding(64, 24, 0, 24);
                textView.setGravity(Gravity.LEFT);

                LinearLayout.LayoutParams layoutParamsFloorItems = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout_floorItems.addView(textView, layoutParamsFloorItems);

//                TextView textView2 = new TextView(context);
//                textView2.setId(indexView + 100);
//                System.out.println("THE ID I MAKE:::::::: " + (indexView + 100));
//                textView2.setPadding(64, 24, 0, 24);
//                textView2.setGravity(Gravity.RIGHT);
//                textView2.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));



                LinearLayout.LayoutParams layoutParamsSpaceItems = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout_spaceItems.addView(linearLayout_floorItems, layoutParamsSpaceItems);
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("CLICKED");
            textView_parentName = view.findViewById(R.id.space_name);
            if (textView_parentName != null && textView_parentName.getId() == R.id.space_name) {
                if (linearLayout_spaceItems.getVisibility() == View.VISIBLE) {
                    linearLayout_spaceItems.setVisibility(View.GONE);
                } else {
                    linearLayout_spaceItems.setVisibility(View.VISIBLE);
                }
            }

            else {
                LinearLayout ll_space = (LinearLayout) view;
                TextView textViewClicked = (TextView) ll_space.getChildAt(0);
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
