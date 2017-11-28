package org.firstinspires.ftc.teamcode.programs.prelimbot.programs.experimentation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.programs.prelimbot.HardwareBase;

import hankextensions.Core;
import hankextensions.logging.ProcessConsole;
import hankextensions.phonesensors.AndroidGyro;
import hankextensions.phonesensors.Gyro;
import hankextensions.threading.Flow;

@Autonomous(name = "Test Range Sensor", group = "Experimentation")
public class TestRangeSensor extends HardwareBase
{
    protected void START() throws InterruptedException
    {
        ProcessConsole processConsole = log.newProcessConsole("Range Sensor");

        while (true)
        {
            processConsole.write("Range Sensor Val: " + rangeSensor.validDistCM(255, 5000));
            Flow.yield();
        }
    }
}
