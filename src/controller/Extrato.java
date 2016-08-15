package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import dao.ExtratoDAO;
import to.ExtratoTO;

public class Extrato {

	public int idCliente;
	private Date dataInicial;
	private Date dataFinal;

	public Extrato(int idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public <E> void consultaExtrato() {
		
		ExtratoDAO extDAO = new ExtratoDAO();
		ExtratoTO extTO = new ExtratoTO();

		extTO.setIdCliente(idCliente);
		extTO.setDataInicial((java.sql.Date) dataInicial);
		extTO.setDataFinal((java.sql.Date) dataFinal);

		ArrayList<ExtratoTO> extrato = new ArrayList<>();
		extrato = extDAO.consultaExtrato(extTO); 
		
		for(int i=0; i < extrato.size(); i++){   
            System.out.println("ID do Cliente: " + extrato.get(i).getIdCliente() + "\nValor Da Movimentacao: " + extrato.get(i).getValorMovimentacao() + "\nValor Saldo Atual: " +
            		extrato.get(i).getSaldoAtual() + "\nTipo Movimentação: " + extrato.get(i).getTipoMovimentacao() + "\nData Movimentacao: " + extrato.get(i).getData() +
            			"\n-------------------------------------------------");   
        }   
    }

	
	public void createDate(){
		
		Date minhaData = new Date();  
		long mili =  minhaData.getTime();  
		java.sql.Date dataSQL = new java.sql.Date(mili); 
		
		setDataFinal(dataSQL);
		setDataInicial(dataSQL);
	}
}
