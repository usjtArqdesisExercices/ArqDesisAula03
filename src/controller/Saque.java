package controller;

import java.sql.Date;

import dao.SaqueDAO;
import to.SaqueTO;

public class Saque {

	public int		idCliente;
	public double	valorSaque;
	public double	saldoAtual;
	public Date		data;
	
	public Saque(int idCliente, double valorSaque, double saldoAtual) {
		super();
		this.idCliente = idCliente;
		this.valorSaque = valorSaque;
		this.saldoAtual = saldoAtual;
	}

	public double getValorSaque() {
		return valorSaque;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getSaldoAtual() {
		return saldoAtual;
	}

	public Date getData() {
		return data;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setSaldoAtual(double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public void setData(Date data) {
		
		this.data = data;
	}
	
	public void setValorSaque(double valorSaque) {
		this.valorSaque = valorSaque;
	}
	
	public void efetuarSaque(){
		
		SaqueDAO saqueDAO = new SaqueDAO();
		SaqueTO saqueTO = new SaqueTO();
				
		saqueTO.setIdCliente(idCliente);
		saqueTO.setSaldoAtual(saldoAtual);
		saqueTO.setValorSaque(valorSaque);
		saqueTO.setData((java.sql.Date) data);
		saqueDAO.efetuarSaque(saqueTO);
		
		salvaMovBanc();
	}
	
	public void salvaMovBanc(){
		
		MovimentacaoBancaria movBancaria = new MovimentacaoBancaria(idCliente, valorSaque, saldoAtual, 2, data);
		
		movBancaria.salvaDebito();		
	}
	
	
	public void createDate(){
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		setData(sqlDate);	
	}
	
	@Override	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Saque other = (Saque) obj;
		if (idCliente != other.idCliente)
			return false;
		if (saldoAtual != other.saldoAtual)
			return false;
		if (valorSaque != other.valorSaque)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}	
	

}
