package com.example.recyclerview.homeFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.Fruit;
import com.example.recyclerview.FruitActivity;
import com.example.recyclerview.R;

import java.util.List;


/**
 * @author Ouyang Yu
 * @date 2019/10/26 13:50
 */

/**
 * /*
 *  *
 *  * RecyclerFruitAdapter在recyclerView用
 *  *
 *  *
 *  *
 */

public class RecyclerFruitAdapter extends RecyclerView.Adapter<RecyclerFruitAdapter.ViewHolder> {
	private List<Fruit> mfruitList; //成员变量
	private Context mContext;//每张图片有不同的适配器
	//这是当前适配器对应的视图

	public RecyclerFruitAdapter(List<Fruit> mfruitList) {
		this.mfruitList = mfruitList;
	}//构造器,参数为一个list<Fruit>集合

	/**
	 * 以下三个为重写的方法
	 * @param parent
	 * @param viewType
	 * @return
	 */
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		if (mContext == null) {
			mContext = parent.getContext();
		}



		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.cardview_item, parent, false);

		final ViewHolder viewHolder = new ViewHolder(view);


//		viewHolder.image.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				int position = viewHolder.getAdapterPosition();
//				Fruit fruit = mfruitList.get(position);
//				Toast.makeText(view.getContext(),"image:"+fruit.getName(),Toast.LENGTH_SHORT).show();
//			}
//		});

		viewHolder.name.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int position = viewHolder.getAdapterPosition();
				Fruit fruit = mfruitList.get(position);
				Toast.makeText(view.getContext(),"text:"+fruit.getName(),Toast.LENGTH_SHORT).show();
			}
		});
		viewHolder.fruitView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//点击整个视图....


				int position=viewHolder.getAdapterPosition();
				Fruit fruit = mfruitList.get(position);
				Intent intent = new Intent(mContext, FruitActivity.class);
				intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
				intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageID());
				mContext.startActivity(intent);
			}
		});


		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Fruit fruit = mfruitList.get(position);

		//holder.image.setImageResource(fruit.imageID);
		Glide.with(mContext).load(fruit.getImageID()).into(holder.image);
		//加载到某个Fruit对象的视图里去
		holder.name.setText(fruit.getName());

	}

	@Override
	public int getItemCount() {
		return mfruitList.size();
	}

	/**
	 * ViewHolder内部类
	 */
	static class ViewHolder extends RecyclerView.ViewHolder{
		View fruitView; //整个视图的对象,点击事件的时候要用
		ImageView image;
		TextView name;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			fruitView=itemView;
			image = itemView.findViewById(R.id.fruitImage);
			name = itemView.findViewById(R.id.fruitName);
		}
	}
}
