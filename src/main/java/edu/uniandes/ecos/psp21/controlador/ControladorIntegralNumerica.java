package edu.uniandes.ecos.psp21.controlador;

import edu.uniandes.ecos.psp21.modelo.CalculadorIntegralNumerica;
import edu.uniandes.ecos.psp21.vista.VistaConsola;
import edu.uniandes.ecos.psp21.vista.VistaWeb;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Clase que controla los llamados para los cálculos de la integral numérica.
 *
 */
public class ControladorIntegralNumerica extends HttpServlet {

    /**
     * Método principal que controla la aplicación
     *
     * @param args son los argumentos del evento.
     */
    public static void main(String[] args) {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        //Server server = new Server(5100);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new ControladorIntegralNumerica()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(ControladorIntegralNumerica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que sobreecribe el método doGet de la página.
     *
     * @param req solicitud de página web.
     * @param resp respuesta de la pagina web.
     * @throws ServletException excepción generada.
     * @throws IOException excepción generada
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        VistaWeb.mostrarFormunlarioInicial(req, resp);
    }

    /**
     * Método que sobreecribe el método doPost de la página.
     *
     * @param req solicitud de página web.
     * @param resp respuesta de la pagina web.
     * @throws ServletException excepción generada.
     * @throws IOException excepción generada
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("btnTest") != null) {
                calculoTest(req, resp);
            } else if (req.getParameter("btnEntrada") != null) {
                VistaWeb.mostrarVentanaEntrada(req, resp);
            } else if (req.getParameter("btnCalcularEntrada") != null) {
                calculoEntrada(req, resp);
            }

            if (req.getParameter("btnInicio") != null) {
                VistaWeb.mostrarFormunlarioInicial(req, resp);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorIntegralNumerica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorIntegralNumerica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorIntegralNumerica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que calcula la integral con datos de pruba.
     *
     * @param req solicitud de página web.
     * @param resp respuesta de la pagina web.
     * @throws Exception excepción generada
     */
    private static void calculoTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
            double respuesta = calculador.calcularLimiteIntegral(10, 0.20, 0.000001, 6.0);
            VistaConsola.mostrarResultado("Datos de prueba:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, 10, 0.20, 0.000001, 6.0, "Integral numérica de datos de prueba");
        } catch (Exception ex) {
            VistaWeb.error(req, resp, ex);
        }
    }

    /**
     * Método que calcula la integral con los datos entrados a través d ela
     * interfaz web.
     *
     * @param req solicitud de página web.
     * @param resp respuesta de la pagina web.
     * @throws ServletException excepción generada.
     * @throws IOException excepción generada
     */
    private static void calculoEntrada(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //double x = Double.parseDouble(req.getParameter("datosx"));
            double e = Double.parseDouble(req.getParameter("error"));
            double p = Double.parseDouble(req.getParameter("btnp"));
            int segmento = Integer.parseInt(req.getParameter("segmento"));
            double dof = Double.parseDouble(req.getParameter("dof"));
            CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
            double respuesta = calculador.calcularLimiteIntegral(segmento, p, e, dof);
            VistaConsola.mostrarResultado("Datos calculados de datos entrados (x):\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, segmento, p, e, dof, "Integral numérica de los datos entrados");
        } catch (Exception ex) {
            VistaWeb.error(req, resp, ex);
        }
    }
}
