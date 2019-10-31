package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author Ouyang Yu
 * @date 2019/10/26 12:56
 */

/**
 *
 * FruitAdapter是ViewList用的适配器
 *
 *
 *
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {
	private int resourceID;//布局资源item.xml

	public FruitAdapter(Context context, int resource, List<Fruit> objects) {
		super(context, resource, objects);
		resourceID=resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Fruit fruit = getItem(position);//适配器每次只对一个item

		View view;
		ViewHolder viewHolder;

		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.fruitImage = view.findViewById(R.id.fruitImage);
			viewHolder.fruitName = view.findViewById(R.id.fruitName);
			view.setTag(viewHolder); //将Viewholder存储在view中
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.fruitName.setText(fruit.getName());
		viewHolder.fruitImage.setImageResource(fruit.imageID);

		return view;


	}
	class ViewHolder{
		ImageView fruitImage;
		TextView fruitName;
	}
}
