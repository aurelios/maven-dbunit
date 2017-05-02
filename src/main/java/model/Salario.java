package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tqi_agimenes on 02/05/2017.
 */
@Entity
public class Salario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;
	private BigDecimal salario;
	private BigDecimal bonus;

	public Salario() {
	}

	public Salario(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Salario salario = (Salario) o;

		return codigo != null ? codigo.equals(salario.codigo) : salario.codigo == null;
	}

	@Override public int hashCode() {
		return codigo != null ? codigo.hashCode() : 0;
	}
}
