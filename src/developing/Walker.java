package developing;
import java.io.File;

public interface Walker<conf>

{
    conf buildConfig(File packetFolder);
    conf readConfig();
}
