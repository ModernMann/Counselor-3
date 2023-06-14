package com.example.imtrying.firebase;

import androidx.annotation.NonNull;

import com.example.imtrying.Models.DataClass;
import com.example.imtrying.Models.DataClassGame;
import com.example.imtrying.Models.Schedule;
import com.example.imtrying.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Database {

    public static void findUserBy(Predicate<User> predicate, Consumer<User> consumer) {
        findUsers(users -> {
            for (User u : users) {
                if (predicate.test(u)) {
                    consumer.accept(u);
                }
            }
        });
    }

    public static void findUsers(Consumer<List<User>> consumer) {
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> users = new ArrayList<>();
                snapshot.getChildren().forEach(d -> users.add(d.getValue(User.class)));
                consumer.accept(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void signIn(String email, String password, Consumer<User> onSuccess, Consumer<String> onError) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> findUserBy(u -> u.getEmail().equals(email), onSuccess))
                .addOnFailureListener(err -> onError.accept(err.getMessage()));
    }

    public static ValueEventListener fetchCandles(Consumer<List<DataClass>> onSuccess, Runnable onError) {
        return fetchDatabase("Candles", DataClass.class, onSuccess, onError);
    }

    public static ValueEventListener fetchGames(Consumer<List<DataClassGame>> onSuccess, Runnable onError) {
        return fetchDatabase("Games", DataClassGame.class, onSuccess, onError);
    }

    public static ValueEventListener fetchSchedule(Consumer<List<Schedule>> onSuccess, Runnable onError) {
        return fetchDatabase("Schedule", Schedule.class, onSuccess, onError);
    }

    public static <T> ValueEventListener fetchDatabase(String path, Class<T> t, Consumer<List<T>> onSuccess, Runnable onError) {
        return FirebaseDatabase.getInstance().getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<T> list = new ArrayList<>();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    list.add(itemSnapshot.getValue(t));
                }
                onSuccess.accept(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onError.run();
            }
        });
    }

    public static void removeGamesListener(ValueEventListener listener) {
        removeListener("Games", listener);
    }

    public static void removeScheduleListener(ValueEventListener listener) {
        removeListener("Schedule", listener);
    }

    private static void removeListener(String path, ValueEventListener listener) {
        FirebaseDatabase.getInstance().getReference(path).removeEventListener(listener);
    }

    public static void logout() {
        FirebaseAuth.getInstance().signOut();
    }

}
