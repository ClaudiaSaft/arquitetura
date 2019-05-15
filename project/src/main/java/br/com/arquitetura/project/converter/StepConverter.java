package br.com.arquitetura.project.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.arquitetura.project.data.StepData;
import br.com.arquitetura.project.entity.Step;

public class StepConverter {
	
	private StepConverter() {
		
	}

	public static List<StepData> convertToStepData(List<Step> steps) {
		List<StepData> stepsData = new ArrayList<>();
		steps.forEach(s -> stepsData.add(convertToStepData(s)));
		return stepsData;
	}
	
	public static StepData convertToStepData(Step step) {
		StepData stepData = new StepData(step.getUid(), step.getDescription(), step.getStatus());
		if(step.getSubSteps() != null) {
			step.getSubSteps().stream().forEach(s -> stepData.addSubProjectStep(convertToStepData(s)));
		}
		return stepData;
	}

	public static Step convertToStep(StepData stepData) {
		Step step = new Step(stepData.getDescription());
		if(stepData.getSubSteps() != null) {
			stepData.getSubSteps().stream().forEach(s -> step.addSubProjectStep(convertToStep(s)));
		}
		return step;
	}

	public static Step convertToStep(Step stepDataBase, StepData stepData) {
		stepDataBase.setDescription(stepData.getDescription());
		stepDataBase.setStatus(stepData.getStatus());
		return stepDataBase;
	}

}
