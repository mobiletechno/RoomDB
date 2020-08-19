package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.View.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Custom.Dialogbox.EightFoldsDatePickerDialog;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Pojo.Note;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.R;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.ViewModel.PageViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    private PageViewModel pageViewModel;
    private TextInputEditText mStudentname, mDatepick, mFathername, mMotherName;
    public static String mydate, imageString, returndate;
    public static Bitmap bitmap;
    private ImageView muploaddata;
    private int PICK_IMAGE_REQUEST = 1;
    public static boolean isdatepicker = false;


    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of this fragment
     * crated by rajkumar
     *
     * @return A new instance of fragment FirstFragment.
     */
    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init ViewModel
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.firstfragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStudentname = view.findViewById(R.id.textInputTextName);
        mDatepick = view.findViewById(R.id.textInputTextName1);
        muploaddata = view.findViewById(R.id.imageView);
        muploaddata.setOnClickListener(this);
        mDatepick.setClickable(false);

        mDatepick.setAlpha(0.5f);
        mDatepick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                date();


                return false;
            }


        });
        mFathername = view.findViewById(R.id.textInputTextName2);
        mMotherName = view.findViewById(R.id.textInputTextName3);
        Button savebutton = view.findViewById(R.id.button);


        savebutton.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                //decode base64 string to image
                imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                Log.e("mybitmap", String.valueOf(imageString));


                muploaddata.setImageBitmap(decodedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveNote() {
        if (mStudentname.getText().toString().trim().length() > 0 && mDatepick.getText().toString().trim().length() > 0 && mFathername.getText().toString().trim().length() > 0 && mMotherName.getText().toString().trim().length() > 0) {

            String strstudent = mStudentname.getText().toString();
            String strfather = mFathername.getText().toString();
            String strmother = mMotherName.getText().toString();
            String strdate = mydate;
            String strimg = imageString;
            mDatepick.setText(strdate);

            //  Note note = new Note(title, description, priority);
            Note note = new Note(strstudent, strfather, strmother, strdate, strimg);


            pageViewModel.insert(note);
            mStudentname.getText().clear();
            mFathername.getText().clear();
            mMotherName.getText().clear();
            mDatepick.getText().clear();
            muploaddata.setImageResource(R.drawable.upload_image);
            alertdialogsucess();
        } else {
            alertdialog();

        }
    }

    private void alertdialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("fill all input fields");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void date() {

        final EightFoldsDatePickerDialog datePickerDialog = new EightFoldsDatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Do something with date
                mydate = dayOfMonth + "/" + month + "/" + year;
                mDatepick.setText(dayOfMonth + "/" + month + "/" + year);
                isdatepicker = false;

            }

        }, 2018, 1, 1);

        // give any  year , month , day values, this will be opened by default in dialog
        if (isdatepicker == false) {


            datePickerDialog.setMinDate(2018, 1, 1); //arguments are   year , month , date (use for setting custom min date)
// sets today's date as min date

            datePickerDialog.setTodayAsMaxDate();    // sets today's date as max date

            datePickerDialog.show();

            isdatepicker = true;

        } else {

            datePickerDialog.dismiss();


        }
    }


    private void alertdialogsucess() {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setMessage("all details are inserted sucessfully");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                saveNote();
                break;
            case R.id.imageView:
                chooseImage();

        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}


