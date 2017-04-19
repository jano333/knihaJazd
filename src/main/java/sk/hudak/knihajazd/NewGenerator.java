package sk.hudak.knihajazd;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by hudak on 5.4.2016.
 */
@Deprecated
public class NewGenerator {



    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");


            List<PrichodOdchod> result = new ArrayList<>();
            File inputFile = new File("C:\\PosAm\\Auto\\dochadzka_vzor.txt");
            List<String> lines = Files.readAllLines(inputFile.toPath(), StandardCharsets.UTF_8);
            for (String line : lines) {
                line = line.trim();
                if (line == null || line.isEmpty()) {
                    continue;
                }
//                System.out.println("je to:"+line);

                PrichodOdchod prichodOdchod = new PrichodOdchod();
                if (line.startsWith("Príchod")) {
                    String datum = line.substring("Príchod".length(), line.length()).trim();
                    prichodOdchod.setPrichod(sdf.parse(datum));

                } else if (line.startsWith("Odchod")) {
                    String datum = line.substring("Odchod".length(), line.length()).trim();
                    prichodOdchod.setOdchod(sdf.parse(datum));
                } else {
                    System.out.println("nieco ine");
                }
                result.add(prichodOdchod);
            }

            // potrebujem teraz casy upravit
            for (int i = result.size() - 1; i >= 0; i--) {
                PrichodOdchod prichodOdchod = result.get(i);
                hh(prichodOdchod);







            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String hh(PrichodOdchod prichodOdchod){
    // prichod
        DateTime prichodPraca = new DateTime(prichodOdchod.getPrichod());
        int random = new Random().nextInt(2 - 1) + 1;
        prichodPraca = prichodPraca.minusMinutes(random);

        System.out.println(prichodPraca);


        return null;

//
//
//
//
//
//
//
//        DateTime dateTime;
//
//        if(minus){
//            dateTime = new DateTime(prichodOdchod.getPrichod());
//        }else {
//
//        }
//
//        DateTime dateTime = new DateTime(2016, MESIAC, 2, hodina, minuta);
//
//        int random = new Random().nextInt(20 - 15) + 15;
//
//        if (minus) {
//            dateTime = dateTime.minusMinutes(random);
//        } else {
//            dateTime = dateTime.plusMinutes(random);
//        }
//
//        return doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
//                doplnNa2(String.valueOf(dateTime.getMinuteOfHour()));
    }

    private static String doplnNa2(String str) {
        return StringUtils.leftPad(str, 2, "0");
    }

    private static int getMinutaPrihod(int hodinaPrichod) {
        if (6 == hodinaPrichod) {
            return new Random().nextInt(59 - 40) + 40;
        } else {
            return new Random().nextInt(35);
        }
    }


    private static class PrichodOdchod {

        private Date prichod;
        private Date odchod;

        public PrichodOdchod() {
        }

        public PrichodOdchod(Date prichod, Date odchod) {
            this.prichod = prichod;
            this.odchod = odchod;
        }

        public Date getPrichod() {
            return prichod;
        }

        public void setPrichod(Date prichod) {
            this.prichod = prichod;
        }

        public Date getOdchod() {
            return odchod;
        }

        public void setOdchod(Date odchod) {
            this.odchod = odchod;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PrichodOdchod that = (PrichodOdchod) o;

            if (prichod != null ? !prichod.equals(that.prichod) : that.prichod != null) return false;
            return !(odchod != null ? !odchod.equals(that.odchod) : that.odchod != null);

        }

        @Override
        public int hashCode() {
            int result = prichod != null ? prichod.hashCode() : 0;
            result = 31 * result + (odchod != null ? odchod.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "PrichodOdchod{" +
                    "prichod=" + prichod +
                    ", odchod=" + odchod +
                    '}';
        }
    }
}
