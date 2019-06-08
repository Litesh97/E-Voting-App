package com.example.rajat.voting_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_signUp extends AppCompatActivity{
    private EditText email;
    private EditText name;
    private EditText pno;
    private EditText pass;
    private ImageButton butt;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference user_table;


   /* ActionCodeSettings actionCodeSettings =
            ActionCodeSettings.newBuilder()
                    // URL you want to redirect back to. The domain (www.example.com) for this
                    // URL must be whitelisted in the Firebase Console.
                    .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                    // This must be true
                    .setHandleCodeInApp(true)
                    .setIOSBundleId("com.example.ios")
                    .setAndroidPackageName(
                            "com.example.android",
                            true,  installIfNotAvailable
                            "12"     minimumVersion )
                    .build();
    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                sendVerificationEmail();
            }
            else {
            }

        }
        private void sendVerificationEmail()
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Admin_signUp.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        overridePendingTransition(0, 0);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());

                    }
                }
            });

        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);
        //initializing firebase auth object
        //firebaseAut = FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.input_admin_email);
        name=(EditText)findViewById(R.id.input_admin_name);
        pno=(EditText)findViewById(R.id.input_admin_contact);
        pass=(EditText)findViewById(R.id.input_admin_password);
        butt=(ImageButton)findViewById(R.id.register_button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==butt){
                    admin_sign_up_Submit();
                }
            }

        });
        firebaseAuth=FirebaseAuth.getInstance();
        user_table=FirebaseDatabase.getInstance().getReference().child("users");

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }

    public void admin_sign_up_Submit() {
        //TODO Code for validating details and putting in database
        //startActivity(new Intent(Admin_signUp.this,Otp_verification.class));
        String finalemail = email.getText().toString().trim();
        String finalpassword = pass.getText().toString().trim();
        String finalname = name.getText().toString().trim();
        String finalphonenumber = pno.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(finalemail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(finalpassword)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(finalname)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(finalphonenumber)) {
            Toast.makeText(this, "Please enter phonenumber", Toast.LENGTH_LONG).show();
            return;
        }

        Task<AuthResult> authResultTask=firebaseAuth.createUserWithEmailAndPassword(finalemail,finalpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String finalemail = email.getText().toString().trim();
                    String finalpassword = pass.getText().toString().trim();
                    String finalname = name.getText().toString().trim();
                    String finalphonenumber = pno.getText().toString().trim();
                    user new_user=new user(finalname,finalemail,finalpassword,finalphonenumber);
                    String prim=finalemail.split("@",2)[0];
                    user_table.child(prim).setValue(new_user);
                    Toast.makeText(Admin_signUp.this,"User Successfully Created!!!",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }
                else
                {
                    Toast.makeText(Admin_signUp.this,"Try Again!!!",Toast.LENGTH_LONG).show();
                }
            }
        });

    };












       /* FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });

        //creating a new user
        firebaseAut.createUserWithEmailAndPassword(finalemail,finalpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    checkifEmailverified();
                    //display some message here
                } else {
                    //display some message here

                    Toast.makeText(Admin_signUp.this, "Registration Error", Toast.LENGTH_LONG).show();
                }
            }
        });

// ...
    }

    private void checkifEmailverified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            Toast.makeText(Admin_signUp.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }*/

}
  /*
btnclick function to go to gallery
  package com.example.gallerypickimage;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final int SELECTED_PICTURE=1;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv=(ImageView)findViewById(R.id.imageView1);
	}
	public void btnClick(View v){
		Intent i=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, SELECTED_PICTURE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case SELECTED_PICTURE:
			if(resultCode==RESULT_OK){
				Uri uri=data.getData();
				String[]projection={MediaStore.Images.Media.DATA};

				Cursor cursor=getContentResolver().query(uri, projection, null, null, null);
				cursor.moveToFirst();

				int columnIndex=cursor.getColumnIndex(projection[0]);
				String filePath=cursor.getString(columnIndex);
				cursor.close();

				Bitmap yourSelectedImage=BitmapFactory.decodeFile(filePath);
				Drawable d=new BitmapDrawable(yourSelectedImage);

				iv.setBackground(d);

			}
			break;

		default:
			break;
		}

	}

}

   */