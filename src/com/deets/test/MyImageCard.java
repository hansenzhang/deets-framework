package com.deets.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardsui.example.R;
import com.fima.cardsui.objects.Card;

public class MyImageCard extends Card {

	public MyImageCard(String title, int image){
		super(title, image);
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_picture, null);

		((TextView) view.findViewById(R.id.title)).setText(title);
		((ImageView) view.findViewById(R.id.imageView1)).setImageResource(image);
		
		return view;
	}

	
	
	
}
