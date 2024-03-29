package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchContact();
    }

    private void fetchContact() {

        ArrayList<String> contacts = new ArrayList<>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = null;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String[] selectionArgs = null;
        String sortOrder = null;

        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while (cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


            contacts.add(name + "\n" + num);
        }
        ((ListView)findViewById(R.id.ListContact)).
                setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts));
    }
}
