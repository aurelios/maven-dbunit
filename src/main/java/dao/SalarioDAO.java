package dao;

import model.Salario;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tqi_agimenes on 02/05/2017.
 */
public class SalarioDAO {

	private EntityManager manager;

	public SalarioDAO(EntityManager manager) {
		this.manager = manager;
	}

	public List<Salario> buscarSalarios() {
		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);

		return manager.createQuery("from Salario c ")
				.getResultList();
	}

	public Salario buscarSalarioDoEmpregado(Long codigo) {
		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);

		return (Salario) manager.createQuery("from Salario c where c.codigo = :codigo ")
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Salario> buscarSalariosMaioresQue(BigDecimal salario) {
		return manager.createQuery("from Salario c where c.salario >= :salario")
				.setParameter("salario", salario)
				.getResultList();
	}
}
