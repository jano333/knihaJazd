package sk.hudak.jasper;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sk.hudak.jasper.to.BasicDto;
import sk.hudak.jasper.to.TableDataDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class VybavaPodlaVinReport extends ReportBase {

    private static final String TEMPLATE_PATH = "C:\\Users\\hudak\\JaspersoftWorkspace\\MyReports\\group_of_products.jasper";

    private BasicDto paramsValues;
    private List<TableDataDto> reportRows;

    public VybavaPodlaVinReport(String outputFilePath, BasicDto paramsValues, List<TableDataDto> reportRows) throws FileNotFoundException {
        super(TEMPLATE_PATH, outputFilePath);
        this.paramsValues = paramsValues;
        this.reportRows = reportRows;
    }

    @Override
    JasperPrint createJasperPrint() throws Exception {
        HashMap<String, Object> props = new HashMap<String, Object>();
        props.put("groupName", paramsValues.getGroupName());
        props.put("unit", paramsValues.getUnit());

        return JasperFillManager.fillReport(
                new File(templatePath).getCanonicalPath(),
                props,
                new JRBeanCollectionDataSource(reportRows));
    }

    public static void main(String[] args) {

        BasicDto paramsValues = new BasicDto();
        paramsValues.setGroupName("Pamper 4");
        paramsValues.setUnit("€/ks");

        List<TableDataDto> reportRows = new ArrayList<>();

        TableDataDto tableDataDto = new TableDataDto();
        tableDataDto.setProductName("Pampers 22 ks");
        tableDataDto.setProductUrl("https://www.feedo.sk/pampers-active-baby-4-maxi-147ks-7-14kg-mega-box-plus-mesacna-zasoba-jednorazove-plienky/");
        tableDataDto.setUnitPrice("0.203");
        tableDataDto.setLastActulization(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        reportRows.add(tableDataDto);

        tableDataDto = new TableDataDto();
        tableDataDto.setProductName("Pampers 46 ks");
        tableDataDto.setProductUrl("https://www.feedo.sk/pampers-active-baby-4-maxi-147ks-7-14kg-mega-box-plus-mesacna-zasoba-jednorazove-plienky/");
        tableDataDto.setUnitPrice("0.214");
        tableDataDto.setLastActulization(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        reportRows.add(tableDataDto);


        try {
            new VybavaPodlaVinReport("C:\\ii\\aloha.pdf", paramsValues, reportRows).generatePdf();
            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
