package br.com.arquitetura.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.data.StepData;
import br.com.arquitetura.entity.Step;

public class StepConverter {

	public static List<StepData> convertToStepData(List<Step> steps) {
		List<StepData> stepsData = new ArrayList<>();
		steps.forEach(s -> stepsData.add(convertToStepData(s)));
		return stepsData;
	}
	
	private static StepData convertToStepData(Step step) {
		return new StepData(step.getUid(), step.getName(), step.getDescription());
	}

	public static Step convertToStep(StepData stepData) {
		return convertToStep(new Step(), stepData);
	}

	public static Step convertToStep(Step stepDataBase, StepData stepData) {
		stepDataBase.setName(stepData.getName());
		stepDataBase.setDescription(stepData.getDescription());
		return stepDataBase;
	}

	public static Step convertToStep(Long uidStep) {
		return new Step(uidStep);
	}

}
