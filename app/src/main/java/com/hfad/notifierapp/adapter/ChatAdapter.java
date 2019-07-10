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
import com.hfad.notifierapp.entity.Chat;
import com.hfad.notifierapp.entity.User;
import com.hfad.notifierapp.storage.SharedPrefManager;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<Chat> {

    public ChatAdapter(@NonNull Context context, ArrayList<Chat> chats) {
        super(context, R.layout.chat_row,chats);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.chat_row,parent,false);

        Chat chat = getItem(position);
        TextView chatName = view.findViewById(R.id.chatNameTV);
        TextView chatSurname = view.findViewById(R.id.chatSurnameTV);
        TextView chatDate = view.findViewById(R.id.chatDateTV);
        TextView chatEmail = view.findViewById(R.id.chatEmailTV);
        TextView chatEmailR = view.findViewById(R.id.chatEmailRTV);
        TextView chatMessage = view.findViewById(R.id.chatMessageTV);

        User user = SharedPrefManager.getInstance(getContext()).getUser();
        if(chat.getGroupNumber() == null){
            if(user.getEmail().equals(chat.getSenderEmail())){
                chatName.setText(chat.getReceiverName());
                chatSurname.setText(chat.getReceiverSurname());
                chatEmail.setText(chat.getReceiverEmail());
                chatEmailR.setText(chat.getSenderEmail());
            }else{
                chatName.setText(chat.getSenderName());
                chatSurname.setText(chat.getSenderSurname());
                chatEmail.setText(chat.getSenderEmail());
                chatEmailR.setText(chat.getReceiverEmail());
            }
        }else{
            if(user.getEmail().equals(chat.getSenderEmail())){
                chatName.setText(chat.getGroupNumber());
                chatSurname.setText("");
                chatEmail.setText(chat.getSenderEmail());
                chatEmailR.setText(chat.getGroupNumber());
            }else {
                chatName.setText(chat.getSenderName());
                chatSurname.setText(chat.getSenderSurname());
                chatEmail.setText(chat.getSenderEmail());
                chatEmailR.setText(chat.getGroupNumber());
            }

        }
        if(!chat.getMessages().isEmpty()){
            chatDate.setText(chat.getMessages().get(chat.getMessages().size()-1).getDate());
            chatMessage.setText(chat.getMessages().get(chat.getMessages().size()-1).getText());
        }else{
            chatDate.setText("");
            chatMessage.setText("");
        }

        return view;
    }
}
