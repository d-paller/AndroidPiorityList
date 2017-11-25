package com.example.dpaller.todov2.Services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dpaller.todov2.Models.TodoItem;
import com.example.dpaller.todov2.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dpall on 11/15/2017.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTV;
        public TextView descriptionTV;
        public TextView tagTV;
        public TextView priorityTV;

        public Button doneBtn;
        public Button editBtn;

        public ViewHolder(View itemView){
            super(itemView);

            titleTV         = (TextView) itemView.findViewById(R.id.title);
            descriptionTV   = (TextView) itemView.findViewById(R.id.description);
            tagTV           = (TextView) itemView.findViewById(R.id.tagText);
            priorityTV      = (TextView) itemView.findViewById(R.id.priorityText);

            doneBtn         = (Button) itemView.findViewById(R.id.done_btn);
            editBtn         = (Button) itemView.findViewById(R.id.done_btn);
        }
    }

    private List<TodoItem> _listItems;
    private Context _context;

    public TodoAdapter(Context context, List<TodoItem> listItems){
        _context = context;
        _listItems = listItems;
    }

    private Context getContext(){
        return _context;
    }

    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View cardView = inflater.inflate(R.layout.card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(cardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        TodoItem todoItem = _listItems.get(position);

        // Set item views based on your views and data model
        TextView titleTV = viewHolder.titleTV;
        titleTV.setText(todoItem.get_title());
        titleTV.setTag(todoItem.get_itemId());

        TextView descriptionTV = viewHolder.descriptionTV;
        descriptionTV.setText(todoItem.get_description());

        TextView tagTV = viewHolder.tagTV;
        tagTV.setText(todoItem.get_tag());

        TextView priorityTV = viewHolder.priorityTV;
        //priorityTV.setText(todoItem.get_priority());


        Button editButton = viewHolder.editBtn;
        Button doneButton = viewHolder.doneBtn;
    }

    @Override
    public int getItemCount() {
        if (_listItems == null)
            return 0;
        else
            return _listItems.size();
    }
}
