package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Custom.Dialogbox;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by A.Rajkumar on 18/08/2020.
 */
public class EightFoldsDatePickerDialog extends DatePickerDialog {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    @RequiresApi(api = Build.VERSION_CODES.N)
    public EightFoldsDatePickerDialog(@NonNull Context context) {
        super(context);
        datePickerDialog = new DatePickerDialog(context);
        datePickerDialog.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public EightFoldsDatePickerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        datePickerDialog = new DatePickerDialog(context, themeResId);
        datePickerDialog.dismiss();
    }

    public EightFoldsDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
        datePickerDialog = new DatePickerDialog(context, listener, year, month, dayOfMonth);
        datePickerDialog.dismiss();


    }

    public EightFoldsDatePickerDialog(@NonNull Context context, int themeResId, @Nullable OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
        datePickerDialog = new DatePickerDialog(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }

    public void show() {
        datePickerDialog.show();
    }

    public void dismiss() {
        datePickerDialog.dismiss();
    }

    public void setMinDate(int year, int month, int day) {

        String minDate = "" + day + "-" + getmonth(month) + "-" + year;

        try {
            datePickerDialog.getDatePicker().setMinDate(dateFormat.parse(minDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setMaxDate(int year, int month, int day) {

        String maxDate = "" + day + "-" + getmonth(month) + "-" + year;
        try {
            datePickerDialog.getDatePicker().setMaxDate(dateFormat.parse(maxDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public int setTodayAsMaxDate() {

        datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTime().getTime());

        return 0;
    }

    public void setTodayAsMinDate() {

        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTime().getTime());

    }

    public int setTodayAsMaxmonth() {

        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTime().getMonth());
        return 0;
    }

    public int setMaxyear() {

        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTime().getYear());
        return 0;
    }


    private String getmonth(int month) {

        switch (month) {
            case 1:
                return "January";

            case 2:
                return "February";

            case 3:
                return "March";

            case 4:
                return "April";

            case 5:
                return "May";

            case 6:
                return "June";

            case 7:
                return "July";

            case 8:
                return "August";

            case 9:
                return "September";

            case 10:
                return "October";

            case 11:
                return "November";

            case 12:
                return "December";


        }
        return "January";

    }

}