package org.firstinspires.ftc.teamcode.programs.prelimbot.programs.experimentation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.programs.prelimbot.HardwareBase;

import hankextensions.logging.ProcessConsole;


@Autonomous(name = "Test Touch Sensor", group = "Experimentation")
public class TestTouchSensor extends HardwareBase{

    @Override
    protected void START() throws InterruptedException {
        ProcessConsole processConsole = log.newProcessConsole("Range Sensor");

        while(true){
            touchSensor.
        }

    }
}
