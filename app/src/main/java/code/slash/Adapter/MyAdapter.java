package code.slash.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import code.slash.Common.Common;
import code.slash.Interface.ILoadMore;
import code.slash.Interface.IRecyclerClickListener;
import code.slash.Model.Event;
import code.slash.R;
import code.slash.event_details;

class  LoadingEvent extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;
    public LoadingEvent(@NonNull View itemView) {
        super(itemView);
        progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView  event_name;
    public TextView  ngo_name;
    public TextView  date;
    public TextView  time;
    IRecyclerClickListener listener;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        event_name=(TextView)itemView.findViewById(R.id.event_name);
        date=(TextView)itemView.findViewById(R.id.event_date);
        ngo_name=(TextView)itemView.findViewById(R.id.ngo_name);
        time=(TextView)itemView.findViewById(R.id.event_time);
        itemView.setOnClickListener(this);
    }

    public void setListener(IRecyclerClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        listener.onItemCliickListener(v, getAdapterPosition());
    }
}

public class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  final int VIEW_TYPE_ITEM_=0,VIEW_TYPE_LOADING=1;
    ILoadMore iLoadMore;
    boolean isLoading;
    Activity activity;
    List<Event> items;
    int visibleThreshold=4;
    int lastVisibleItem,totalItemCount;

    public MyAdapter(RecyclerView recyclerView,Activity activity, List<Event> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager=(LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount=linearLayoutManager.getItemCount();
                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading&& totalItemCount<=(lastVisibleItem+visibleThreshold)){
                    if (iLoadMore !=null){
                        iLoadMore.onLoadMore();

                        isLoading=true;
                    }
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position)==null?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM_;
    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ITEM_){
            View view= LayoutInflater.from(activity)
                    .inflate(R.layout.event_list,parent,false);
            return new ItemViewHolder(view);
        }
        else if (viewType==VIEW_TYPE_LOADING){

            View view= LayoutInflater.from(activity)
                    .inflate(R.layout.event_list,parent,false);
            return new ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ItemViewHolder){

            final Event item = items.get(position);
            ItemViewHolder viewHolder=(ItemViewHolder)holder;

            viewHolder.ngo_name.setText(items.get(position).getNgo_name());
            viewHolder.event_name.setText(items.get(position).getEvent_name());
            viewHolder.date.setText(items.get(position).getDate());
            viewHolder.time.setText(items.get(position).getTime());

            ((ItemViewHolder) holder).setListener(new IRecyclerClickListener() {
                @Override
                public void onItemCliickListener(View view, int pos) {
                    Common.selectedEvent = items.get(pos);
                    Intent intent = new Intent(activity, event_details.class);
                    activity.startActivity(intent);

                }
            });
        }else if(holder instanceof LoadingEvent  ) {
            LoadingEvent loadEvent=(code.slash.Adapter.LoadingEvent)holder;
            loadEvent.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}
