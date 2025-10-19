
package org.firstinspires.ftc.teamcode.hardware.subsystem;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.hardware.robot.Config;
import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.robot.enums.GameStage;

import java.util.ArrayList;
import java.util.List;

public class Outtake implements SubSystem {

    // Lift positions
    public enum OuttakeState {
       STOPPED,
        SPINNING
    }


    // Constants for joystick thresholds

    private final Config config;
    private DcMotor outtake;
    private DigitalChannel switchV;

    OuttakeState state;


    public Outtake(Config config) {
        this.config = config;
    }

    @Override
    public void init() {
        outtake = config.hardwareMap.get(DcMotor.class, Globals.Outtake.OUTTAKE_MOTOR);

        // Set motor directions
        outtake.setDirection(DcMotor.Direction.REVERSE);

        // Reset encoders and set motor modes
        resetMotors();
    }

    @Override
    public void start() {}

    @Override
    public List<Action> update() {
        List<Action> newActions = new ArrayList<>();

        if (config.gamepad2.right_trigger >= 0.1) {
           newActions.add(startOuttake(config.gamepad2.right_trigger));
        } else{
            newActions.add(stopOuttake());
        }



        // Add telemetry data
        addTelemetryData();

        return newActions;
    }

    private void addTelemetryData() {
        config.telemetry.addData("Outtake Power", outtake.getPower());
    }


    public InstantAction startOuttake(double speed) {
        return new InstantAction(() -> {
             outtake.setPower(speed);
             state = OuttakeState.SPINNING;
        });
    }

    public InstantAction stopOuttake() {
        return new InstantAction(() -> {
            outtake.setPower(Globals.Outtake.POWER_OFF);
            state = OuttakeState.SPINNING;
        });
    }




    private void resetMotors() {
        outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}