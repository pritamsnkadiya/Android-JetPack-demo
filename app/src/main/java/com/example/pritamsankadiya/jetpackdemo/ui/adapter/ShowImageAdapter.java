package com.example.pritamsankadiya.jetpackdemo.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pritamsankadiya.jetpackdemo.R;
import com.example.pritamsankadiya.jetpackdemo.data.model.MultiView;
import com.example.pritamsankadiya.jetpackdemo.databinding.LayoutImageViewBinding;

import java.util.List;

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.MyViewHolder> {

    private List<MultiView> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;


    public ShowImageAdapter(List<MultiView> postList, PostsAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LayoutImageViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_image_view, parent, false);
        return new MyViewHolder(binding);

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setMultiView(postList.get(position));
        holder.binding.actionImage.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPostClicked(postList.get(position),holder.getPosition());
            }
        });
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final LayoutImageViewBinding binding;

        public MyViewHolder(final LayoutImageViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public interface PostsAdapterListener {
        void onPostClicked(MultiView post, int position);
    }
}