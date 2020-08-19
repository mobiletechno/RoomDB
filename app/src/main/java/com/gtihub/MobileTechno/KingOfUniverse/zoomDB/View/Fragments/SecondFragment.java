package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.View.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Adapters.NoteAdapter;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Pojo.Note;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.R;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.ViewModel.PageViewModel;

import java.util.List;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */

public class SecondFragment extends Fragment {
    private PageViewModel pageViewModel;
    private TextView txtName;


    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment SecondFragment.
     */
    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialise ViewModel here
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondfragment, container, false);
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        pageViewModel.getAllNotes().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.submitList(notes);

                Log.e("mylist", String.valueOf(notes));
            }
        });


    }
}
