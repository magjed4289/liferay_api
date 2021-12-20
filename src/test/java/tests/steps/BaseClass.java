package tests.steps;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import lombok.extern.java.Log;

import java.sql.SQLException;

@Log
public class BaseClass implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, setup);
        eventPublisher.registerHandlerFor(TestRunFinished.class, teardown);
    }

    private EventHandler<TestRunStarted> setup = event -> {
        try {
            beforeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    private void beforeAll() throws SQLException {
       log.info("Logging in to API");
       //log in to API in the portal
       log.info("Logged");
    }

    private EventHandler<TestRunFinished> teardown = event -> {
        try {
            afterAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    private void afterAll() throws SQLException {
        log.info("ALL DONE");
    }
}