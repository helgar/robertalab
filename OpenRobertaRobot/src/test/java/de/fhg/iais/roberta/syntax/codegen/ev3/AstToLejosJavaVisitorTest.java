package de.fhg.iais.roberta.syntax.codegen.ev3;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.fhg.iais.roberta.components.ev3.EV3Actor;
import de.fhg.iais.roberta.components.ev3.EV3Actors;
import de.fhg.iais.roberta.components.ev3.EV3Sensor;
import de.fhg.iais.roberta.components.ev3.EV3Sensors;
import de.fhg.iais.roberta.components.ev3.Ev3Configuration;
import de.fhg.iais.roberta.shared.action.ev3.ActorPort;
import de.fhg.iais.roberta.shared.action.ev3.DriveDirection;
import de.fhg.iais.roberta.shared.action.ev3.MotorSide;
import de.fhg.iais.roberta.shared.sensor.ev3.SensorPort;
import de.fhg.iais.roberta.testutil.Helper;

public class AstToLejosJavaVisitorTest {
    //TODO: change diameter and trackwidth to changeable
    // when sensors are added to nxt, fix the sensors description here

    private static final String MAIN_METHOD = ""
    + "#define WHEELDIAMETER 5.6\n"
    + "#define TRACKWIDTH 17.0\n"
    + "task main(){"
    + "    SetSensorTouch(S1);\n"
    //+ "    SetSensorSound(S2);\n"
    //+ "    SetSensorLight(S3);\n"
        + "    SetSensorLowspeed(S2);\n";

    private static final String SUFFIX = "";
    private static Ev3Configuration brickConfiguration;

    @BeforeClass
    public static void setupConfigurationForAllTests() {
        Ev3Configuration.Builder builder = new Ev3Configuration.Builder();
        builder.setTrackWidth(17).setWheelDiameter(5.6);
        builder.addActor(ActorPort.A, new EV3Actor(EV3Actors.EV3_MEDIUM_MOTOR, true, DriveDirection.FOREWARD, MotorSide.LEFT)).addActor(
            ActorPort.B,
            new EV3Actor(EV3Actors.EV3_LARGE_MOTOR, true, DriveDirection.FOREWARD, MotorSide.RIGHT));
        builder.addSensor(SensorPort.S1, new EV3Sensor(EV3Sensors.EV3_TOUCH_SENSOR)).addSensor(SensorPort.S2, new EV3Sensor(EV3Sensors.EV3_ULTRASONIC_SENSOR));
        brickConfiguration = builder.build();
    }

    @Test
    public void test() throws Exception {

        String a = "" //
            + MAIN_METHOD
            + "        TextOut(\"Hallo\", 0, 3);\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator.xml");
    }

    @Test
    public void test1() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "        for ( float k0 = 0; k0 < 10; k0+=1 ) {\n"
            + "           TextOut(\"Hallo\", 0, 3);\n"
            + "        }\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator1.xml");
    }

    @Test
    public void test2() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "        if (hal.isPressed(SensorPort.S1)) {\n"
            + "            hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "        } else if ( Pickcolor.RED == SetSensor(IN_SensorPort.S3 ,COLOUR);) {\n"
            + "        if ( TRUE ) {\n"
            + "            while ( true ) {\n"
            + "               drawPicture(ShowPicture.EYESOPEN, 0, 0);\n\n"
            + "                  RotateMotor(OUT_B,30);"
            + "            }\n"
            + "        }\n"
            + "        }\n"
            + "        playFile(1);\n"
            + "        setVolume(50);\n"
            + "        for ( float i = 1; i < 10; i += 1 ) {\n\n"
            + "        RotateMotor(OUT_B,30,360.0*1);"
            + "        }\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator2.xml");
    }

    @Test
    public void test3() throws Exception {

        String a = "" //

            + MAIN_METHOD

            + "        if (hal.isPressed(SensorPort.S1)) {\n"
            + "            hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "        } else {\n"
            + "            if (hal.isPressed(SensorPort.S1)) {\n"
            + "                hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "            } else if (0==getUltraSonicSensorDistance(SensorPort.S4)) {\n"
            + "                drawPicture(ShowPicture.FLOWERS, 15, 15);\n"
            + "            } else {\n"
            + "            if ( TRUE ) {\n"
            + "                while ( !hal.isPressed(BrickKey.UP) ) {\n\n"
            + "                    RotateMotor(OUT_B,30);"
            + "                }\n"
            + "            }\n"
            + "            }\n"
            + "        }\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator3.xml");
    }

    @Test
    public void test4() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "        if ( 5 < hal.getRegulatedMotorSpeed(ActorPort.B) ) {\n\n\n"
            + "             RotateMotor(OUT_B,30);\n"
            + "          RotateMotor(OUT_B,30,360.0*1);\n"
            + "            turn_right(50);\n"
            + "        }\n"
            + "        if ((hal.getRegulatedMotorTachoValue(ActorPort.A, MotorTachoMode.ROTATION) + SetSensorInfrared(IN_SensorPort.S4,DISTANCE); )== getUltraSonicSensorDistance(SensorPort.S4)) {\n"
            + "            hal.ledOff();\n"
            + "        } else {\n"
            + "           SetSensorGyro(IN_SensorPort.S2,RESET);\n"
            + "        if ( TRUE ) {\n"
            + "            while ( hal.isPressed(SensorPort.S1)) {\n"
            + "                drawPicture(ShowPicture.OLDGLASSES, 0, 0);\n"
            + "                ClearScreen();\n"
            + "            }\n"
            + "         }\n"
            + "            hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "        }\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator4.xml");
    }

    @Test
    public void test5() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "          RotateMotor(OUT_B,0);"
            + "        RotateMotor(OUT_B,30,360.0*0);"
            + "        turn_right(0);"
            + "        setVolume(50);"
            + "        PlayTone(0,0);"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator5.xml");
    }

    @Test
    public void test6() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "        TextOut(\"Hallo\", 0, 0);\n"
            + "        PlayTone(300, 3000);\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator6.xml");
    }

    @Test
    public void test7() throws Exception {
        String a = "" //
            + MAIN_METHOD

            + "          RotateMotor(OUT_B,30);\n"
            + "          RotateMotor(OUT_B,30,360.0*1);\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator7.xml");
    }

    @Test
    public void test8() throws Exception {

        final String a = "" //
            + MAIN_METHOD
            + "        float item = 10;\n"
            + "        string item2 = \"TTTT\";\n"
            + "        boolean item3 = true;\n"
            + "        TextOut(string(item), 0, 0);\n"
            + "        TextOut(string(item2), 0, 0);\n"
            + "        TextOut(string(item3), 0, 0);\n"
            + "        item3 = false;\n"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator8.xml");
    }

    @Test
    public void test19() throws Exception {

        String a = "" //
            + MAIN_METHOD
            + "        float variablenName = 0;\n"

            + "OnFwd(OUT_AB,50)"
            + "drawPicture(ShowPicture.OLDGLASSES,0,0);"
            + SUFFIX

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator9.xml");
    }

    //Test accepts some types, those don't exist in NXC. Should be fixed later, when the
    //OpenRoberta for NXT is ready

    // @Test
    //public void test9() throws Exception {

    //String a = "" //
    //+ MAIN_METHOD
    //+ "        floatitem=0;"
    //+ "        stringitem2=\"ss\";"
    //+ "        booleanitem3=true;"
    //+ "        floatitem4={1,2,3};"
    //+ "        stringitem5={\"a\",\"b\"};"
    //+ "        booleanitem6={true,false};"

    //+ SUFFIX

    //+ "}\n";

    //assertCodeIsOk(a, "/ast/task/task_mainTask.xml");
    //}

    @Test
    public void test10() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "       RotateMotor(OUT_B,30,360.0*1);"
            + "        macheEtwas(10, 10);"

            + "    private void macheEtwas(float x, float x2) {\n"
            + "        drawPicture(ShowPicture.OLDGLASSES, x, x2);\n"
            + "    }"
            + "}\n";

        assertCodeIsOk(a, "/syntax/methods/method_void_1.xml");
    }

    @Test
    public void test11() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "        test();"

            + "    private void test() {\n"
            + "        hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "    }"
            + "}\n";

        assertCodeIsOk(a, "/syntax/methods/method_void_2.xml");
    }

    @Test
    public void test12() throws Exception {

        String a = "" //
            + MAIN_METHOD

            + "        test(true);"

            + "    private void test(boolean x) {\n"
            + "        if (x) return;"
            + "        hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "    }"
            + "}\n";

        assertCodeIsOk(a, "/syntax/methods/method_if_return_1.xml");
    }

    @Test
    public void test13() throws Exception {

        String a = "" //
            + MAIN_METHOD
            + "    float variablenName=0;\n"
            + "    boolean variablenName2=true;\n"

            + "        test1(0, 0);"
            + "        test2();"

            + "    private void test1(float x, float x2) {\n"
            + "        TextOut(\"Hallo\", x, x2);\n"
            + "    }\n\n"
            + "    private void test2() {\n"
            + "        if (variablenName2) return;"
            + "        hal.ledOn(BrickLedColor.GREEN, BlinkMode.ON);\n"
            + "    }"
            + "}\n";

        assertCodeIsOk(a, "/syntax/methods/method_void_3.xml");
    }

    @Test
    public void test14() throws Exception {

        String a = "" //
            + MAIN_METHOD
            + "    string variablenName[]={\"a\",\"b\",\"c\"};\n"

            + "        TextOut(string(test(0, variablenName)), 0, 0);"

            + "    private float test(float x, string x2[]) {\n"
            + "       TextOut(string(x2), x, 0);\n"
            + "        return x;\n"
            + "    }"
            + "}\n";

        assertCodeIsOk(a, "/syntax/methods/method_return_1.xml");
    }

    //Test accepts some types, those don't exist in NXC. Should be fixed later, when the
    //OpenRoberta for NXT is ready
    //@Test
    //public void test15() throws Exception {

    //String a = "" //
    //+ MAIN_METHOD
    //+ "    string variablenName=BlocklyMethods.createListWithString(\"a\", \"b\", \"c\");\n"

    //+ "        hal.drawText(String.valueOf(test()), 0, 0);"

    //+ "    private Pickcolor test() {\n"
    //+ "        hal.drawText(String.valueOf(variablenName), 0, 0);\n"
    //+ "        return Pickcolor.NONE;\n"
    //+ "    }"
    //+"}\n";

    //    assertCodeIsOk(a, "/syntax/methods/method_return_2.xml");
    //}

    //Test accepts some types, those don't exist in NXC. Should be fixed later, when the
    //OpenRoberta for NXT is ready
    //@Test
    //public void test16() throws Exception {

    //    String a = "" //

    //        + "    string variablenName=BlocklyMethods.createListWithString(\"a\", \"b\", \"c\");\n"

    //        + "        hal.drawText(String.valueOf(test()), 0, 0);"
    //        + "}\n";

    //    assertCodeIsOk(a, "/syntax/methods/method_if_return_2.xml");
    //}

    @Test
    public void test17() throws Exception {
        // regression test for https://mp-devel.iais.fraunhofer.de/jira/browse/ORA-610
        String a = "" //
            + MAIN_METHOD
            + "    string message=\"exit\";\n"

            + "        if (message.equals(\"exit\")) {\n"
            + "           TextOut(\"done\", 0, 0);"
            + "        }\n"

            + "}\n";

        assertCodeIsOk(a, "/syntax/stmt/if_stmt4.xml");
    }

    @Test
    public void test18() throws Exception {
        String a = "" //
            + MAIN_METHOD
            + "    float item=0;\n"
            + "    string item2=\"cc\";\n"

            + "}\n";

        assertCodeIsOk(a, "/syntax/code_generator/java/java_code_generator11.xml");
    }

    @Ignore
    public void testStmtForEach() throws Exception {
        final String a = "" //
            + MAIN_METHOD
            + "ArrayList<Pickcolor>variablenName=BlocklyMethods.createListWithColour(Pickcolor.NONE,Pickcolor.RED,Pickcolor.BLUE);\n"
            + "    public void run() throwsException {\n"
            + "        for (PickcolorvariablenName2 : variablenName) {\n"
            + "            TextOut(String(variablenName2),0,0);\n"
            + "        }\n"
            + "    }\n"
            + "}\n";

        assertCodeIsOk(a, "/syntax/stmt/forEach_stmt.xml");
    }

    private void assertCodeIsOk(String a, String fileName) throws Exception {
        // Assert.assertEquals(a, Helper.generateString(fileName, brickConfiguration));
        Assert.assertEquals(a.replaceAll("\\s+", ""), Helper.generateString(fileName, brickConfiguration).replaceAll("\\s+", ""));
    }
}
