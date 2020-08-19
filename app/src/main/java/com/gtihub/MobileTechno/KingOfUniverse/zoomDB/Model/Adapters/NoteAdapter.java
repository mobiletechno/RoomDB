package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Pojo.Note;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.R;

import java.io.ByteArrayOutputStream;

import static com.gtihub.MobileTechno.KingOfUniverse.zoomDB.View.Fragments.FirstFragment.imageString;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;
    private Bitmap daptorbtmap;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }


    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getStudentname().equals(newItem.getStudentname()) &&
                    oldItem.getFathername().equals(newItem.getFathername()) &&
                    oldItem.getMothername().equals(newItem.getMothername()) &&
                    oldItem.getDate().equals(newItem.getDate());
            //                 oldItem.getDate() == newItem.getDate();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.studentname.setText("Name : " + currentNote.getStudentname());
        holder.datepicker.setText("Date : " + currentNote.getDate());
        holder.fathername.setText("Father : " + String.valueOf(currentNote.getFathername()));
        holder.mothername.setText("Mother :" + String.valueOf(currentNote.getMothername()));


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        String tempstr = currentNote.getImgurl();
        //decode base64 string to image
        imageBytes = Base64.decode(tempstr, Base64.DEFAULT);
        Bitmap decodedImageokey = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.imgid.setImageBitmap(decodedImageokey);

    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView studentname;
        private TextView datepicker;
        private TextView fathername;
        private TextView mothername;
        private ImageView imgid;

        public NoteHolder(View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.student_name);
            datepicker = itemView.findViewById(R.id.date_value);
            fathername = itemView.findViewById(R.id.father_name);
            mothername = itemView.findViewById(R.id.mother_name);
            imgid = itemView.findViewById(R.id.student_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}