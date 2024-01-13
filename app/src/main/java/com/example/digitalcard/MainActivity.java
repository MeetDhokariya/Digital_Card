package com.example.digitalcard;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView imageCircle;
    private TextInputEditText fullName,designation,company,aboutMe,contactNumber,whatappNumber,email,servicesInfo;
    private TextInputLayout fullNameLayout,designationLayout,companyLayout,aboutMeLayout,contactNumberLayout,whatappNumberLayout,emailLayout,servicesInfoLayout;
    private Button next;
    private RadioGroup rdGroup;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBilding();

        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                imageCircle.setImageURI(uri);
            }
        });

        imageCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosseAlert();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameTextEdit = fullName.getText().toString();
                String designationTextEdit = designation.getText().toString();
                String companyTextEdit = company.getText().toString();
                String aboutMeEditText = aboutMe.getText().toString();
                String contactNumberTextEdit = contactNumber.getText().toString();
                String whatappNumberTextEdit = whatappNumber.getText().toString();
                String emailTextEdit = email.getText().toString();
                String servicesInfoTextEdit = servicesInfo.getText().toString();
                int id = rdGroup.getCheckedRadioButtonId();

                if (fullNameTextEdit.isEmpty() && designationTextEdit.isEmpty() &&
                        companyTextEdit.isEmpty() && aboutMeEditText.isEmpty() &&
                        contactNumberTextEdit.isEmpty() && whatappNumberTextEdit.isEmpty()
                        && emailTextEdit.isEmpty() && servicesInfoTextEdit.isEmpty()){

                    fullName.setError("Enter Your Full Name");
                    fullNameLayout.setError("Enter Your Full Name");
                    fullNameLayout.isErrorEnabled();
                    designation.setError("Enter Your Designation");
                    designationLayout.setError("Enter Your Designation");
                    company.setError("Enter Your Company Name");
                    companyLayout.setError("Enter Your Company Name");
                    aboutMe.setError("Enter Your AboutMe");
                    aboutMeLayout.setError("Enter Your AboutMe");
                    contactNumber.setError("Enter Your Contact Number");
                    contactNumberLayout.setError("Enter Your Contact Number");
                    whatappNumber.setError("Enter Your Whatapp Number");
                    whatappNumberLayout.setError("Enter Your Whatapp Number");
                    email.setError("Enter Your Email");
                    emailLayout.setError("Enter Your Email");
                    servicesInfo.setError("Enter Your Services Info");
                    servicesInfoLayout.setError("Enter Your Services Info");
                } else if (fullNameTextEdit.isEmpty()) {
                    fullName.setError("Enter Your Full Name");
                    fullNameLayout.setError("Enter Your Full Name");

                } else if (designationTextEdit.isEmpty()) {
                    designation.setError("Enter Your Designation");
                    designationLayout.setError("Enter Your Designation");
                } else if (companyTextEdit.isEmpty()) {
                    company.setError("Enter Your Company Name");
                    companyLayout.setError("Enter Your Company Name");
                } else if (aboutMeEditText.isEmpty()) {
                    aboutMe.setError("Enter Your AboutMe");
                    aboutMeLayout.setError("Enter Your AboutMe");
                } else if (contactNumberTextEdit.isEmpty()) {
                    contactNumber.setError("Enter Your Contact Number");
                    contactNumberLayout.setError("Enter Your Contact Number");
                } else if (whatappNumberTextEdit.isEmpty()) {
                    whatappNumber.setError("Enter Your Whatapp Number");
                    whatappNumberLayout.setError("Enter Your Whatapp Number");
                } else if (emailTextEdit.isEmpty()) {
                    email.setError("Enter Your Email");
                    emailLayout.setError("Enter Your Email");
                } else if (servicesInfoTextEdit.isEmpty()) {
                    servicesInfo.setError("Enter Your Services Info");
                    servicesInfoLayout.setError("Enter Your Services Info");
                }
                else {
                }
                Intent intent = new Intent(MainActivity.this,InformationActivity.class);
                startActivity(intent);

            }
        });

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rdGroup.getCheckedRadioButtonId();

                if (id == R.id.yesButton){
                    whatappNumberLayout.setVisibility(View.GONE);
                }
                else{
                    whatappNumberLayout.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void initBilding() {
        imageCircle = findViewById(R.id.imgeCircle);
        fullName = findViewById(R.id.fullNameTextEdit);
        fullNameLayout = findViewById(R.id.fullName);
        designation = findViewById(R.id.designationTextEdit);
        designationLayout = findViewById(R.id.designation);
        company = findViewById(R.id.companyTextEdit);
        companyLayout = findViewById(R.id.company);
        aboutMe = findViewById(R.id.aboutMeEditText);
        aboutMeLayout = findViewById(R.id.aboutMe);
        contactNumber = findViewById(R.id.contactNumberTextEdit);
        contactNumberLayout = findViewById(R.id.contactNumber);
        whatappNumber = findViewById(R.id.whatappNumberTextEdit);
        whatappNumberLayout = findViewById(R.id.whatappNumber);
        email = findViewById(R.id.emailTextEdit);
        emailLayout = findViewById(R.id.email);
        servicesInfo = findViewById(R.id.servicesInfoTextEdit);
        servicesInfoLayout = findViewById(R.id.servicesInfo);
        next = findViewById(R.id.nextButton);
        rdGroup = findViewById(R.id.dot);
    }

    void chosseAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Any Select.");
        alertDialog.setMessage("Your Choose.");
        alertDialog.setPositiveButton(
                "Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,1);
                    }
                });
        alertDialog.setNegativeButton(
                "Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        pickMedia.launch(new PickVisualMediaRequest.Builder()
                                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                                .build());
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                         startActivityForResult(intent,2);


                    }
                });
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (data != null) {
                Uri uri = data.getData();
                imageCircle.setImageURI(uri);
            }
        } else if (requestCode == 1) {
            if (data != null) {
                Bitmap b1 = (Bitmap) data.getExtras().get("data") ;
                imageCircle.setImageBitmap(b1);
            }
        }
    }
}