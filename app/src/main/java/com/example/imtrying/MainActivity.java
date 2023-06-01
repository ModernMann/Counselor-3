package com.example.imtrying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.imtrying.Models.User;
import com.example.imtrying.databinding.ActivityMainBinding;
import com.example.imtrying.firebase.Database;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

//
// Вход в систему
// Логин admin@mail.ru
// Пароль adminn
//
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSignIn.setOnClickListener(v -> {
            // Проверка вытягивания данных из БД
            showSignInWindow();
        });
        binding.btnRegistration.setOnClickListener(v -> showRegisterWindow());
    }

    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signInWindow = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(signInWindow);

        final MaterialEditText email = signInWindow.findViewById(R.id.emailFiled);
        final MaterialEditText password = signInWindow.findViewById(R.id.passFiled);

        dialog.setNegativeButton("Отменить", (dialogInterface, which) -> dialogInterface.dismiss());

        //
        // Обработка заполненных полей
        //
        dialog.setPositiveButton("Войти", (dialogInterface, which) -> {
            // Проверка на заполнение полей
            if(TextUtils.isEmpty(email.getText().toString())){
                Snackbar.make(binding.getRoot(),"Введите email",Snackbar.LENGTH_LONG).show();
                return;
            }
            if(password.getText().length() < 5 ){
                Snackbar.make(binding.getRoot(),"Введите пароль более 5 символов",Snackbar.LENGTH_LONG).show();
                return;
            }
            Database.signIn(email.getText().toString(), password.getText().toString(), user -> {
                Intent intent = new Intent(MainActivity.this, ActivityMenu.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }, e -> Snackbar.make(binding.getRoot(),"Ошибка авторизации. " + e,Snackbar.LENGTH_LONG).show());
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

        dialog.setNegativeButton("Отменить", (dialogInterface, which) -> dialogInterface.dismiss());

        //
        // Обработка заполненных полей
        //
        dialog.setPositiveButton("Зарегистрироваться", (dialogInterface, which) -> {

            // Проверка на заполнение полей
            if(TextUtils.isEmpty(email.getText().toString())){
                Toast.makeText(MainActivity.this, "Введите email", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(fname.getText().toString())){
                Toast.makeText(MainActivity.this, "Введите имя", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(phone.getText().toString())){
                Toast.makeText(MainActivity.this, "Введите телефон", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(team.getText().toString())){
                Toast.makeText(MainActivity.this, "Введите номер отряда", Toast.LENGTH_SHORT).show();
            }
            else if(password.getText().toString().length() < 5 ){
                Toast.makeText(MainActivity.this, "Введите пароль более 5 символов", Toast.LENGTH_SHORT).show();}
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(authResult -> {
                            User user = new User();
                            user.setEmail(email.getText().toString());
                            user.setName(fname.getText().toString());
                            user.setPassword(password.getText().toString());
                            user.setPhone(phone.getText().toString());
                            user.setTeam(team.getText().toString());

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnSuccessListener(unused -> Snackbar.make(binding.getRoot(),"Регистрация выполнена"
                                            ,Snackbar.LENGTH_LONG).show()).addOnFailureListener(e -> Snackbar.make(binding.getRoot(),"Ошибка регистрации. "
                                            + e.getMessage(),Snackbar.LENGTH_LONG).show());                          });
            }

            //
            //Регистрация пользователя
            //

        });
        dialog.show();
    }

}