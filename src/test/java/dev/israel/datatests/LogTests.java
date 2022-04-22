package dev.israel.datatests;

import dev.israel.utilities.LogLevel;
import dev.israel.utilities.Logger;
import org.junit.jupiter.api.Test;

public class LogTests {

    @Test
    void info_log_test(){
        Logger.logInfo("Hello.", LogLevel.INFO);
        Logger.logInfo("What's up!!!!!", LogLevel.DEBUG);
    }
}

