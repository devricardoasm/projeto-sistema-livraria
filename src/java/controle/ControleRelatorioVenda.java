/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.PedidoDAO;
import modelo.dao.RelatorioDeVendasDAO;
import modelo.dao.UsuarioDAO;
import modelos.Pedido;
import modelos.RelatorioDeVendas;
import modelos.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author 11131103404
 */
public class ControleRelatorioVenda extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/pdf");

            String path = getServletContext().getRealPath("/relatorios");

            String relJasper = path + "\\relatorioPedidoFinalizado.jasper";
            

            RelatorioDeVendasDAO relatorioVendas = new RelatorioDeVendasDAO();            
            // na variavel "relatorio" esta recuperando o dados do SELECT do banco
            ArrayList<RelatorioDeVendas> relatorio = relatorioVendas.listarRelatorio();
             
            // na variavel totalDeVendas esta o valor TOTAL DAS VENDAS (SUM)
            //double totalDeVendas = relatorioVendas.calcularTotal();
            
            //na variavel vetor esta o ULTIMO numero da lista
            //int vetor  = relatorio.size();
            
            // é dado um SET no ULTIMO da lista com o total de vendas pois na hora de montar o jasper ele vai pegar o ultimo
            //relatorio.get(vetor - 1).setTotal_vendas(totalDeVendas);  
            
            //é passado o relatorio COMPLETO com todos os dados nescessarios
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(relatorio);
            Map parametros = new HashMap();
            try {

                JasperPrint print = JasperFillManager.fillReport(relJasper, parametros, ds);
                byte relatorioPdf[] = JasperExportManager.exportReportToPdf(print);
                response.getOutputStream().write(relatorioPdf);

            } catch (JRException e) {
                response.getWriter().println(e.getMessage());
            }

        } catch (SQLException ex) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
