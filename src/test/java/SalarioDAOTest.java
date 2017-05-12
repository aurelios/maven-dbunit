import dao.SalarioDAO;
import model.Salario;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

/**
 * Created by tqi_agimenes on 02/05/2017.
 */
public class SalarioDAOTest {

	private static DbUnitHelper dbUnitHelper;
	private static EntityManagerFactory factory;

	private EntityManager manager;
	private SalarioDAO salarioDAO;

	@BeforeClass
	public static void initClass() {
		dbUnitHelper = new DbUnitHelper("DbUnitXml");
		factory = Persistence.createEntityManagerFactory("integracaoMavenDbUnit");
	}

	@Before
	public void init() {
		dbUnitHelper.execute(DatabaseOperation.DELETE_ALL, "salarioDBUnit.xml");

		dbUnitHelper.execute(DatabaseOperation.INSERT, "salarioDBUnit.xml");

		manager = factory.createEntityManager();
		this.salarioDAO = new SalarioDAO(manager);
	}

	@After
	public void end() {
		this.manager.close();
	}


	@Test
	public void testSalario() throws SQLException {
		Salario salario = salarioDAO.buscarSalarioDoEmpregado(1L);
		assertEquals(new BigDecimal(2000), salario.getSalario());
	}

	@Test
	public void deveRetornarSalariosMaior500() {
		List<Salario> resultado = salarioDAO.buscarSalariosMaioresQue(new BigDecimal(1000));
		assertThat(resultado, hasItems(new Salario(1L), new Salario(2L)));
	}
}
