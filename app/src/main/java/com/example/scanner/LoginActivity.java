package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scanner.db.MyDbManager;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private Button loginButton;
    private MyDbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Инициализация элементов пользовательского интерфейса
        emailInput = findViewById(R.id.login_mail_input);
        passwordInput = findViewById(R.id.login_password_input);
        loginButton = findViewById(R.id.login_btn);


        // Инициализация MyDbManager
        dbManager = new MyDbManager(this);
        dbManager.openDb();

        // Обработка нажатия кнопки "Вход"
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Обработка нажатия кнопки "Сканирование"
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Выполнение действий при нажатии на кнопку "Сканирование"
                Intent scanIntent = new Intent(LoginActivity.this, KnopkaScan.class);
                startActivity(scanIntent);
            }
        });
    }

    // Метод для авторизации пользователя
    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            List<String> userList = dbManager.getFromDb();
            if (userList.contains(email)) {
                Toast.makeText(this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                // Дополнительные действия после успешной авторизации
            } else {
                Toast.makeText(this, "Неверные данные авторизации", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Закрытие базы данных при уничтожении активити
        dbManager.closeDb();
    }
}