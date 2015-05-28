package com.petrodevelopment.copdapp.record;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.petrodevelopment.copdapp.R;
import com.petrodevelopment.copdapp.activities.BaseActivity;
import com.petrodevelopment.copdapp.record.AudioRecorder;
import com.petrodevelopment.copdapp.record.fragments.NoteRecordFragment;
import com.petrodevelopment.copdapp.record.fragments.PhotoRecordFragment;
import com.petrodevelopment.copdapp.record.fragments.ReminderRecordFragment;
import com.petrodevelopment.copdapp.record.fragments.VoicePlayFragment;
import com.petrodevelopment.copdapp.record.fragments.VoiceRecordFragment;
import com.petrodevelopment.copdapp.util.U;

import java.io.File;

/**
 *
 * Record a note, an audio and images
 * Created by andrey on 10/05/2015.
 */
public class RecordActivity extends BaseActivity {
//    AudioRecorder audioRecorder;
//    boolean recording = false;
//    boolean recorded = false;
//    boolean playing = false;
    VoiceRecordFragment voiceRecordFragment;
    VoicePlayFragment voicePlayFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initToolbar();
        initVoice();
        initVoiceRecordFragment();
        initVoicePlayFragment();
        selectAction(0);
    }


    private void initVoice(){
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp";
        File file = new File(fileName);
        if (file.exists()) {
            initVoicePlayFragment();
        } else {
            initVoiceRecordFragment();
        }
    }



    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initVoiceRecordFragment() {
        voiceRecordFragment =  new VoiceRecordFragment();
        voiceRecordFragment.setOnStopListener(new VoiceRecordFragment.OnStopListener() {
            @Override
            public void onStop(String fileName) {
                U.log(this, "FILE CREATED: " + fileName);
                initVoicePlayFragment();
            }
        });
        replaceFragment(R.id.voice_container, voiceRecordFragment);
    }

    private void initVoicePlayFragment() {
        voicePlayFragment =  new VoicePlayFragment();
        voicePlayFragment.setOnDeleteListener(new VoicePlayFragment.OnDeleteListener() {
            @Override
            public void onDelete(String fileName) {
                U.log(this, "FILE DELETED: " + fileName);
                initVoiceRecordFragment();
            }
        });
        replaceFragment(R.id.voice_container, voicePlayFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveAndClose();
                return true;
            case R.id.action_delete:
                deleteAndClose();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deleteAndClose() {
        U.log(this, "delete record and close activity");
        finish();
    }

    @Override
    public void onBackPressed() {
        saveAndClose();
    }

    private void saveAndClose() {
        U.log(this, "save record and close activity");
        finish();
    }


    public void onNoteClick(View v) {
        selectAction(0);
    }

    public void onVoiceClick(View v) {
        selectAction(1);
    }

    public void onPhotoClick(View v) {
        selectAction(2);
    }

    public void onReminderClick(View v) {
        selectAction(3);
    }


    public void selectAction(int position) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, createSectionFragment(position))
                    .commit();

    }

    private Fragment createSectionFragment(int position) {
        switch (position) {
            case 0:
                return NoteRecordFragment.newInstance(position, getString(R.string.action_note));
            case 1:
                return VoiceRecordFragment.newInstance(position, getString(R.string.action_voice));
            case 2:
                return PhotoRecordFragment.newInstance(position, getString(R.string.action_photo));
            default:
                return ReminderRecordFragment.newInstance(position, getString(R.string.action_reminder));
        }
    }



    //second action bar methods
//    public void onNoteClick(View v) {
//        if (audioRecorder == null) audioRecorder = new AudioRecorder("test.3gp");
//
//        if (!recording & !playing) {
//            recording = true;
//            recorded = false;
//            audioRecorder.startRecording();
//            U.log(this, "start recording");
//        } else {
//            recording = false;
//            recorded = true;
//            audioRecorder.stopRecording();
//            U.log(this, "stop recording");
//        }
//    }
//
//    public void onVoiceClick(View v) {
//        if (recorded) {
//            playing = true;
//            audioRecorder.startPlaying();
//        } else {
//            playing = false;
//            audioRecorder.stopPlaying();
//        }
//    }



    //TODO move to voice fragment
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (audioRecorder != null) audioRecorder.releaseRecorderAndPlayer();
//    }


}
