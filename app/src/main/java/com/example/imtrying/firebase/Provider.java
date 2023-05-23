package com.example.imtrying.firebase;

import androidx.annotation.NonNull;

import com.example.imtrying.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Provider {

    public void GetUsers(Consumer<List<User>> fun) {
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> users = new ArrayList<>();
                snapshot.getChildren().forEach(dataSnapshot -> {
                    String email = (String) dataSnapshot.child("email").getValue();

                    User user = new User();
                    user.setEmail(email);
                    users.add(user);
                });
                fun.accept(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
