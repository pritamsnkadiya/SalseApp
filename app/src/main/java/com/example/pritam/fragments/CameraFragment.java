package com.example.pritam.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pritam.salseapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class CameraFragment extends Fragment {

    private static final int requestCode = 20;
    private Button add_new_camera_next, click_image1, click_image2, click_image3, save_camera_images;
    private ImageView image_view1, image_view2, image_view3;
    private View rootView;
    static int a = 0, b = 0, c = 0;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDetach() {
        super.onDetach ();
    }

    public static CameraFragment newInstance(String param1, String param2) {
        CameraFragment fragment = new CameraFragment ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_camera, container, false);
        add_new_camera_next = (Button) rootView.findViewById (R.id.add_new_camera_next);
        click_image1 = (Button) rootView.findViewById (R.id.click_image1);
        click_image2 = (Button) rootView.findViewById (R.id.click_image2);
        click_image3 = (Button) rootView.findViewById (R.id.click_image3);
        save_camera_images = (Button) rootView.findViewById (R.id.save_camera_images);
        image_view1 = (ImageView) rootView.findViewById (R.id.image_view1);
        image_view2 = (ImageView) rootView.findViewById (R.id.image_view2);
        image_view3 = (ImageView) rootView.findViewById (R.id.image_view3);

        save_camera_images.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                add_new_camera_next.setEnabled (true);
                add_new_camera_next.setTextColor (Color.WHITE);
                add_new_camera_next.setBackgroundColor (getResources ().getColor (R.color.colorPrimary));
                save_camera_images.setVisibility (View.INVISIBLE);
            }
        });
        add_new_camera_next.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                swapFragment ();
            }
        });
        click_image1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                a = 1;
                Intent photoCaptureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult (photoCaptureIntent, requestCode);
            }
        });
        click_image2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                b = 1;
                Intent photoCaptureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult (photoCaptureIntent, requestCode);
            }
        });
        click_image3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                c = 1;
                Intent photoCaptureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult (photoCaptureIntent, requestCode);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void swapFragment() {
        ProductFragment newProductFragment = new ProductFragment ();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction ();
        fragmentTransaction.replace (R.id.frame_container, newProductFragment);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras ().get ("data");

            String partFilename = currentDateFormat ();
            storeCameraPhotoInSDCard (bitmap, partFilename);

            // display the image from SD Card to ImageView Control
            String storeFilename = "/photo_" + partFilename + ".jpg";
            Bitmap mBitmap = getImageFileFromSDCard (storeFilename);
            if (a == 1) {
                image_view1.setImageBitmap (bitmap);
                a = 0;
            }
            if (b == 1) {
                image_view2.setImageBitmap (bitmap);
                b = 0;
            }
            if (c == 1) {
                image_view3.setImageBitmap (bitmap);
                c = 0;
            }
        }
    }

    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format (new Date ());
        return currentTimeStamp;
    }

    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate) {
        File outputFile = new File (Environment.getExternalStorageDirectory (), "/photo_" + currentDate + ".jpg");
        Toast.makeText (getContext (), outputFile + " :", Toast.LENGTH_SHORT).show ();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream (outputFile);
            bitmap.compress (Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush ();
            fileOutputStream.close ();
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        Log.d ("Image path :", outputFile + " :");
    }

    private Bitmap getImageFileFromSDCard(String filename) {
        Bitmap bitmap = null;
        File imageFile = new File (Environment.getExternalStorageDirectory () + filename);
        try {
            FileInputStream fis = new FileInputStream (imageFile);
            bitmap = BitmapFactory.decodeStream (fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        Log.d ("Image path :", imageFile + " :");
        return bitmap;
    }
}
