package ClubParana;

import javafx.beans.property.SimpleStringProperty;

public class PagoObservable {

    private SimpleStringProperty fecha;
    private SimpleStringProperty monto;
    private SimpleStringProperty concepto;

    public PagoObservable(String fecha, String monto, String concepto){
        this.fecha = new SimpleStringProperty(fecha);
        this.monto = new SimpleStringProperty(monto);
        this.concepto = new SimpleStringProperty(concepto);
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getMonto() {
        return monto.get();
    }

    public SimpleStringProperty montoProperty() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto.set(monto);
    }

    public String getConcepto() {
        return concepto.get();
    }

    public SimpleStringProperty conceptoProperty() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto.set(concepto);
    }
}
