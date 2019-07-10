package com.hfad.notifierapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hfad.notifierapp.R;
import com.hfad.notifierapp.adapter.MessageAdapter;
import com.hfad.notifierapp.entity.Message;
import com.hfad.notifierapp.entity.Body;
import com.hfad.notifierapp.retrofit.RetrofitClient;
import com.hfad.notifierapp.storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {
    private int number;
    private String name;
    private ListView messageLV;
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messageList;
    private ArrayList<Message> messages;
    private TextView nameTV;
    private Button sendButton;
    private EditText sendET;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        getActivity().setTitle(R.string.message);
        number = getArguments().getInt("Number");
        name = getArguments().getString("Name");
        nameTV = view.findViewById(R.id.fragmentMessageTV);
        nameTV.setText(name);
        sendET = view.findViewById(R.id.fragmentMessageEnterET);
        sendButton = view.findViewById(R.id.fragmentMessageButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSendClick();
            }
        });
        messageLV = view.findViewById(R.id.fragmentMessageLV);
        messageList = new ArrayList<>();
        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(view.getContext(),messageList);
        messageLV.setAdapter(messageAdapter);
        getMessages();
        
        
        return view;
    }

    private void onSendClick() {
        String string = sendET.getText().toString().trim();
        if (string.isEmpty()) {
            sendET.setError("Enter message");
            sendET.requestFocus();
            return;
        }
        Call<Body> call = RetrofitClient.getInstance().getApi().addmessage(SharedPrefManager.getInstance(getActivity()).getToken(),new Body(string,number));

        call.enqueue(new Callback<Body>() {
            @Override
            public void onResponse(Call<Body> call, Response<Body> response) {
                if(response.isSuccessful()){
                    Body body = response.body();
                    getMessages();
                    sendET.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    sendET.setText("");
                }
            }

            @Override
            public void onFailure(Call<Body> call, Throwable t) {

            }
        });
    }

    private void getMessages() {
        Call<ArrayList<Message>> call = RetrofitClient.getInstance().getApi().getMessages(SharedPrefManager.getInstance(getActivity()).getToken(),new Body("Number",number));

        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if(response.isSuccessful()){
                    messages = response.body();
                    messageList.clear();
                    for(Message message: messages){
                        messageList.add(message);
                    }
                    messageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

            }
        });
    }
}
