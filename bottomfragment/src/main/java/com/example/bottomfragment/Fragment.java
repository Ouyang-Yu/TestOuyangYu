package com.example.bottomfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ouyang Yu
 * @date 2019/10/22 11:03
 */
public class Fragment extends android.app.Fragment {
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	  View view = inflater.inflate(R.layout.fra1, null);
	  return view;
   }//为Fragment设置布局
}
