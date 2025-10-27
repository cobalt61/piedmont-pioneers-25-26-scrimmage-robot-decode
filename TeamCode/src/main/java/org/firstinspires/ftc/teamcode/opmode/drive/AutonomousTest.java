package org.firstinspires.ftc.teamcode.opmode.drive;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.robot.AutonomousBlue;
import org.firstinspires.ftc.teamcode.hardware.robot.Config;
import org.firstinspires.ftc.teamcode.hardware.robot.ManualRobot;
import org.firstinspires.ftc.teamcode.hardware.robot.enums.Alliance;
import org.firstinspires.ftc.teamcode.hardware.robot.enums.GameStage;

    @TeleOp(name = "Autonomous Test", group = "Autonomous")
    public class AutonomousTest extends LinearOpMode {
        AutonomousBlue robot;
        Config config;
        FtcDashboard dashboard;

        @Override
        public void runOpMode() throws InterruptedException {
            config = new Config(telemetry, hardwareMap, gamepad1, gamepad2, GameStage.TeleOp, Alliance.BLUE);
            robot = new AutonomousBlue(config);
            waitForStart();

        }


    }