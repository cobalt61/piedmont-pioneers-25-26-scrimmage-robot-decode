
package org.firstinspires.ftc.teamcode.hardware.subsystem;


import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    private DcMotor outtake,buffer;
    private Servo servo;
    private DigitalChannel switchV;
    private ElapsedTime timer = new ElapsedTime();
    OuttakeState state;


    public Outtake(Config config) {
        this.config = config;
    }

    @Override
    public void init() {
        outtake = config.hardwareMap.get(DcMotor.class, Globals.Outtake.OUTTAKE_MOTOR);
        buffer = config.hardwareMap.get(DcMotor.class, Globals.Outtake.BUFFER);
        //servo = config.hardwareMap.get(Servo.class, Globals.Outtake.OUTTAKE_SERVO);

        // Set motor directions
        outtake.setDirection(DcMotor.Direction.REVERSE);
        buffer.setDirection(DcMotor.Direction.FORWARD);
        buffer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // How to use motors with encoders
        /* outtake2.setTargetPosition(200); // 200 here is the amount of encoder ticks
        outtake2.setPower(1);*/
       // servo.setDirection(Servo.Direction.REVERSE);

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
        } else if (config.gamepad2.right_bumper) {
            newActions.add(runBuffer());
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
            // servo.setPosition(Globals.Outtake.OUTTAKE_SERVO_OPEN);
        });
    }

    public InstantAction stopOuttake() {
        return new InstantAction(() -> {
            outtake.setPower(Globals.Outtake.POWER_OFF);
            state = OuttakeState.STOPPED;
           // servo.setPosition(Globals.Outtake.OUTTAKE_SERVO_CLOSED);
        });
    }
    public InstantAction runBuffer(){
        timer.reset();
        return new InstantAction(() -> {
           buffer.setTargetPosition(
                   (int) Math.floor(Globals.Outtake.TICKS_PER_REVOLUTION_5202 / 3 + buffer.getCurrentPosition())
           );
           buffer.setPower(1);
          /* while (timer.time() < 500){

           }
           buffer.setPower(0);*/
        });
    }




    private void resetMotors() {
        outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}