package com.cc.mad.cheatseats;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        int noOfChildTextViews = holder.linearLayout_childItems.getChildCount();
        int noOfChild = currentItem.getFloors().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {
                TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
                currentTextView.setVisibility(View.GONE);
            }
        }
        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(textViewIndex);
            currentTextView.setText(currentItem.getFloors().get(textViewIndex).getName());
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
        private LinearLayout linearLayout_childItems;


        SpaceCardViewHolder (@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.space_name);
            textView_parentType = itemView.findViewById(R.id.space_type);

            linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
            linearLayout_childItems.setVisibility(View.GONE);

            int intMaxNoOfChild = 0;
            for (int index = 0; index < cardList.size(); index++) {
                int intMaxSizeTemp = cardList.get(index).getFloors().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }
            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setPadding(0, 20, 0, 20);
                textView.setGravity(Gravity.CENTER);
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setOnClickListener(this);
                linearLayout_childItems.addView(textView, layoutParams);
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("CLICKED");
            textView_parentName = itemView.findViewById(R.id.space_name);
            if (textView_parentName.getId() == R.id.space_name) {
                if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                    linearLayout_childItems.setVisibility(View.GONE);
                } else {
                    linearLayout_childItems.setVisibility(View.VISIBLE);
                }
            } else {
                TextView textViewClicked = (TextView) textView_parentName;
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
