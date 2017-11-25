package edu.cornell.nds_instagram;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.net.Uri;
import android.database.Cursor;


import java.io.FileNotFoundException;
import java.io.InputStream;


public class SearchOrUploadActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private ImageView imgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_or_upload);



        imgPicture = (ImageView)  findViewById(R.id.imgPicture);

    }

    /** Called when the user taps the Send button */
    public void UploadPhotoActivity(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

        }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {

            Context context = getApplicationContext();
            CharSequence text = "Selection Success!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();




            Uri selectedImage = data.getData();

            InputStream inputStream;

            try {
                inputStream = getContentResolver().openInputStream(selectedImage);
                Bitmap image = BitmapFactory.decodeStream(inputStream);

                imgPicture.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this,"Unable to fetch img",Toast.LENGTH_SHORT).show();
            }

        }
    }
}


