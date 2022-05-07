package tables;

import controllers_tables.TabelaFuncionariosEdicaoControl;
import domain.Model.ControleTabelas.TabelaResumoAlteracaoModel;
import infrastructure_dto.VisaoTabelaDto;
import domain.Model.ControleTabelas.AlteracoesAvaliarMenuModel;
import domain.Model.ControleTabelas.FuncionarioTabelaModel;
import domain.Modelo.FuncionarioModel;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

public class TabelaFuncionarioEdicao extends AbstractTableModel {

    private final String[] nomescolunas = {
        "Matrícula",
        "Nome",
        "Setor",
        "Email"

    };

    private final List<FuncionarioModel> mList;

    public TabelaFuncionarioEdicao(List<FuncionarioModel> pList) {
        mList = pList;

    }

    public TabelaFuncionarioEdicao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    @Override
    public int getColumnCount() {
        return nomescolunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {

            case 0:
                return mList.get(linha).getMat();
            case 1:
                return mList.get(linha).getNome();
            case 2:
                return mList.get(linha).getCargo();
            case 3:
                return mList.get(linha).getEmail();
        }
        return 0;
    }

    @Override
    public String getColumnName(int indice) {
        return nomescolunas[indice];
    }

    public Class getColClass(int coluna) {
        switch (coluna) {

            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;

        }
        return null;
    }

}
