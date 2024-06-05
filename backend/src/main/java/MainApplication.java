
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

//pone el prefijo /api a todos los paths
@ApplicationPath("/api")
public class MainApplication extends Application {
}
