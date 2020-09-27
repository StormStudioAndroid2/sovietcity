package com.sovietcity.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sovietcity.Model.AuthorisationWorker;
import com.sovietcity.Model.BuildModel;
import com.sovietcity.Model.ConstructionFunctionComponent;
import com.sovietcity.Model.FactoryFunctionComponent;
import com.sovietcity.Model.ISaveLoader;
import com.sovietcity.Model.Save;
import com.sovietcity.Model.SaveCreator;
import com.sovietcity.Model.ShopFunctionComponent;
import com.sovietcity.Model.State;
import com.sovietcity.Model.StockFunctionComponent;
import com.sovietcity.Presenter.World;
import com.sovietcity.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ISaveLoader {
    private static final int MENU_POP_INFO = Menu.FIRST;
    private static final String NAME_KEY = "name";
    private static final String MAIL_KEY = "mail";
    private World world;
    private Toolbar toolbar;
    private static final int RC_SAVED_GAMES = 9009;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private static final int REQUEST_RESOLVE_ERROR = 1002;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    private static final int ID_POP_TEXT = 0;
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";
    private String mCurrentSaveName = "snapshotTemp";
    private static final int MAX_SNAPSHOT_RESOLVE_RETRIES = 3;
    private TextView textPopulation;
    private TextView textMoney;
    private TextView textDate;
    private ImageView playFaster;
    private byte[] data;
    private Save save;

    @Override
    protected synchronized void onCreate(final Bundle savedInstanceState) {
        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        byte[] array = intent.getByteArrayExtra("SAVE");
        if (array != null) {
            try {
                this.save = Save.deserialize(array);
                this.world = new World(this, save);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.world = new World(this);
        }
        world.getThreadManager().setMainActivity(this);
        findToolbar(); //    находит тулбар и задает название
        createNavigationView();//    инициализирует navigationView
        AuthorisationWorker authorisationWorker = new AuthorisationWorker();
        authorisationWorker.getNameUser(this);
        world.setAuthorisationWorker(authorisationWorker);
        if (array != null) {
            surfaceTransaction(); //    подключает SurfaceView
        } else {
            firstSurfaceTransaction();
        }
        initializeMenu();

        if (array == null) {
            startFragmentTransaction();
        }
        save = null;


    }

    public void initializeMenu() {
        this.textPopulation = (TextView) findViewById(R.id.text_population);
        this.textDate = (TextView) findViewById(R.id.text_date);
        this.textMoney = (TextView) findViewById(R.id.money_text);
        ImageView map = (ImageView) findViewById(R.id.change_map);

        ImageView pause = (ImageView) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                world.getThreadManager().pause();
            }
        });
        ImageView play = (ImageView) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                world.getThreadManager().setSlow();
            }
        });
        playFaster = (ImageView) findViewById(R.id.faster_play);
        playFaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                world.getThreadManager().setFaster();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (world.getDrawerManager().isPhysicMap()) {
                    world.getDrawerManager().setPhysicMap(false);
                    textMoney.setVisibility(View.GONE);
                    textPopulation.setVisibility(View.GONE);
                    textDate.setVisibility(View.GONE);
                } else {
                    world.getDrawerManager().setPhysicMap(true);
                    textMoney.setVisibility(View.VISIBLE);
                    textPopulation.setVisibility(View.VISIBLE);
                    textDate.setVisibility(View.VISIBLE);

                }
                world.getDrawerManager().needUpdate();
            }
        });




        map.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Смена режима карты", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        textPopulation.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Текущее население", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        textDate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Текущая дата", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        textMoney.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Бюджет города", Toast.LENGTH_LONG).show();
                return true;
            }
        });


    }

    public void updateUI() {
        Utils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textPopulation.setText(Integer.toString(world.getPopulationManager().getPopulation().getNowPopulation()));
                textMoney.setText(Long.toString(Math.round(world.getCashManager().getCash())));
                textDate.setText(world.getTimeManager().toString());
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    //    Далее описываются все транзакции, которые меняют меню на экране. В дальнейшем для этого будет создан менеджер транзаций. А пока дедлайн(((
    public synchronized void changeTaskFragment() {
        if (world.getTaskManager().isComplete()) {
            completeTaskTransaction();
        } else {
            taskTransaction();
        }
    }

    public synchronized void saveFragmentTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        Date date = new Date(world.getTimeManager().getDate().getTimeInMillis());
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy_MM_dd");
        String str = format1.format(date);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SaveFragment saveFragment = new SaveFragment();
        saveFragment.setiSaveLoader(this);
        saveFragment.setName(str);

        saveFragment.setSaver(Utils.getSaver());
        saveFragment.setCanSave(true);
        fragmentTransaction.add(R.id.content_frame, saveFragment, "SAVE_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void buildDescribeTransaction(BuildModel buildModel) {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DescribeBuildingFragment describeBuildingFragment = new DescribeBuildingFragment();
        describeBuildingFragment.setMainActivity(this);
        describeBuildingFragment.setBuildModel(buildModel);
        describeBuildingFragment.setDrawerManager(world.getDrawerManager());
        fragmentTransaction.add(R.id.content_frame, describeBuildingFragment, "DESCRIBE_BUILDING_FRAGMENT");
        fragmentTransaction.commit();

    }

    public synchronized void taskTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setTask(world.getTaskManager().getNowTask());
        taskFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, taskFragment, "TASK_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void completeTaskTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CompleteTaskFragment taskFragment = new CompleteTaskFragment();
        taskFragment.setTaskManager(world.getTaskManager());
        taskFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, taskFragment, "COMPLETE_TASK_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void specialtyTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SpecialtyFragment specialtyFragment = new SpecialtyFragment();
        specialtyFragment.setSpecialties(world.getSpecialtyManager().getSpecialties());
        specialtyFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, specialtyFragment, "SPECIALTY_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void surfaceTransaction() {
        deleteAllFragments();
        world.getDrawerManager().needUpdate();
        world.getThreadManager().closeMenu();
        navigationView.getMenu().getItem(0).setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SurfaceFragment surfaceFragment = new SurfaceFragment();
        surfaceFragment.setWorld(this.world);
        surfaceFragment.setMainActivity(this);
        fragmentTransaction.replace(R.id.content_frame, surfaceFragment, "SURFACE_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void firstSurfaceTransaction() {
        deleteAllFragments();
        world.getDrawerManager().needUpdate();
        navigationView.getMenu().getItem(0).setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SurfaceFragment surfaceFragment = new SurfaceFragment();
        surfaceFragment.setWorld(this.world);
        surfaceFragment.setMainActivity(this);
        fragmentTransaction.replace(R.id.content_frame, surfaceFragment, "SURFACE_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void deleteAllFragments() {
        deleteFragment("POPULATION_FRAGMENT");
        deleteFragment("BUILD_FRAGMENT");
        deleteFragment("CONSTRUCT_FRAGMENT");
        deleteFragment("ROAD_FRAGMENT");
        deleteFragment("DESCRIBE_FAB_FRAGMENT");
        deleteFragment("VIEW_STOCK_FRAGMENT");
        deleteFragment("CHOOSE_STOCK_FRAGMENT");
        deleteFragment("TASK_FRAGMENT");
        deleteFragment("COMPLETE_TASK_FRAGMENT");
        deleteFragment("SPECIALTY_FRAGMENT");
        deleteFragment("CONSTRUCT_WINDOW_FRAGMENT");
        deleteFragment("VIEW_SHOP_FRAGMENT");
        deleteFragment("POPULATION_DATA_FRAGMENT");
        deleteFragment("GAME_MENU_FRAGMENT");
        deleteFragment("START_FRAGMENT");
        deleteFragment("LESSONS_FRAGMENT");
        deleteFragment("LEARNING_FRAGMENT");
        deleteFragment("LESSON_FRAGMENT");
        deleteFragment("EVENT_FRAGMENT");
        deleteFragment("DELIVERY_FRAGMENT");
        deleteFragment("DELIVERY_LIST_FRAGMENT");
        deleteFragment("SAVE_FRAGMENT");
        deleteFragment("LESSON_MISSION_FRAGMENT");
    }

    public synchronized void factoryDescribeTransaction(BuildModel buildModel, FactoryFunctionComponent factoryFunctionComponent) {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FactoryDescriberFragment factoryDescriberFragment = new FactoryDescriberFragment();
        factoryDescriberFragment.setFactoryFunctionComponent(factoryFunctionComponent);
        factoryDescriberFragment.setBuildModel(buildModel);
        factoryDescriberFragment.setMainActivity(this);
        factoryDescriberFragment.setTerrainModelFabric(world.getMapManager().getTerrainModelFabric());
        factoryDescriberFragment.setResourceManager(world.getResourceManager());
        fragmentTransaction.add(R.id.content_frame, factoryDescriberFragment, "DESCRIBE_FAB_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void populationTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PeopleViewerFragment peopleViewerFragment = new PeopleViewerFragment();
        peopleViewerFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, peopleViewerFragment, "POPULATION_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void deliveryFragmentTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DeliveryFragment deliveryFragment = new DeliveryFragment();
        deliveryFragment.setCashManager(world.getCashManager());
        deliveryFragment.setMainActivity(this);
        deliveryFragment.setDeliveryManager(world.getDeliveryManager());
        fragmentTransaction.add(R.id.content_frame, deliveryFragment, "DELIVERY_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void startFragmentTransaction() {
        deleteAllFragments();
        world.getThreadManager().setState(State.RUN_SURFACE_ONCE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StartFragment startFragment = new StartFragment();
        startFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, startFragment, "START_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void learningFragmentTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LearningFragment learningFragment = new LearningFragment();
        learningFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, learningFragment, "LEARNING_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void listDeliveryTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DeliveryListFragment deliveryListFragment = new DeliveryListFragment();
        deliveryListFragment.setDeliveries(world.getDeliveryManager().getDeliveries());
        deliveryListFragment.setImage(world.getDeliveryManager().getTruckIcon());

        fragmentTransaction.add(R.id.content_frame, deliveryListFragment, "DELIVERY_LIST_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void lessonFragmentTransaction(int id) {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LessonFragment lessonFragment = new LessonFragment();
        lessonFragment.setMainActivity(this);
        lessonFragment.setId(id);
        fragmentTransaction.add(R.id.content_frame, lessonFragment, "LESSON_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void populationDataTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PopulationDataFragment populationDataFragment = new PopulationDataFragment();
        populationDataFragment.setMainActivity(this);
        populationDataFragment.setPopulationDataComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent());
        fragmentTransaction.add(R.id.content_frame, populationDataFragment, "POPULATION_DATA_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void loseTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoseFragment loseFragment = new LoseFragment();
        loseFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, loseFragment, "LOSE_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void winTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WinFragment winFragment = new WinFragment();
        winFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, winFragment, "WIN_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void deleteFragment(String tag) {
        if (getFragmentManager().findFragmentByTag(tag) != null) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag(tag)).commit();
        } else {
        }
    }

    public synchronized void stockResourceChooserFragmentTransaction(StockFunctionComponent stockFunctionComponent) {
        deleteAllFragments();

        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StockResourceShooserFragment stockResourceShooserFragment = new StockResourceShooserFragment();
        stockResourceShooserFragment.setResources(world.getResourceManager().getResources());
        stockResourceShooserFragment.setMainActivity(this);
        stockResourceShooserFragment.setStockFunctionComponent(stockFunctionComponent);
        fragmentTransaction.add(R.id.content_frame, stockResourceShooserFragment, "CHOOSE_STOCK_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void eventFragmentTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EventFragment eventFragment = new EventFragment();
        eventFragment.setMainActivity(this);
        eventFragment.setEventGame(world.getEventManager().getNowEventGame());
        world.getEventManager().setNowEventGame(null);
        fragmentTransaction.add(R.id.content_frame, eventFragment, "EVENT_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void stockViewerFragmentTransaction(StockFunctionComponent stockFunctionComponent) {
        deleteAllFragments();

        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StockViewerFragment stockViewerFragment = new StockViewerFragment();
        stockViewerFragment.setMainActivity(this);
        stockViewerFragment.setResourceManager(world.getResourceManager());
        stockViewerFragment.setTerrainModelFabric(world.getMapManager().getTerrainModelFabric());
        stockViewerFragment.setStockFunctionComponent(stockFunctionComponent);
        fragmentTransaction.add(R.id.content_frame, stockViewerFragment, "VIEW_STOCK_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void shopFragmentTransaction(ShopFunctionComponent shopFunctionComponent) {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setShopFunctionComponent(shopFunctionComponent);
        shopFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, shopFragment, "VIEW_SHOP_FRAGMENT");
        fragmentTransaction.commit();
    }

    public synchronized void lessonMissionFragmentTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LessonMissionFragment lessonMissionFragment = new LessonMissionFragment();
        lessonMissionFragment.setMainActivity(this);
        lessonMissionFragment.setLessonMissionLoader(world.getLessonMissionLoader());
        fragmentTransaction.add(R.id.content_frame, lessonMissionFragment, "LESSON_MISSION_FRAGMENT");
        fragmentTransaction.commit();
    }

    public void stockFragmentTransaction(StockFunctionComponent stockFunctionComponent) {
        if (stockFunctionComponent.materialIsNull()) {
            stockResourceChooserFragmentTransaction(stockFunctionComponent);
        } else {
            stockViewerFragmentTransaction(stockFunctionComponent);
        }
    }

    public void constructWindowFragmentTransaction(ConstructionFunctionComponent constructionFunctionComponent, BuildModel buildModel) {

        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ConstructWindowFragment constructWindowFragment = new ConstructWindowFragment();
        constructWindowFragment.setBuildModel(buildModel);
        constructWindowFragment.setMainActivity(this);
        constructWindowFragment.setTerrainModelFabric(world.getMapManager().getTerrainModelFabric());
        constructWindowFragment.setBuildManager(world.getBuildManager());

        constructWindowFragment.setConstructionFunctionComponent(constructionFunctionComponent);
        fragmentTransaction.add(R.id.content_frame, constructWindowFragment, "CONSTRUCT_WINDOW_FRAGMENT");
        fragmentTransaction.commit();
    }

    public void buildTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SurfaceFragment surfaceFragment = (SurfaceFragment) fragmentManager.findFragmentByTag("SURFACE_FRAGMENT");
        surfaceFragment.getSurfaceView().getSovietThread().setDone(true);
        BuildListFragment buildListFragment = new BuildListFragment();
        buildListFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, buildListFragment, "BUILD_FRAGMENT");
        fragmentTransaction.commit();
    }

    public World getWorld() {
        return this.world;
    }

    public void createNavigationView() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        this.navigationView = navigationView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_map) {
                    if ((!world.isLose()) && (!world.isWin())) surfaceTransaction();
                } else if (id == R.id.nav_build) {
                    if ((!world.isLose()) && (!world.isWin())) buildTransaction();

                } else if (id == R.id.nav_population) {
                    if ((!world.isLose()) && (!world.isWin())) populationTransaction();
                } else if (id == R.id.nav_education) {
                    if ((!world.isLose()) && (!world.isWin())) {
                        learningFragmentTransaction();
                    }
                } else if (id == R.id.nav_auto) {
                    deliveryFragmentTransaction();
                } else if (id == R.id.nav_tasks) {
                    changeTaskFragment();
                } else if (id == R.id.nav_lessons) {
                    lessonMissionFragmentTransaction();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void findToolbar() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);

        this.toolbar = mActionBarToolbar;
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(this.toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        this.world.setEventManager(new EventManager(world, getApplicationContext()));
//        this.world.setEventManager(new EventManager(world, getApplicationContext()));
//        this.world.setEventManager(new EventManager(world, getApplicationContext()));
        surfaceTransaction();

        world.getThreadManager().setSlow();
    }

    @Override
    public void onBackPressed() {
        if ((!world.isLose()) && (!world.isWin())) {
            if (world.getThreadManager().isWorking()) {
                gameMenuTransaction();
            } else {
                surfaceTransaction();
            }
        }
    }

    public void gameMenuTransaction() {
        deleteAllFragments();
        world.getThreadManager().openMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GameMenuFragment gameMenuFragment = new GameMenuFragment();
        gameMenuFragment.setMainActivity(this);
        fragmentTransaction.add(R.id.content_frame, gameMenuFragment, "GAME_MENU_FRAGMENT");
        fragmentTransaction.commit();
    }

    public void mainMenuIntent() {
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
        this.finish();
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        return true;
    }

    private void showPopulationInformation() {
        Toast.makeText(this, "Текущее население: " + world.getPopulationManager().getPopulation().getNowPopulation() + " Макс.население: " + world.getPopulationManager().getPopulation().getMaxPopulation(), Toast.LENGTH_LONG).show();
    }

    private void showDataInformation() {
        Toast.makeText(this, world.getTimeManager().toString(), Toast.LENGTH_LONG).show();

    }

    private void showMoneyInformation() {
        Toast.makeText(this, "Текущий бюджет города: " + Double.toString(world.getCashManager().getCash()) + " рублей", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        world.getThreadManager().pause();
    }


    // The rest of this code is all about building the error dialog

    /* Creates a dialog for an error message */


    @Override
    public void loadSave(byte[] array) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("SAVE", array);
        finish();
        startActivity(intent);
    }

    @Override
    public byte[] writeSave() {

        try {
            return new SaveCreator().createSave(world);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public void closeSaveMenu() {
        deleteAllFragments();
        surfaceTransaction();
    }


    AsyncTask<Void, Void, Void> myAsynkTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            world = new World(getApplicationContext(), save);
            return null;
        }
    };
}