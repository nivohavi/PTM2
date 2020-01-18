package view_model;

import ex4.Simulator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.SimulatorModel;

import java.io.IOException;

public class ViewModel {

    // models
    private SimulatorModel sm;
    /* ***data members related to view*** */
    // ** simulator **

    public StringProperty simulatorIP;
    public StringProperty simulatorPort;
    // throttle & rudder
    public DoubleProperty rudder;
    public DoubleProperty throttle;

    // joystick arguments
    public StringProperty aileron;
    public StringProperty elevator;

    // script text



    // *** end of variables
    public ViewModel(SimulatorModel sm) {
        this.sm = sm;
        simulatorIP = new SimpleStringProperty();
        simulatorPort = new SimpleStringProperty();
        rudder = new SimpleDoubleProperty();
        throttle = new SimpleDoubleProperty();
        aileron = new SimpleStringProperty();
        elevator = new SimpleStringProperty();

        throttle.addListener((o,old,nw)->setThrottle());
        rudder.addListener((o,old,nw)->setRudder());
        //aileron.addListener((o,old,nw)->setJoystickChanges());
        //elevator.addListener((o,old,nw)->setJoystickChanges());

        // Connect to FlightGear
        //openServer();
        connectToSimulator();
        System.out.println("Connected to flight-gear server");
    }

    private void openServer() {
        try {
            sm.openServer("5400", "10");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToSimulator() {
        sm.connectToSimulator("127.0.0.1", 5402);
    }

    public void setThrottle() {
        sm.setThrottle(throttle.get());
    }

    public void setRudder() {
        sm.setRudder(rudder.get());
    }

    public void setJoystickChanges() {
        sm.setAileron(Double.parseDouble(aileron.get()));
        sm.setElevator(Double.parseDouble(elevator.get()));
    }
}
