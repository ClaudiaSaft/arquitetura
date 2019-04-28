package br.com.arquitetura.project.converter.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.arquitetura.project.converter.StepConverter;
import br.com.arquitetura.project.data.StepData;
import br.com.arquitetura.project.entity.Step;
import br.com.arquitetura.project.enumeration.StepStatusEnum;

public class StepConverterTest {

	@Test
	public void test() {
		StepData stepData1 = new StepData(1L, "Step Data 1 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData2 = new StepData(2L, "Step Data 2 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData3 = new StepData(3L, "Step Data 3 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData4 = new StepData(4L, "Step Data 4 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData6 = new StepData(6L, "Step Data 6 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData7 = new StepData(7L, "Step Data 7 description", StepStatusEnum.AGUARDANDO_INICIO);
		
		stepData1.addSubProjectStep(stepData2);
			stepData2.addSubProjectStep(stepData3);
				stepData3.addSubProjectStep(stepData6);
					stepData6.addSubProjectStep(stepData7);
			stepData2.addSubProjectStep(stepData4);
		
		Step step = StepConverter.convertToStep(stepData1);
		
		assertEquals(1, step.getSubSteps().size());
		assertEquals(2, getStep2(step).getSubSteps().size());
		assertEquals("Step Data 2 description", getStep2(step).getDescription());
		assertEquals("Step Data 3 description", getStep3(step).getDescription());
		assertEquals("Step Data 6 description", getStep6(step).getDescription());
		assertEquals("Step Data 3 description", getStep6(step).getStepOwner().getDescription());
		assertEquals("Step Data 7 description", getStep7(step).getDescription());
		assertTrue(getStep7(step).getSubSteps().isEmpty());
		
	}

	private Step getStep7(Step step) {
		return getStep6(step).getSubSteps().get(0);
	}

	private Step getStep6(Step step) {
		return getStep3(step).getSubSteps().get(0);
	}

	private Step getStep3(Step step) {
		return getStep2(step).getSubSteps().get(0);
	}

	private Step getStep2(Step step) {
		return step.getSubSteps().get(0);
	}

}
