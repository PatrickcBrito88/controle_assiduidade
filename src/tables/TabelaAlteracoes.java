package tables;

import infrastructure_dto.VisaoTabelaDto;
import domain.Modelo.PerfilModel;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

public class TabelaAlteracoes extends AbstractTableModel {

    private final String[] nomescolunas = {
        "Referência",
        "Gerência",
        "Departamento",
        "Gabinete",
        "Diretoria",
        "Presidência",
        "RH"
    };

    private final List<VisaoTabelaDto> mList;
    private final PerfilModel perfil;

    public TabelaAlteracoes(List<VisaoTabelaDto> pList, PerfilModel p) {
        mList = pList;
        perfil=p;
    }

    public TabelaAlteracoes() {
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
                return mList.get(linha).getReferencia();
            case 1:
                return mList.get(linha).getAprovacaoGerencia();
            case 2:
                if ((perfil.isFuncionarioGercom())||(perfil.isGerenteGercom())){
                    return mList.get(linha).getAprovacaoGenerica();
                } else {
                return mList.get(linha).getAprovacaoDepartamento();
                }
            case 3:
                if ((perfil.isFuncionarioGercom())||(perfil.isGerenteGercom())){
                     return mList.get(linha).getAprovacaoDepartamento();
                } else {
                    return mList.get(linha).getAprovacaoGenerica();
                }   
            case 4:
                return mList.get(linha).getAprovacaoDiretoria();
            case 5:
                return mList.get(linha).getAprovacaoPresidencia();
            case 6:
                return mList.get(linha).getRecebimentoRH();
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
                return Date.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return boolean.class;
            case 5:
                return JLabel.class;
        }
        return null;
    }

//    public VisaoTabelaAlteracaoModel getObjeto (int linha){
//        VisaoTabelaAlteracaoModel visaoLinha = new VisaoTabelaAlteracaoModel();
//        visaoLinha.setDataEvento(mList.get(linha).getDataEvento());
//        visaoLinha.setAprovacaoGerencia(mList.get(linha).());
//        visaoLinha.setAprovacaoDepartamento(mList.get(linha).isAprovacaoDepartamento());
//        visaoLinha.setAprovacaoDiretoria(mList.get(linha).isAprovacaoDiretoria());
//        visaoLinha.setRecebimentoRH(mList.get(linha).isRecebimentoRH());
//        visaoLinha.setAnexo(mList.get(linha).isAnexo());
//        
//        return visaoLinha;
//    }
    //FAZER METODOS PARA PERSONALIZAAR A TABLE
}
