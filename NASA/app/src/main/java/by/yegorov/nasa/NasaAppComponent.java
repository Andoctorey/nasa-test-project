package by.yegorov.nasa;


import by.yegorov.nasa.core.dagger.PerApp;
import dagger.Component;

@PerApp
@Component(
        modules = {
                NasaModule.class,
                AndroidModule.class
        }
)
public interface NasaAppComponent {

}
