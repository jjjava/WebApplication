package br.com.schumaker.dao;

import br.com.schumaker.model.Automovel;
import java.util.List;

/**
 *
 * @author hudson.sales
 */
public interface AutomovelDAO {

    void salva(Automovel a);
    List<Automovel> lista();
}
