package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This code encapsulates the range sensor and enables the user to guarantee that the value returned
 * is valid and not just a sensor blip (often misfires and gets 255 when the dist is less)
 */
public class HankuTankuRange
{
    public final ModernRoboticsI2cRangeSensor sensor;
    public HankuTankuRange(ModernRoboticsI2cRangeSensor sensor)
    {
        this.sensor = sensor;
    }

    /**
     * If false, the robot needs to be power cycled.
     */
    public boolean returningValidOutput()
    {
        return !(sensor.getDistance (DistanceUnit.CM) < 1.0);
    }

    /**
     * This method enables the sensor to work for a while to try to find a valid value (such as
     * finding distances just at the range of its ability to detect ultrasonic waves.  Makes sure
     * that a valid value is received.
     *
     * @param defaultVal The value which is returned if it still can't find a good value.
     * @param maxTimePermitted The time in ms that it has to look for a good value.
     * @return
     */
    public double validDistCM(double defaultVal, long maxTimePermitted)
    {
        double ultrasonicDist = sensor.cmUltrasonic ();

        if (ultrasonicDist >= 255 || ultrasonicDist <= 0)
        {
            if (maxTimePermitted > 0)
            {
                long startTime = System.currentTimeMillis ();
                while (System.currentTimeMillis () - startTime <= maxTimePermitted)
                {
                    ultrasonicDist = sensor.cmUltrasonic ();
                    if (!(ultrasonicDist >= 255 || ultrasonicDist <= 0))
                        break;
                }
            }
            else
                return defaultVal;
        }

        return ultrasonicDist;
    }


    public double validDistCM(double defaultVal)
    {
        return validDistCM (defaultVal, 0);
    }
}