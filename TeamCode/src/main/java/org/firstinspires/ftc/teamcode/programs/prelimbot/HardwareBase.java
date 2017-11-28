package org.firstinspires.ftc.teamcode.programs.prelimbot;

import android.hardware.Sensor;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;


import org.firstinspires.ftc.teamcode.hardware.HankuTankuRange;

import hankextensions.Core;

/*
Config robot elements(motors and servos)
 */


public abstract class HardwareBase extends Core
{
    //Driving Motors
    protected DcMotor left, right, middle, miniLift, primaryLift;
    protected Servo topLeftGrabber, topRightGrabber, bottomLeftGrabber, bottomRightGrabber, swingServo;
    protected ColorSensor colorSensor;
    protected HankuTankuRange rangeSensor;

    // Constants to open and close grabber clamps.
    private static final double
            TOP_LEFT_OPEN = 1, TOP_LEFT_CLOSED = .6,
            TOP_RIGHT_OPEN = 0, TOP_RIGHT_CLOSED = .4,
            BOTTOM_LEFT_OPEN  = 0, BOTTOM_LEFT_CLOSED = .7,
            BOTTOM_RIGHT_OPEN = .8, BOTTOM_RIGHT_CLOSED = .1;

    //Other motors

    protected void setTopClampsTo(boolean open)
    {
        topLeftGrabber.setPosition(open ? TOP_LEFT_OPEN : TOP_LEFT_CLOSED);
        topRightGrabber.setPosition(open ? TOP_RIGHT_OPEN : TOP_RIGHT_CLOSED);
    }

    protected void setBottomClampsTo(boolean open)
    {
        bottomLeftGrabber.setPosition(open ? BOTTOM_LEFT_OPEN : BOTTOM_LEFT_CLOSED);
        bottomRightGrabber.setPosition(open ? BOTTOM_RIGHT_OPEN : BOTTOM_RIGHT_CLOSED);
    }

    @Override
    protected void HARDWARE() throws InterruptedException
    {
        left               = initHardwareDevice(DcMotor.class, "Left");
        right              = initHardwareDevice(DcMotor.class, "Right");
        middle             = initHardwareDevice(DcMotor.class, "Middle");

        miniLift           = initHardwareDevice(DcMotor.class, "lift 1");
        primaryLift        = initHardwareDevice(DcMotor.class, "lift 2");

        topLeftGrabber     = initHardwareDevice(Servo.class, "Top Left");
        topRightGrabber    = initHardwareDevice(Servo.class, "Top Right");
        bottomLeftGrabber  = initHardwareDevice(Servo.class, "Bottom Left");
        bottomRightGrabber = initHardwareDevice(Servo.class, "Bottom Right");

        swingServo         = initHardwareDevice(Servo.class, "Swing Servo");

        //colorSensor        = initHardwareDevice(ColorSensor.class, "Color Sensor");

        rangeSensor        = new HankuTankuRange(initHardwareDevice(ModernRoboticsI2cRangeSensor.class, "Range Sensor"));

        right.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
