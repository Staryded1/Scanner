package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scanner.db.MyDbManager;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameInput, emailInput, passwordInput;
    private Button registerButton;
    private MyDbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Инициализация элементов пользовательского интерфейса
        usernameInput = findViewById(R.id.register_username_input);
        emailInput = findViewById(R.id.register_mail_input);
        passwordInput = findViewById(R.id.register_password_input);
        registerButton = findViewById(R.id.register_btn);

        // Инициализация MyDbManager
        dbManager = new MyDbManager(this);
        dbManager.openDb();

        // Обработка нажатия кнопки "Регистрация"
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    // Метод для регистрации пользователя
    private void registerUser() {
        String username = usernameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            // Выполнение регистрации пользователя в базе данных
            dbManager.insertToDb(username, email, password);
            Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
            // Дополнительные действия после успешной регистрации
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