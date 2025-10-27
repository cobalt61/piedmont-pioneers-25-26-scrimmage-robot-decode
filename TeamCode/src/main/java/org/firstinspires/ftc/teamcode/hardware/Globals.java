package org.firstinspires.ftc.teamcode.hardware;

public class Globals {
    /*
        Wrapper Class for all constants that we use.
        Each Subsystem gets its own subclass to hold constants.
        All fields should be public, static, and final.
     */



    public static final class Outtake {
        public static final String OUTTAKE_MOTOR = "outtakeMotor";
        public static final double POWER_OFF = 0;
    }

    public static final class Drive {
        public static final String LEFT_FRONT_DRIVE = "leftFront";
        public static final String RIGHT_FRONT_DRIVE = "rightFront";
        public static final String LEFT_BACK_DRIVE = "leftBack";
        public static final String RIGHT_BACK_DRIVE = "rightBack";

        public static final double MAX_SPEED = 0.5;
        public static final double WHEEL_LOCK_MIN = 0.2;
    }


public static final class Autonomous{
    public static final String LEFT_FRONT_DRIVE = "leftFront";
    public static final String RIGHT_FRONT_DRIVE = "rightFront";
    public static final String LEFT_BACK_DRIVE = "leftBack";
    public static final String RIGHT_BACK_DRIVE = "rightBack";
    public static final double     FORWARD_SPEED = 0.6;
    public static final double     TURN_SPEED    = 0.5;

    public static final double MOTOR_FORWARD_SPEED = 1.0;
}
}