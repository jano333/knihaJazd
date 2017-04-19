package sk.hudak.knihajazd;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.Random;

/**
 * Created by hudak on 23.12.2015.
 */
public class CasGenerator {

    public static void main(String[] args) {


//        DateTime dateTime = new DateTime(2016, 2, 1, 6, 49);
//
//        int random = new Random().nextInt(20 - 15) + 15;
//        dateTime = dateTime.minusMinutes(random);
//        System.out.println(doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
//                doplnNa2(String.valueOf(dateTime.getMinuteOfHour())));
//
//        metoda_1();

        DateTime dateTime = new DateTime(2016, 2, 1, 15,49);

        int random = new Random().nextInt(20 - 15) + 15;
        dateTime = dateTime.plusMinutes(random);
//        dateTime = dateTime.minusMinutes(random);
        System.out.println(doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                doplnNa2(String.valueOf(dateTime.getMinuteOfHour())));



    }

    private static void metoda_1() {
        for (int i = 0; i < 23; i++) {
            int hodinaPrichod = new Random().nextBoolean() ? 6 : 7;
            int minutaPrichod = getMinutaPrihod(hodinaPrichod);

            DateTime dateTime = new DateTime(2016, 1, 1, hodinaPrichod, minutaPrichod);
            System.out.println(doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                    doplnNa2(String.valueOf(dateTime.getMinuteOfHour())));

            int random = new Random().nextInt(20 - 15) + 15;
            dateTime = dateTime.plusMinutes(random);
            System.out.println(doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                    doplnNa2(String.valueOf(dateTime.getMinuteOfHour())));


            //---------------------

            int hodinaOdchod = 16;
            int minutaOdchod = new Random().nextInt(59);

            dateTime = new DateTime(2015, 12, 1, hodinaOdchod, minutaOdchod);
            System.out.println(doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                    doplnNa2(String.valueOf(dateTime.getMinuteOfHour())));

            random = new Random().nextInt(20 - 15) + 15;
            dateTime = dateTime.plusMinutes(random);
            System.out.println(doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                    doplnNa2(String.valueOf(dateTime.getMinuteOfHour())));

            System.out.println("-----");
        }
    }

    private static int getMinutaPrihod(int hodinaPrichod) {
        if (6 == hodinaPrichod) {
            return new Random().nextInt(59 - 40) + 40;
        } else {
            return new Random().nextInt(35);
        }
    }


    private static String doplnNa2(String str) {
        return StringUtils.leftPad(str, 2, "0");
    }
}
