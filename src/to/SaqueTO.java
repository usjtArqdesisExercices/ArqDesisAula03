package to;

import java.sql.Date;

public class SaqueTO {
	
	public int		idCliente;
	public double	valorSaque;
	public double	saldoAtual;
	public Date		data;
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaqueTO other = (SaqueTO) obj;
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
