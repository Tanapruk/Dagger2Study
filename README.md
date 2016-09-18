# Dagger2Study


####NO Dependency Injection
```Java
public class Employee {
 Address address;
    public Employee() {
        this.address = new Address();
    }

   
}
Employee  employee = new Employee();
```
####Dependency Injection
```Java
public class Employee {
    Address address;
public Employee(Address address) {
        this.address = address;
    }
}

Address address = new Address(....);
Employee employee = new Employee(address);
```


###Dagger Real Android World on SharedPreferences

####No Dagger

```Java 
public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences =    PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString("Key", "save in xml").apply();
    }
}
```

####With Dagger

```Java
public class MainActivity extends AppCompatActivity {
    @Inject
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MainApplication) getApplication()).getUtilComponent().inject(this);
        sharedPreferences.edit().putString("Key", "save in xml").apply();
    }
}
```

####Dagger is better with

* @Inject
* No Initialization ~~PreferenceManager.getDefaultSharedPreferences(this);~~
* Magic!


###Let's make the magic

####AppModule.java with @Module and @Provides

* Since PreferenceManager.getDefaultSharedPreferences() required `context`
* We need to provide a context (application)

```Java
@Module
public class AppModule {
    Application mApplication;
    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }
    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
```

####UtilModule.java with @Module and @Provides
*  Dagger will magically look through every @Provides inside every @Module and get the dependency they want
*  so this `application` will get the @Provides from the `AppModule`
```Java
@Module
public class UtilModule {
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
```

####Link both modules togeter with @Component
* With this annotation both modules can talk to each other

```Java
@Singleton
@Component(modules = {AppModule.class, UtilModule.class})
public interface UtilComponent {
    void inject(MainActivity activity);
}
```


####Initialize the Component inside Custom Application class
* Satisfy dependencies in this module

```Java
public class MainApplication extends Application {
    private UtilComponent mUtilComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Instantiating the component
        //DaggerUtilComponent will show up after build
        mUtilComponent = DaggerUtilComponent.builder()
                .appModule(new AppModule(this))
                .utilModule(new UtilModule())
                .build();

    }

    public UtilComponent getUtilComponent() {
        return mUtilComponent;
    }
}
```

####Injection inside MainActivity
```Java
((MainApplication) getApplication()).getUtilComponent().inject(this);
```

####Done.
