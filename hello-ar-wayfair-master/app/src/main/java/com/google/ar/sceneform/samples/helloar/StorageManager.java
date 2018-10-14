package com.google.ar.sceneform.samples.helloar;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/** Helper class for Firebase storage of cloud anchor IDs. */
class StorageManager {

    private static final String TAG = StorageManager.class.getName();
    private static final String KEY_ROOT_DIR = "anchors";
    private final DatabaseReference rootRef;

    StorageManager(Context context) {
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(context);
        rootRef = FirebaseDatabase.getInstance(firebaseApp).getReference().child(KEY_ROOT_DIR);
        DatabaseReference.goOnline();
    }

    void store(String id) {
        rootRef.child(id).setValue(true);
    }

    void getAnchors(ValueEventListener listener) {
        rootRef.addValueEventListener(listener);
    }
}