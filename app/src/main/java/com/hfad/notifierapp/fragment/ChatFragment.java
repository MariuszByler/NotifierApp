package com.hfad.notifierapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hfad.notifierapp.R;
import com.hfad.notifierapp.adapter.ChatAdapter;
import com.hfad.notifierapp.entity.Chat;
import com.hfad.notifierapp.entity.Body;
import com.hfad.notifierapp.entity.User;
import com.hfad.notifierapp.retrofit.RetrofitClient;
import com.hfad.notifierapp.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {
    private ListView chatLV;
    private ChatAdapter chatAdapter;
    private ArrayList<Chat> chatsList;
    private ArrayList<Chat> chats;
    private Button addButton;
    private EditText addET;
    private Button searchButton;
    private EditText searchET;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        getActivity().setTitle(R.string.messenger);
        addET = view.findViewById(R.id.fragmentChatEnterET);
        searchET = view.findViewById(R.id.fragmentChatET);
        searchButton = view.findViewById(R.id.fragmentChatSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchClick();
            }
        });
        addButton = view.findViewById(R.id.fragmentChatButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddClick();
            }
        });
        chatLV = view.findViewById(R.id.fragmentChatLV);
        chatLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                String name = "Name";
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(chatsList.get(i).getGroupNumber() == null){
                    if(user.getEmail().equals(chatsList.get(i).getSenderEmail())){
                        name = chatsList.get(i).getReceiverName() + " "+ chatsList.get(i).getReceiverSurname();
                    }else{
                        name = chatsList.get(i).getSenderName() + " "+ chatsList.get(i).getSenderSurname();

                    }
                }else{
                    if(user.getEmail().equals(chatsList.get(i).getSenderEmail())){
                        name = chatsList.get(i).getGroupNumber();
                    }else{
                        name = chatsList.get(i).getSenderName() + " "+ chatsList.get(i).getSenderSurname();
                    }
                }
                bundle.putInt("Number",chatsList.get(i).getNumber());
                bundle.putString("Name",name);
                MessageFragment messageFragmentg = new MessageFragment();
                messageFragmentg.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, messageFragmentg).addToBackStack("tag").commit();

            }
        });
        chatsList = new ArrayList<>();
        chats = new ArrayList<>();
        chatAdapter = new ChatAdapter(view.getContext(),chatsList);
        chatLV.setAdapter(chatAdapter);
        getChats();
        return view;
    }

    private void onSearchClick() {
        String searchText = searchET.getText().toString().trim();

        Pattern pattern = Pattern.compile(searchText.toLowerCase());

        chatsList.clear();
        for(Chat chat : chats){
            String name = "Name";
            User user = SharedPrefManager.getInstance(getContext()).getUser();
            if(chat.getGroupNumber() == null){
                if(user.getEmail().equals(chat.getSenderEmail())){
                    name = chat.getReceiverName() + " "+ chat.getReceiverSurname();
                }else{
                    name = chat.getSenderName() + " "+ chat.getSenderSurname();

                }
            }else{
                if(user.getEmail().equals(chat.getSenderEmail())){
                    name = chat.getGroupNumber();
                }else{
                    name = chat.getSenderName() + " "+ chat.getSenderSurname();
                }
            }
            Matcher matcher = pattern.matcher(name.toLowerCase());
            if(matcher.find()){
                chatsList.add(chat);
            }
        }
        chatAdapter.notifyDataSetChanged();
        searchET.onEditorAction(EditorInfo.IME_ACTION_DONE);
        searchET.setText("");
    }


    private void onAddClick() {
        String string = addET.getText().toString().trim();

        if(string.isEmpty()){
            addET.setError("Enter email");
            addET.requestFocus();
            return;
        }
        Call<Body> call = RetrofitClient.getInstance().getApi().addChat(SharedPrefManager.getInstance(getActivity()).getToken(),new Body(string,1));
        call.enqueue(new Callback<Body>() {
            @Override
            public void onResponse(Call<Body> call, Response<Body> response) {
                if(response.isSuccessful()){
                    Body body = response.body();
                    addET.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    addET.setText("");
                    getChats();
                }
            }

            @Override
            public void onFailure(Call<Body> call, Throwable t) {

            }
        });
    }

    private void getChats() {
        Call<ArrayList<Chat>> call = RetrofitClient.getInstance().getApi().getChats(SharedPrefManager.getInstance(getActivity()).getToken());

        call.enqueue(new Callback<ArrayList<Chat>>() {
            @Override
            public void onResponse(Call<ArrayList<Chat>> call, Response<ArrayList<Chat>> response) {
                if(response.isSuccessful()){
                    chats = response.body();
                    chatsList.clear();
                    for(Chat chat : chats){
                        chatsList.add(chat);
                    }
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chat>> call, Throwable t) {

            }
        });
    }


}