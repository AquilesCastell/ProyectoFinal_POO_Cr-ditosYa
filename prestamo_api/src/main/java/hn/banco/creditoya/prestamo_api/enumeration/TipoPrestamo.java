package hn.banco.creditoya.prestamo_api.enumeration;

public enum TipoPrestamo {
    
    V("Vehicular"),
    P("Personal"),
    H("Hipotecario");

    private final String descripcion;

    TipoPrestamo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
