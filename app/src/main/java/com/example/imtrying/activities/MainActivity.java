package com.example.imtrying.activities;

import static com.example.imtrying.store.SharedPreferences.EMAIL_KEY;
import static com.example.imtrying.store.SharedPreferences.IS_SIGN_IN_KEY;
import static com.example.imtrying.store.SharedPreferences.PASSWORD_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.databinding.ActivityMainBinding;
import com.example.imtrying.firebase.Database;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

import java.io.IOException;

import kotlin.jvm.functions.Function2;

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

        SharedPreferences prefs = getPrefs();
        boolean isSign = prefs.getBoolean(IS_SIGN_IN_KEY, false);
        if (isSign) {
            String em = prefs.getString(EMAIL_KEY, "");
            String pwd = prefs.getString(PASSWORD_KEY, "");
            signIn(em, pwd);
        }
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
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);

        final MaterialEditText email = sign_in_window.findViewById(R.id.emailFiled);
        final MaterialEditText password = sign_in_window.findViewById(R.id.passFiled);

        dialog.setNegativeButton("Отменить", (dialogInterface, which) -> dialogInterface.dismiss());

        //
        // Обработка заполненных полей
        //
        if (email.equals(null) || password.equals(null)) {
            Snackbar.make(binding.getRoot(), "Ошибка авторизации. Проверьте правильность заполнения полей", Snackbar.LENGTH_LONG).show();
        }
        else {
            email.addValidator(addValidator("Не корректный email", (text, isEmpty) ->
                    text.toString().matches("\\w+@\\w+\\.[a-z]") || !isEmpty));
            password.addValidator(addValidator("Пароль должен быть более 5 символов", (text, isEmpty) -> text.length() > 5));
            dialog.setPositiveButton("Войти", (dialogInterface, which) -> {
                Database.signIn(email.getText().toString(), password.getText().toString(), user -> {
                    Intent intent = new Intent(MainActivity.this, ActivityMenu.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }, err -> Snackbar.make(binding.getRoot(), "Ошибка авторизации. " + err, Snackbar.LENGTH_LONG).show());
            });
            dialog.show();
        }
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

        email.addValidator(addValidator("Не корректный email", (text, isEmpty) ->
                text.toString().matches("\\w+@\\w+\\.[a-z]") || !isEmpty));
        password.addValidator(addValidator("Пароль должен быть более 5 символов", (text, isEmpty) -> text.length() > 5));
        fname.addValidator(addValidator("Не корректное имя", (text, isEmpty) -> !isEmpty));
        fname.addValidator(addValidator("Не корректный телефон", (text, isEmpty) -> !isEmpty));
        fname.addValidator(addValidator("Не корректный номер отряда", (text, isEmpty) -> !isEmpty));

        dialog.setNegativeButton("Отменить", (dialogInterface, which) -> dialogInterface.dismiss());

        //
        // Обработка заполненных полей
        //
        dialog.setPositiveButton("Зарегистрироваться", (dialogInterface, which) -> {
            //
            //Регистрация пользователя
            //
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
        });
        dialog.show();
    }

    private SharedPreferences getPrefs() {
        return getSharedPreferences(com.example.imtrying.store.SharedPreferences.STORE_NAME, Context.MODE_PRIVATE);
    }

    private void setPrefs(String email, String password, boolean isSign) {
        getPrefs().edit()
                .putString(EMAIL_KEY, email)
                .putString(PASSWORD_KEY, password)
                .putBoolean(IS_SIGN_IN_KEY, isSign)
                .apply();
    }

    private void signIn(String email, String password) {
        try{
            Database.signIn(email, password, user -> {
                Intent intent = new Intent(MainActivity.this, ActivityMenu.class);
                intent.putExtra("user", user);
                startActivity(intent);
                setPrefs(email, password, true);
                finish();
            }, err -> Snackbar.make(binding.getRoot(), "Ошибка авторизации. " + err, Snackbar.LENGTH_LONG).show());
        }
        catch(NullPointerException ex){
            Snackbar.make(binding.getRoot(), "Ошибка авторизации. Проверьте правильность заполнения полей"+ex, Snackbar.LENGTH_LONG).show();
        }
    }

    private METValidator addValidator(String error, Function2<CharSequence, Boolean, Boolean> isValid) {
        return new METValidator(error) {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return isValid.invoke(text, isEmpty);
            }
        };
    }

}