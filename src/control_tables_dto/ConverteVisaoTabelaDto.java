package control_tables_dto;

import domain_enums.EnumSituacao;
import infrastructure_dto.VisaoTabelaDto;
import domain.Modelo.SituacaoAFModel;
import domain.Model.ControleTabelas.TabelaVisaoAlteracaoModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConverteVisaoTabelaDto {

    public VisaoTabelaDto getVisaoTabelaDto(SituacaoAFModel visao) {
        VisaoTabelaDto visaoTabelaDto = new VisaoTabelaDto();
        SituacaoAFModel situacaoAFModel = new SituacaoAFModel();

        //Departamento
        if (visao.getSituacaoDepartamento().equals(EnumSituacao.RECEBIDA)) {
            visaoTabelaDto.setAprovacaoDepartamento(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Recebida.png"))));
        }
        if (visao.getSituacaoDepartamento().equals(EnumSituacao.ABONADA)) {
            visaoTabelaDto.setAprovacaoDepartamento(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
        }
        if (visao.getSituacaoDepartamento().equals(EnumSituacao.NAO_RECEBIDA)) {
            visaoTabelaDto.setAprovacaoDepartamento(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoRecebida.png"))));
        }
        if (visao.getSituacaoDepartamento().equals(EnumSituacao.PARCIALMENTE_ABONADA)) {
            visaoTabelaDto.setAprovacaoDepartamento(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Parcial.png"))));
        }
        if (visao.getSituacaoDepartamento().equals(EnumSituacao.NAO_ABONADA)) {   
        }

        //Gerência 
        if (visao.getSituacaoGerencia().equals(EnumSituacao.RECEBIDA)) {
            visaoTabelaDto.setAprovacaoGerencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Recebida.png"))));
        }
        if (visao.getSituacaoGerencia().equals(EnumSituacao.ABONADA)) {
            visaoTabelaDto.setAprovacaoGerencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
        }
        if (visao.getSituacaoGerencia().equals(EnumSituacao.NAO_RECEBIDA)) {
            visaoTabelaDto.setAprovacaoGerencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoRecebida.png"))));
        }
        if (visao.getSituacaoGerencia().equals(EnumSituacao.PARCIALMENTE_ABONADA)) {
            visaoTabelaDto.setAprovacaoGerencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Parcial.png"))));
        }
        
        if (visao.getSituacaoGerencia().equals(EnumSituacao.NAO_ABONADA)) {
            visaoTabelaDto.setAprovacaoGerencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
        }

        //Diretoria  
        if (visao.getSituacaoDiretoria().equals(EnumSituacao.RECEBIDA)) {
            visaoTabelaDto.setAprovacaoDiretoria(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Recebida.png"))));
        }
        if (visao.getSituacaoDiretoria().equals(EnumSituacao.ABONADA)) {
            visaoTabelaDto.setAprovacaoDiretoria(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
        }
        if (visao.getSituacaoDiretoria().equals(EnumSituacao.NAO_RECEBIDA)) {
            visaoTabelaDto.setAprovacaoDiretoria(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoRecebida.png"))));
        }
        if (visao.getSituacaoDiretoria().equals(EnumSituacao.PARCIALMENTE_ABONADA)) {
            visaoTabelaDto.setAprovacaoDiretoria(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Parcial.png"))));
        }
        if (visao.getSituacaoDiretoria().equals(EnumSituacao.NAO_ABONADA)) {
            visaoTabelaDto.setAprovacaoDiretoria(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
        }

        //RH    
        if (visao.getSituacaoRH().equals(EnumSituacao.RECEBIDA)) {
            visaoTabelaDto.setRecebimentoRH(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Recebida.png"))));
        }
        if (visao.getSituacaoRH().equals(EnumSituacao.ABONADA)) {
            visaoTabelaDto.setRecebimentoRH(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
        }
        if (visao.getSituacaoRH().equals(EnumSituacao.NAO_RECEBIDA)) {
            visaoTabelaDto.setRecebimentoRH(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoRecebida.png"))));
        }
        if (visao.getSituacaoRH().equals(EnumSituacao.PARCIALMENTE_ABONADA)) {
            visaoTabelaDto.setRecebimentoRH(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Parcial.png"))));
        }
        if (visao.getSituacaoRH().equals(EnumSituacao.NAO_ABONADA)) {
            visaoTabelaDto.setRecebimentoRH(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
        }

        //Presidência    
        if (visao.getSituacaoPresidencia().equals(EnumSituacao.RECEBIDA)) {
            visaoTabelaDto.setAprovacaoPresidencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Recebida.png"))));
        }
        if (visao.getSituacaoPresidencia().equals(EnumSituacao.ABONADA)) {
            visaoTabelaDto.setAprovacaoPresidencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Ok.png"))));
        }
        if (visao.getSituacaoPresidencia().equals(EnumSituacao.NAO_RECEBIDA)) {
            visaoTabelaDto.setAprovacaoPresidencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoRecebida.png"))));
        }
        if (visao.getSituacaoPresidencia().equals(EnumSituacao.PARCIALMENTE_ABONADA)) {
            visaoTabelaDto.setAprovacaoPresidencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/Parcial.png"))));
        }
        if (visao.getSituacaoPresidencia().equals(EnumSituacao.NAO_ABONADA)) {
            visaoTabelaDto.setAprovacaoPresidencia(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoOk.png"))));
        }
        visaoTabelaDto.setReferencia(visao.getReferencia());
        
         visaoTabelaDto.setAprovacaoGenerica(new JLabel(new ImageIcon(getClass().getResource("/ImagensTabela/NaoRecebida.png"))));

        return visaoTabelaDto;

    }

}
