package br.com.schumaker.dao;

import br.com.schumaker.connection.HsConnection;
import br.com.schumaker.model.Automovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hudson.sales
 */
public class AutomovelDaoImpl implements AutomovelDAO {

    @Override
    public void salva(Automovel a) {
        String sql = "insert into automoveis "
                + "(anoFabricacao, anoModelo, marca, modelo, observacoes)"
                + " values (?, ?, ?, ?, ?)";
        Connection cn = HsConnection.getConnection();
        try {
            PreparedStatement pst = null;
            pst = cn.prepareStatement(sql);
            pst.setInt(1, a.getAnoFabricacao());
            pst.setInt(2, a.getAnoModelo());
            pst.setString(3, a.getMarca());
            pst.setString(4, a.getModelo());
            pst.setString(5, a.getObservacoes());
            pst.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public List<Automovel> lista() {
        List<Automovel> automoveis = new ArrayList<>();
        String sql = "select * from Automovel";
        Connection cn = HsConnection.getConnection();
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Automovel automovel = new Automovel();
                automovel.setId(rs.getLong("id"));
                automovel.setAnoFabricacao(rs.getInt("anoFabricacao"));
                automovel.setAnoModelo(rs.getInt("anoModelo"));
                automovel.setMarca(rs.getString("marca"));
                automovel.setModelo(rs.getString("modelo"));
                automovel.setObservacoes(rs.getString("observacoes"));
                automoveis.add(automovel);
            }
        } catch (SQLException e) {
            System.err.println(e);//throw new RuntimeException(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return automoveis;
    }
}