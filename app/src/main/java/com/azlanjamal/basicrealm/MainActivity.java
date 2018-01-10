package com.azlanjamal.basicrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.azlanjamal.basicrealm.model.SocialAccount;
import com.azlanjamal.basicrealm.model.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etPersonName, etAge, etSocialAccountName, etStatus;

    private Realm myRealm;
    private RealmAsyncTask realmAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPersonName 		= (EditText) findViewById(R.id.etPersonName);
        etAge 				= (EditText) findViewById(R.id.etAge);
        etSocialAccountName = (EditText) findViewById(R.id.etSocialAccount);
        etStatus 			= (EditText) findViewById(R.id.etStatus);

        myRealm = Realm.getDefaultInstance();
    }

    // Add data to Realm using Main UI Thread. Be Careful: As it may BLOCK the UI.
    public void addUserToRealm_Synchronously(View view) {

        final String id = UUID.randomUUID().toString();
        final String name 				= etPersonName.getText().toString();
        final int age 					= Integer.valueOf(etAge.getText().toString());
        final String socialAccountName 	= etSocialAccountName.getText().toString();
        final String status 				= etStatus.getText().toString();

//        try {
//            myRealm.beginTransaction();
//            myRealm.commitTransaction();
//        } catch (Exception e) {
//            myRealm.cancelTransaction();
//        }

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SocialAccount socialAccount = realm.createObject(SocialAccount.class);
                socialAccount.setName(socialAccountName);
                socialAccount.setStatus(status);

                User user = realm.createObject(User.class, id);
                user.setName(name);
                user.setAge(age);
                user.setSocialAccount(socialAccount);
                Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Add Data to Realm in the Background Thread.
    public void addUserToRealm_ASynchronously(View view) {

    }

    public void displayAllUsers(View view) {

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
