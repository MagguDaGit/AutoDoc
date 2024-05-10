import org.junit.Assert;
import org.junit.Test;
import org.rubrum.service.DirectoryHandler;

import java.io.File;

public class DirectoryHandlerTest {


    @Test
    public void canFindSource() {
        DirectoryHandler handler = new DirectoryHandler();
        File src = handler.findJavaSourceFolder();
        Assert.assertNotNull(src);
    }
}
