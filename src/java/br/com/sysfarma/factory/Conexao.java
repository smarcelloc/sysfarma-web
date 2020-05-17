/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarma.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public class Conexao {

    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private static final String URL = "jdbc:mysql://localhost/sysfarma";

    public static Connection getConexao() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(URL, USUARIO, SENHA);

    }
}
