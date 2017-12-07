package org.firstinspires.ftc.teamcode.experimentation;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

@Autonomous(name="Test Range Alone", group="Test")
public class RangeSensorThingTest extends OpMode
{
    private ModernRoboticsI2cRangeSensor sensor;

    @Override
    public void init() {
        sensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range Sensor");
        sensor.setI2cAddress(I2cAddr.create8bit(0x10));
    }

    @Override
    public void loop() {
        telemetry.addLine("Val = " + sensor.cmUltrasonic());
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}
