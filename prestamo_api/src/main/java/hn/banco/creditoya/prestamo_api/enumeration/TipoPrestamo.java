package hn.banco.creditoya.prestamo_api.enumeration;

public enum TipoPrestamo {
    
    VEHICULAR("V", "Préstamo Vehicular", 0.05),
    PERSONAL("P", "Préstamo Personal", 0.07),
    HIPOTECARIO("H", "Préstamo Hipotecario", 0.08);

    private final String codigo;
    private final String descripcion;
    private final double tasaInteres;

    TipoPrestamo(String codigo, String descripcion, double tasaInteres) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tasaInteres = tasaInteres;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }
}
