package sk.hudak.knihajazd;

/**
 * Created by hudak on 2.10.2015.
 */
public class DataObject {

    private String datum;
    private String od;
    private String doo;
    private String praca;
    private String dovolenka;
    private String sviatok;
    private String paragraf;
    private String pnka;
    private String ocr;
    private String nahradnevolno;
    private String neplatenevolno;
    private String lekar;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public String getDoo() {
        return doo;
    }

    public void setDoo(String doo) {
        this.doo = doo;
    }

    public String getPraca() {
        return praca;
    }

    public void setPraca(String praca) {
        this.praca = praca;
    }

    public String getDovolenka() {
        return dovolenka;
    }

    public void setDovolenka(String dovolenka) {
        this.dovolenka = dovolenka;
    }

    public String getSviatok() {
        return sviatok;
    }

    public void setSviatok(String sviatok) {
        this.sviatok = sviatok;
    }

    public String getParagraf() {
        return paragraf;
    }

    public void setParagraf(String paragraf) {
        this.paragraf = paragraf;
    }

    public String getPnka() {
        return pnka;
    }

    public void setPnka(String pnka) {
        this.pnka = pnka;
    }

    public String getOcr() {
        return ocr;
    }

    public void setOcr(String ocr) {
        this.ocr = ocr;
    }

    public String getNahradnevolno() {
        return nahradnevolno;
    }

    public void setNahradnevolno(String nahradnevolno) {
        this.nahradnevolno = nahradnevolno;
    }

    public String getLekar() {
        return lekar;
    }

    public void setLekar(String lekar) {
        this.lekar = lekar;
    }

    public String getNeplatenevolno() {
        return neplatenevolno;
    }

    public void setNeplatenevolno(String neplatenevolno) {
        this.neplatenevolno = neplatenevolno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataObject that = (DataObject) o;

        if (datum != null ? !datum.equals(that.datum) : that.datum != null) return false;
        if (od != null ? !od.equals(that.od) : that.od != null) return false;
        if (doo != null ? !doo.equals(that.doo) : that.doo != null) return false;
        if (praca != null ? !praca.equals(that.praca) : that.praca != null) return false;
        if (dovolenka != null ? !dovolenka.equals(that.dovolenka) : that.dovolenka != null) return false;
        if (sviatok != null ? !sviatok.equals(that.sviatok) : that.sviatok != null) return false;
        if (paragraf != null ? !paragraf.equals(that.paragraf) : that.paragraf != null) return false;
        if (pnka != null ? !pnka.equals(that.pnka) : that.pnka != null) return false;
        if (ocr != null ? !ocr.equals(that.ocr) : that.ocr != null) return false;
        if (nahradnevolno != null ? !nahradnevolno.equals(that.nahradnevolno) : that.nahradnevolno != null)
            return false;
        if (neplatenevolno != null ? !neplatenevolno.equals(that.neplatenevolno) : that.neplatenevolno != null)
            return false;
        return !(lekar != null ? !lekar.equals(that.lekar) : that.lekar != null);

    }

    @Override
    public int hashCode() {
        int result = datum != null ? datum.hashCode() : 0;
        result = 31 * result + (od != null ? od.hashCode() : 0);
        result = 31 * result + (doo != null ? doo.hashCode() : 0);
        result = 31 * result + (praca != null ? praca.hashCode() : 0);
        result = 31 * result + (dovolenka != null ? dovolenka.hashCode() : 0);
        result = 31 * result + (sviatok != null ? sviatok.hashCode() : 0);
        result = 31 * result + (paragraf != null ? paragraf.hashCode() : 0);
        result = 31 * result + (pnka != null ? pnka.hashCode() : 0);
        result = 31 * result + (ocr != null ? ocr.hashCode() : 0);
        result = 31 * result + (nahradnevolno != null ? nahradnevolno.hashCode() : 0);
        result = 31 * result + (neplatenevolno != null ? neplatenevolno.hashCode() : 0);
        result = 31 * result + (lekar != null ? lekar.hashCode() : 0);
        return result;
    }
}
