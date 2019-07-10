package com.hfad.notifierapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hfad.notifierapp.R;
import com.hfad.notifierapp.entity.Message;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, ArrayList<Message> messages) {
        super(context, R.layout.message_row ,messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.message_row,parent,false);

        Message message = getItem(position);
        TextView messageName = view.findViewById(R.id.messageNameTV);
        TextView messageSurname = view.findViewById(R.id.messageSurnameTV);
        TextView messageDate = view.findViewById(R.id.messageDateTV);
        TextView messageText = view.findViewById(R.id.messageTextTV);

        messageName.setText(message.getName());
        messageSurname.setText(message.getSurname());
        messageDate.setText(message.getDate());
        messageText.setText(message.getText());

        return view;
    }
}
