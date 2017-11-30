package org.firstinspires.ftc.teamcode.experimentation;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

@Autonomous(name="Test Range Alone", group="Test")
public class RangeSensorThingTest extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        UltrasonicSensor rangeSensor = hardwareMap.ultrasonicSensor.get("Range Sensor");

        while (opModeIsActive())
        {
            telemetry.addLine("Is currently: " + rangeSensor.getUltrasonicLevel());
            telemetry.update();

            idle();
        }
    }
}
