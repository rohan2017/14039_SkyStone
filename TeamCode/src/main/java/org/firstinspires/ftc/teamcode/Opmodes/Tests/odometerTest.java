package org.firstinspires.ftc.teamcode.Opmodes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Movement.Localization.OdometerIMU2W;
import org.firstinspires.ftc.teamcode.Movement.Localization.OdometerKIMU2W;
import org.firstinspires.ftc.teamcode.Movement.MecanumDrive;
import org.firstinspires.ftc.teamcode.Movement.Movement;
import org.firstinspires.ftc.teamcode.Utility.RobotHardware;
import org.firstinspires.ftc.teamcode.Utility.Timer;

@Autonomous(name="Odometer Test", group="Testing")

public class odometerTest extends LinearOpMode {

    // Declare OpMode Members
    private RobotHardware hardware = new RobotHardware();
    private OdometerKIMU2W odometer;
    //private OdometerIMU2W odometer;
    private MecanumDrive drivetrain;
    private Movement movement;
    private Timer timer;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        timer.start();
        odometer.startTracking(0, 0, 0);
        telemetry.addData("status","running");
        telemetry.update();

        while(opModeIsActive()){
            odometer.update();
            telemetry.addData("X", odometer.x);
            telemetry.addData("Y", odometer.y);
            telemetry.addData("Heading", odometer.heading);
            telemetry.addData("Vertical", odometer.vertical);
            telemetry.addData("Horizontal", odometer.horizontal);
            telemetry.update();

        }

    }

    private void initialize(){
        hardware.hardwareMap(hardwareMap);

        //odometer = new OdometerIMU2W(this, hardware);
        odometer = new OdometerKIMU2W(this, hardware);
        drivetrain = new MecanumDrive(this, hardware);
        timer = new Timer(this, odometer);
        movement = new Movement(this, drivetrain, odometer, timer);

        drivetrain.initialize();
        odometer.initialize();

        telemetry.addData("status","initialized");
        telemetry.update();

    }
}
