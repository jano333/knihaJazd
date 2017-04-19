package sk.hudak.knihajazd;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by hudak on 2.10.2015.
 */
public class Creator {

    public static final int MESIAC = 11;

    public static void main(String[] args) {
        try {
            Creator creator = new Creator();
            URL url = creator.getClass().getResource("/stranka.html");
            File file = new File(url.toURI());
            Document document = Jsoup.parse(file, StandardCharsets.UTF_8.name());

            Elements elements = document.select("table[class=lotusTable] tbody tr td");
            int pocet = elements.size();
            System.out.println("pocet " + pocet);


            int i = 0;
            // prve tri preskakujem
            elements.get(i++);
            elements.get(i++);
            elements.get(i++);

            List<DataObject> zoznamy = new ArrayList<DataObject>();

            while (i < pocet) {
                DataObject dataObject = new DataObject();
                dataObject.setDatum(elements.get(i++).text());
                dataObject.setOd(elements.get(i++).text());
                dataObject.setDoo(elements.get(i++).text());
                dataObject.setPraca(elements.get(i++).text());
                dataObject.setDovolenka(elements.get(i++).text());
                dataObject.setSviatok(elements.get(i++).text());
                dataObject.setParagraf(elements.get(i++).text());
                dataObject.setPnka(elements.get(i++).text());
                dataObject.setOcr(elements.get(i++).text());
                dataObject.setNahradnevolno(elements.get(i++).text());
                dataObject.setNeplatenevolno(elements.get(i++).text());
                dataObject.setLekar(elements.get(i++).text());
                zoznamy.add(dataObject);

                elements.get(i++);
            }


            for (DataObject dataObject : zoznamy) {
//                System.out.println("P: " + getDatum(dataObject) + "|" + dataObject.getOd() + "|" + dataObject.getDoo());

                String pracaPrichod = recalculate(dataObject.getOd(), true);
                String domovOdchod = recalculateHome(pracaPrichod, true);
                String pracaOdchod = recalculate(dataObject.getDoo(), false);
                String domovPrichod = recalculateHome(pracaOdchod, false);

                System.out.println(getDatum(dataObject) + "|" +
                        domovOdchod + "|" +
                        pracaPrichod + "|" +
                        pracaOdchod + "|" +
                        domovPrichod);

//                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static String recalculateHome(String input, boolean minus) {
        if (input.isEmpty()) {
            return input;
        }
        int hodina = Integer.valueOf(input.substring(0, input.indexOf(":")));
        int minuta = Integer.valueOf(input.substring(input.indexOf(":") + 1));

        DateTime dateTime = new DateTime(2015, MESIAC, 2, hodina, minuta);

        int random = new Random().nextInt(20 - 15) + 15;

        if (minus) {
            dateTime = dateTime.minusMinutes(random);
        } else {
            dateTime = dateTime.plusMinutes(random);
        }

        return doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                doplnNa2(String.valueOf(dateTime.getMinuteOfHour()));
    }

    private static String recalculate(String input, boolean minus) {
        if (input.isEmpty()) {
            return input;
        }
        int hodina = Integer.valueOf(input.substring(0, input.indexOf(":")));
        int minuta = Integer.valueOf(input.substring(input.indexOf(":") + 1));
        DateTime dateTime = new DateTime(2015, MESIAC, 2, hodina, minuta);
//        System.out.println(">>");
//        System.out.println(new SimpleDateFormat("HH:mm").format(dateTime.toDate()));
//        System.out.println("<<");
        boolean random = new Random().nextBoolean();

        if (minus) {
            dateTime = dateTime.minusMinutes(random ? 1 : 2);
        } else {
            dateTime = dateTime.plusMinutes(random ? 1 : 2);
        }
        return doplnNa2(String.valueOf(dateTime.getHourOfDay())) + ":" +
                doplnNa2(String.valueOf(dateTime.getMinuteOfHour()));
    }

    private static String doplnNa2(String str) {
        return StringUtils.leftPad(str, 2, "0");
    }


    private static String getDatum(DataObject dataObject) {
        String datum = dataObject.getDatum();
        datum = datum.substring(datum.indexOf(",") + 2, datum.indexOf("."));
        return datum;
    }

}
