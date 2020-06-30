package com.andresvasquez.holamundoenclases.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andresvasquez.holamundoenclases.R;
import com.andresvasquez.holamundoenclases.model.QuarantineTask;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<QuarantineTask> items;

    public TaskAdapter(Context context, List<QuarantineTask> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public QuarantineTask getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.items.get(position).getId();
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            //No se puede reciclar --> Nuevo
            viewHolder = new ViewHolder();

            //Mostrar el layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.quantine_task_layout, null);

            //Vincular los objetos con los IDs
            viewHolder.parentLinearLayout = view.findViewById(R.id.parentLinearLayout);
            viewHolder.nameTextView = view.findViewById(R.id.nameTextView);
            viewHolder.detailsTextView = view.findViewById(R.id.detailsTextView);
            viewHolder.durationTextView = view.findViewById(R.id.durationTextView);
            viewHolder.iconImageView = view.findViewById(R.id.iconImageView);

            view.setTag(viewHolder); //Guardar para reciclar
        } else {
            //Se puede reciclar
            viewHolder = (ViewHolder) view.getTag(); //Obtenemos el dato reciclado
        }

        //Sí o sí tenemos una vista, ya sea reciclada o nueva
        QuarantineTask task = this.items.get(position);
        viewHolder.nameTextView.setText(task.getName());
        viewHolder.detailsTextView.setText(task.getDetails());
        viewHolder.durationTextView.setText(task.getDuration());
        viewHolder.iconImageView.setImageResource(task.getImage());

        if (task.isFinished()) {
            viewHolder.parentLinearLayout.setBackgroundResource(R.drawable.style_border_finished);
        } else {
            viewHolder.parentLinearLayout.setBackgroundResource(R.drawable.style_border);
        }
        return view;
    }

    static class ViewHolder {
        LinearLayout parentLinearLayout;
        TextView nameTextView;
        TextView detailsTextView;
        TextView durationTextView;
        ImageView iconImageView;
    }

    public void setItems(List<QuarantineTask> items) {
        this.items = items;
    }
}
