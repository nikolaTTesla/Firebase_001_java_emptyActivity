package com.example.firebase_001_java_emptyactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textID);

        //Making reference to our database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");
        DatabaseReference myRef = database.getReference("ShumPLUStemp");

        //Writing data to firebase project:
        //myRef.setValue("Hello, whats up!");

        //read from database:
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Here we want to execute code when receiving data:
                String s = (String) snapshot.getValue();
                textView.setText(s);
                //If temp is higher than 90 send notification
                String sTemp = "";
                int iCounter = 0;
                for(int i = 0; i <= s.length(); i++)
                {
                    if(s.charAt(i) == 'f')
                    {
                        break;
                    }
                    if(iCounter == 1)
                    {
                        sTemp += s.charAt(i);
                    }
                    if(s.charAt(i) == '_')
                    {
                        iCounter++;
                    }
                }

                System.out.println(sTemp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}