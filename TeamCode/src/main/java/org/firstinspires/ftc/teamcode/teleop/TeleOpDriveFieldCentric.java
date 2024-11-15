package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;

@TeleOp(name = "Field Centric", group = "Robot")

public class TeleOpDriveFieldCentric extends LinearOpMode {

    // Create a RobotHardware object to be used to access robot hardware.
    // Prefix any hardware function with "robot."
    // to access this class.
    RobotHardware robot = new RobotHardware(this);
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        double drive;
        double strafe;
        double turn;

        // Initialize all the hardware, using the hardware class.
        robot.init();

        // Send a telemetry message to signify the robot waiting; wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Run until the end of the match (driver presses STOP).
        while (opModeIsActive()) {

            // Field Centric Mode use the left joystick to go forward & strafe,
            // and the right joystick to rotate from the perspective of the driver
            drive = -gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            turn = gamepad1.right_stick_x;

            // Combine drive, strafe, and turn for blended motion. Use RobotHardware class
            robot.driveFieldCentric(drive, strafe, turn);

            // Send a telemetry message to explain controls and show robot status
            telemetry.addData("\nStatus", "Run Time: " + runtime);
            telemetry.addData("Manual", "Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
            telemetry.update();

            // Place this loop so hands move at a reasonable speed
            sleep(50);
        }
    }
}
