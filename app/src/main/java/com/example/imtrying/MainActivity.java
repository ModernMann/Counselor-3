package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.imtrying.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;


//
// Вход в систему
// Логин admin@mail.ru
// Пароль adminn
//
public class MainActivity extends AppCompatActivity {


    Button btnSignIn, btnRegistration;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверка вытягивания данных из БД

                showSignInWindow();
            }
        });

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
    }

    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);

        final MaterialEditText email = sign_in_window.findViewById(R.id.emailFiled);
        final MaterialEditText password = sign_in_window.findViewById(R.id.passFiled);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        //
        // Обработка заполненных полей
        //
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                // Проверка на заполнение полей
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root,"Введите email",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.getText().toString().length() < 5 ){
                    Snackbar.make(root,"Введите пароль более 5 символов",Snackbar.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        List<User> userList = new ArrayList<>();
                                        snapshot.getChildren().forEach(dataSnapshot -> {
                                            userList.add(dataSnapshot.getValue(User.class));
                                        });
                                        for (User user : userList) {
                                            if (user.getEmail().toString().equals(email.getText().toString())){
                                                Intent intent = new Intent(MainActivity.this, ActivityMenu.class);
                                                intent.putExtra("email",user.getEmail());
                                                startActivity(intent);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                Intent intent = new Intent(MainActivity.this, ActivityMenu.class);
                                startActivity(intent);

                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root,"Ошибка авторизации. "
                                        + e.getMessage(),Snackbar.LENGTH_LONG).show();
                            }
                        });

            }
        });

        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.registration_window, null);
        dialog.setView(register_window);

        final MaterialEditText email = register_window.findViewById(R.id.emailFiled);
        final MaterialEditText password = register_window.findViewById(R.id.passFiled);
        final MaterialEditText fname = register_window.findViewById(R.id.fnameFiled);
        final MaterialEditText phone = register_window.findViewById(R.id.phoneFiled);
        final MaterialEditText team = register_window.findViewById(R.id.teamField);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        //
        // Обработка заполненных полей
        //
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                // Проверка на заполнение полей
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root,"Введите email",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(fname.getText().toString())){
                    Snackbar.make(root,"Введите имя",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root,"Введите телефон",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(team.getText().toString())){
                    Snackbar.make(root,"Введите номер отряда",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.getText().toString().length() < 5 ){
                    Snackbar.make(root,"Введите пароль более 5 символов",Snackbar.LENGTH_LONG).show();
                    return;
                }


                //
                //Регистрация пользователя
                //
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setName(fname.getText().toString());
                                user.setPassword(password.getText().toString());
                                user.setPhone(phone.getText().toString());
                                user.setTeam(team.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(root,"Регистрация выполнена"
                                                        ,Snackbar.LENGTH_LONG).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(root,"Ошибка регистрации. "
                                                        + e.getMessage(),Snackbar.LENGTH_LONG).show();
                                            }
                                        });                          }
                        });
            }
        });

        dialog.show();


    }
    private void init() {
        btnRegistration = findViewById(R.id.btnRegistration);
        btnSignIn = findViewById(R.id.btnSignIn);

        root = findViewById(R.id.root_activity);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
    }

}