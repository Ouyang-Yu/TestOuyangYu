package com.example.recyclerview.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.List;

/**
 * @author Ouyang Yu
 * @date 2019/10/28 18:42
 */
public class ChatMsgAdapter extends RecyclerView.Adapter<ChatMsgAdapter.ViewHolder> {

	private List<MsgItem> msgItemList;
	static class ViewHolder extends RecyclerView.ViewHolder {
		LinearLayout leftlayout;
		LinearLayout rightlayout;
		TextView leftTV;
		TextView rightTV;
		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			leftlayout = itemView.findViewById(R.id.left);
			rightlayout = itemView.findViewById(R.id.right);
			leftTV = itemView.findViewById(R.id.msg_left);
			rightTV=itemView.findViewById(R.id.msg_right);
		}
	}

	public ChatMsgAdapter(List<MsgItem> msgItemList) {
		this.msgItemList = msgItemList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.msg_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		MsgItem message = msgItemList.get(position);
		if (message.getType() == MsgItem.RECEIVE) {
			holder.leftlayout.setVisibility(View.VISIBLE);
			holder.rightlayout.setVisibility(View.GONE);
			holder.leftTV.setText(message.content);
		}
		if (message.getType() == MsgItem.SEND) {
			holder.leftlayout.setVisibility(View.GONE);
			holder.rightlayout.setVisibility(View.VISIBLE);
			holder.rightTV.setText(message.content);
		}
	}

	@Override
	public int getItemCount() {
		return msgItemList.size();
	}



}
