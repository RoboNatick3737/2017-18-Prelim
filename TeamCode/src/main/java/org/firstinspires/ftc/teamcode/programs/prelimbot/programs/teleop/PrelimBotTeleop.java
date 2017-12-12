package org.firstinspires.ftc.teamcode.programs.prelimbot.programs.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.programs.prelimbot.HardwareBase;

import hankextensions.logging.ProcessConsole;
import hankextensions.threading.Flow;

@TeleOp(name = "Prelim Bot Teleop", group = Constants.PRELIM_BOT_OPMODES)
public class PrelimBotTeleop extends HardwareBase
{
    private long lastTopClampAdjustment = 0, lastBottomClampAdjustment = 0;
    private boolean topClampsClosed = false, bottomClampsClosed = false;

    private long lastBPress = 0;
    private boolean swingDown = false;

    private void moveRobot() {
        if (!(gamepad1.left_bumper || gamepad1.right_bumper)) {
            left.setPower(Range.clip(gamepad1.left_stick_y, -1, 1));
            right.setPower(Range.clip(gamepad1.right_stick_y, -1, 1));
        } else {
            if (gamepad1.left_bumper) {
                left.setPower(0.4);
                right.setPower(0.4);
            } else {
                left.setPower(-0.4);
                right.setPower(-0.4);
            }
        }

        middle.setPower(Range.clip(1.0 * gamepad1.right_trigger + -1.0 * gamepad1.left_trigger, -1, 1));
    }
    private void moveClamps()
    {
        // Top clamps
        if (gamepad1.y && (System.currentTimeMillis() - lastTopClampAdjustment) > 400)
        {
            topClampsClosed = !topClampsClosed;
            openTopClamps(!topClampsClosed);
            lastTopClampAdjustment = System.currentTimeMillis();
        }

        // Bottom clamps.
        if (gamepad1.a && (System.currentTimeMillis() - lastBottomClampAdjustment) > 400)
        {
            bottomClampsClosed = !bottomClampsClosed;
            openBottomClamps(!bottomClampsClosed);
            lastBottomClampAdjustment = System.currentTimeMillis();
        }
    }
    private void moveArm() {
        if (gamepad1.b && !swingDown && System.currentTimeMillis() - lastBPress > 400)
        {
            swingServo.setPosition(0);

            swingDown = true;
            lastBPress = System.currentTimeMillis();
        } else if(gamepad1.b && swingDown && System.currentTimeMillis() - lastBPress > 400)
        {
            swingServo.setPosition(1);

            swingDown = false;
            lastBPress = System.currentTimeMillis();
        }
    }
    private void moveLift() {
        if (gamepad2.y)
        {
            miniLift.setPower(0.6);
        } else if(gamepad2.a)
        {
            miniLift.setPower(-0.3);
        } else if(!gamepad2.y && !gamepad2.a)
        {
            miniLift.setPower(0);
        }

        if (gamepad2.dpad_up)
        {
            primaryLift.setPower(0.5);
        } else if(gamepad2.dpad_down)
        {
            primaryLift.setPower(-0.5);
        } else if(!gamepad2.dpad_up && !gamepad2.dpad_down)
        {
            primaryLift.setPower(0);
        }
    }

    @Override
    public void START() throws InterruptedException
    {
        ProcessConsole processConsole = log.newProcessConsole("Teleop");

        while (true)
        {
            moveRobot();
            moveClamps();
            moveArm();
            moveLift();

            processConsole.write(
                    "Top Clamps are currently " + (topClampsClosed ? "closed " : "open"),
                    "Bottom clamps are currently " + (bottomClampsClosed ? "closed" : "open")
            );

            processConsole.write("Range = " + rangeSensor.cmUltrasonic());

            Flow.yield();
        }
    }
}
