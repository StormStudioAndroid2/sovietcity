package com.sovietcity.View;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sovietcity.Model.AuthorisationWorker;
import com.sovietcity.Model.ISaveLoader;
import com.sovietcity.R;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class MainMenuActivity extends AppCompatActivity implements ISaveLoader {


    private String describe;
    // Unique tag for the error dialog fragment
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;
    private static final String NAME_KEY = "name";
    private static final String MAIL_KEY = "mail";
    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */

    private ExitFragment exitFragment;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";
    private AlertDialog.Builder alertDialog;
    private AuthorisationWorker authorisationWorker;
    private View mControlsView;
    private static final int RC_SAVED_GAMES = 9009;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
        super.onCreate(savedInstanceState);
        Utils.init(getApplicationContext());
        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
        setContentView(R.layout.activity_main_menu);
        this.exitFragment = new ExitFragment();
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        Intent intent = getIntent();
        this.describe = intent.getStringExtra("SAVE_DESCRIBE");
        Button newGameButton = (Button) findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        this.authorisationWorker = new AuthorisationWorker();
//        authorisationWorker.getNameUser(this);
        Button loadButton = (Button) findViewById(R.id.load_game_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFragmentTransaction();
            }
        });
        Button exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitFragment.show(getSupportFragmentManager(), "EXIT_FRAGMENT");
            }
        });
//        Button buttonRecords = (Button) findViewById(R.id.records_button);
//        buttonRecords.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recordFragmentTransaction();
//            }
//        });
//        if (!authorisationWorker.isAuthorisation()) {
//            authorisationFragmentTransaction();
//        }

    }

    public synchronized void saveFragmentTransaction() {
        deleteFragment("SAVE_FRAGMENT");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SaveFragment saveFragment = new SaveFragment();
        saveFragment.setiSaveLoader(this);
        saveFragment.setSaver(Utils.getSaver());
        saveFragment.setCanSave(false);
        fragmentTransaction.add(R.id.content_frame_main, saveFragment, "SAVE_FRAGMENT");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        exitFragment.show(getSupportFragmentManager(), "EXIT_FRAGMENT");
    }

    public synchronized void deleteFragment(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(tag)).commit();
        } else {
        }
    }

    public synchronized void authorisationFragmentTransaction() {
        deleteFragment("SAVE_FRAGMENT");
        deleteFragment("AUTHORISATION_FRAGMENT");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AuthorisationFragment authorisationFragment = new AuthorisationFragment();
        authorisationFragment.setMainMenuActivity(this);
        authorisationFragment.setAuthorisationWorker(authorisationWorker);
        fragmentTransaction.add(R.id.content_frame_main, authorisationFragment, "AUTHORISATION_FRAGMENT");
        fragmentTransaction.commit();
    }
    public synchronized void recordFragmentTransaction() {
        deleteFragment("SAVE_FRAGMENT");
        deleteFragment("AUTHORISATION_FRAGMENT");
        deleteFragment("RECORD_FRAGMENT");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecordsFragment recordsFragment = new RecordsFragment();
        recordsFragment.setMainMenuActivity(this);
        recordsFragment.setAuthorisationWorker(authorisationWorker);
        fragmentTransaction.add(R.id.content_frame_main, recordsFragment, "RECORD_FRAGMENT");
        fragmentTransaction.commit();
    }
    public void deleteAllFragments() {
        deleteFragment("SAVE_FRAGMENT");
        deleteFragment("AUTHORISATION_FRAGMENT");
        deleteFragment("RECORD_FRAGMENT");

    }

    @Override
    public void loadSave(byte[] array) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("SAVE", array);
        intent.putExtra(NAME_KEY, authorisationWorker.getName());
        intent.putExtra(MAIL_KEY, authorisationWorker.getMail());
        finish();
        startActivity(intent);
    }

    @Override
    public byte[] writeSave() {
        return new byte[0];
    }

    @Override
    public void closeSaveMenu() {
        deleteFragment("SAVE_FRAGMENT");
    }


}