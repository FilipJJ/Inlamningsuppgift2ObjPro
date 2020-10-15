package GymTest;

import Gym.Gym;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by Filip Jakobsson
 * Date: 2020-10-09
 * Time: 14:57
 * Project: testProjJavaB
 * Copyright: MIT
 */
public class GymTest {

    //Jag ska läsa in ett namn eller ett personnummer
    //Jag ska kolla om namnet eller personnumret finns i textfilen
    //Jag ska kolla om personen är en nuvarande medlem

    Gym a = new Gym();

    @Test
    public void getNameTest(){

        String line = "7603021234, Alhambra Aromes";

        assertTrue(a.getName(line).equals("Alhambra Aromes"));
        assertFalse(a.getName(line).equals("Bear Belle"));

    }

    @Test
    public void getSocialNumberTest(){

        String line = "7603021234, Alhambra Aromes";

        assertTrue(a.getSocialNumber(line).equals("7603021234"));
        assertFalse(a.getSocialNumber(line).equals("8104021234"));

    }

    @Test
    public void parseDateFromLineTest(){

        String line = "2019-07-01";

        assertTrue(a.parseDateFromLine(line).equals(LocalDate.of(2019,07,01)));
        assertFalse(a.parseDateFromLine(line).equals(LocalDate.of(2018,12,02)));

    }



}
