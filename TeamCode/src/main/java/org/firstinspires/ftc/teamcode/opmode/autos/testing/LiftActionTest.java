package org.firstinspires.ftc.teamcode.opmode.autos.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.robot.enums.Alliance;
import org.firstinspires.ftc.teamcode.hardware.robot.AutonomousRobot;
import org.firstinspires.ftc.teamcode.hardware.robot.Config;
import org.firstinspires.ftc.teamcode.hardware.robot.enums.CycleTarget;
import org.firstinspires.ftc.teamcode.hardware.robot.enums.GameStage;

@Disabled
@Autonomous(name="Lift Action Test", group="Test")
public class LiftActionTest extends LinearOpMode {
    Config config;

    AutonomousRobot robot;

    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {
        dashboard = FtcDashboard.getInstance();

        config = new Config(telemetry, hardwareMap, gamepad1, gamepad2, GameStage.Autonomous, Alliance.RED);

        robot = new AutonomousRobot(config);

        robot.init();

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        robot.outtake.bar(),
                        robot.sleep(2),
                        robot.outtake.clip(),
                        robot.sleep(2),
                        robot.outtake.down()
                )
        );
    }
}
