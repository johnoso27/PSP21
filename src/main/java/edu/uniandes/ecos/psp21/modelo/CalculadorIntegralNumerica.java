package edu.uniandes.ecos.psp21.modelo;

/**
 * Clase que calcula una integral numérica, usando la regla de simpsom.
 *
 * @version 1.0.1.0
 * @author JohnDany
 */
public class CalculadorIntegralNumerica {

    /**
     * Variable para recalcular el valor d para modificar el valor de x.
     */
    private double auxiliarX;

    /**
     * Bandera para verificar si el error ha cambiado de signo.
     */
    private boolean cambioSigno;

    /**
     * Método que calcula el valor de una integral numérica usando la regla de
     * Simpson. No se valida la presición respecto al margen de error.
     *
     * @param numeroSegmentos Número de segmentos. El valor es par.
     * @param x es valor límite del rango a calcular.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor de la integral numérica calculada.
     * @throws Exception lanzada en caso de error.
     */
    public double calcularIntegralNumerica(int numeroSegmentos, double x, double dof) throws Exception {
        if (numeroSegmentos % 2 == 1) {
            throw new Exception("Se ingresó el número de segmentos impar. Se requiere un número par");
        }

        double w = x / numeroSegmentos;
        double parteFx1 = this.calculaParteDeFx(dof);

        double resultado = 0.0;
        double acumulado = 0.0;
        double parcial = 0.0;
        double fx = 0.0;
        int multiplicador = 0;
        for (int i = 0; i <= numeroSegmentos; i++) {
            if (i == 0 || i == numeroSegmentos) {
                multiplicador = 1;
            } else if (i % 2 == 0) {
                multiplicador = 2;
            } else {
                multiplicador = 4;
            }

            double parteFx2 = (1 + (Math.pow(acumulado, 2)) / dof);
            fx = parteFx1 * Math.pow(parteFx2, (-(dof + 1) / 2));

            parcial = (w / 3) * multiplicador * fx;
            resultado = resultado + parcial;
            acumulado = acumulado + w;
        }

        return resultado;
    }

    /**
     * Método que calcula el valor de una integral numérica usando la regla de
     * Simpson. No se valida la presición respecto al margen de error.
     *
     * @param numeroSegmentos Número de segmentos. El valor es par.
     * @param x es valor límite del rango a calcular.
     * @param e es el margen de error.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor de la integral numérica calculada.
     * @throws Exception lanzada en caso de error.
     */
    public double calcularIntegralNumericaEnMargenError(int numeroSegmentos, double x, double e, double dof) throws Exception {
        int segmentos = numeroSegmentos;
        double ultimoResultado = this.calcularIntegralNumerica(segmentos, x, dof);
        double funcionRecalculada = ultimoResultado;

        do {
            segmentos = segmentos * 2;
            ultimoResultado = funcionRecalculada;
            funcionRecalculada = this.calcularIntegralNumerica(segmentos, x, dof);
        } while ((ultimoResultado - funcionRecalculada) > e);

        return funcionRecalculada;
    }

    /**
     * Método que calcula el valor de x para el cual, integrar la función t
     * desde 0 a x, dado el valor de p.
     *
     * @param numeroSegmentos Número de segmentos. El valor es par.
     * @param p es el valor propuesto como resultado de la integral.
     * @param e es el margen de error.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor del límite de la integral.
     * @throws Exception lanzada en caso de presentarse alguna será relanzada.
     */
    public double calcularLimiteIntegral(int numeroSegmentos, double p, double e, double dof) throws Exception {
        double x = 1.0;
        this.auxiliarX = 0.5;
        this.cambioSigno = false;

        double resultadoIntegral = this.calcularIntegralNumericaEnMargenError(numeroSegmentos, x, e, dof);
        if ((resultadoIntegral >= (p - e)) && (resultadoIntegral <= (p + e))) {
            return x;
        } else if (resultadoIntegral < p) {
            this.cambioSigno = true;
            return this.buscarLimite(x + this.auxiliarX, numeroSegmentos, p, e, dof);
        }

        return this.buscarLimite(x - this.auxiliarX, numeroSegmentos, p, e, dof);
    }

    /**
     * Método que calcula el valor de x de acuerdo al valor propuesto p.
     *
     * @param x es el límite de la integral.
     * @param numeroSegmentos cantidad de subdiviciones.
     * @param p es el valor propuesto como resultado de la integral
     * @param e es el margen de error.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return l valor del límite de la integral.
     * @throws Exception lanzada en caso de presentarse alguna será relanzada.
     */
    private double buscarLimite(double x, int numeroSegmentos, double p, double e, double dof) throws Exception {

        double resultadoIntegral = 0.0;
        while (resultadoIntegral != p) {
            resultadoIntegral = this.calcularIntegralNumericaEnMargenError(numeroSegmentos, x, e, dof);

            if (this.cambioSigno) {
                this.auxiliarX = this.auxiliarX / 2;
            }

            if (resultadoIntegral > p) {
                if (this.cambioSigno == false) {
                    this.cambioSigno = true;
                }
                x = x - this.auxiliarX;
            } else if (resultadoIntegral < p) {
                if (this.cambioSigno == true) {
                    this.cambioSigno = false;
                }
                x = x + this.auxiliarX;
            }
        }

        return x;
    }

    /**
     * Método para calcula el factorial de un número.
     *
     * @param n indica el númerador del valor a calcularle la función gamma.
     * @param d indica el divisor del valor a calcularle la función gamma. Si el
     * número es un valor entero, colocar 1.
     * @return Gamma del número parametrizado.
     */
    private double calcularGamma(double n, double d) {
        double x = n / d;
        double a = Math.floor(x);
        double b = x - a;

        double resultado = 1.0;
        if (b == 0.0) {
            for (int i = 1; i < x; i++) {
                resultado *= i;
            }
            return resultado;
        } else {
            double aa = 1.0;
            double bb = 1.0;
            for (int i = 1; i < n; i++) {
                if (i % 2 == 1) {
                    aa = aa * i;
                    bb = bb * d;
                }
            }
            resultado = (aa / bb) * Math.sqrt(Math.PI);

            return resultado;
        }
    }

    /**
     * Método que calcula una parte de la función Fx().
     *
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor calculado para el parámetro ingresado.
     */
    private double calculaParteDeFx(double dof) {
        double a = this.calcularGamma((dof + 1), 2.0);
        double b1 = (Math.pow((dof * Math.PI), 0.5));
        double b2 = this.calcularGamma(dof, 2.0);
        double b = b1 * b2;
        return a / b;
    }
}
