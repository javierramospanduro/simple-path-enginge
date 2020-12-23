package org.javi.engine.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javi.engine.core.dto.Axis;
import org.javi.engine.core.dto.Signal;
import org.javi.engine.core.types.AxisType;
import org.javi.engine.core.types.Operation;
import org.javi.engine.core.types.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AllTests {
	private static final Log LOG = LogFactory.getLog(AllTests.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOG.info("Before class execution");
	}
	@Before
	public static void setUpBeforeTest() throws Exception {
		LOG.info("Before test execution");
	}
	@Test
	public void test01() {
		LOG.info("Probando bando bando");
		List<Signal> path = paramFactory();
		Map<String,Object> indications = indicationsFactory();
		LOG.info("Path -> " + path);
		LOG.info("Indications -> " + indications);
		path.get(0).explore(indications);
		LOG.info("Explored, result: " + path.get(0).isGoodDirection());
		LOG.info("And the data is: " + path);
		if (path.get(0).isGoodDirection()) {
			LOG.info("Is the good direction, the result is: " + path.get(0).getGoodResponse());
		} else {
			LOG.info("Is the bad direction, the result is: " + path.get(0).getBadResponse());
		}		
	}

	public List<Axis> axisFactory() {
		List<Axis> out = new ArrayList<>();
		out.add(new Axis("idProceso","Identificador de proceso",AxisType.ROOT_AXIS));
		out.add(new Axis("comercio","Identificador de comercio",AxisType.AXIS));
		out.add(new Axis("comercio","Identificador de colaborador",AxisType.AXIS));
		out.add(new Axis("aap","Código de AAP Consumidora",AxisType.AXIS));
		out.add(new Axis("importe","Importe de la operación",AxisType.AXIS));
		out.add(new Axis("capthureMethod","Método de captura",AxisType.AXIS));
		out.add(new Axis("score","Score de la operación",AxisType.FINAL_AXIS));
		return out;
	}
	public List<Signal> paramFactory() {
		List<Signal> out = new ArrayList<>();
		Signal rootSignal = new Signal(new Axis("idProceso", "Identificador de proceso", AxisType.ROOT_AXIS),Operation.EQUALS, Response.OK, Response.KO, true, "00000001");
		{
			List<Signal> nextSignalsLevel1 = new ArrayList<>();			
			{
				Signal signalLevel1 = new Signal(new Axis("aap", "AAP Consumidora", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "00000019");
				List<Signal> nextSignalsLevel2 = new ArrayList<>();
				{
					Signal signalLevel2 = new Signal(new Axis("identificationType", "Tipo de identificación", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "01");
					List<Signal> nextSignalsLevel3 = new ArrayList<>();
					{
						nextSignalsLevel3.add(new Signal(new Axis("score", "Puntuación obtenida", AxisType.FINAL_AXIS),Operation.EQUALS, Response.OK, Response.KO, true, new BigDecimal(60.)));						
					}
					signalLevel2.setNextSignals(nextSignalsLevel3);
					nextSignalsLevel2.add(signalLevel2);					
				}
				{
					Signal signalLevel2 = new Signal(new Axis("identificationType", "Tipo de identificación", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "02");
					List<Signal> nextSignalsLevel3 = new ArrayList<>();
					{
						nextSignalsLevel3.add(new Signal(new Axis("score", "Puntuación obtenida", AxisType.FINAL_AXIS),Operation.EQUALS, Response.OK, Response.KO, true, new BigDecimal(60.)));						
					}
					signalLevel2.setNextSignals(nextSignalsLevel3);
					nextSignalsLevel2.add(signalLevel2);					
				}
				{
					Signal signalLevel2 = new Signal(new Axis("identificationType", "Tipo de identificación", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "03");
					List<Signal> nextSignalsLevel3 = new ArrayList<>();
					{
						nextSignalsLevel3.add(new Signal(new Axis("score", "Puntuación obtenida", AxisType.FINAL_AXIS),Operation.EQUALS, Response.OK, Response.KO, true, new BigDecimal(60.)));						
					}
					signalLevel2.setNextSignals(nextSignalsLevel3);
					nextSignalsLevel2.add(signalLevel2);					
				}
				{
					Signal signalLevel2 = new Signal(new Axis("identificationType", "Tipo de identificación", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "04");
					List<Signal> nextSignalsLevel3 = new ArrayList<>();
					{
						nextSignalsLevel3.add(new Signal(new Axis("score", "Puntuación obtenida", AxisType.FINAL_AXIS),Operation.EQUALS, Response.OK, Response.KO, true, new BigDecimal(60.)));						
					}
					signalLevel2.setNextSignals(nextSignalsLevel3);
					nextSignalsLevel2.add(signalLevel2);					
				}
				
				signalLevel1.setNextSignals(nextSignalsLevel2);		
				nextSignalsLevel1.add(signalLevel1);
			}
			{
				Signal signalLevel1 = new Signal(new Axis("aap", "AAP Consumidora", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "00000019");
				List<Signal> nextSignalsLevel2 = new ArrayList<>();
				{
					Signal signalLevel2 = new Signal(new Axis("verificationType", "Tipo de verificación", AxisType.AXIS), Operation.EQUALS,Response.OK, Response.KO, true, "01");
					List<Signal> nextSignalsLevel3 = new ArrayList<>();
					{
						nextSignalsLevel3.add(new Signal(new Axis("score", "Puntuación obtenida", AxisType.FINAL_AXIS),Operation.GREATER, Response.OK, Response.KO, true,new BigDecimal(59.99)));						
					}
					signalLevel2.setNextSignals(nextSignalsLevel3);
					nextSignalsLevel2.add(signalLevel2);					
				}
				signalLevel1.setNextSignals(nextSignalsLevel2);
				nextSignalsLevel1.add(signalLevel1);
			}
			rootSignal.setNextSignals(nextSignalsLevel1);
		}
		out.add(rootSignal);
		return out;
	}

	public Map<String, Object> indicationsFactory() {
		Map<String, Object> indications = new HashMap<>();
		indications.put("idProceso", "00000001");
		indications.put("aap","00000019");
		indications.put("verificationType","01");
		indications.put("score",new BigDecimal(89.));
		return indications;
	}

}
