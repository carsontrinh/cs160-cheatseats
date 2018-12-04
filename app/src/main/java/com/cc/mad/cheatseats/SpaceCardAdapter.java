package com.cc.mad.cheatseats;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpaceCardAdapter extends RecyclerView.Adapter<SpaceCardAdapter.SpaceCardViewHolder> {

    private ArrayList<SpaceCardItem> cardList;
    private ViewGroup recycler;

    public SpaceCardAdapter(ArrayList<SpaceCardItem> list) {
        cardList = list;
    }

    @NonNull
    @Override
    public SpaceCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        recycler = viewGroup;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.space_card, viewGroup, false);
        return new SpaceCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpaceCardViewHolder holder, int i) {
        System.out.println("THE VALUE OF i::::: " + i);
        SpaceCardItem currentItem = cardList.get(i);

        holder.textView_parentName.setText(currentItem.getSpaceName());
        holder.textView_parentType.setText(currentItem.getSpaceType());

        // First, we set some of the subfloor views to be invisible since there are extra.

        int noOfChildTextViews = holder.linearLayout_spaceItems.getChildCount();
        int noOfChild = currentItem.getFloors().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {

                LinearLayout currentLinearLayout = (LinearLayout) holder.linearLayout_spaceItems.getChildAt(index);
                TextView currentFloorTextView = (TextView) currentLinearLayout.getChildAt(0);
                currentFloorTextView.setVisibility(View.GONE);
            }
        }
        for (int floorIndex = 0; floorIndex < noOfChild; floorIndex++) {
            LinearLayout currentLinearLayout = (LinearLayout) holder.linearLayout_spaceItems.getChildAt(floorIndex);
            TextView currentFloorTextView = (TextView) currentLinearLayout.getChildAt(0);
            currentFloorTextView.setText(currentItem.getFloors().get(floorIndex).getName());
            //View itemView = LayoutInflater.from(currentLinearLayout.getContext()).inflate(R.layout.floor_card, currentLinearLayout, false);
            //currentLinearLayout.addView(itemView);
            final Context context = holder.context;
            final FloorItem floor = currentItem.getFloors().get(floorIndex);

            currentLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ScrollingActivity) context).switchToFloorViewActivity(floor);
                }
            });
        }


        // Now, we set all the icons

        Context context2 = holder.context;

        for (int indexView = 0; indexView < noOfChild; indexView++) {

            LinearLayout linearLayout_floorItems = (LinearLayout) holder.linearLayout_spaceItems.getChildAt(indexView);

            ImageView img_cellular, img_noise, img_food, img_power;

            boolean cellular, noise, food, power;
            cellular = noise = food = power = false;
            int index = 1;

            SpaceCardItem currentSpace = cardList.get(i);

            FloorItem floor = currentSpace.getFloors().get(indexView);

            System.out.println("FUUUU");
            System.out.println(currentSpace.getSpaceName());
            System.out.println(floor.getName());

            Crowdedness status = floor.getCrowdedness();
            ImageView img_status;

            cellular = floor.hasGoodCellular();
            noise = floor.isQuiet();
            food = floor.allowsFood();
            power = floor.hasOutlets();


            View itemView = LayoutInflater.from(linearLayout_floorItems.getContext()).inflate(R.layout.floor_card, linearLayout_floorItems, false);
            linearLayout_floorItems.addView(itemView);

            TextView currentFloorTextView = itemView.findViewById(R.id.floor_name);
            currentFloorTextView.setText(currentItem.getFloors().get(indexView).getName());


            img_cellular = itemView.findViewById(R.id.cellular_status);
            img_food = itemView.findViewById(R.id.food_status);
            img_noise = itemView.findViewById(R.id.noise_status);
            img_power = itemView.findViewById(R.id.power_status);
            img_status = itemView.findViewById(R.id.floor_status);



            if (cellular) {
                img_cellular.setAlpha(1.0f);
            }

            if (noise) {
                img_noise.setAlpha(1.0f);
            }

            if (food) {
                img_food.setAlpha(1.0f);
            }

            if (power) {
                img_power.setAlpha(1.0f);
            }

            if (status == Crowdedness.LOW) {
                img_status.setImageResource(R.drawable.small_crowded_low);
            }

            if (status == Crowdedness.MEDIUM) {
                img_status.setImageResource(R.drawable.small_crowded_medium);
            }

            if (status == Crowdedness.HIGH) {
                img_status.setImageResource(R.drawable.small_crowded_high);
            }

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
                linearLayout_floorItems.setBackground(ContextCompat.getDrawable(context, R.drawable.subcard_background));
                linearLayout_floorItems.setOnClickListener(this);


                TextView textView = new TextView(context);
                textView.setId(0);
                textView.setPadding(0, 0, 0, 0);
                textView.setGravity(Gravity.LEFT);

                LinearLayout.LayoutParams layoutParamsFloorItems = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout_floorItems.addView(textView, 0);
                textView.setVisibility(View.GONE);

                LinearLayout.LayoutParams layoutParamsSpaceItems = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout_spaceItems.addView(linearLayout_floorItems, layoutParamsSpaceItems);
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("CLICKED");
            ImageView arrow = view.findViewById(R.id.expand_collapse);
            arrow.animate().rotation(arrow.getRotation() + 180).start();
            System.out.println(arrow.getRotation());
            textView_parentName = view.findViewById(R.id.space_name);
            if (textView_parentName != null && textView_parentName.getId() == R.id.space_name) {
                if (linearLayout_spaceItems.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(recycler, new AutoTransition());
                    linearLayout_spaceItems.setVisibility(View.GONE);
                } else {
                    TransitionManager.beginDelayedTransition(recycler, new AutoTransition());
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
