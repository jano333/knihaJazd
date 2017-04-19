package sk.hudak.knihajazd;

import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by hudak on 25.05.2016.
 */
public class NewVersion {


    private void haha() {
        try {
            File inputTxt = new File("C:\\PosAm\\Auto\\dochadzka.txt");
            List<String> lines = Files.readAllLines(inputTxt.toPath(), StandardCharsets.UTF_8);


            List<String> oneDayLines = new ArrayList<>(4);
            String fistTwoChars = null;
            for (String line : lines) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                line = line.trim();
                if (!line.contains("Hudak")) {
                    continue;
                }
                if (fistTwoChars == null) {
                    fistTwoChars = line.substring(0, 2);
                }
                if (line.startsWith(fistTwoChars)) {
                    oneDayLines.add(line);
                } else {
                    spracujDen(oneDayLines);
                    oneDayLines = new ArrayList<>(4);
                    oneDayLines.add(line);
                    fistTwoChars = line.substring(0, 2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void spracujDen(List<String> oneDayLines) {
        StringBuffer sb = new StringBuffer();
        String day = null;
        String prichodDochadzka = null;
        String odchodDochadzka = null;
        for (String oneDayLine : oneDayLines) {
            if (day == null) {
                day = oneDayLine.substring(0, 2);
            }

            if (oneDayLine.contains("Pr")) {
                if (prichodDochadzka == null) {
                    // beriem prvy
                    prichodDochadzka = oneDayLine;
                }
                continue;
            }

            if (oneDayLine.contains("Odchod")) {
                // beriem posledny
                odchodDochadzka = oneDayLine;
                continue;
            }
        }

        //
        prichodDochadzka = processPrichod(prichodDochadzka);
        odchodDochadzka = processOdchod(odchodDochadzka);

        if (prichodDochadzka == null) {
            prichodDochadzka = "-";
        }
        if (odchodDochadzka == null) {
            odchodDochadzka = "-";
        }

        String odchodZDomu = calculateOdchodZDomu(prichodDochadzka);
        String prichodPraca = calculatePrichodPraca(prichodDochadzka);

        String odchodZPraca = calculateOdchodZPraca(odchodDochadzka);
        String prichodDomov = calculatePrichodDomov(odchodDochadzka);

        sb.append(day);
        sb.append(" ");
        sb.append(odchodZDomu);
        sb.append(" ");
        sb.append(prichodPraca);
        sb.append(" ");
        sb.append(odchodZPraca);
        sb.append(" ");
        sb.append(prichodDomov);
        System.out.println(sb.toString());
    }

    private String calculateOdchodZDomu(String prichodDochadzka) {
        if(prichodDochadzka.equals("-")){
            return prichodDochadzka;
        }

        DateTime dateTime = new DateTime()
        .withHourOfDay(Integer.valueOf(prichodDochadzka.substring(0, 2)))
        .withMinuteOfHour(Integer.valueOf(prichodDochadzka.substring(3, prichodDochadzka.length())));

        int random = new Random().nextInt(20 - 15) + 15;
        dateTime = dateTime.minusMinutes(random);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = dateTime.toDate();
        return sdf.format(date);
    }

    private String calculatePrichodPraca(String prichodDochadzka) {
        if(prichodDochadzka.equals("-")){
            return prichodDochadzka;
        }

        DateTime dateTime = new DateTime()
                .withHourOfDay(Integer.valueOf(prichodDochadzka.substring(0, 2)))
                .withMinuteOfHour(Integer.valueOf(prichodDochadzka.substring(3, prichodDochadzka.length())));

        int random = new Random().nextInt(2 - 1) + 1;
        dateTime = dateTime.minusMinutes(random);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = dateTime.toDate();
        return sdf.format(date);
    }

    private String calculateOdchodZPraca(String odchodDochadzka) {
        if(odchodDochadzka.equals("-")){
            return odchodDochadzka;
        }

        DateTime dateTime = new DateTime()
                .withHourOfDay(Integer.valueOf(odchodDochadzka.substring(0, 2)))
                .withMinuteOfHour(Integer.valueOf(odchodDochadzka.substring(3, odchodDochadzka.length())));

        int random = new Random().nextInt(2 - 1) + 1;
        dateTime = dateTime.plusMinutes(random);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = dateTime.toDate();
        return sdf.format(date);
    }

    private String calculatePrichodDomov(String odchodDochadzka) {
        if(odchodDochadzka.equals("-")){
            return odchodDochadzka;
        }

        DateTime dateTime = new DateTime()
                .withHourOfDay(Integer.valueOf(odchodDochadzka.substring(0, 2)))
                .withMinuteOfHour(Integer.valueOf(odchodDochadzka.substring(3, odchodDochadzka.length())));

        int random = new Random().nextInt(20 - 15) + 15;
        dateTime = dateTime.plusMinutes(random);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = dateTime.toDate();
        return sdf.format(date);
    }

    private String processPrichod(String prichod) {
        if (prichod == null) {
            return null;
        }
        String[] result = prichod.split(" ");
        String cas = result[1];

        String[] split2 = cas.split("\t");
        cas = split2[0];

        cas = cas.substring(0, cas.length() - 3);


        return cas;
    }

    private String processOdchod(String odchod) {
        if (odchod == null) {
            return null;
        }
        String[] result = odchod.split(" ");
        String cas = result[1];

        String[] split2 = cas.split("\t");
        cas = split2[0];

        cas = cas.substring(0, cas.length() - 3);

        return cas;
    }


    public static void main(String[] args) {
        new NewVersion().haha();

    }
}
