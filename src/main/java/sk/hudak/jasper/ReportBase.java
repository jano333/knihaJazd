package sk.hudak.jasper;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class ReportBase {

    protected String templatePath;
    protected String outputFile;

    public ReportBase(String templatePath, String outputFilePath) throws FileNotFoundException {
        this.outputFile = outputFilePath;
        this.templatePath = templatePath;
        if (!new File(templatePath).exists()) {
            throw new FileNotFoundException("Nenasla sa sablona: " + templatePath);
        }
    }

    abstract JasperPrint createJasperPrint() throws Exception;

    public void generatePdf() throws Exception {
        JasperPrint jasperPrint = createJasperPrint();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile);
        exporter.exportReport();
    }
}
